package com.test.viewdemo.xview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created  on 2017/8/21.
 *  建设一个需求，
 *  将小明各科成绩(没科成绩为0-100分)，以条形图的形式展示出来
 * @author xyb
 */

public class BarGraph extends View {
    private final Paint mainPaint;
    int barColor = Color.BLUE;//柱状的颜色
    int bgColor = Color.parseColor("#afafafaf");
    int originPaddingLeft = 50;//原点距画布左边的距离
    int originPaddingBottom = 50;//原点距画布底边的距离
    int origin_x, origin_y;
    int x_width = 700;//x轴宽度
    int y_height = 700;//y轴高度
    int bar_with = 20;//条形宽度
    int bar_gap = 60;//两个条形之间的间隙


    private Paint pointPaint;
    List<BarModel> barModelList;

    public BarGraph(Context context) {
        super(context);
    }

    public BarGraph(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BarGraph(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {

        barModelList = new ArrayList<>();
        pointPaint = new Paint();
        pointPaint.setStrokeWidth(5);
        pointPaint.setColor(Color.RED);

        mainPaint = new Paint();
        BarModel barModel1 = new BarModel(80, "数学");
        BarModel barModel2 = new BarModel(40, "英语");
        BarModel barModel3 = new BarModel(70, "语文");
        BarModel barModel4 = new BarModel(60, "生物");
        BarModel barModel5 = new BarModel(50, "物理");
        barModelList.add(barModel1);
        barModelList.add(barModel2);
        barModelList.add(barModel3);
        barModelList.add(barModel4);
        barModelList.add(barModel5);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawColor(bgColor);//设置整个canvase的颜色，

        drawXY(canvas);


        mainPaint.setStrokeWidth(30);
        mainPaint.setTextSize(20);
        Paint.FontMetrics fontMetrics = mainPaint.getFontMetrics();

        int bar_x = origin_x;
        for (int i = 0; i < barModelList.size(); i++) {

            BarModel barModel = barModelList.get(i);

            //绘制条形
            mainPaint.setColor(barColor);
            int barHeight = (int) (barModel.barHeight*1f/100*configYHeight());
            bar_x = bar_x + bar_gap + bar_with;
            canvas.drawLine(bar_x, origin_y, bar_x, origin_y - barHeight, mainPaint);

            canvas.drawPoint(bar_x, origin_y, pointPaint);//把起始点画出来，作为观察绘制的辅助点

            //绘制y轴上的文字
            String heightText =barModel.barHeight+"";
            canvas.drawText(heightText,origin_x-mainPaint.measureText(heightText),origin_y-barHeight,mainPaint);

            //绘制x轴上的文字
            mainPaint.setColor(Color.GRAY);
            String name = barModel.barText;
            int name_width = (int) mainPaint.measureText(name);
            int startX = bar_x;
            int textX = startX - name_width / 2;
            float textY = origin_y + (fontMetrics.bottom - fontMetrics.top + fontMetrics.leading);
            canvas.drawText(name, textX, textY, mainPaint);

            canvas.drawPoint(textX, textY, pointPaint);
        }
    }

    /**
     * 绘制x轴和y轴
     *
     * @param canvas
     * @return
     */
    @NonNull
    private void drawXY(Canvas canvas) {

        mainPaint.setColor(Color.WHITE);
        origin_x = originPaddingLeft;
        origin_y = getHeight() - originPaddingBottom;
        mainPaint.setStrokeWidth(3);
        x_width = configXWidth();
        y_height = configYHeight();
        canvas.drawLine(origin_x, origin_y, origin_x + x_width, origin_y, mainPaint);//横轴
        canvas.drawLine(origin_x, origin_y, origin_x, origin_y - y_height, mainPaint);//竖轴

    }

    /**
     * 设置y轴高度
     *
     * @return
     */
    private int configYHeight() {
        return getHeight() - 80;
    }

    /**
     * 设置横轴宽度
     *
     * @return
     */
    private int configXWidth() {
        return getWidth() - 80;
    }

    public static class BarModel {
        public BarModel(int barHeight, String barText) {
            this.barHeight = barHeight;
            this.barText = barText;
        }

        public float barHeight;
        public String barText;
    }
}
