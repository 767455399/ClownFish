package com.example.administrator.clownfish.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.clownfish.BaseActivity;
import com.example.administrator.clownfish.Car;
import com.example.administrator.clownfish.Fragment.HiTaoFragment;
import com.example.administrator.clownfish.Fragment.HomePageFragment;
import com.example.administrator.clownfish.Fragment.MineFragment;
import com.example.administrator.clownfish.Fragment.WalletFragment;
import com.example.administrator.clownfish.R;
import com.example.administrator.clownfish.fragmentAdapter.FragmentPagerItemAdapter;
import com.example.administrator.clownfish.fragmentAdapter.FragmentPagerItems;
import com.example.administrator.clownfish.fragmentAdapter.NotScrollViewPager;
import com.example.administrator.clownfish.view.Fangfa;
import com.example.administrator.clownfish.view.Gouzhaohanshu;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private NotScrollViewPager mainPager;
    private FragmentPagerItemAdapter fragmentPagerItemAdapter;
    private LinearLayout homePageLinearLayout;
    private LinearLayout haitaoLinearLayout;
    private LinearLayout walletLinearLayout;
    private LinearLayout mineLinearLayout;
    private ImageView homePageImageView;
    private ImageView haitaoImageView;
    private ImageView walletImageView;
    private ImageView mineImageView;
    private TextView homePageTextView;
    private TextView haitaoTextView;
    private TextView walletTextView;
    private TextView mineTextView;
    private String abc;
    public static String wangqing;
    Gouzhaohanshu gouzhaohanshu=new Gouzhaohanshu("wangqing");
    Gouzhaohanshu gouzhaohanshu1=new Gouzhaohanshu("aa","bb");
    private String blue="blue";
    private String red;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
       /* Intent intent=new Intent();
        intent.setClass(MainActivity.this,ViewPagerActivty.class);
        startActivity(intent);*/
        Car car1=Car.getCar();
        car1.setCarColor(blue);
        Car car2=Car.getCar();
        car2.getCarColor();

      /*  Fangfa.getMax(this);*/
        Fangfa fangfa=new Fangfa();
        fangfa.getMin(this);
        homePageLinearLayout = (LinearLayout) findViewById(R.id.homePageLinearLayout);
        haitaoLinearLayout = (LinearLayout) findViewById(R.id.haitaoLinearLayout);
        walletLinearLayout = (LinearLayout) findViewById(R.id.walletLinearLayout);
        mineLinearLayout = (LinearLayout) findViewById(R.id.mineLinearLayout);
        mainPager = (NotScrollViewPager) findViewById(R.id.mainPager);
        homePageImageView = (ImageView) findViewById(R.id.homePageImageView);
        haitaoImageView = (ImageView) findViewById(R.id.haitaoImageView);
        walletImageView = (ImageView) findViewById(R.id.walletImageView);
        mineImageView = (ImageView) findViewById(R.id.mineImageView);
        homePageTextView = (TextView) findViewById(R.id.homePageTextView);
        haitaoTextView = (TextView) findViewById(R.id.haitaoTextView);
        walletTextView = (TextView) findViewById(R.id.walletTextView);
        mineTextView = (TextView) findViewById(R.id.mineTextView);
        homePageLinearLayout.setOnClickListener(this);
        haitaoLinearLayout.setOnClickListener(this);
        walletLinearLayout.setOnClickListener(this);
        mineLinearLayout.setOnClickListener(this);
        if (null != mainPager) {
            mainPager.setOffscreenPageLimit(4);
        }

        fragmentPagerItemAdapter = new FragmentPagerItemAdapter
                (getSupportFragmentManager(), FragmentPagerItems.with(MainActivity.this)
                        .add(getString(R.string.home_page), HomePageFragment.class)
                        .add(getString(R.string.hi_tao), HiTaoFragment.class)
                        .add(getString(R.string.wallet), WalletFragment.class)
                        .add(getString(R.string.mine), MineFragment.class)
                        .create());
        mainPager.setAdapter(fragmentPagerItemAdapter);
    }




    @Override
    protected void loadData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
   /*     setTabSelected(0);*/
    }

    /* @Override
    protected void mobclickAgentStart() {

    }

    @Override
    protected void mobclickAgentEnd() {

    }*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homePageLinearLayout:
                setTabSelected(0);
                break;
            case R.id.haitaoLinearLayout:
                setTabSelected(1);
                break;
            case R.id.walletLinearLayout:
                setTabSelected(2);
                break;
            case R.id.mineLinearLayout:
                setTabSelected(3);
                break;
        }

    }

    public void setTabSelected(int index) {
        mainPager.setCurrentItem(index, false);
        switch (index) {
            case 0:
                homePageImageView.setImageResource(R.mipmap.ic_index_selected);
                haitaoImageView.setImageResource(R.mipmap.ic_mall_default);
                walletImageView.setImageResource(R.mipmap.ic_wallet_default);
                mineImageView.setImageResource(R.mipmap.ic_mine_default);
                homePageTextView.setTextColor(Color.BLUE);
                haitaoTextView.setTextColor(Color.BLACK);
                walletTextView.setTextColor(Color.BLACK);
                mineTextView.setTextColor(Color.BLACK);
                break;
            case 1:
                homePageImageView.setImageResource(R.mipmap.ic_index_default);
                haitaoImageView.setImageResource(R.mipmap.ic_mall_selected);
                walletImageView.setImageResource(R.mipmap.ic_wallet_default);
                mineImageView.setImageResource(R.mipmap.ic_mine_default);
                homePageTextView.setTextColor(Color.BLACK);
                haitaoTextView.setTextColor(Color.BLUE);
                walletTextView.setTextColor(Color.BLACK);
                mineTextView.setTextColor(Color.BLACK);
                break;
            case 2:
                homePageImageView.setImageResource(R.mipmap.ic_index_default);
                haitaoImageView.setImageResource(R.mipmap.ic_mall_default);
                walletImageView.setImageResource(R.mipmap.ic_wallet_selected);
                mineImageView.setImageResource(R.mipmap.ic_mine_default);
                homePageTextView.setTextColor(Color.BLACK);
                haitaoTextView.setTextColor(Color.BLACK);
                walletTextView.setTextColor(Color.BLUE);
                mineTextView.setTextColor(Color.BLACK);
                break;
            case 3:
                homePageImageView.setImageResource(R.mipmap.ic_index_default);
                haitaoImageView.setImageResource(R.mipmap.ic_mall_default);
                walletImageView.setImageResource(R.mipmap.ic_wallet_default);
                mineImageView.setImageResource(R.mipmap.ic_mine_selected);
                homePageTextView.setTextColor(Color.BLACK);
                haitaoTextView.setTextColor(Color.BLACK);
                walletTextView.setTextColor(Color.BLACK);
                mineTextView.setTextColor(Color.BLUE);
                break;
        }
    }
}
