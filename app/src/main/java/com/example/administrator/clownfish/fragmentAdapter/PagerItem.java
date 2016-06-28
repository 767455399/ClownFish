package com.example.administrator.clownfish.fragmentAdapter;

/**
 * 项目名称：ClownFish
 * 类描述：
 * 创建人：WangQing
 * 创建时间：2016/5/17 14:27
 * 修改人：WangQing
 * 修改时间：2016/5/17 14:27
 * 修改备注：
 */
public class PagerItem {

    protected static final float DEFAULT_WIDTH = 1.f;

    private final CharSequence title;
    private final float width;

    protected PagerItem(CharSequence title, float width) {
        this.title = title;
        this.width = width;
    }

    public CharSequence getTitle() {
        return title;
    }

    public float getWidth() {
        return width;
    }

}
