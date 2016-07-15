package com.example.administrator.clownfish.present;

import com.example.administrator.clownfish.Activity.ProductInformationActivity;
import com.example.administrator.clownfish.modle.ProductInformationModle;
import com.example.administrator.clownfish.myCallBack;
import com.example.administrator.clownfish.network.NetworkRequest;
import com.example.administrator.clownfish.view.BasePresenter;

/**
 * Created by hzwq on 2016/7/15.
 */
public class ProductInformationPresent implements BasePresenter<ProductInformationActivity> {
    private ProductInformationActivity view;

    @Override
    public void bind(ProductInformationActivity view) {
        this.view = view;
    }

    public void getProductInformation(String path) {
        view.showLoadingDialog();
        NetworkRequest.getProductInformation(path, new myCallBack<ProductInformationModle>() {
            @Override
            public void Success(ProductInformationModle productInformationModle) {
                view.setProductInformation(productInformationModle);
                view.dismissLoadingDialog();
            }

            @Override
            public void Fail(String fail) {
                view.dismissLoadingDialog();
            }
        });
    }
}
