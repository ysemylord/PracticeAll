package com.test.viewdemo.xview.myprogressBar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.test.viewdemo.R;

@SuppressLint("DrawAllocation")
public class NumberInProgressView extends View {

	private int backgroundColor = 0xffe5e5e5;
	private int startProgressColor = 0xfff29310;
	private int endProgressColor = 0xfffd715a;
	private int indicateColor = 0xffef4f37;
	private int rectRadius = 0; //进度条四个角的角度px
	private int max = 100;
	private int barHeight=5;
	private int progress;
	private Paint backPaint;//进度条背景画笔
	private Paint progressPaint;//进读条进度画笔
	private Paint indicateTextPaint;//进度条指示器画笔
	private int maxTextWidth;


	public NumberInProgressView(Context context) {
		super(context);
	}

	public NumberInProgressView(Context context, AttributeSet attributeSet) {
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
		indicateTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		indicateTextPaint.setTextSize(48);
		indicateTextPaint.setColor(indicateColor);
		indicateTextPaint.setStyle(Paint.Style.FILL);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		String str = "100%";
		Rect bounds = new Rect();
		indicateTextPaint.getTextBounds(str,0,str.length(), bounds);
		maxTextWidth = bounds.width();
		setMeasuredDimension(getMeasuredWidth(),Math.max(bounds.height(),barHeight));
	}

	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);
		//指示器为一个圆环

		int barWidth = getWidth();//进度条宽度
		int height = getHeight();



		int allBarleft = 0 ;
		int allBarRight = allBarleft + barWidth;
		int allBarTop = height / 2 - barHeight / 2;
		int allBarBottom = height / 2 + barHeight / 2;
		float progressX = allBarleft + barWidth * getScale();//当前进度对应的横坐标



		String progressText=progress+"%";
		Rect bounds = new Rect();
		indicateTextPaint.getTextBounds(progressText,0,progressText.length(), bounds);
		float textWidth =indicateTextPaint.measureText(progressText);
		float textHeight =bounds.height();


		float bgBarLeft = progressX + textWidth;
		RectF backRectF = new RectF(bgBarLeft, allBarTop, allBarRight, allBarBottom);
		canvas.drawRoundRect(backRectF, rectRadius, rectRadius, backPaint);




		RectF progressRectF;
		float textX;
		if(barWidth-progressX< textWidth) {//剩余的宽度比文字宽度小
			 progressRectF = new RectF(allBarleft, allBarTop, barWidth-textWidth, allBarBottom);
			 textX=barWidth- textWidth;
		}else {
			 progressRectF = new RectF(allBarleft, allBarTop, progressX, allBarBottom);
			 textX = progressX;
		}
		LinearGradient lGradient = new LinearGradient(progressRectF.left,progressRectF.top,progressRectF.right,progressRectF.bottom,
				startProgressColor, endProgressColor, Shader.TileMode.MIRROR);
		progressPaint.setShader(lGradient);

		canvas.drawRoundRect(progressRectF, rectRadius, rectRadius, progressPaint);
		canvas.drawText(progressText,textX,height/2+textHeight/2, indicateTextPaint);
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
