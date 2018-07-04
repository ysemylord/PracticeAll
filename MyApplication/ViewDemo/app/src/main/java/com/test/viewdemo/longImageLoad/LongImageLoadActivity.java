package com.test.viewdemo.longImageLoad;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.test.viewdemo.R;

import java.io.IOException;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LongImageLoadActivity extends AppCompatActivity {

    @Bind(R.id.bird_imageview)
    ImageView mBirdImageview;
    @Bind(R.id.long_load_imageview)
    LongLoadImageView mLongLoadImageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_image_load);
        ButterKnife.bind(this);
        try {
            InputStream birdInputStream = getAssets().open("bird.jpg");

            BitmapFactory.Options tempOptions = new BitmapFactory.Options();
            tempOptions.inJustDecodeBounds = true;//只获取边界
            Bitmap justDecodeBitmap = BitmapFactory.decodeStream(birdInputStream, null, tempOptions);
            int width = tempOptions.outWidth;
            int height = tempOptions.outHeight;

            BitmapRegionDecoder bitmapRegionDecoder = BitmapRegionDecoder.newInstance(birdInputStream, false);
            BitmapFactory.Options options = new BitmapFactory.Options();

            int centerX = (width) / 2;
            int centerY = (height) / 2;
            //获取图片中间100*100的中间区域
            Bitmap bitmap = bitmapRegionDecoder.decodeRegion(new Rect(centerX - 100, centerY - 100, centerX + 100, centerY + 100), options);
            mBirdImageview.setImageBitmap(bitmap);



        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream birdInputStream = null;
        try {
            birdInputStream = getAssets().open("world.jpg");
            mLongLoadImageview.setInputStream(birdInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
