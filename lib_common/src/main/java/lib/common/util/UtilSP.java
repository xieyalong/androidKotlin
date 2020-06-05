package lib.common.util;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.SPUtils;

public class UtilSP {


    private  static  UtilSP instance;
    @Deprecated
    public static UtilSP getInstance(){
        if (null==instance){
            instance=new UtilSP();
        }
        return  instance;
    }
    public static UtilSP i(){
        if (null==instance){
            instance=new UtilSP();
        }
        return  instance;
    }
    public void put(@NonNull final String key, final String value) {
        SPUtils.getInstance().put(key,value);
    }
    public void remove(@NonNull final String key) {
        SPUtils.getInstance().remove(key);
    }
    public String getString(@NonNull final String key) {
        String str= null;
        try {
            str = SPUtils.getInstance().getString(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
   public  void clear(){
        SPUtils.getInstance().clear();
   }

}
