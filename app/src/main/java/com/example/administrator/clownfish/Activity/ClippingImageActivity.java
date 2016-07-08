package com.example.administrator.clownfish.Activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.clownfish.R;
import com.example.administrator.clownfish.tool.AlbumIntentUtil;
import com.example.administrator.clownfish.tool.PermissionUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.Locale;

/*照片的裁取（从拍照的照片裁取图片和从相册裁取图片）*/

public class ClippingImageActivity extends AppCompatActivity implements View.OnClickListener{
    /** Called when the activity is first created. */
    private Button selectImageBtn;
    private ImageView imageView;
   /* String path="http://www.55115588.cc/imgall/o53xoltcpi2tkltdn5wq/uploads/allimg/150213/139-150213155047.jpg";

    private String fileName;
    private AlertDialog dialog;
    private int crop = 180;*/
    private static final int CHOOSE_BIG_PICTURE=0x0088;
    private static final int REQUEST_CODE_PERMISSION_CAMERA = 0x0099;
    private static final String DIRECTORY_TYPE = Environment.DIRECTORY_PICTURES;
    private static final int RESULT_CAMERA_ONLY = 100;
    private static final int RESULT_CAMERA_CROP_PATH_RESULT = 301;
    private ImageView mImage;
    private Button takePhoto;
    private Uri imageUri;
    private Uri imageCropUri;
    private Uri cameraOutPutUri = null;
    private Uri takePhotoCropUri;
    private static final String CAMERA_FILE_NAME = "camera_pic.jpg";
    private static final String CROP_FILE_NAME = "crop_pic.jpg";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clipping_image);
        selectImageBtn = (Button) findViewById(R.id.selectImageBtn);
        takePhoto=(Button)findViewById(R.id.takePhoto);
        imageView = (ImageView) findViewById(R.id.imageView);
        selectImageBtn.setOnClickListener(this);
        takePhoto.setOnClickListener(this);
        String path = getSDCardPath();
        File file = new File(path + "/temp.jpg");
        imageUri = Uri.fromFile(file);
        File cropFile = new File(getSDCardPath() + "/temp_crop.jpg");
        imageCropUri = Uri.fromFile(cropFile);
        File takePhotoFile=new File(path+"/take_photo_crop.jpg");
        takePhotoCropUri=Uri.fromFile(takePhotoFile);
        checkCameraPermission();
    }

    /**
     * 得到本地或者网络上的bitmap url - 网络或者本地图片的绝对路径,比如:
     *
     * A.网络路径: url="http://blog.foreverlove.us/girl2.png" ;
     *
     * B.本地路径:url="file://mnt/sdcard/photo/image.png";
     *
     * C.支持的图片格式 ,png, jpg,bmp,gif等等
     *
     * @param
     * @return
     */
   /* public static Bitmap GetLocalOrNetBitmap(String url)
    {
        Bitmap bitmap = null;
        InputStream in = null;
        BufferedOutputStream out = null;
        try
        {
            in = new BufferedInputStream(new URL(url).openStream(), 1024);
            final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
            out = new BufferedOutputStream(dataStream, 1024);
            copy(in, out);
            out.flush();
            byte[] data = dataStream.toByteArray();
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            data = null;
            return bitmap;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private static void copy(InputStream in, OutputStream out)
            throws IOException {
        byte[] b = new byte[1024];
        int read;
        while ((read = in.read(b)) != -1) {
            out.write(b, 0, read);
        }
    }*/

    private void takeCameraOnly() {
        Intent intent = null;
        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//action is capture
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, RESULT_CAMERA_ONLY);
    }

    private void takePhoto(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 2);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 600);
        intent.putExtra("outputY", 300);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        startActivityForResult(intent, CHOOSE_BIG_PICTURE);
    }

    public void cropImg(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 700);
        intent.putExtra("outputY", 700);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageCropUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, RESULT_CAMERA_CROP_PATH_RESULT);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkCameraPermission() {
        if (PermissionUtil.checkPermission(ClippingImageActivity.this, Manifest.permission.CAMERA)) {
           /* cameraOutPutUri = Uri
                    .fromFile(Environment.getExternalStoragePublicDirectory(DIRECTORY_TYPE))
                    .buildUpon()
                    .appendPath(System.currentTimeMillis() + "_" + CAMERA_FILE_NAME)
                    .build();
            AlbumIntentUtil.startCameraActivityForResult(ClippingImageActivity.this, cameraOutPutUri);*/
        } else {
            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    REQUEST_CODE_PERMISSION_CAMERA);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION_CAMERA:
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    PermissionUtil.showPermissionsDescDialog(this, "请在设置-应用-小泰乐活-权限中开启相机权限，以正常使用拍照，二维码扫描等功能");
                } else {
                    // Permission GRANTED
                  /*  cameraOutPutUri = Uri
                            .fromFile(Environment.getExternalStoragePublicDirectory(DIRECTORY_TYPE))
                            .buildUpon()
                            .appendPath(System.currentTimeMillis() + "_" + CAMERA_FILE_NAME)
                            .build();
                    AlbumIntentUtil.startCameraActivityForResult(ClippingImageActivity.this, cameraOutPutUri);*/
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK)
            return;
        switch (requestCode) {
            case RESULT_CAMERA_ONLY: {
                cropImg(imageUri);
            }
            break;
            case CHOOSE_BIG_PICTURE:{
                cropImg(takePhotoCropUri);
            }
            case RESULT_CAMERA_CROP_PATH_RESULT: {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageCropUri));
                        imageView.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            break;
        }
    }

    public static String getSDCardPath() {
        String cmd = "cat /proc/mounts";
        Runtime run = Runtime.getRuntime();// 返回与当前 Java 应用程序相关的运行时对象
        try {
            Process p = run.exec(cmd);// 启动另一个进程来执行命令
            BufferedInputStream in = new BufferedInputStream(p.getInputStream());
            BufferedReader inBr = new BufferedReader(new InputStreamReader(in));

            String lineStr;
            while ((lineStr = inBr.readLine()) != null) {
                // 获得命令执行后在控制台的输出信息
                if (lineStr.contains("sdcard")
                        && lineStr.contains(".android_secure")) {
                    String[] strArray = lineStr.split(" ");
                    if (strArray != null && strArray.length >= 5) {
                        String result = strArray[1].replace("/.android_secure",
                                "");
                        return result;
                    }
                }
                // 检查命令是否执行失败。
                if (p.waitFor() != 0 && p.exitValue() == 1) {
                    // p.exitValue()==0表示正常结束，1：非正常结束
                }
            }
            inBr.close();
            in.close();
        } catch (Exception e) {
            return Environment.getExternalStorageDirectory().getPath();
        }

        return Environment.getExternalStorageDirectory().getPath();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.selectImageBtn:
                takeCameraOnly();
                break;
            case R.id.takePhoto:
                takePhoto();
                break;
            default:
                break;
        }
    }
}
