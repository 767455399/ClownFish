package com.example.administrator.clownfish.present;

import com.example.administrator.clownfish.Activity.ContactsActivity;
import com.example.administrator.clownfish.tool.ContactsUtil;
import com.example.administrator.clownfish.view.BasePresenter;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hzwq on 2016/7/18.
 */
public class ContactsPresent implements BasePresenter<ContactsActivity> {
    private ContactsActivity view;

    @Override
    public void bind(ContactsActivity view) {
        this.view = view;
        loadContactList();
    }

    private void loadContactList() {
        view.showLoadingDialog();
        Subscriber<Void> subscriber = new Subscriber<Void>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.dismissLoadingDialog();
            }

            @Override
            public void onNext(Void aVoid) {
                view.dismissLoadingDialog();
                view.refreshList();
            }
        };
    /*    view.getSubscriberManager().add(subscriber);*/
        Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                view.setAdapter(ContactsUtil.getContactList());
                subscriber.onNext(null);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
