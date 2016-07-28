package com.example.administrator.clownfish.Activity;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import com.example.administrator.clownfish.R;

public class ChronometerActivity extends BaseActivity implements View.OnClickListener {
    private Chronometer chronometer;
    private TextView start, stop, clear;
    private Boolean press=false;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_chronometer);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        start = (TextView) findViewById(R.id.start);
        stop = (TextView) findViewById(R.id.stop);
        clear = (TextView) findViewById(R.id.clear);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        clear.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start:
                if(press){
                    //将时间设置为暂停时的时间
                    chronometer.setBase(convertStrTimeToLong(chronometer.getText().toString()));
                    chronometer.start();//开始计时
                }else{
                    chronometer.start();
                    press=true;
                }
                break;
            case R.id.stop:
                //chronometer.stop()方法只是停止刷新计时器的时间显示，而并没有真正停止计时。当调用stop()方法后，计时器还在计时，只是不再刷新界面罢了。
                chronometer.stop();
                break;
            case R.id.clear:
                chronometer.setBase(SystemClock.elapsedRealtime());
                break;
            default:
                break;
        }
    }
    /** * 将String类型的时间转换成long,如：12:01:08 * @param strTime String类型的时间 * @return long类型的时间 * */
    protected long convertStrTimeToLong(String strTime) {
        // TODO Auto-generated method stub
        String []timeArry=strTime.split(":");
        long longTime=0;
        if (timeArry.length==2) {
            //如果时间是MM:SS格式
            longTime=Integer.parseInt(timeArry[0])*1000*60+Integer.parseInt(timeArry[1])*1000;
        }else if (timeArry.length==3){
            //如果时间是HH:MM:SS格式
            longTime=Integer.parseInt(timeArry[0])*1000*60*60+Integer.parseInt(timeArry[1])
                    *1000*60+Integer.parseInt(timeArry[0])*1000;
        }
        return SystemClock.elapsedRealtime()-longTime;
    }

}
