package com.test.viewdemo.xview.myprogressBar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.test.viewdemo.R;

@SuppressLint("DrawAllocation")
public class IndicateProgressView extends View {
	
	private int backgroundColor = 0xfff5f5f5;
	private int startProgressColor = 0xfff29310;
	private int endProgressColor = 0xfffd715a;
	private int indicateColor = 0xffef4f37;
	private int rectRadius = 10; //进度条四个角的角度px
	private int max = 100;
	private int progress;
	private Paint backPaint;//进度条背景画笔
	private Paint progressPaint;//进读条进度画笔
	private Paint indicatePaint;//进度条指示器画笔

	public IndicateProgressView(Context context) {
		super(context);
	}
	
	public IndicateProgressView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		init(context, attributeSet);
	}
	
	private void init(Context context, AttributeSet attributeSet) {
		TypedArray taArray = context.obtainStyledAttributes(attributeSet, R.styleable.IndicateProgressView);
		if (taArray != null) {
			backgroundColor = taArray.getColor(R.styleable.IndicateProgressView_backgroundColor, backgroundColor);
			startProgressColor = taArray.getColor(R.styleable.IndicateProgressView_startProgressColor, startProgressColor);
			endProgressColor = taArray.getColor(R.styleable.IndicateProgressView_endProgressColor, endProgressColor);
			indicateColor = taArray.getColor(R.styleable.IndicateProgressView_indicateColor, indicateColor);
			taArray.recycle();
		}
		//初始化进度条背景画笔
		backPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		backPaint.setColor(backgroundColor);
		backPaint.setStyle(Paint.Style.FILL_AND_STROKE);

		//初始化进度条进度画笔
		progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		progressPaint.setStyle(Paint.Style.FILL);

		//初始化进度条指示器背景框画笔
		indicatePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		indicatePaint.setColor(indicateColor);
		indicatePaint.setStyle(Paint.Style.FILL);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);
		//指示器为一个圆环
		int radius=10;//指示器圆环内部半径
		int strokeWidth = 20;//指示器圆环宽度
		int barWidth = getWidth()-2*radius-2*strokeWidth;//进度条宽度
		int height = getHeight();


		//画背景
		int barleft = radius + strokeWidth;
		int barBackgroundRight = barleft + barWidth;

		RectF backRectF = new RectF(barleft, height * 2 / 5, barBackgroundRight, height * 3 / 5);
		backPaint.setColor(backgroundColor);
		canvas.drawRoundRect(backRectF, rectRadius, rectRadius, backPaint);

		//画进度
		float progressRight = barleft + barWidth * getScale();
		RectF progressRectF = new RectF(barleft, height * 2 / 5, progressRight, height * 3 / 5);
		LinearGradient lGradient = new LinearGradient(0, height * 2 / 5, barWidth * getScale(), height * 3 / 5,
				startProgressColor, endProgressColor, Shader.TileMode.MIRROR);
		progressPaint.setShader(lGradient);
		canvas.drawRoundRect(progressRectF, radius, radius, progressPaint);

		//画指示器边框

		float indicateCx = progressRight;

		indicatePaint.setColor(indicateColor);
		indicatePaint.setStrokeWidth(strokeWidth);
		indicatePaint.setStyle(Paint.Style.STROKE);

		canvas.drawCircle(indicateCx,height/2,radius, indicatePaint);
		
		//画指示器内部为白色
		indicatePaint.setColor(Color.WHITE);
		indicatePaint.setStyle(Paint.Style.FILL);
		canvas.drawCircle(indicateCx,height/2,radius, indicatePaint);

	}
	
	public void setBackgroundColor(int color) {
		this.backgroundColor = color;
		backPaint.setColor(backgroundColor);
		postInvalidate();
	}
	
	public void setStartProgressColor(int color) {
		this.startProgressColor = color;
//		progressPaint.setColor(startProgressColor);
		postInvalidate();
	}
	
	public void setEndProgressColor(int color) {
		this.endProgressColor = color;
		postInvalidate();
	}
	
	public void setIndicateColor(int color) {
		this.indicateColor = color;
		postInvalidate();
	}
	
	public void setMax(int max) {
		this.max = max;
	}
	
	public void setProgress(int progress) {
		this.progress = progress;
		postInvalidate();
	}
	
	private float getScale() {
		float scale;
		if (max == 0) {
			scale = 0;
		} else {
			scale = (float)progress / (float)max;
		}
		return scale;
	}
	

}
