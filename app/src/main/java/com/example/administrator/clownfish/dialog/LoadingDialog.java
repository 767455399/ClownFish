package com.example.administrator.clownfish.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.clownfish.R;

/**
 * 项目名称：ClownFish
 * 类描述：
 * 创建人：WangQing
 * 创建时间：2016/5/17 9:28
 * 修改人：WangQing
 * 修改时间：2016/5/17 9:28
 * 修改备注：加载的进度框
 */
public class LoadingDialog extends Dialog{

   /* public LoadingDialog(Context context) {
        super(context, R.style.LoadingDialogStyle);
        View view= LayoutInflater.from(getContext()).inflate(R.layout.loading_dialog,null);
        ImageView imageView=(ImageView)view.findViewById(R.id.logo);
        AnimationDrawable animationDrawable=(AnimationDrawable)imageView.getBackground();
        animationDrawable.start();
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setContentView(view);
    }*/

    public LoadingDialog(Context context) {
        super(context, R.style.LoadingDialogStyle);
        View view= LayoutInflater.from(getContext()).inflate(R.layout.sunloading,null);
       /* ImageView imageView=(ImageView)view.findViewById(R.id.logo);
        AnimationDrawable animationDrawable=(AnimationDrawable)imageView.getBackground();
        animationDrawable.start();*/
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setContentView(view);
    }

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected LoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
