package com.example.administrator.clownfish.Activity;

import android.content.Intent;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.example.administrator.clownfish.BaseActivity;
import com.example.administrator.clownfish.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CustomCameraActivity extends BaseActivity implements SurfaceHolder.Callback {
    private Button takePhoto;
    private SurfaceView preview;
    private Camera myCamera;
    private SurfaceHolder surfaceHolder;
    private Camera.PictureCallback mpictureCallback= new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File  tempfile=new File("/sdcard/temp.png");
            try {
                FileOutputStream fileOutputStream=new FileOutputStream(tempfile);
                try {
                    fileOutputStream.write(data);
                    fileOutputStream.close();
                    Intent intent=new Intent(CustomCameraActivity.this,PictureActivity.class);
                    intent.putExtra("picPath",tempfile.getAbsolutePath());
                    startActivity(intent);
                    finish();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    };


    @Override
    protected void initView() {
        setContentView(R.layout.activity_custom_camera);
        takePhoto = (Button) findViewById(R.id.takePhoto);
        preview = (SurfaceView) findViewById(R.id.preview);
        surfaceHolder = preview.getHolder();
        surfaceHolder.addCallback(this);
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCamera.autoFocus(null);
            }
        });
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Camera.Parameters parameters = myCamera.getParameters();
                //设置拍照模式；
                parameters.setPictureFormat(ImageFormat.JPEG);
                parameters.setPictureSize(800,400);
                //自动对焦
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
                myCamera.autoFocus(new Camera.AutoFocusCallback(){
                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {
                        if(success){
                            myCamera.takePicture(null,null,mpictureCallback);
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void loadData() {

    }


    /*
    * 获取相机
    * */
    private Camera getCamera() {
        Camera camera;
        try {
            camera = Camera.open();
        } catch (Exception e) {
            camera = null;
            e.printStackTrace();
        }
        return camera;

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (myCamera == null) {
            myCamera = getCamera();
            if (surfaceHolder != null) {
                setStartPreview(myCamera, surfaceHolder);
            }
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
    }

    /*
            * 开始预览相机内容
            * **/
    private void setStartPreview(Camera camera, SurfaceHolder mySurfaceHolder) {
        try {
            camera.setPreviewDisplay(mySurfaceHolder);
            //将系统camera预览角度进行调整。
            camera.setDisplayOrientation(90);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void releaseCamera() {
        if (myCamera != null) {
            myCamera.setPreviewCallback(null);
            //停止相机取景功能
            myCamera.stopPreview();
            //释放相机资源
            myCamera.release();
            myCamera = null;
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //绑定相机功能；
        setStartPreview(myCamera, surfaceHolder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //关闭相机；
        myCamera.stopPreview();
        //重启相机拍照功能；
        setStartPreview(myCamera, surfaceHolder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //释放相机资源；
        releaseCamera();

    }
}
