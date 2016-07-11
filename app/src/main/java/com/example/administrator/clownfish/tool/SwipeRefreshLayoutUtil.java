package com.example.administrator.clownfish.tool;

import android.support.v4.widget.SwipeRefreshLayout;

import com.example.administrator.clownfish.R;

/**
 * Created by hzwq on 2016/7/11.
 */
public class SwipeRefreshLayoutUtil {

    public static void initStyle(SwipeRefreshLayout swipeRefreshLayout) {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setColorSchemeResources(
                    R.color.loading_color_1,
                    R.color.loading_color_2,
                    R.color.loading_color_3,
                    R.color.loading_color_4,
                    R.color.loading_color_5);
        }
    }
}
