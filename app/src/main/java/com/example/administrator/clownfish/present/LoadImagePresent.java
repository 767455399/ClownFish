package com.example.administrator.clownfish.present;

import com.example.administrator.clownfish.Activity.LoadImageActivity;
import com.example.administrator.clownfish.view.BasePresenter;

/**
 * Created by hzwq on 2016/7/25.
 */
public class LoadImagePresent implements BasePresenter<LoadImageActivity>{
    private LoadImageActivity view;
    @Override
    public void bind(LoadImageActivity view) {
        this.view=view;
    }

    public void getImage(String url){
       /* OkHttpUtils
                .get()//
                .url(url)//
                .build()//
                .execute(new BitmapCallback()
                {
                    @Override
                    public void onError(Request request, Exception e)
                    {
                        mTv.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(Bitmap bitmap)
                    {
                        mImageView.setImageBitmap(bitmap);
                    }
                });*/
    }

}
