package lib.common.util;

import android.text.TextUtils;

public class UtilStr {
    private  static  UtilStr instance;

    public static UtilStr i() {
        if (null==instance){
            instance=new UtilStr();
        }
        return instance;
    }
    public String isStrExist(String str){
        if (TextUtils.isEmpty(str)){
            return "暂无";
        }
        return  str;
    }
}
