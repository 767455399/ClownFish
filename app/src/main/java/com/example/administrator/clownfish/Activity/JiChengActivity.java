package com.example.administrator.clownfish.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.administrator.clownfish.R;

public class JiChengActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ji_cheng);
        study();
        work();
    }

    private void work() {
        Toast.makeText(this,"work_hard",Toast.LENGTH_LONG).show();
    }

    private void study() {
        Toast.makeText(this,"study_hard",Toast.LENGTH_LONG).show();
    }


}
