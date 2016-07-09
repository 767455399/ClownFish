package com.example.administrator.clownfish.view;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by hzwq on 2016/6/21.
 */
public   class Fangfa {

    public static void getMax(Activity activity){
        Toast.makeText(activity,"max",Toast.LENGTH_LONG).show();
    }
    public void getMin(Activity activity){
  //      Toast.makeText(activity,"min",Toast.LENGTH_LONG).show();
        Fangfa.aaa(activity);
    }

    public static void aaa(Activity activity){
        Toast.makeText(activity,"aaa",Toast.LENGTH_LONG).show();
    }
}
