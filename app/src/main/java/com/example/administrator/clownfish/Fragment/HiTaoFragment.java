package com.example.administrator.clownfish.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps.MapView;
import com.example.administrator.clownfish.BaseFragment;
import com.example.administrator.clownfish.R;

/**
 * 项目名称：ClownFish
 * 类描述：
 * 创建人：WangQing
 * 创建时间：2016/5/17 15:57
 * 修改人：WangQing
 * 修改时间：2016/5/17 15:57
 * 修改备注：
 */
public class HiTaoFragment extends BaseFragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void loadData() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hi_tao,container,false);
    }


}
