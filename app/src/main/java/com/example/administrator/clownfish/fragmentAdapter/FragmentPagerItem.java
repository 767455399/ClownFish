package com.example.administrator.clownfish.fragmentAdapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * 项目名称：ClownFish
 * 类描述：
 * 创建人：WangQing
 * 创建时间：2016/5/17 14:26
 * 修改人：WangQing
 * 修改时间：2016/5/17 14:26
 * 修改备注：
 */
public class FragmentPagerItem extends PagerItem {

    private static final String TAG = "FragmentPagerItem";
    private static final String KEY_POSITION = TAG + ":Position";

    private final String className;
    private final Bundle args;

    protected FragmentPagerItem(CharSequence title, float width, String className, Bundle args) {
        super(title, width);
        this.className = className;
        this.args = args;
    }

    public static FragmentPagerItem of(CharSequence title, Class<? extends Fragment> clazz) {
        return of(title, DEFAULT_WIDTH, clazz);
    }

    public static FragmentPagerItem of(CharSequence title, Class<? extends Fragment> clazz,
                                       Bundle args) {
        return of(title, DEFAULT_WIDTH, clazz, args);
    }

    public static FragmentPagerItem of(CharSequence title, float width,
                                       Class<? extends Fragment> clazz) {
        return of(title, width, clazz, new Bundle());
    }

    public static FragmentPagerItem of(CharSequence title, float width,
                                       Class<? extends Fragment> clazz, Bundle args) {
        return new FragmentPagerItem(title, width, clazz.getName(), args);
    }

    public static boolean hasPosition(Bundle args) {
        return args != null && args.containsKey(KEY_POSITION);
    }

    public static int getPosition(Bundle args) {
        return (hasPosition(args)) ? args.getInt(KEY_POSITION) : 0;
    }

    static void setPosition(Bundle args, int position) {
        args.putInt(KEY_POSITION, position);
    }

    public Fragment instantiate(Context context, int position) {
        setPosition(args, position);
        return Fragment.instantiate(context, className, args);
    }

}