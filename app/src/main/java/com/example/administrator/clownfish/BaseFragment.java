package com.example.administrator.clownfish;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.administrator.clownfish.dialog.LoadingDialog;

/**
 * 项目名称：ClownFish
 * 类描述：
 * 创建人：WangQing
 * 创建时间：2016/5/17 14:47
 * 修改人：WangQing
 * 修改时间：2016/5/17 14:47
 * 修改备注：
 */
public abstract class BaseFragment extends Fragment {
    private LoadingDialog loadingDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadingDialog=new LoadingDialog(getActivity());
        initView(view);
        loadData();
    }

    protected abstract void initView(View view);


    protected abstract void loadData();

    protected BaseActivity getBaseActivity() {
        return (BaseActivity) (getActivity());
    }

    public void showLoadingDialog(){
        if(null!=loadingDialog&&!loadingDialog.isShowing()){
           loadingDialog.show();
        }
    }

    public void dismissLoadingDialog(){
        if(null!=loadingDialog&&loadingDialog.isShowing()){
            loadingDialog.dismiss();
        }
    }



}
