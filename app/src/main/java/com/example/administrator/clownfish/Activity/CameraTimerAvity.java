package com.example.administrator.clownfish.Activity;

import android.content.ContentValues;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.clownfish.R;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

public class CameraTimerAvity extends AppCompatActivity implements SurfaceHolder.Callback,
        View.OnClickListener, Camera.PictureCallback {

    private static final String CAMERA_CONTROLL = "CAMERA_CONTROLL";
    private SurfaceView imageSView;
    private Button startButton;
    // private TextView countDownTextView;
    private EditText countDownEditTextView;
    private Camera camera;
    private SurfaceHolder surfaceHolder;
    private Handler timerUpdateHandler;
    private boolean timerRunning = false;
    private int currentTimer = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_timer);
        imageSView = (SurfaceView) findViewById(R.id.imageView);
        startButton = (Button) findViewById(R.id.startBtn);
        // countDownTextView = (TextView) findViewById(R.id.countDowntextView);
        countDownEditTextView = (EditText) findViewById(R.id.countDownEditTextView);
        /*
        countDownEditTextView.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                    int count) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                    int arg2, int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                currentTimer = Integer.parseInt(countDownEditTextView.getText().toString());
            }
        });
        */
        surfaceHolder = imageSView.getHolder();

        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        surfaceHolder.addCallback(this);

        startButton.setOnClickListener(this);

        timerUpdateHandler = new Handler();
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        // TODO Auto-generated method stub
        Uri imageFileUri = getContentResolver().insert(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new ContentValues());
        try {
            OutputStream imageFileOS = getContentResolver().openOutputStream(
                    imageFileUri);
            imageFileOS.write(data);
            imageFileOS.flush();
            imageFileOS.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        camera.startPreview();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        currentTimer = Integer.parseInt(countDownEditTextView.getText().toString());
        switch (v.getId()) {
            case R.id.startBtn:
                if (!timerRunning) {
                    timerRunning = true;
                    timerUpdateHandler.post(timerUpdateTask);
                }
                break;
        }
    }

    private Runnable timerUpdateTask = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            if (currentTimer > 1) {
                currentTimer--;
                timerUpdateHandler.postDelayed(timerUpdateTask, 1000);
            } else {
                camera.takePicture(null, null, null, CameraTimerAvity.this);
                timerRunning = false;
                currentTimer = 10;
            }
            countDownEditTextView.setText(currentTimer + "");
        }
    };

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO Auto-generated method stub
        camera.startPreview();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        int cameraNums = Camera.getNumberOfCameras();
        Log.e(CAMERA_CONTROLL, cameraNums + "");
        try {
            camera = Camera.open(cameraNums - 1);
        } catch (Exception e) {
            Log.e(CAMERA_CONTROLL, e.getMessage());
        }
        try {
            camera.setPreviewDisplay(holder);
            Camera.Parameters parameters = camera.getParameters();
            if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
                parameters.set("orientation", "portrait");
                camera.setDisplayOrientation(90);
                parameters.setRotation(90);
            }
            List<String> colorEffects = parameters.getSupportedColorEffects();
            Iterator<String> cei = colorEffects.iterator();
            while (cei.hasNext()) {
                String currentEffect = cei.next();
                if (currentEffect.equals(Camera.Parameters.EFFECT_SOLARIZE)) {
                    parameters
                            .setColorEffect(Camera.Parameters.EFFECT_SOLARIZE);
                    break;
                }
            }
            camera.setParameters(parameters);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            camera.release();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        camera.stopPreview();
        camera.release();
    }

}
