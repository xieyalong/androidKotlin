package lib.common.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * json解析
 */

public class UtilJson {
    private static UtilJson instance;
    public  static UtilJson i(){
        if (null==instance){
            instance=new UtilJson();
        }
        return  instance;
    }


    public static <T extends Object> T getBaseBean(String str, Class<T> cls) {
        T bean = JSON.parseObject(str, cls);
        return bean;
    }

    public static <T extends Object> List<T> getArrayBean(String str, Class<T> cls) {
        List<T> mItems = JSON.parseArray(str, cls);
        return mItems;
    }

    public static JSONObject toJson(Object obj) {
        return (JSONObject) JSON.toJSON(obj);
    }

    public static String getString(String str, String key) {
        JSONObject jsonObject = JSON.parseObject(str);
        return jsonObject.getString(key);
    }

    public static int getInteger(String str, String key) {
        JSONObject jsonObject = JSON.parseObject(str);
        return jsonObject.getInteger(key);
    }

    public static boolean getBoolean(String str, String key) {
        JSONObject jsonObject = JSON.parseObject(str);
        return jsonObject.getBoolean(key);
    }

    public static Long getLong(String str, String key) {
        JSONObject jsonObject = JSON.parseObject(str);
        return jsonObject.getLong(key);
    }

    public static <T extends Object> JSONArray toJsonArray(List<T> arr) {
        if (arr == null) {
            return null;
        }
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(arr);
        return jsonArray;
    }

    /**
     * 字符串转jsonarray
     *
     * @param str
     * @return
     */
    public static JSONArray toJsonArray(String str) {
        return JSONArray.parseArray(str);

    }

    public static JSONObject getJsonObject(String str) {
        return JSON.parseObject(str);
    }

    /**
     * 判断是否是json数据
     *
     * @param test
     * @return
     */
    public static boolean isJSONValid(String test) {
        try {
            JSONObject.parseObject(test);
        } catch (JSONException ex) {
            try {
                JSONObject.parseArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }


    /**
     * Parse json to object.
     *
     * @param json json string.
     * @param type the type of object.
     * @param <T>  type.
     * @return object.
     */
    public static <T> T parseJson(String json, Type type) {
        return JSON.parseObject(json, type);
    }

}
