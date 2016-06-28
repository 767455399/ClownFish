package com.example.administrator.clownfish.Activity;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.clownfish.BaseActivity;
import com.example.administrator.clownfish.R;

public class CameraActivity extends BaseActivity implements View.OnClickListener {
    private Button customCameraButton;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_camera);
        customCameraButton=(Button)findViewById(R.id.customCameraButton);
        customCameraButton.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.customCameraButton:
                Intent intent=new Intent();
                intent.setClass(CameraActivity.this,CustomCameraActivity.class);
                startActivity(intent);
                break;
        }
    }
}
