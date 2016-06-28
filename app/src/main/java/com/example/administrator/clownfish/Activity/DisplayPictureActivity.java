package com.example.administrator.clownfish.Activity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.clownfish.R;
import com.squareup.picasso.Picasso;


public class DisplayPictureActivity extends BaseActivity {
    private TextView textView;
    private GestureDetector gestureDetector;
    final int RIGHT = 0;
    final int LEFT = 1;
    private ImageView imageView;
    String[] images;
    int i = 0;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_display_picture);
        gestureDetector = new GestureDetector(DisplayPictureActivity.this, onGestureListener);
        Bundle b = this.getIntent().getExtras();
        images = b.getStringArray("image");
        imageView = (ImageView) findViewById(R.id.imageView);
        textView=(TextView)findViewById(R.id.textView);
        loadImage(0);
    }

    @Override
    protected void loadData() {

    }

    private GestureDetector.OnGestureListener onGestureListener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float x = e2.getX() - e1.getX();
            float y = e2.getY() - e1.getY();

            if (x > 0) {
                doResult(RIGHT);
            } else if (x < 0) {
                doResult(LEFT);
            }
            return true;
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public void doResult(int action) {
        switch (action) {
            case RIGHT:
                System.out.println("go left");
                if(i>0){
                    i--;
                    loadImage(i);
                }else{
                    loadImage(images.length-1);
                }
                Toast.makeText(DisplayPictureActivity.this, "go left", Toast.LENGTH_LONG).show();
                break;
            case LEFT:
                if(i<images.length-1){
                    i++;
                    loadImage(i);
                }else{
                    i=0;
                    loadImage(i);
                }
                break;
        }
    }

    private void loadImage(int i) {
        Picasso.with(DisplayPictureActivity.this).load(images[i]).into(imageView);
        textView.setText(String.valueOf(i));
    }
}
