package com.example.administrator.clownfish.fragmentAdapter;

import android.content.Context;

import java.util.ArrayList;

/**
 * 项目名称：ClownFish
 * 类描述：
 * 创建人：WangQing
 * 创建时间：2016/5/17 14:28
 * 修改人：WangQing
 * 修改时间：2016/5/17 14:28
 * 修改备注：
 */
public class PagerItems<T extends PagerItem> extends ArrayList<T> {

    private final Context context;

    protected PagerItems(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

}
