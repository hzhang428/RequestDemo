package com.example.haozhang.requestdemo.api;

import android.util.Log;

import com.example.haozhang.requestdemo.model.LoginData;
import com.example.haozhang.requestdemo.model.LogoutData;
import com.example.haozhang.requestdemo.util.ModelUtil;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by haozhang on 6/7/17.
 *
 */

public class Acre {

    private static final String TAG = "DISCUZ_API";

    private static final String API_URL = "http://52.32.224.254/dapi/v1/";

    private static final String LOGIN_END_POINT = "login";

    private static final String LOGOUT_END_POINT = "logout";

    private static OkHttpClient client = new OkHttpClient();

    private static final TypeToken<LoginData> LOGIN = new TypeToken<LoginData>(){};

    private static final TypeToken<LogoutData> LOGOUT = new TypeToken<LogoutData>(){};

    private static String accessToken = "12a3f75e96f4932ae4076382f0ca6f71";

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static <T> T parseResponse(Response response,
                                       TypeToken<T> typeToken) throws IOException, JsonSyntaxException {
        String responseString = response.body().string();
        Log.d(TAG, responseString);
        return ModelUtil.toObject(responseString, typeToken);
    }

    public static String logoutRequest() throws IOException {
        Request request =  new Request.Builder().addHeader("token", accessToken).addHeader("content-type", "application/json").url(API_URL + LOGOUT_END_POINT).build();
        Log.d(TAG, request.toString());
        Response response = client.newCall(request).execute();
        LogoutData data = parseResponse(response, LOGOUT);
        Acre.accessToken = null;
        return data.msg;
    }

    public static String loginRequest() throws IOException {
        JSONObject obj = new JSONObject();
        try {
            obj.put("username", "testuser4");
            obj.put("password", "123456");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG, obj.toString());
        RequestBody body = RequestBody.create(JSON, obj.toString());
        Request request = new Request.Builder()
                .addHeader("content-type", "application/json")
                .url(API_URL + LOGIN_END_POINT)
                .post(body)
                .build();
        Log.d(TAG, request.toString());
        Response response = client.newCall(request).execute();
        Log.d(TAG, response.networkResponse().body().string());
        LoginData data = parseResponse(response, LOGIN);
        Acre.accessToken = data.data.token;
        return data.data.token;
    }
}
