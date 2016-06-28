package com.example.administrator.clownfish.fragmentAdapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 项目名称：ClownFish
 * 类描述：
 * 创建人：WangQing
 * 创建时间：2016/5/17 14:28
 * 修改人：WangQing
 * 修改时间：2016/5/17 14:28
 * 修改备注：
 */
public class NotScrollViewPager extends ViewPager {


    public NotScrollViewPager(Context context) {
        super(context);
    }

    public NotScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return false;
    }

}

