package com.example.administrator.clownfish.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.clownfish.R;

public class TextActivityA extends JiChengActivity {
    private TextView textView;
    int[] arr = new int[]{8, 2, 1, 0, 3};
    int[] index = new int[]{2, 0, 3, 2, 4, 0, 1, 3, 2, 3, 3};


    protected void initView() {
        setContentView(R.layout.activity_text2);
        textView=(TextView)findViewById(R.id.textView);
        String tel = "";
        for(int i:index){
            tel+=arr[i];
        }
        Toast.makeText(this,tel,Toast.LENGTH_LONG).show();
        textView.setText(tel);
    }
    protected void loadData() {

    }
}

