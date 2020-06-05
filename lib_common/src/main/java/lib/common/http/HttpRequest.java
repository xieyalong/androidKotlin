package lib.common.http;

import android.content.Context;

import com.blankj.utilcode.util.NetworkUtils;

import java.util.Map;

import lib.common.CAPP;


public class HttpRequest {
    protected Context mContext;
    private static HttpRequest instance;

    private HttpRequest() {}
    @Deprecated
    public  static  HttpRequest getInstance(){
        if (null==instance){
            instance=new HttpRequest();
        }
        instance.mContext = CAPP.getInstance().getActivity();
        return  instance;
    }
    public  static  HttpRequest i(){
        if (null==instance){
            instance=new HttpRequest();
        }
        instance.mContext = CAPP.getInstance().getActivity();
        return  instance;
    }

    /**
     * get请求
     *
     * @param url
     * @param params
     * @param httpCall
     */
    public void get(String url, Map<String,Object> params, HttpCall httpCall) {
        if (NetworkUtils.isConnected()) {
            if (httpCall!=null){
                httpCall.onBefore();
                httpCall.onStartBefore();
            }
            OkGoRequest.getInstance(mContext).get(url, params,httpCall);
        } else {
            if (httpCall!=null){
                httpCall.onFailure(null, "请链接网络", url);
                httpCall.onFinish();
            }
        }
    }
//    public void get(String url,HttpCall httpCall) {
//        if (NetworkUtils.isConnected()) {
//            OkGoRequest.getInstance(mContext).get(url, null,httpCall);
//        } else {
//            httpCall.onFailure(null,"请链接网络", url);
//        }
//    }
    /**
     * post请求
     *
     * @param url
     * @param params
     * @param httpCall
     */
    public void post(String url, Map<String,Object>  params, HttpCall httpCall) {
        try {
            if (NetworkUtils.isConnected()) {
                if (httpCall!=null){
                    httpCall.onBefore();
                    httpCall.onStartBefore();
                }
    //            LogUtils.e(">]"+url.substring(url.indexOf("/"), url.length()) + "-----请求------:" + (params != null ? params.toString() : ""));
                OkGoRequest.getInstance(mContext).post(url, params, url, httpCall);
            } else {
                if (httpCall!=null){
                    httpCall.onFailure(null, "请链接网络", url);
                    httpCall.onFinish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    public void get(String url, Map<String,Object>  params, HttpCall httpCall) {
//        if (NetworkUtils.isConnected()) {
////            LogUtils.e(">]"+url.substring(url.indexOf("/"), url.length()) + "-----请求------:" + (params != null ? params.toString() : ""));
//            OkGoRequest.getInstance(mContext).get(url, params, url, httpCall);
//        } else {
//            httpCall.onFailure(null,"请链接网络", url);
//        }
//    }
//    public void post(String url, HttpCall httpCall) {
//        if (NetworkUtils.isConnected()) {
////            LogUtils.e(">]"+url.substring(url.indexOf("/"), url.length()) + "-----请求------:" + (params != null ? params.toString() : ""));
//            OkGoRequest.getInstance(mContext).post(url, null, url, httpCall);
//        } else {
//            httpCall.onFailure(null, "请链接网络", url);
//        }
//    }

    /**
     * 下载文件
     *
     * @param url
     * @param destFileDir
     * @param fileName
     * @param httpCall
     */
    public void download(String url, String destFileDir, String fileName, HttpCall httpCall) {
        if (NetworkUtils.isConnected()) {
//            LogUtils.i(">]"+url.substring(url.indexOf("/"), url.length()) + "-----请求------:" + (destFileDir + fileName));
            OkGoRequest.getInstance(mContext).downloadFile(url, destFileDir, fileName, url, httpCall);
        } else {
            httpCall.onFailure(null,"请链接网络", url);
        }
    }
}
