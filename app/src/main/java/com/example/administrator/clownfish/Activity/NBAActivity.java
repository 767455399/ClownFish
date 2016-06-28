package com.example.administrator.clownfish.Activity;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.administrator.clownfish.BaseActivity;
import com.example.administrator.clownfish.Fragment.NBATodayFragment;
import com.example.administrator.clownfish.Fragment.NBATomorrowFragment;
import com.example.administrator.clownfish.Fragment.NBAYesterdayFragment;
import com.example.administrator.clownfish.R;
import com.example.administrator.clownfish.fragmentAdapter.FragmentPagerItemAdapter;
import com.example.administrator.clownfish.fragmentAdapter.FragmentPagerItems;

       /* 1，这里需要注意的是我们用到的是TabLayout，而不是TableLayout,
        在使用TabLayout的时候，我们需要导入compile 'com.android.support:design:23.4.0'包。
        2，我们要为每个fragment设置布局，否则将会出现左右滑动屏幕的时候出现类似界面卡顿的情况。*/

public class NBAActivity extends BaseActivity {
    private ViewPager nbaViewPager;
    private TabLayout nbaTabLayout;



    @Override
    protected void initView() {
        setContentView(R.layout.activity_nba);
        nbaViewPager=(ViewPager)findViewById(R.id.nbaViewPager);
        nbaTabLayout=(TabLayout)findViewById(R.id.nbaTabLayout);
        FragmentPagerItemAdapter fragmentPagerItemAdapter=new FragmentPagerItemAdapter(getSupportFragmentManager(), FragmentPagerItems.with(NBAActivity.this)
                .add(R.string.yesterday, NBAYesterdayFragment.class)
                .add(R.string.today,NBATodayFragment.class)
                .add(R.string.tomorrow,NBATomorrowFragment.class).create());
        nbaViewPager.setAdapter(fragmentPagerItemAdapter);
        nbaTabLayout.setupWithViewPager(nbaViewPager);

    }

    @Override
    protected void loadData() {

    }


}
