package com.example.administrator.clownfish.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.clownfish.R;
import com.example.administrator.clownfish.view.CustomEdittext;
import com.example.administrator.clownfish.view.RippleView;
import com.example.administrator.clownfish.view.StereoView;

public class LandingFunctionActivity extends AppCompatActivity implements View.OnClickListener {
    private StereoView stereoView;
    private CustomEdittext et_password;
    private RippleView rv_password;
    private CustomEdittext et_email;
    private RippleView rv_email;
    private CustomEdittext et_username;
    private RippleView rv_username;
    private int translateY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_function);
        init();
        stereoView.setStartScreen(1);
        stereoView.post(new Runnable() {
            @Override
            public void run() {
                int[] location = new int[2];
                stereoView.getLocationOnScreen(location);
                translateY = location[1];
            }
        });
        stereoView.setiStereoListener(new StereoView.IStereoListener() {
            @Override
            public void toPre(int curScreen) {

            }

            @Override
            public void toNext(int curScreen) {

            }
        });
    }

    private void init() {
        stereoView=(StereoView)findViewById(R.id.stereoView);
        et_password=(CustomEdittext)findViewById(R.id.et_password);
        et_email=(CustomEdittext)findViewById(R.id.et_email);
        et_username=(CustomEdittext)findViewById(R.id.et_username);
        rv_password=(RippleView)findViewById(R.id.rv_password);
        rv_email=(RippleView)findViewById(R.id.rv_email);
        rv_username=(RippleView)findViewById(R.id.rv_username);
        rv_password.setOnClickListener(this);
        rv_email.setOnClickListener(this);
        rv_username.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rv_password:
                rv_password.setiRippleAnimListener(new RippleView.IRippleAnimListener() {
                    @Override
                    public void onComplete(View view) {
                            stereoView.toPre();
                    }
                });
                break;
            case R.id.rv_email:
                rv_email.setiRippleAnimListener(new RippleView.IRippleAnimListener() {
                    @Override
                    public void onComplete(View view) {
                        stereoView.toPre();
                    }
                });
                break;
            case R.id.rv_username:
                rv_username.setiRippleAnimListener(new RippleView.IRippleAnimListener() {
                    @Override
                    public void onComplete(View view) {
                        stereoView.toPre();
                    }
                });
                break;
            default:
                break;
        }
    }
}
