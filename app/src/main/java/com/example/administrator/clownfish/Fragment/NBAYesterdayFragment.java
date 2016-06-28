package com.example.administrator.clownfish.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.administrator.clownfish.BaseFragment;
import com.example.administrator.clownfish.R;

/**
 * 项目名称：ClownFish
 * 类描述：
 * 创建人：WangQing
 * 创建时间：2016/5/20 11:41
 * 修改人：WangQing
 * 修改时间：2016/5/20 11:41
 * 修改备注：
 */
public class NBAYesterdayFragment extends BaseFragment {
private EditText editText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_nba_yesterday,container,false);
        return view;
    }

    @Override
    protected void initView(View view) {
        editText=(EditText)view.findViewById(R.id.editText);
    }

    @Override
    protected void loadData() {

    }
}
