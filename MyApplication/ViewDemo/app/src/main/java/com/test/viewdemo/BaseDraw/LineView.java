package com.test.viewdemo.BaseDraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by xuyabo on 2017/12/14.
 */

public class LineView extends View{
    private Paint mPaint=new Paint();
    private Path mPath=new Path();
    private List<Point> points=new ArrayList<>();
    public LineView(Context context) {
        super(context);
    }

    public LineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.STROKE);
        Point point0=new Point(100,100);
        points.add(point0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < points.size(); i++) {
            Point point=points.get(i);
            if(i==0){
                mPath.moveTo(point.x,point.y);
                continue;
            }
            mPath.lineTo(point.x,point.y);
            Log.i("draw", "onDraw: point.x "+point.x);
            Log.i("draw", "onDraw: point.y "+point.y);
        }

        canvas.drawPath(mPath,mPaint);
        Point lastPoint=points.get(points.size()-1);
        int i1 = new Random().nextInt(50);
        int i2 = new Random().nextInt(50);
        points.add(new Point(lastPoint.x+i1,lastPoint.y+i2));
        if(lastPoint.x<getWidth()){
             invalidate();
        }
    }
}
