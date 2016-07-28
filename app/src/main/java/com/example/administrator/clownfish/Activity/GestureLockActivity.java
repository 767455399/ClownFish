package com.example.administrator.clownfish.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.clownfish.R;
import com.example.administrator.clownfish.lock.GestureLockViewGroup;
import com.example.administrator.clownfish.tool.ToastUtil;

public class GestureLockActivity extends BaseActivity {
    private GestureLockViewGroup gestureLockViewGroup;



    @Override
    protected void initView() {
        setContentView(R.layout.activity_gesture_lock);
        gestureLockViewGroup = (GestureLockViewGroup) findViewById(R.id.id_gestureLockViewGroup);
        gestureLockViewGroup.setAnswer(new int[]{1, 2, 3, 4, 5, 6});
    }

    @Override
    protected void loadData() {
        gestureLockViewGroup.setOnGestureLockViewListener(new GestureLockViewGroup.OnGestureLockViewListener() {
            @Override
            public void onBlockSelected(int cId) {

            }

            @Override
            public void onGestureEvent(boolean matched) {
                if (matched) {
                    ToastUtil.showSuccessToast("密码正确", GestureLockActivity.this);
                    gestureLockViewGroup.reset();
                    Intent intent = new Intent();
                    intent.setClass(GestureLockActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    gestureLockViewGroup.reset();
                }

            }

            @Override
            public void onUnmatchedExceedBoundary() {
                ToastUtil.showSuccessToast("密码第五次输错", GestureLockActivity.this);
                finish();
                //gestureLockViewGroup.setUnMatchExceedBoundary(5);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        finish();
       /* Intent intent1 = new Intent();
        intent1.setClass(GestureLockActivity.this, ViewPagerActivty.class);
        startActivity(intent);*/
    }
}
