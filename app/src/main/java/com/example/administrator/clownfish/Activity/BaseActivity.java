package com.example.administrator.clownfish.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.clownfish.R;
import com.example.administrator.clownfish.dialog.LoadingDialog;
/*
import com.umeng.analytics.MobclickAgent;
*/

public abstract class BaseActivity extends AppCompatActivity {
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        loadingDialog = new LoadingDialog(BaseActivity.this);
        initView();
        loadData();
    }

    protected abstract void initView();

    protected abstract void loadData();

    public  void showLoadingDialog() {
        if (null == loadingDialog) {
            loadingDialog = new LoadingDialog(this);
        }
        if (!loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    ;

    public void dismissLoadingDialog() {
        if (null!=loadingDialog&&loadingDialog.isShowing()){
            loadingDialog.dismiss();
        }
    }

   /* *//**
     * 如果是fragmentActivity 这两个方法实现后，里面不需要对友盟统计实现
     *//*
    protected abstract void mobclickAgentStart();

    protected abstract void mobclickAgentEnd();

    @Override
    protected void onResume() {
        super.onResume();
        mobclickAgentStart();
        MobclickAgent.onResume(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        mobclickAgentEnd();
        MobclickAgent.onPause(this);
    }
*/

}
