package lib.common.http;

public class HttpRequestApi {
    //    private final static  String host="http://172.16.103.97:8093/jksc";
//    private final static  String host="http://172.16.103.97:9083/jksc";
    protected final static  String host="https://114.242.25.97:6011/jksc";
    public  static String url(String url){
        return  host+url;
    }
    public  static String url2(String url){
        return  host+"/"+url;
    }
}
