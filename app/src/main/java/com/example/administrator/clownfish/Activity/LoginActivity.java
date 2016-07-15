package com.example.administrator.clownfish.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.administrator.clownfish.R;
import com.example.administrator.clownfish.tool.PhoneNumberUtils;
import com.example.administrator.clownfish.tool.ToastUtil;
import com.example.administrator.clownfish.tool.permanent;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private CheckBox passedwordChecked;
    private EditText passwordEditText;
    private Button regesitButton;
    private Button loginButton;
    private EditText phoneNumEditText;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
        phoneNumEditText = (EditText) findViewById(R.id.phoneNumEditText);
        regesitButton = (Button) findViewById(R.id.regesitButton);
        loginButton = (Button) findViewById(R.id.loginButton);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        phoneNumEditText.setText(permanent.city+"123");
        /* 默认密码不可见*/
        passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        regesitButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        passedwordChecked = (CheckBox) findViewById(R.id.passedwordChecked);
        passedwordChecked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                   /* 密码可见*/
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                  /*  密码不可见*/
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                passwordEditText.setSelection(passwordEditText.length());
            }
        });
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regesitButton:

                break;
            case R.id.loginButton:
                login();
                break;
            default:
                break;
        }
    }

    private void login() {
        String phoneNum=phoneNumEditText.getText().toString();
        String passWord=passwordEditText.getText().toString();
        if(TextUtils.isEmpty(phoneNum)){
            ToastUtil.showWarningToast("手机号码不能为空",LoginActivity.this);
        }else if(TextUtils.isEmpty(passWord)){
            ToastUtil.showWarningToast("密码不能为空",LoginActivity.this);
        }else{
            if(PhoneNumberUtils.isMobileNO(phoneNum)){
                Intent intent=new Intent();
                intent.setClass(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }else{
                ToastUtil.showSuccessToast("请输入正确的手机号码",LoginActivity.this);
            }

        }
    }
}
