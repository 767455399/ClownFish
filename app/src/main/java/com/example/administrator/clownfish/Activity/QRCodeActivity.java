package com.example.administrator.clownfish.Activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.clownfish.R;
import com.example.administrator.clownfish.view.CircleImageView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.squareup.picasso.Picasso;

import java.util.Hashtable;

public class QRCodeActivity extends BaseActivity {
    private CircleImageView HeadPortraitImageView;
    private TextView nameTextView;
    private TextView authenticationTextView;
    private ImageView qrCodeImageView;
    private String path="http://img0.imgtn.bdimg.com/it/u=56809917,2007050343&fm=21&gp=0.jpg";


    @Override
    protected void initView() {
        setContentView(R.layout.activity_qrcode);
        HeadPortraitImageView = (CircleImageView) findViewById(R.id.HeadPortraitImageView);
        nameTextView=(TextView)findViewById(R.id.nameTextView);
        authenticationTextView=(TextView)findViewById(R.id.authenticationTextView);
        qrCodeImageView=(ImageView)findViewById(R.id.qrCodeImageView);
        qrCodeImageView.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Bitmap bitmap= Create2DCode("王清",qrCodeImageView.getWidth())  ;
                    qrCodeImageView.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void loadData() {
        Picasso.with(QRCodeActivity.this).load(path).error(R.mipmap.ic_launcher).into(HeadPortraitImageView);

    }

     public static Bitmap Create2DCode(String text,int size) throws WriterException {
                //生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
                        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
                 hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
                 hints.put(EncodeHintType.MARGIN, 0);
                 BitMatrix matrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, size, size,hints);
                 int width = matrix.getWidth();
                 int height = matrix.getHeight();
                 //二维矩阵转为一维像素数组,也就是一直横着排了
                         int[] pixels = new int[width * height];
                 for (int y = 0; y < height; y++) {
                         for (int x = 0; x < width; x++) {
                                 if(matrix.get(x, y)){
                                         pixels[y * width + x] = 0xff000000;
                                     } else {
                                         pixels[y * width + x] = 0xffffffff;
                                     }

                                     }
                     }

                         Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                 //通过像素数组生成bitmap,具体参考api
                         bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
                 return bitmap;
             }


}
