package com.example.administrator.clownfish.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.administrator.clownfish.R;
import com.example.administrator.clownfish.view.ShoppingView;

public class ShoppingViewActivity extends BaseActivity {

    @Override
    protected void initView() {
        setContentView(R.layout.activity_shopping_view);
        ShoppingView mSv1 = (ShoppingView) findViewById(R.id.sv_1);
        mSv1.setOnShoppingClickListener(new ShoppingView.ShoppingClickListener() {
            @Override
            public void onAddClick(int num) {
                Log.d("@=>", "add.......num=> " + num);
            }

            @Override
            public void onMinusClick(int num) {
                Log.d("@=>", "minus.......num=> " + num);
            }
        });

        ShoppingView mSv2 = (ShoppingView) findViewById(R.id.sv_2);
        mSv2.setTextNum(1);
    }

    @Override
    protected void loadData() {

    }
}
