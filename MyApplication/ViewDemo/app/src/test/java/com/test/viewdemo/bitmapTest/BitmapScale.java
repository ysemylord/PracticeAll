package com.test.viewdemo.bitmapTest;


import com.test.viewdemo.Log;

import org.junit.Test;

public class BitmapScale {
    @Test
    public void test() {
        for(int i=0;i<1400;i++) {
            testBitmapScale(i);
        }
    }

    private void testBitmapScale(int bitmapSize) {
        String TAG = "BitmapScale";
        //Log.i(TAG, "Bitmap bitmapSize is " + bitmapSize);
        int targetSize = 32;
        double finalSize;
        if (bitmapSize > targetSize) {
            double scale = Math.ceil(bitmapSize * 1f / targetSize);
            //Log.i(TAG, "the scale is " + scale);
            scale = Math.sqrt(scale);
            //Log.i(TAG, "the sqrt scale is " + scale);
            finalSize = bitmapSize * 1f / scale / scale;
        } else {
            finalSize = bitmapSize;
        }
        if(finalSize>32){
           Log.i(TAG, "error res "+finalSize);
        }
    }
}
