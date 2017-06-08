package com.example.haozhang.requestdemo.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by haozhang on 6/7/17.
 */

public class ModelUtil {
    private static Gson gson = new Gson();

    public static <T> T toObject(String json, TypeToken<T> typeToken) {
        return gson.fromJson(json, typeToken.getType());
    }

    public static <T> String toString(T object, TypeToken<T> typeToken) {
        return gson.toJson(object, typeToken.getType());
    }
}
