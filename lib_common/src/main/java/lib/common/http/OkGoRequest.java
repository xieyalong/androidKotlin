package lib.common.http;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.LogUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lib.common.CStaticKey;
import lib.common.bean.MsgBean;
import lib.common.util.UtilLog;
import lib.common.util.UtilMsg;
import lib.common.util.UtilSP;
import lib.common.util.UtilToast;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class OkGoRequest {
    private static OkGoRequest mInstance;
    private static Context mContext;

    public static OkGoRequest getInstance(Context context) {
        mContext = context;
        if (mInstance == null) {
            synchronized (OkGoRequest.class) {
                if (mInstance == null) {
                    mInstance = new OkGoRequest();
                }
            }
        }
        return mInstance;
    }

    private GetCallBack getCallBack;
    private PostCallBack postCallBack;

    public OkGoRequest setGetCallBack(GetCallBack getBack) {
        getCallBack = getBack;
        return this;
    }

    public OkGoRequest setPostCallBack(PostCallBack postCallBack) {
        this.postCallBack = postCallBack;
        return this;
    }

    //    public void get(String url, HttpParams params) {
//        get(url, params, url, null);
//    }
    public  String urlParams(Map<String,Object> map){
        StringBuilder sb=new StringBuilder();
        for(String key:map.keySet()){
            sb.append(key+"="+map.get(key)+"&");
        }
        System.out.println(sb.substring(0, sb.length()-1));
        return sb.toString();
    }
    public void get(final String url,  Map<String,Object> params, final HttpCall httpCall) {
        try {
            final Map<String,Object> bodyMap=new HashMap<>();
            if (null!=params&&params.size()>0){
                bodyMap.putAll(params);
            }
            bodyMap.put("token", UtilSP.getInstance().getString(CStaticKey.sp_token));
//        bodyMap.put("imei", DeviceUtils.getManufacturer()+"-"+DeviceUtils.getAndroidID());
            final String httpUrl=HttpRequestApi.url(url)+"?"+urlParams(bodyMap);
            LogUtils.i(">]get url="+httpUrl);
            if (null!=httpCall){
                httpCall.onBefore();
            }
            OkGo.<String>get(httpUrl)
    //                .params(params)
                    . headers("Content-Type", "application/json; charset=utf-8")
                    .execute(new StringCallback() {
                        @Override
                        public void onStart(Request<String, ? extends Request> request) {
                            super.onStart(request);
                            if (null==httpCall){
                                return;
                            }
                            httpCall.onStart();
                        }

                        @Override
                        public void onSuccess(Response<String> response) {
                            try {
                                String strData=response.body();
                                LogUtils.i(">]response="+ strData);
    //                        LogUtils.i(">]url="+url+"----response="+ strData);
                                if (null==httpCall){
                                    return;
                                }
                                JSONObject responseJson=JSON.parseObject(strData);
                                int code=responseJson.getIntValue("code");
    //                            int expire=responseJson.getIntValue("expire");
                                String msg=responseJson.getString("msg");
                                if (httpCall.isToast&&code!=0&&code!=401){
                                    UtilToast.i().showWarn(msg);
                                }
                                if (401==code){//token过期
    //                                UtilActivity.getInstance().startLoginActivity();
                                    UtilSP.getInstance().clear();
                                    UtilMsg.getInstance().post(new MsgBean(CStaticKey.msg_login));
                                }
                                String data=responseJson.getString("data");
                                if (!responseJson.containsKey("data")||TextUtils.isEmpty(data)|| "[]".equals(data)||"{}".equals(data)){
                                    httpCall.onJsonSuccess(code,msg,null);
                                }else{
                                    JSONObject dataJson=responseJson.getJSONObject("data");
                                    httpCall.onJsonSuccess(code,msg,dataJson);
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                                UtilToast.i().showWarn("服务器繁忙,请稍后再试。。。");
                            }
                        }

                        @Override
                        public void onError(Response<String> response) {
                            UtilToast.i().showWarn("服务器繁忙...");
                            UtilToast.i().showShort(response.message());
                            LogUtils.i(">]url="+url+"----请求失败="+response.message());
                            super.onError(response);
                            if (httpCall != null) {
                                httpCall.onFailure(response.message());
                            }

                        }

                        @Override
                        public void onFinish() {
                            super.onFinish();
                            if (httpCall != null) {
                                httpCall.onFinish();
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void post(String url, HttpParams params) {
//        post(url, params, url, null);
//    }


    public void post(final String url, final Map<String,Object> params, final String tag, final HttpCall httpCall) {
        try {
            final Map<String,Object> bodyMap=new HashMap<>();
            if (null!=params&&params.size()>0){
                bodyMap.putAll(params);
            }
            bodyMap.put("token", UtilSP.i().getString(CStaticKey.sp_token));
//        bodyMap.put("imei", DeviceUtils.getManufacturer()+"-"+DeviceUtils.getAndroidID());
//        bodyMap.put("equipmentType","android");
            String jsonData=JSON.toJSONString(bodyMap);
            MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
//        MediaType JSON = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
            RequestBody body =RequestBody.create(mediaType, jsonData);
            String httpUrl=HttpRequestApi.url(url);
            LogUtils.i(">]url="+httpUrl+"----params="+jsonData);
            OkGo.<String>post(httpUrl).upRequestBody(body)//application/json
    //                .params(params)//表单 HttpParams
                    .execute(new StringCallback() {
                        @Override
                        public void onStart(Request<String, ? extends Request> request) {
                            super.onStart(request);
                            httpCall.onStart();
                        }

                        @Override
                        public void onSuccess(Response<String> response) {
                            try {
                                String strData=response.body();
                                LogUtils.i(">]url="+url+"----response="+ strData);
    //                        LogUtils.i(">]url="+url+"----response="+ strData);
                                if (null==httpCall){
                                    return;
                                }
                                JSONObject responseJson=JSON.parseObject(strData);
                                int code=responseJson.getIntValue("code");
    //                            int expire=responseJson.getIntValue("expire");
                                String msg=responseJson.getString("msg");
                                if (httpCall.isToast&&code!=0&&code!=401){
                                    UtilToast.i().showWarn(msg);
                                }
                                if (401==code){//token过期
    //                                UtilActivity.getInstance().startLoginActivity();
                                    UtilSP.i().clear();
                                    UtilMsg.i().post(new MsgBean(CStaticKey.msg_login));
                                    return;
                                }

                                String data=responseJson.getString("data");
                                if ("[]".equals(data)||"{}".equals(data)||!responseJson.containsKey("data")||TextUtils.isEmpty(data)){
                                    httpCall.onJsonSuccess(code,msg,null);
                                }else{
                                    JSONObject dataJson=responseJson.getJSONObject("data");
                                    httpCall.onJsonSuccess(code,msg,dataJson);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                UtilToast.i().showWarn("服务器繁忙,请稍后再试。。。");
                            }
    //                        httpCall.onSuccess(strData);

    //                        httpCall.onSuccessJson();
    //                        LogUtils.e("----------成功也不来");
    //                        if (httpCall != null) {
    //                            LogUtils.e(tag.substring(tag.indexOf("/"), tag.length()) + "-----结果------:" + response.body());
    //                            httpCall.onSuccess(response.body(), tag);
    //                        }
    //                        if (postCallBack != null) {
    //                            postCallBack.onSuccess(response.body());
    //                            postCallBack.getMsg(response.message());
    //                        }
                        }

                        @Override
                        public void onError(Response<String> response) {
                            UtilToast.i().showWarn("服务器繁忙...");
                            UtilLog.getInstance().i(">]请求失败="+response.message());
                            super.onError(response);
                            if (httpCall != null) {
                                httpCall.onFailure(response.message());
                            }

    //                        if (httpCall != null) {
    //                            httpCall.onFailure(response.body(), response.message(), tag);
    //                        }
    //                        Log.e("cjx", "0000:" + response.toString());
    //                        Log.e("cjx", "1111:" + response.getException().getMessage());
    //                        Log.e("cjx", "2222:" + response.code());
    //                        Log.e("cjx", "3333:" + response.isSuccessful());
    //                        if (postCallBack != null) {
    //                            postCallBack.onError(response.message());
    //                        }
                        }

                        @Override
                        public void onFinish() {
                            super.onFinish();
                            if (httpCall != null) {
                                httpCall.onFinish();
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//

    /**
     * 文件下载
     *
     * @param url
     * @param destFileDir
     * @param fileName
     * @param tag
     * @param httpCall
     */
    public void downloadFile(String url, String destFileDir, String fileName, final String tag, final HttpCall httpCall) {
        OkGo.<File>get(url).execute(new FileCallback(destFileDir, fileName) {
            @Override
            public void onSuccess(Response<File> response) {
                if (httpCall != null) {
                    httpCall.onSuccess(response.body().getAbsolutePath(), tag);
                }
            }

            @Override
            public void onError(Response<File> response) {
                super.onError(response);
                if (httpCall != null) {
                    httpCall.onFailure(response.message(), response.message(), tag);
                }
            }

            @Override
            public void downloadProgress(Progress progress) {
                super.downloadProgress(progress);
                float dLProgress = progress.fraction;
                if (httpCall != null) {
                    httpCall.onProgress((long) dLProgress);
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                LogUtils.e("---------:下载完成");
            }
        });
//        new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
////                        LogUtils.e("----------成功也不来");
//                        if (httpCall != null) {
//                            LogUtils.e(tag.substring(tag.indexOf("/"), tag.length()) + "-----结果------:" + response.body());
//                            httpCall.onSuccess(response.body(), tag);
//                        }
//                        if (postCallBack != null) {
//                            postCallBack.onSuccess(response.body());
//                            postCallBack.getMsg(response.message());
//                        }
//                    }
//
//                    @Override
//                    public void onError(Response<String> response) {
////                        LogUtils.e("----------失败也不来");
//                        super.onError(response);
//                        if (httpCall != null) {
//                            httpCall.onFailure(response.body(), response.message(), tag);
//                        }
//                        Log.e("cjx", "0000:" + response.toString());
//                        Log.e("cjx", "1111:" + response.getException().getMessage());
//                        Log.e("cjx", "2222:" + response.code());
//                        Log.e("cjx", "3333:" + response.isSuccessful());
//                        if (postCallBack != null) {
//                            postCallBack.onError(response.message());
//                        }
//                    }
//
//                });
    }

    public void postFile(String url, HttpParams params, List<File> files) {
        OkGo.<String>post(url)
                .params(params)
                .addFileParams("file", files)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (postCallBack != null) {
                            postCallBack.onSuccess(response.body());
                            postCallBack.getMsg(response.message());
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.e("cjx", "0000:" + response.toString());
                        Log.e("cjx", "1111:" + response.getException().getMessage());
                        Log.e("cjx", "2222:" + response.code());
                        Log.e("cjx", "3333:" + response.isSuccessful());
                        postCallBack.onError(response.message());
                    }
                });
    }



    public interface GetCallBack {
        void onSuccess(String response);

        void getMsg(String msg);

        void onError(String errorMsg);
    }

    public interface PostCallBack {
        void onSuccess(String response);

        void getMsg(String msg);

        void onError(String errorMsg);
    }
}
