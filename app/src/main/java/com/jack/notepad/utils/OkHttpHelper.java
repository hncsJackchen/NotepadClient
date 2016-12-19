package com.jack.notepad.utils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.jack.notepad.response.HttpCallback;
import com.jack.notepad.response.ResponseResult;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Author： Jackchen
 * Time： 2016/11/22
 * Description:okhttp帮助类
 */
public class OkHttpHelper {
    private static final String TAG = "OkHttpHelper";
    private static final OkHttpClient okClient = new OkHttpClient();
    private static final Handler handler = new Handler(Looper.getMainLooper());

    public static void testHttp(HttpCallback callback) {
        //url
        String url = "http://192.168.1.197:8080/user/login";
        //参数
        HashMap<String, String> map = new HashMap<>();
        map.put("userName", "hncschen");
        map.put("password", "123456");
        //请求
        request(url, map, callback);
    }

    /**
     * 对外接口
     *
     * @param url 请求地址
     * @param map 参数
     * @param callback 回调
     */
    public static void httpPost(String url, HashMap<String, String> map,
        final HttpCallback callback) {
        request(url, map, callback);
    }

    private static void request(String url, HashMap<String, String> map,
        final HttpCallback callback) {
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(url);

        if (map != null && map.size() > 0) {
            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
            RequestBody body = builder.build();
            requestBuilder.post(body);
        }

        Request request = requestBuilder.build();
        Call call = okClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                Log.w(TAG, "onFailure()");
                e.printStackTrace();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null) {
                            callback.onFailure("onFailure");
                        }
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.w(TAG, "onResponse success");
                    String s = response.body().string();
                    final ResponseResult resp = JSON.parseObject(s, ResponseResult.class);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (callback != null) {
                                callback.onSuccess(resp);
                            }
                        }
                    });
                } else {
                    Log.w(TAG, "onResponse failure");
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (callback != null) {
                                callback.onFailure("onResponse failure");
                            }
                        }
                    });
                }
            }
        });
    }
}
