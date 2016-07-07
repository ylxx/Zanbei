package com.langdunzx.www.zanbei.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.langdunzx.www.zanbei.R;

import java.util.List;

public class ViewPagerIndictor extends LinearLayout {

	private Paint mPaint;

	private Path mPath;

	private int mTriangleWidth;

	private int mTriangleHeight;

	private static final float RADIO_TRIANGLE_WIDTH = 7/9F; 
	//初始化偏移位置
	private int mInitTranslationX;
	//移动时的偏移
	private int mTranslationX;
	//tab数量
	private int mTabVisibleCount;
	//默认tab数量
	private static final int COUNT_DEFAULT_TAB = 3;

	private List<String> mTitles;

	private static final int COLOR_TEXT_NORMAL = 0X77009999;

	private static final int COLOR_TEXT_HIGHLIGHT = 0XFF009999;

	private ViewPager mViewPager;
	/**
	 * viewPager滚动监听接口
	 * @author dc
	 *
	 */
	public interface PageOnChangeListener{

		public void onPageScrolled(int position, float positionOffect, int positionOffectPixels);

		public void onPageSelected(int position);

		public void onPageScrolledStateChanged(int state);
	}

	public PageOnChangeListener mListener;

	public void setOnPageChangeListener(PageOnChangeListener listener){
		this.mListener = listener;
	}

	public ViewPagerIndictor(Context context) {
		this(context,null);
	}

	public ViewPagerIndictor(Context context, AttributeSet attrs) { 
		super(context, attrs);

		//获取自定义属性可见tab的数量
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.ViewPagerIndictor);
		mTabVisibleCount = a.getInt(R.styleable.ViewPagerIndictor_visible_tab_count, COUNT_DEFAULT_TAB);
		if(mTabVisibleCount < 0){
			mTabVisibleCount = COUNT_DEFAULT_TAB;
		}

		a.recycle();

		//初始化画笔
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.parseColor("#ff009999"));
		mPaint.setStyle(Style.FILL_AND_STROKE);
		//三角形角度变圆润
		mPaint.setPathEffect(new CornerPathEffect(3));
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {

		canvas.save();

		canvas.translate(mInitTranslationX+mTranslationX, getHeight()+4);
		canvas.drawPath(mPath, mPaint);

		canvas.restore();

		super.dispatchDraw(canvas);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		//三角形宽度
		mTriangleWidth = (int) (w/mTabVisibleCount*RADIO_TRIANGLE_WIDTH); 
		mInitTranslationX = w/mTabVisibleCount/2-mTriangleWidth/2;
		initTriangle();

	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

		int cCount = getChildCount();
		if(cCount == 0)return;

		for(int i=0;i<cCount;i++){
			View view = getChildAt(i);
			LayoutParams lp = (LayoutParams) view.getLayoutParams();
			lp.weight = 0;
			lp.width = getScreenWidth()/mTabVisibleCount;
			view.setLayoutParams(lp);
		}
		
		setItemClickEvent();

	}
	/**
	 * 获得屏幕宽度
	 * @return
	 */
	private int getScreenWidth() {
		// TODO Auto-generated method stub
		WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}

	/**
	 * 初始化三角形
	 */
	private void initTriangle() {
		// TODO Auto-generated method stub

//		mTriangleHeight = mTriangleWidth/2;
		mPath = new Path();
		mPath.moveTo(mTranslationX, 0);
		mPath.lineTo(mTriangleWidth, 0);
		mPath.lineTo(mTriangleWidth,-mTriangleWidth/40);
		mPath.lineTo(mTranslationX,-mTriangleWidth/40);
		mPath.close();
	}

	/**
	 * 指示器跟随手指进行移动
	 * @param position
	 * @param offset
	 */
	public void scroll(int position, float offset) {

		int tabWidth = getWidth()/mTabVisibleCount;
		mTranslationX = (int) (tabWidth*(offset + position)); 
		//容器移动，在tab处于移动至最后一个时
		if(position >= (mTabVisibleCount-2) && offset>0 && getChildCount()>mTabVisibleCount)
		{
			if(mTabVisibleCount != 1){
				this.scrollTo(
						(position-(mTabVisibleCount-2))*tabWidth + (int)(tabWidth*offset),
						0);
			}else{
				this.scroll(position*tabWidth + (int)(tabWidth*offset), 0);
			}

		}

		invalidate();
	}


	public void setTabItemTitles(List<String> titles)
	{
		if(titles != null && titles.size() > 0)
		{
			this.removeAllViews();
			mTitles = titles;
			for(String title:mTitles)
			{
				addView(generateTextView(title));
			}
			
			setItemClickEvent();
			
		}
	}
	/**
	 * 设置可见Tab数量 
	 * @param count
	 */
	public void setVisiableTabCount(int count){
		mTabVisibleCount = count;
	}

	/**
	 * 根据title创建tab
	 * @param title
	 * @return
	 */
	private View generateTextView(String title) {

		TextView tv = new TextView(getContext());
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		lp.width = getScreenWidth()/mTabVisibleCount;
		tv.setText(title);
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
		tv.setTextColor(COLOR_TEXT_NORMAL);
		tv.setLayoutParams(lp);
		return tv;
	}

	/**
	 * 设置关联的ViewPager
	 * @param viewPager
	 * @param pos
	 */
	public void setViewPager(ViewPager viewPager, int pos){

		mViewPager = viewPager;

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {

				if(mListener != null){
					mListener.onPageSelected(position);
				}
				highLightTextView(position);
			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPaxels) {
				scroll(position,positionOffset);

				if(mListener != null){
					mListener.onPageScrolled(positionOffsetPaxels, positionOffset, positionOffsetPaxels);
				}
			}

			@Override
			public void onPageScrollStateChanged(int state) {
				if(mListener != null){
					mListener.onPageScrolledStateChanged(state);
				}

			}
		});
		mViewPager.setCurrentItem(pos);

		highLightTextView(pos);
	}

	/**
	 * 重置Tab文本颜色
	 */
	private void resetTextViewColor(){

		for(int i=0;i<getChildCount();i++){
			View view = getChildAt(i);
			if(view instanceof TextView){
				((TextView)view).setTextColor(COLOR_TEXT_NORMAL);
			}
		}
	}

	/**
	 *设置tab的点击事件
	 */
	private void setItemClickEvent(){
		int cCount = getChildCount();
		for(int i=0;i<cCount;i++){
			final int j = i;
			View view = getChildAt(i);
			
			view.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					mViewPager.setCurrentItem(j);
				}
			});
		}
	}
	
	/**
	 * 高亮某个Tab的文本
	 * @param pos
	 */
	private void highLightTextView(int pos){

		resetTextViewColor();
		View view = getChildAt(pos);
		if(view instanceof TextView){
			((TextView)view).setTextColor(COLOR_TEXT_HIGHLIGHT);
		}
	}

}
