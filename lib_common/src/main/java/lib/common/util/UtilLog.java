package lib.common.util;

import com.blankj.utilcode.util.LogUtils;

public class UtilLog {
    private  static  UtilLog instance;

    public static UtilLog getInstance() {
        if (null==instance){
            instance=new UtilLog();
        }
        return instance;
    }
    public  void i(final Object... contents){
        LogUtils.i(contents);
    }
}
