package lib.common.http;

import android.app.Dialog;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.model.Progress;
import com.zhl.cbdialog.CBDialogBuilder;

import lib.common.util.UtilActivity;

/**
 * http返回接口
 * Created by MIAOYALIN on 2018/3/1.
 */
public abstract class HttpCall {
    public  boolean isToast=true;
    public  boolean isLoadDialog=false;
    public Dialog dialog;
    public View view;

    public  void onJsonSuccess(int code, String msg, JSONObject jsonData){}
    /**
     * 请求成功
     *
     * @param json
     * @param tag
     */
    public  void onSuccess(String json, String tag){};

    /**
     * 请求失败
     *
     * @param json
     * @param msg
     * @param tag
     */
    public  void onFailure(String json, String msg, String tag){};

    /**
     * 进度条
     *
     * @param progress
     */
    public void  onProgress(long progress) {

    }
    //在onStartBefore之前 主要设置数据用
    public void onBefore(){

    }
    //在onStart之前，根据设置的数据做相应的操作
    public void onStartBefore() {
        if (view!=null){
            view.setClickable(false);
        }
    }
    public void onStart() {
        try {
            if (isLoadDialog){
                if (null==dialog||!dialog.isShowing()){
                    dialogShow();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCacheSuccess() {
    }

    public void onFailure(String msg) {
    }

    public void onFinish() {
        try {
            if (isLoadDialog){
                if (null!=dialog||dialog.isShowing()){
                    dialog.dismiss();
                }
            }
            if (view!=null){
                view.setClickable(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void uploadProgress(Progress progress) {
    }

    public void downloadProgress(Progress progress) {
    }

    public  void dialogShow(){
        dialog=   new CBDialogBuilder(UtilActivity.i().getActivity(), CBDialogBuilder.DIALOG_STYLE_PROGRESS, 0.5f)
                .showCancelButton(true)
                .setMessage("正在加载请稍后...")
                .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                .setOnProgressOutTimeListener(1, new CBDialogBuilder.onProgressOutTimeListener() {
                    @Override
                    public void onProgressOutTime(Dialog dialog, TextView dialogMsgTextView) {
//                                dialogMsgTextView.setText("出错啦");
                    }
                })
                .setProgressTimeOutLimit(false)
//                        .setProgressStyleColorRes(new int[]{0xFF37474F,0xFF263238,0xFF21272B,0xFF80CBC4,0xFF009688,0xFFDE6262,0xFF0F519F})
                .create();
        dialog.show();
    }

}
