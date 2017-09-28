package com.wenba.aixuestore.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class JsonWrapper {

    private static Gson gson;

    private static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static String getJsonString(Object object) {
        return getGson().toJson(object);
    }

    public static <T> T parse(String json, Class<T> tClass) {
        return getGson().fromJson(json, tClass);
    }

    public static <T> T parse(String json, Type type) {
        try {
            return getGson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}