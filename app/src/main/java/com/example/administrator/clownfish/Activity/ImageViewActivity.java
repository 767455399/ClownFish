package com.example.administrator.clownfish.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.clownfish.BaseActivity;
import com.example.administrator.clownfish.R;
import com.example.administrator.clownfish.view.Gouzhaohanshu;
import com.squareup.picasso.Picasso;

public class ImageViewActivity extends BaseActivity {
    private String imagepath;
    private ImageView imageView;
    private TextView textView;
    public static String zhanmusi="詹姆斯";



    @Override
    protected void initView() {
        setContentView(R.layout.activity_image_view);
        textView=(TextView)findViewById(R.id.textView);
        imageView=(ImageView)findViewById(R.id.imageView);
        final Intent intent = this.getIntent();        //获取已有的intent对象
        Bundle bundle = intent.getExtras();    //获取intent里面的bundle对象
        imagepath = bundle.getString("imagePath");    //获取Bundle里面的字符串
     /*   Gouzhaohanshu gouzhaohanshu=new Gouzhaohanshu("abc");*/
        textView.setText(Gouzhaohanshu.wangqing);
        Picasso.with(ImageViewActivity.this).load(imagepath).error(R.mipmap.ic_launcher).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent();
                intent1.setClass(ImageViewActivity.this,FamousQuotesActivity.class);
                startActivity(intent1);
                /*finish();*/
            }
        });
    }

    @Override
    protected void loadData() {

    }
}
