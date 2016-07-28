package com.example.administrator.clownfish.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.administrator.clownfish.R;
import com.example.administrator.clownfish.present.LoadImagePresent;

public class LoadImageActivity extends BaseActivity {
    private LoadImagePresent present;
    private ImageView imageView;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_load_image);
        imageView=(ImageView)findViewById(R.id.imageView);
    }

    @Override
    protected void loadData() {

    }
}
