package com.example.administrator.clownfish.Activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.administrator.clownfish.R;
import com.example.administrator.clownfish.text.HttpThread;

public class HttpActivity extends BaseActivity {
    private WebView webView;
    private Handler handler=new Handler();
    private String url="https://www.baidu.com/";
    @Override
    protected void initView() {
        setContentView(R.layout.activity_http);
        webView=(WebView)findViewById(R.id.webView);
        new HttpThread(url,webView,handler).start();
    }

    @Override
    protected void loadData() {

    }
}
