package com.example.administrator.clownfish.Activity;

import android.content.Intent;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.clownfish.Fragment.HomePageFragment;
import com.example.administrator.clownfish.R;
import com.jack.qrcode.QRCodeReaderView;

public class ScanCodeActivity extends BaseActivity {
    public static final int QR_SCAN_REQUEST_CODE = 0x0002;
    private QRCodeReaderView qrCodeReaderView;
    private TextView qrTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_scan_code);
        qrTitle=(TextView)findViewById(R.id.qrTitle);
        qrCodeReaderView = (QRCodeReaderView) findViewById(R.id.qrCodeReaderView);
        qrCodeReaderView.setOnQRCodeReadListener(new QRCodeReaderView.OnQRCodeReadListener() {
            @Override
            public void onQRCodeRead(String s, PointF[] pointFs) {
                qrTitle.setText(s);
            }

            @Override
            public void cameraNotFound() {

            }

            @Override
            public void QRCodeNotFoundOnCamImage() {

            }
        });

    }


    public static void startForScanResult(HomePageFragment fragment) {
        fragment.startActivityForResult(new Intent(fragment.getActivity(),ScanCodeActivity.class), QR_SCAN_REQUEST_CODE);
    }

    @Override
    protected void loadData() {

    }
}
