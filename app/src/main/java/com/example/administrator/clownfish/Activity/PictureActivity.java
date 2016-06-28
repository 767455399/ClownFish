package com.example.administrator.clownfish.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.administrator.clownfish.BaseActivity;
import com.example.administrator.clownfish.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PictureActivity extends BaseActivity {
private ImageView imageView;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_picture);
        //获取拍照保存图片的路径；
        String path= getIntent().getStringExtra("picPath");
        ImageView imageView=(ImageView)findViewById(R.id.imageView);
        Bitmap bitmap=BitmapFactory.decodeFile(path);
        imageView.setImageBitmap(bitmap);
       /*  将存储在本地的照片转换成位图并显示出来
        try {
            //获取流对象；
            FileInputStream fileInputStream=new FileInputStream(path);
            //将流转化成BitMap对象；
            Bitmap bitmap=BitmapFactory.decodeStream(fileInputStream);
            //建立矩阵对象；
            Matrix matrix=new Matrix();
            //旋转90度；
            matrix.setRotate(90);
            //生成新的Bitmap;
            bitmap=Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
            imageView.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap bitmap= BitmapFactory.decodeFile(path);
        imageView.setImageBitmap(bitmap);*/
    }

    @Override
    protected void loadData() {

    }
}
