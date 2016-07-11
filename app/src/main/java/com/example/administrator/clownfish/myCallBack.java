package com.example.administrator.clownfish;

/**
 * Created by hzwq on 2016/7/9.
 */
public interface myCallBack<T> {
    public void Success(T object);
    public void Fail(String fail);
}
