package com.langdunzx.www.zanbei.view;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import com.langdunzx.www.zanbei.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.view.ViewPager.PageTransformer;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

/**
 *
 * 轮播图View
 */
@SuppressLint("HandlerLeak")
public class FlashView extends FrameLayout implements OnGestureListener {

	private ImageHandler mhandler = new ImageHandler(new WeakReference<FlashView>(this));
	private List<String> imageUris;
	private List<ImageView> imageViewsList;
	private List<ImageView> dotViewsList;// 点的集合
	private LinearLayout mLinearLayout;// 点的线性布局
	private ViewPager mViewPager;
	private FlashViewListener mFlashViewListener;// 向外提供接口
	private int dot1 = R.drawable.circle_show;// 选中点的 当前图片
	private int dot2 = R.drawable.circle_hide;// 没选中点
	private int dotMargin = 25;// 点之间的间隙 单位 是px
	private MyPagerAdapter adapter;
	private LinearLayout.LayoutParams lp;
	private GestureDetector mGestureDetector;// 增加长按停止轮播的手势检测
	private DisplayImageOptions options;
//	private int s;

	public FlashView(Context context) {
		this(context, null);

	}

	public FlashView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FlashView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initUI(context);
		if (!(imageUris.size() <= 0)) {
			setImageUris(imageUris);
		}
		options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).resetViewBeforeLoading()
				.build();

	}

	private void initUI(Context context) {
		imageViewsList = new ArrayList<ImageView>();
		dotViewsList = new ArrayList<ImageView>();
		imageUris = new ArrayList<String>();
		mGestureDetector = new GestureDetector(context, this);
		LayoutInflater.from(context).inflate(R.layout.layout_slideshow, this, true);

		mLinearLayout = (LinearLayout) findViewById(R.id.linearlayout);
		mViewPager = (ViewPager) findViewById(R.id.viewPager);

	}

	public void setImageUris(List<String> imageuris) {
		//s = 4;
		imageUris.clear();
		imageViewsList.clear();
		dotViewsList.clear();
		mLinearLayout.removeAllViews();
		if (imageuris.size() <= 0) {// 如果得到的图片张数为0，则增加一张默认的图片
			imageUris.add("drawable://" + R.drawable.top_img_loadings);
		} else {
			for (int i = 0; i < imageuris.size() ; i++) {
				imageUris.add(imageuris.get(i % imageuris.size()));
			}
		}
		if (lp == null) {
			lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			lp.setMargins(dotMargin, 0, 0, 0);
		}
		for (int i = 0; i < imageUris.size(); i++) {
			ImageView imageView = new ImageView(getContext());
			imageView.setScaleType(ScaleType.FIT_XY);// X和Y方向都填满

			ImageLoader.getInstance().displayImage(imageUris.get(i), imageView, options);
			imageViewsList.add(imageView);
		}
		for (int i = 0; i < imageUris.size() ; i++) {

			ImageView viewDot = new ImageView(getContext());
			if (i == 0) {
				viewDot.setBackgroundResource(dot1);
			} else {
				viewDot.setBackgroundResource(dot2);
			}
			viewDot.setLayoutParams(lp);
			dotViewsList.add(viewDot);
			mLinearLayout.addView(viewDot);
		}
		mViewPager.setFocusable(true);
		if (adapter == null) {
			adapter = new MyPagerAdapter();
			mViewPager.setAdapter(adapter);

			mViewPager.setOnPageChangeListener(new MyPageChangeListener());
			if (imageUris.size() <= 1) {

			} else {
				mViewPager.setCurrentItem(100 * imageViewsList.size());
				mhandler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);

			}
		}
	}

	/**
	 * 切换轮播小点的显示
	 *
	 * @param selectItems
	 */
	private void setImageBackground(int selectItems) {
		for (int i = 0; i < dotViewsList.size(); i++) {
			if (i == selectItems % dotViewsList.size()) {
				dotViewsList.get(i).setBackgroundResource(dot1);
			} else {
				dotViewsList.get(i).setBackgroundResource(dot2);
			}
		}
	}

	/**
	 *
	 * 数据适配器
	 *
	 */
	private class MyPagerAdapter extends PagerAdapter {
		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewGroup) container).removeView((View) object);
		}

		@Override
		public Object instantiateItem(View container, int position) {
			System.out.println("position::" + position);
			position = position % (imageViewsList.size());

			if (position < 0) {
				position = position + imageUris.size();

			}
			final int pos = position;
			View view;


			ImageView imageView = new ImageView(getContext());
			imageView.setScaleType(ScaleType.FIT_XY);// X和Y方向都填满
			DisplayImageOptions options = new
					DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(
					true).build();
			ImageLoader.getInstance().displayImage(imageUris.get(position),
					imageView, options);
			view = imageView;

			//	view = imageViewsList.get(position);
			// ImageLoader.getInstance().displayImage(imageUris.get(position),
			// imageViewsList.get(position));

			view.setTag(position);
			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					mFlashViewListener.onClick(pos);
				}
			});

			ViewGroup pager = (ViewGroup) container;
			pager.removeView(view);
			System.out.println("view::" + view);
			((ViewPager) container).addView(view);
			return view;
		}

		@Override
		public int getCount() {
			if (imageUris.size() <= 1) {
				return 1;
			} else {
				return Integer.MAX_VALUE;
			}

		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
	}

	private class MyPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {

			switch (arg0) {
				case ViewPager.SCROLL_STATE_DRAGGING:
					mhandler.sendEmptyMessage(ImageHandler.MSG_KEEP_SILENT);
					break;
				case ViewPager.SCROLL_STATE_IDLE:
					mhandler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
					break;
				default:
					break;
			}

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int pos) {
			// TODO Auto-generated method stub
			mhandler.sendMessage(Message.obtain(mhandler, ImageHandler.MSG_PAGE_CHANGED, pos, 0));
			setImageBackground(pos);

		}

	}

	@SuppressWarnings("unused")
	private void destoryBitmaps() {
		for (int i = 0; i < imageViewsList.size(); i++) {
			ImageView imageView = imageViewsList.get(i);
			Drawable drawable = imageView.getDrawable();
			if (drawable != null) {
				drawable.setCallback(null);
			}
		}
	}

	public void setPageTransformer(PageTransformer rotateTransformer) {
		// TODO Auto-generated method stub
		mViewPager.setPageTransformer(false, rotateTransformer);
	}

	private static class ImageHandler extends Handler {

		protected static final int MSG_UPDATE_IMAGE = 1;

		protected static final int MSG_KEEP_SILENT = 2;

		protected static final int MSG_BREAK_SILENT = 3;

		protected static final int MSG_PAGE_CHANGED = 4;

		protected static final long MSG_DELAY = 4000;

		private WeakReference<FlashView> weakReference;
		private int currentItem = 0;

		protected ImageHandler(WeakReference<FlashView> wk) {
			weakReference = wk;

		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			FlashView activity = weakReference.get();
			if (activity == null) {
				return;
			}
			if (activity.mhandler.hasMessages(MSG_UPDATE_IMAGE)) {
				if (currentItem > 0) // 这里必须加入currentItem>0的判断，否则不能完美的自动轮播
				{
					activity.mhandler.removeMessages(MSG_UPDATE_IMAGE);
				}
			}
			switch (msg.what) {
				case MSG_UPDATE_IMAGE:

					currentItem++;
					activity.mViewPager.setCurrentItem(currentItem);
					activity.mhandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
					break;
				case MSG_KEEP_SILENT:
					break;
				case MSG_BREAK_SILENT:
					activity.mhandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
					break;
				case MSG_PAGE_CHANGED:
					currentItem = msg.arg1;
					break;
				default:
					break;
			}
		}

	}

	/**
	 * 设置点击图片点击监听
	 *
	 * @param mListener
	 */
	public void setOnFlashViewListener(FlashViewListener mListener) {
		mFlashViewListener = mListener;
	}

	/**
	 * 设置点的位置 可以设置居左 居右 居中
	 *
	 * @param gravity
	 */
	public void setDogGravity(int gravity) {
		mLinearLayout.setGravity(gravity);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_CANCEL || ev.getAction() == MotionEvent.ACTION_UP) {
			mhandler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, 500);
		}
		mGestureDetector.onTouchEvent(ev);
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		mhandler.sendEmptyMessage(ImageHandler.MSG_KEEP_SILENT);
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		// TODO Auto-generated method stub
		return false;
	}
}
