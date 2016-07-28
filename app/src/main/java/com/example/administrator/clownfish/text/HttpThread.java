package com.example.administrator.clownfish.text;

import android.os.Handler;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.http.GET;


/**
 * Created by hzwq on 2016/7/22.
 */
public class HttpThread extends Thread{
    private String url;
    private WebView webView;
    private Handler handler;

    public HttpThread(String url,WebView webView,Handler handler){
        this.url=url;
        this.webView=webView;
        this.handler=handler;
    }

    public void run(){
        try {
            URL httpurl=new URL(url);
            try {
                HttpURLConnection httpURLConnection= (HttpURLConnection) httpurl.openConnection();
              /*  网络访问操作*/
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                /*设置网络访问方式*/
                httpURLConnection.setRequestMethod("GET");
                /*设置缓冲*/
                final StringBuffer sb=new StringBuffer();
                String str;
               /* 获取流中的数据*/
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
             /*   bufferedReader.readLine();读取一行的数据*/
                while ((str=bufferedReader.readLine())!=null){
                   sb.append(str);
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                     /*   下载源代码信息*/
                   webView.loadData(sb.toString(),"text/html;charset=utf-8",null);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
