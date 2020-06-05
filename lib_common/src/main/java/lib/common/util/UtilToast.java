package lib.common.util;

import android.view.Gravity;
import android.widget.Toast;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * 吐司的工具类
 */

public class UtilToast {
//    View view=null;
//    TextView txt;
//    ImageView img;
//    Toast toast=null;
//    Handler handler;
//    Runnable runnable;
//    boolean isShow=false;
    private UtilToast(){}
    private  static UtilToast instance;
    public  static UtilToast i(){
        if (null==instance){
            instance=new UtilToast();
        }
        return instance;
    }
    @Deprecated
    public  static UtilToast getInstance(){
        if (null==instance){
            instance=new UtilToast();
        }
        return instance;
    }
    public  void showSucceed(String text){
        Toast toast= FancyToast.makeText(ActivityUtils.getTopActivity(),text,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    public  void showWarn(String text){
//        ToastUtils.setGravity(Gravity.CENTER,0,0);
      Toast toast= FancyToast.makeText(ActivityUtils.getTopActivity(),text,FancyToast.LENGTH_LONG,FancyToast.WARNING,true);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
    public  void showShort(String text){
        ToastUtils.showShort(text);
    }
//    public  void showToast( String text){
//        init();
//        if (isShow){
//            return;
//        }
//        txt.setText(text);
//        img.setImageResource(R.mipmap.toast1);
//        toast.show();
//        isShow=true;
//        toastCancel();
//    }
//    public  void showErrorToast( String text){
//        init();
//        if (isShow){
//            return;
//        }
//        txt.setText(text);
//        img.setImageResource(R.mipmap.toast2);
//        toast.show();
//        isShow=true;
//        toastCancel();
//    }
//    //    public  void showToast( String text,int type){
////        if (toast == null) {
////            toast = Toast.makeText(CommonAPP.getInstanc().getApplicationContext(), text, Toast.LENGTH_SHORT);
////            View view=View.inflate(CommonAPP.getInstanc().getApplicationContext(),R.layout.toast,null);
////            toast.setView(view);
////        } else {
////            toast.setText(text);
////            toast.setDuration(Toast.LENGTH_SHORT);
////        }
////        toast.show();
////    }
//    public  void showToastBean(SendHeadBean head){
//        if (0==head.getCode()){
//            showToast(head.getText());
//        }else{
//            showErrorToast(head.getText());
//        }
//    }
//    public  void init(){
//        if (toast == null) {
//            toast = Toast.makeText(CAPP.getInstanc().getApplicationContext(), "", Toast.LENGTH_SHORT);
//            toast.setGravity(Gravity.CENTER|Gravity.FILL_HORIZONTAL|Gravity.FILL_VERTICAL,0,0);
//            toast.setDuration(Toast.LENGTH_SHORT);
//            view=View.inflate(CAPP.getInstanc().getApplicationContext(),R.layout.toast,null);
//            toast.setView(view);
//            txt=view.findViewById(R.id.txt);
//            img=view.findViewById(R.id.img);
//        }
//    }
//
//    private  void toastCancel(){
//        if (null==handler){
//            handler=new Handler(Looper.getMainLooper());
//            runnable=new Runnable() {
//                @Override
//                public void run() {
//                    toast.cancel();
//                    isShow=false;
//                }
//            };
//        }
//        handler.postDelayed(runnable,1000);
//    }
}
