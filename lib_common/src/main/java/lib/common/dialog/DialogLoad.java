package lib.common.dialog;

import android.app.Dialog;
import android.widget.TextView;

import com.zhl.cbdialog.CBDialogBuilder;

import lib.common.util.UtilActivity;

public class DialogLoad {
    Dialog dialog;
    public  void show(final ShowAfter showBefore){
        if (null==dialog){
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
        }
        if (null!=dialog&&!dialog.isShowing()){
            dialog.show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (null!=showBefore){
                        showBefore.click();
                    }
                }
            }).start();

        }
    }
    public  void show(){
        if (null==dialog){
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
        }
        if (null!=dialog&&!dialog.isShowing()){
            dialog.show();
        }
    }
    public  void dismiss(){
        dialog.dismiss();
    }
   //先show 在处理请求参数和请求
    public interface  ShowAfter{
        void click();
    }
}
