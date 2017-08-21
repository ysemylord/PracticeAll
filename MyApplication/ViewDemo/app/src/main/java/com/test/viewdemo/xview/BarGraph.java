package com.test.viewdemo.xview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created  on 2017/8/21.
 *
 * @author xyb
 */

public class BarGraph extends View {
    int origin_x, origin_y;
    int x_width = 700, y_height = 700;
    int bar_with = 20, bar_gap = 60;
    int[] bar_height_arrs = new int[]{100, 500, 400, 600, 300};
    String[] bar_name_arrs = new String[]{"java", "oc", "c++", "swift", "kotlin"};

    public BarGraph(Context context) {
        super(context);
    }

    public BarGraph(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BarGraph(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint pointPaint=new Paint();
        pointPaint.setStrokeWidth(5);
        pointPaint.setColor(Color.RED);

        canvas.drawColor(Color.GRAY);

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        origin_x = 50;
        origin_y = getHeight() - 50;
        paint.setStrokeWidth(3);
        canvas.drawLine(origin_x, origin_y, origin_x + x_width, origin_y, paint);//横轴
        canvas.drawLine(origin_x, origin_y, origin_x, origin_y - y_height, paint);//竖轴


        int bar_x = origin_x;
        paint.setStrokeWidth(30);
        paint.setColor(Color.GREEN);

        Paint.FontMetrics fontMetrics= paint.getFontMetrics();
        paint.setTextSize(20);

        for (int i = 0; i < bar_height_arrs.length; i++) {

            int barHeight=bar_height_arrs[i];
            bar_x = bar_x + bar_gap + bar_with;
            canvas.drawLine(bar_x, origin_y, bar_x, origin_y - barHeight, paint);

            canvas.drawPoint(bar_x,origin_y,pointPaint);

            String name=bar_name_arrs[i];
            int name_width = (int) paint.measureText(name);
            int startX = bar_x ;
            Rect textBounds = new Rect();
            paint.getTextBounds(name, 0, name.length(), textBounds);
            canvas.drawText(name, startX-name_width/2,origin_y+(fontMetrics.bottom-fontMetrics.top+fontMetrics.leading ), paint);

            canvas.drawPoint(startX,origin_y + textBounds.height()+10,pointPaint);
        }
    }
}
