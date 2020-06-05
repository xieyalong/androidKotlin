package lib.common.util;

import com.blankj.utilcode.util.EncodeUtils;

public class UtilBase64 {
    private  static UtilBase64 instance;
    public static UtilBase64 i(){
        if (null==instance){
            instance=new UtilBase64();
        }
        return  instance;
    }
    public   byte[] base64Encode(final byte[] input){
        return  EncodeUtils.base64Encode(input);
    }
    public   String base64Encode2String(final byte[] input){
        return  EncodeUtils.base64Encode2String(input);
    }
}
