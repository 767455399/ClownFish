package com.example.administrator.clownfish.network;

import android.os.Handler;
import android.os.Looper;

import com.example.administrator.clownfish.modle.ProductInformationModle;
import com.example.administrator.clownfish.modle.StoreInformationModle;
import com.example.administrator.clownfish.myCallBack;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hzwq on 2016/7/9.
 */
public class NetworkRequest {

    public static void getStoreInformaition(String path, final myCallBack<StoreInformationModle> mycallback) {
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder()
                .url(path)
                .build();
        //new call
        final Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                /*将线程切换到主线程，返回数据*/
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        mycallback.Fail(e.toString());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String htmlStr = response.body().string();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                       /* 直接通过Gson解析服务器返回的数据*/
                       mycallback.Success(new Gson().fromJson(htmlStr,StoreInformationModle.class));
                    }
                });
            }
        });
    }

    public static void getProductInformation(String path,final myCallBack<ProductInformationModle>myCallBack){
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder()
                .url(path)
                .build();
        //new call
        final Call call=mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                     /*  myCallBack.Fail(e.toString());*/
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String htmlStr = response.body().string();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                           /* 直接通过Gson解析服务器返回的数据*/
                        myCallBack.Success(new Gson().fromJson(htmlStr,ProductInformationModle.class));
                    }
                });
            }
        });
    }
}

