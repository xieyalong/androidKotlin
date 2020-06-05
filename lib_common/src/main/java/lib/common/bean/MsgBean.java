package lib.common.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  class MsgBean {
    public  String key;
    public  String strData;
    public  Object objData;
    public Map<String,Object> objMap;
    public List<Object> objList;

    public MsgBean(String key) {
        this.key = key;
    }

    public MsgBean(String key, String strData) {
        this.key = key;
        this.strData = strData;
    }

    public MsgBean(String key, Object objData) {
        this.key = key;
        this.objData = objData;
    }
}
