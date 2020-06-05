package lib.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.zhl.cbdialog.CBDialogBuilder;

import lib.common.util.UtilActivity;

public class DialogCancelConfirm {
    Dialog dialog;
    CBDialogBuilder builder;
    public  void show(String msg,final ConfirmButton confirm,final CancelButton cancel){
        if (null!=dialog){
            dialog.dismiss();
        }
        builder=new CBDialogBuilder(UtilActivity.i().getActivity())
                .setTouchOutSideCancelable(false)
                .showIcon(true)
                .showCancelButton(true)
                .setTitle("提示")
                .setMessage(msg)
                .setConfirmButtonText("确定")
                .setCancelButtonText("取消")
                .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                .setButtonClickListener(true, new CBDialogBuilder.onDialogbtnClickListener() {
                    @Override
                    public void onDialogbtnClick(Context context, Dialog dialog, int whichBtn) {
                        switch (whichBtn) {
                            case BUTTON_CONFIRM:
                                if (null!=confirm){
                                    confirm.onClick(context,dialog);
                                }else{
                                    dialog.dismiss();
                                }
//                                Toast.makeText(context, "点击了确认按钮", Toast.LENGTH_SHORT).show();
                                break;
                            case BUTTON_CANCEL:
                                if (null!=cancel){
                                    cancel.onClick(context,dialog);
                                }else{
                                    dialog.dismiss();
                                }
//                                Toast.makeText(context, "点击了取消按钮", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                    }
                });
        builder.getView(com.zhl.cbdialog.R.id.dialog_title).setVisibility(View.GONE);
        dialog=  builder.create();
        dialog.show();
    }
    public  void dismiss(){
        if (dialog!=null){
            dialog.dismiss();
        }
    }
    public  interface  ConfirmButton{
        void onClick(Context context, Dialog dialog);
    }
    public  interface  CancelButton{
        void onClick(Context context, Dialog dialog);
    }
}
