package lib.common.util;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.blankj.utilcode.util.AppUtils;

public class UtilApp {
    private  static UtilApp instance;
    public static UtilApp i(){
        if (null==instance){
            instance=new UtilApp();
        }
        return  instance;
    }
    public  void tel(String tel){
//        tel="13589249636";
        try {
            if (TextUtils.isEmpty(tel)){
                UtilToast.i().showWarn("电话号码不正确");
                return;
            }
            if (tel.length()<7){
                UtilToast.i().showWarn("电话号码不正确");
                return;
            }
            if (tel.contains("-")){
                tel=tel.replace("-","");
            }
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri data = Uri.parse("tel:" + tel);
            intent.setData(data);
            UtilActivity.i().getActivity().startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getAppVersionName(){
        return  AppUtils.getAppVersionName();
    }
    public int getAppVersionCode(){
        return  AppUtils.getAppVersionCode();
    }
}
