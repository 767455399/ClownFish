package com.example.administrator.clownfish.Activity;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.clownfish.R;
import com.example.administrator.clownfish.map.PoiKeywordSearchActivity;

import java.util.ArrayList;
import java.util.List;
/*
* app启动页
*
* */
public class ViewPagerActivty extends BaseActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private View view1,view2,view3;
    private List<View>viewList;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_view_pager_activty);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        LayoutInflater layoutInflater=getLayoutInflater();
        view1=layoutInflater.inflate(R.layout.viewpager_layout1,null);
        view2=layoutInflater.inflate(R.layout.viewpager_layout2,null);
        view3=layoutInflater.inflate(R.layout.viewpager_layout3,null);
        viewList=new ArrayList<View>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        TextView textView=(TextView)view3.findViewById(R.id.textView);
        textView.setOnClickListener(this);
        PagerAdapter pagerAdapter=new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }
        };
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textView:
                Intent intent=new Intent();
                intent.setClass(ViewPagerActivty.this,ContactListActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
