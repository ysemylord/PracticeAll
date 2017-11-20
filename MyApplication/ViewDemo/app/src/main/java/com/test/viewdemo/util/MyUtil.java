package com.test.viewdemo.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created  on 2017/11/20.
 *
 * @author xyb
 */

public class MyUtil {
    public static float dpToPx(Context context, int dp){
        Resources r =context.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
        return px;
    }
}
