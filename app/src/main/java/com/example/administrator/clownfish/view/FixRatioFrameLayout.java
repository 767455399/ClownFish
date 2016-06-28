package com.example.administrator.clownfish.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.example.administrator.clownfish.R;

/**
 * 项目名称：ClownFish
 * 类描述：
 * 创建人：WangQing
 * 创建时间：2016/5/18 14:23
 * 修改人：WangQing
 * 修改时间：2016/5/18 14:23
 * 修改备注：
 */
public class FixRatioFrameLayout extends FrameLayout {

    private boolean baseOnWith = true;
    private float ratio = 1;

    public FixRatioFrameLayout(Context context) {
        super(context);
    }

    public FixRatioFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FixRatioFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FixRatioFrameLayout);
        ratio = typedArray.getFloat(R.styleable.FixRatioFrameLayout_ratio, 1f);
        baseOnWith = typedArray.getBoolean(R.styleable.FixRatioFrameLayout_baseOnWidth, true);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (baseOnWith) {
            height = (int) (width * ratio);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        } else {
            width = (int) (height * ratio);
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
