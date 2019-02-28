package com.test.viewdemo.bitmapDemo;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.test.viewdemo.R;

import java.util.ArrayList;
import java.util.List;

public class BitmapDemoActivity extends AppCompatActivity {
    private static final String TAG = "BitmapDemoActivity";
    List<Bitmap> arrays=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_demo);
       /* BitmapFactory.Options options=new BitmapFactory.Options();
        //inDensity/inTargetDensity=1:1
        options.inDensity=400;
        options.inTargetDensity=400;
        options.inPreferredConfig= Bitmap.Config.ARGB_8888;
        options.inMutable=true;
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.bitmaptest,options);
        Canvas canvas=new Canvas(bitmap1);
        canvas.drawText("223333",100,100,new Paint());
        File file=new File(Environment.getExternalStorageDirectory()+File.separator+"new.png");
        try {
            OutputStream outputStream=new FileOutputStream(file);
            bitmap1.compress(Bitmap.CompressFormat.PNG,100,outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageView imageView=findViewById(R.id.image_view1);
        imageView.setImageBitmap(bitmap1);*/
      /*  int bitmapSize=bitmap.getByteCount()/1024;
        Log.i(TAG, "Bitmap bitmapSize is "+bitmapSize);
        int targetSize=32;
        if(bitmapSize>targetSize){
            double scale=  Math.ceil(bitmapSize*1f/targetSize);
            Log.i(TAG, "the scale is "+scale);
            scale=  Math.sqrt(scale);
            Log.i(TAG, "the sqrt scale is "+scale);
            Bitmap smallBitmap= Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth()/scale),(int)(bitmap.getHeight()/scale),true);
            Log.i(TAG, "THe size of small Bitmap  is "+smallBitmap.getByteCount()/1024);
        }*/
      int count=0;
      while(true){
          //returnBGBitmapWithId(this,R.drawable.lib_base_bg);
          if(count==20){
              return;
          }
          count++;
      }

    }

    public  Drawable returnBGBitmapWithId2(Context context, int BGResId) {
        Resources res = context.getResources();
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        //根据资源id获取图片
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inMutable=true;
        Bitmap bitmap = BitmapFactory.decodeResource(res, BGResId,options);

        //计算高宽比
        Canvas canvas=new Canvas(bitmap);


        Matrix matrix=new Matrix();
        matrix.setScale(2, 2);
        canvas.setMatrix(matrix);
       // arrays.add(bitmap);
        return new BitmapDrawable(res, bitmap);
    }


    public  Drawable returnBGBitmapWithId1(Context context, int BGResId) {
        Resources res = context.getResources();

        BitmapFactory.Options options=new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeResource(res, BGResId,options);

        //计算高宽比

        Matrix matrix=new Matrix();
        matrix.setScale(2, 2);
        Bitmap newBitmap=Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
        //arrays.add(newBitmap);
        //arrays.add(bitmap);
        return new BitmapDrawable(res, bitmap);
    }

}
