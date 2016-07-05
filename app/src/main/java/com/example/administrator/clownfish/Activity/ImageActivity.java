package com.example.administrator.clownfish.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.clownfish.R;
import com.example.administrator.clownfish.view.PhotoView;
import com.squareup.picasso.Picasso;

public class ImageActivity extends AppCompatActivity {
    private PhotoView photoView;
    private String path="http://www.425575.cc/imgall/nfwwomrognwgsylofzrw63i/2014/f4/51/d/13.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        photoView=(PhotoView)findViewById(R.id.photoView);
        photoView.enable();
        Picasso.with(ImageActivity.this).load(path).into(photoView);

    }
}
