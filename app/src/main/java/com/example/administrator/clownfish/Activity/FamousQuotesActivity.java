package com.example.administrator.clownfish.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.clownfish.BaseActivity;
import com.example.administrator.clownfish.R;
import com.example.administrator.clownfish.dialog.LoadingDialog;

public class FamousQuotesActivity extends BaseActivity {
   /* LoadingDialog loadingDialog;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      /*  LoadingDialog loadingDialog = new LoadingDialog(FamousQuotesActivity.this);
        loadingDialog.show();*/

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_famous_quotes);
        this.showLoadingDialog();
    }

    @Override
    protected void loadData() {

    }
}
