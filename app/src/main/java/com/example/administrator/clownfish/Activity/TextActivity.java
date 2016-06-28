package com.example.administrator.clownfish.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.clownfish.BaseActivity;
import com.example.administrator.clownfish.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Arrays;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;


public class TextActivity extends BaseActivity {
    private TextView textView;
    private Button camera;
    private int[] array = {2, 13, 232, 34, 354, 32, 45, 76, 54, 33, 1234};
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_text);
        textView = (TextView) findViewById(R.id.textView);
        camera = (Button) findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(TextActivity.this, CameraTimerAvity.class);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
      /*  textView.setText(bundle.get("key").toString());*/
     /*   int n=getMax(array);*/
        int n = getMin(array);
    /*    textView.setText(array[n] + "");*/
      /*  Arrays.sort(array);*/
        StringBuffer stringBuffer = new StringBuffer();
        for (int i : maopao(array)) {
            stringBuffer.append(i + ",");
        }
        textView.setText(stringBuffer);
    }

    @Override
    protected void loadData() {

    }

    protected int getMax(int[] shuzu) {
        int max = 0;
        for (int i = 0; i < shuzu.length; i++) {
            if (shuzu[max] < shuzu[i]) {
                max = i;
            }
        }
        return max;
    }

    protected int getMin(int[] zuixiao) {
        int min = 0;
        for (int i = 0; i < zuixiao.length; i++) {
            if (zuixiao[min] > zuixiao[i]) {
                min = i;
            }
        }
        return min;
    }

    Observable observable=Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("wang");
            subscriber.onNext("qing");
            subscriber.onNext("123");
            subscriber.onCompleted();
        }
    });

    Observer<String>observer=new Observer<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {

        }
    };

    Subscriber<String>subscriber=new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {

        }
    };

    //冒泡排序法；
    protected static int[] maopao(int[] maopao) {
        for (int i = 0; i < maopao.length - 1; i++) {
            for (int j = i + 1; j < maopao.length; j++) {
                if (maopao[i] > maopao[j]) {
                    int min;
                    min = maopao[j];
                    maopao[j] = maopao[i];
                    maopao[i] = min;
                }
            }
        }
        return maopao;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Text Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.administrator.clownfish.Activity/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Text Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.administrator.clownfish.Activity/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
