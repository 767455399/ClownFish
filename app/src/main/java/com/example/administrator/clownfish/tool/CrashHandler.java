package com.example.administrator.clownfish.tool;

import android.util.Log;

/**
 * Created by hzwq on 2016/7/18.
 */
public class CrashHandler  implements Thread.UncaughtExceptionHandler {

    private static CrashHandler crashHandler = null;

    private CrashHandler() {

    }

    public static CrashHandler getInstance() {
        if (crashHandler == null) {
            synchronized (CrashHandler.class) {
                if (crashHandler == null) {
                    crashHandler = new CrashHandler();
                }
            }
        }
        return crashHandler;
    }


    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        Log.e("ex", ex.toString());
        System.exit(10);
    }
}
