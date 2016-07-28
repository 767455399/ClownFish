package com.example.administrator.clownfish.Activity;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.administrator.clownfish.R;
import com.example.administrator.clownfish.view.SuccessView;

public class PaySuccessActivity extends BaseActivity implements View.OnClickListener {
    private Button paySuccessButton;
    private SuccessView successView;
    private RelativeLayout paySuccessRelativeLayout;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_pay_success);
        paySuccessButton = (Button) findViewById(R.id.paySuccessButton);
        paySuccessButton.setOnClickListener(this);
        successView = (SuccessView) findViewById(R.id.successView);
        paySuccessRelativeLayout = (RelativeLayout) findViewById(R.id.paySuccessRelativeLayout);
        paySuccessRelativeLayout.setAlpha(0);

    }

    @Override
    protected void loadData() {
        ViewCompat.animate(paySuccessRelativeLayout)
                .alpha(1f)
                .setDuration(700)
                .withStartAction(new Runnable() {
                    @Override
                    public void run() {
                        successView.startAnim();
                    }
                })
                .start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.paySuccessButton:
                finish();
                break;
            default:
                break;
        }
    }
}
