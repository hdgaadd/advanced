package com.codeman;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpAccess {
    public static void main(String[] args) {
        OkHttpClient okHttpClient = new OkHttpClient();
        try {
            Request req = new Request.Builder().url("https://baidu.com").build();
            Response resq = client.newCall(req).execute();
            System.out.println(resq.body().toString());
        } finally {
            client.close();
        }
    }
}
