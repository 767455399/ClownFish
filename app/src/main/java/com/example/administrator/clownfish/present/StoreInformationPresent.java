package com.example.administrator.clownfish.present;

import com.example.administrator.clownfish.Activity.StoreInformationActivity;
import com.example.administrator.clownfish.modle.StoreInformationModle;
import com.example.administrator.clownfish.myCallBack;
import com.example.administrator.clownfish.network.NetworkRequest;
import com.example.administrator.clownfish.tool.ToastUtil;
import com.example.administrator.clownfish.view.BasePresenter;

/**
 * Created by hzwq on 2016/7/11.
 */
public class StoreInformationPresent implements BasePresenter<StoreInformationActivity> {
    private StoreInformationActivity view;
    private String path = "http://www.jutongbao.com/jtb/phone/newshop_list.action?companyCode=05710001&userId=131D5455-C4DD-410E-8EDE-8BD1A4E371E4&pageIndex=";
    private String loadingPath;

    @Override
    public void bind(StoreInformationActivity view) {
        this.view = view;
    }

    public void getStoreInformation(Boolean refresh) {
        view.showLoadingDialog();
        if (refresh) {
            view.pageIndex = 1;
        } else {
            view.pageIndex++;
        }
        loadingPath = path + view.pageIndex;
        NetworkRequest.getStoreInformaition(loadingPath, new myCallBack<StoreInformationModle>() {
            @Override
            public void Success(StoreInformationModle storeInformationModle) {
                view.nextPageIsLoading = false;
                if (view.storeInformationSwipeRefreshLayout.isRefreshing()) {
                    view.storeInformationSwipeRefreshLayout.setRefreshing(false);
                }
                view.setStoreInformation(storeInformationModle);
                view.dismissLoadingDialog();
            }

            @Override
            public void Fail(String fail) {
                view.nextPageIsLoading = false;
                if (view.storeInformationSwipeRefreshLayout.isRefreshing()) {
                    view.storeInformationSwipeRefreshLayout.setRefreshing(false);
                }
                view.dismissLoadingDialog();
            }
        });

    }
}
