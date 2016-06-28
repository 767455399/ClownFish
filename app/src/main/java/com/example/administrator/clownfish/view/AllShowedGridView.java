package com.example.administrator.clownfish.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.example.administrator.clownfish.R;

/**
 * 项目名称：ClownFish
 * 类描述：
 * 创建人：WangQing
 * 创建时间：2016/5/19 15:22
 * 修改人：WangQing
 * 修改时间：2016/5/19 15:22
 * 修改备注：
 */
public class AllShowedGridView  extends LinearLayout {

    private int column = 1;
    private boolean fixEmpty = false;

    private BaseAdapter adapter;
    private OnItemClickListener listener;

    public AllShowedGridView(Context context) {
        super(context);
    }

    public AllShowedGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public AllShowedGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AllShowedGridView);
        column = typedArray.getInteger(R.styleable.AllShowedGridView_column, 1);
        fixEmpty = typedArray.getBoolean(R.styleable.AllShowedGridView_fixEmpty, false);
        typedArray.recycle();
    }

    public void setAdapter(BaseAdapter adapter) {
        this.adapter = adapter;
        int lineHeight = getWidth() / column;
        removeAllViews();
        int itemCount = adapter.getCount();
        if (itemCount > 0) {
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setMotionEventSplittingEnabled(false);
            linearLayout.setWeightSum(column);
            addView(linearLayout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            for (int i = 0; i < itemCount; i++) {
                View view = adapter.getView(i, null, null);
                final int finalI = i;
                view.findViewById(R.id.itemGirdView).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.onClick(finalI);
                        }
                    }
                });

                view.setLayoutParams(new LayoutParams(lineHeight, lineHeight));
                if (linearLayout.getChildCount() < column) {
                    linearLayout.addView(view);
                } else {
                    linearLayout = new LinearLayout(getContext());
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    linearLayout.setMotionEventSplittingEnabled(false);
                    linearLayout.setWeightSum(column);
                    linearLayout.addView(view);
                    addView(linearLayout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
                }
            }
            int emptyItemCount = column - linearLayout.getChildCount();
            if (fixEmpty && emptyItemCount > 0) {
                for (int y = 0; y < emptyItemCount; y++) {
                    View view = LayoutInflater.from(getContext()).inflate(R.layout.item_mine_grid_empty, null);
                    view.setLayoutParams(new LayoutParams(lineHeight, lineHeight));
                    linearLayout.addView(view);
                }
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public interface OnItemClickListener {
        void onClick(int position);
    }
}
