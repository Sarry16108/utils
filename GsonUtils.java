package com.example.lib_generic.utils;

import android.util.ArrayMap;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GsonUtils {
    private static Gson mGson = new Gson();


    /**
     * 将对象（包括类实例，List，Map等）转化成json字符串。
     * @param object
     * @return
     */
    public static String castObjectJson(Object object) {
        return mGson.toJson(object);
    }

    /**
     * 只能解析可转化为类的实例的json字符串
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T castJsonObject(String json, Class<T> type) {
        return mGson.fromJson(json, type);
    }

    /**
     * 将json字符串转化为字符串列表数据
     */
    public static List<String> castJsonObjList(String json) {
        return mGson.fromJson(json, new TypeToken<List<String>>(){}.getType());
    }

    /**
     * 将json字符串转化为列表数据
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> List<T> castJsonObjList(String json, Class<T> type) {
        List<T> list = new ArrayList<T>();
        try {
            JsonArray arry = new JsonParser().parse(json).getAsJsonArray();
            for (JsonElement jsonElement : arry) {
                list.add(mGson.fromJson(jsonElement, type));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 将json字符串转化为Set数据
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> Set<T> castJsonObjSet(String json, Class<T> type) {
        return mGson.fromJson(json, new TypeToken<Set<T>>() {}.getType());
    }

    /**
     * 将json字符串转化为字符串map键值对数据
     */
    public static Map<String, String> castJsonObjMap(String json) {
        return mGson.fromJson(json, new TypeToken<Map<String, String>>(){}.getType());
    }

    /**
     * 将json转化为Map key/value数据
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> castJsonToMap(String json, Class<T> type) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        Map<String, T> retMap = new ArrayMap<>();
        for (Map.Entry<String, JsonElement> item : jsonObject.entrySet()) {
            retMap.put(item.getKey(), mGson.fromJson(item.getValue(), type));
        }

        return retMap;
    }

}
