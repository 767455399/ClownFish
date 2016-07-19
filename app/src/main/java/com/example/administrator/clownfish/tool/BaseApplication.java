package com.example.administrator.clownfish.tool;

import android.app.Application;
import android.content.Context;

/**
 * Created by hzwq on 2016/7/19.
 */
public class BaseApplication extends Application {

    private static Context applicationContext;

    public BaseApplication() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(CrashHandler.getInstance());
    }


    public static Context getContext() {
        return applicationContext;
    }

    public static void setContext(Context context) {
        if (applicationContext == null) {
            applicationContext = context;
        }
    }

    public static Boolean getIsLogin() {
        return SharedPreferencesUtil.getBoolean("loginStatus", false);
    }

    public static void setIsLogin(Boolean isLogin) {
        SharedPreferencesUtil.save("loginStatus", isLogin);
    }


}