package com.example.xuyabo.scatteredpractice;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

public class BitmapFactoryPracticeActivity extends AppCompatActivity {
    private ImageView image_view_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                0);
        image_view_1 = (ImageView) findViewById(R.id.image_view_1);
    }

    /**
     * 从图片资源加载Bitmap
     *
     * @param imageView
     * @param resId
     */
    private void loadBitmap(ImageView imageView, int resId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), resId, options);
        int simpleSize = getSimpleSize(imageView.getWidth(), imageView.getHeight(), options);
        options.inJustDecodeBounds = false;
        options.inSampleSize = simpleSize;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId, options);
        imageView.setImageBitmap(bitmap);
    }

    /**
     * 从图片资源加载Bitmap
     *
     * @param imageView
     * @param
     */
    private void loadBitmap(ImageView imageView, File file) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        int simpleSize = getSimpleSize(imageView.getWidth(), imageView.getHeight(), options);
        options.inJustDecodeBounds = false;
        options.inSampleSize = simpleSize;
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        imageView.setImageBitmap(bitmap);
    }



    /**
     * 根据需要的高宽和图片的高宽获取需要缩放的比例
     *
     * @param requestWidth
     * @param requestHeight
     * @param options
     * @return
     */
    private int getSimpleSize(int requestWidth, int requestHeight, BitmapFactory.Options options) {
        int simpleSize = 1;
        int bitmapWidth = options.outWidth;
        int bitmapHeight = options.outHeight;
        if (bitmapWidth > requestWidth || bitmapHeight > requestHeight) {
            int widthRatio = Math.round((float) bitmapWidth / (float) requestWidth);
            int heightRatio = Math.round((float) bitmapHeight / (float) requestHeight);//向上取整
            simpleSize = Math.max(widthRatio, heightRatio);
        }
        Log.i("getSimpleSize", "getSimpleSize: " + simpleSize);
        return simpleSize;
    }

    public void addResDrawable(View view) {
        loadBitmap(image_view_1, R.drawable.test);
    }

    public void loadSDImage(View view) {
        if (Environment.getExternalStorageState() .equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(Environment.getExternalStorageDirectory(), "/avatar.jpg");
            loadBitmap(image_view_1, file);
        }
    }
}
