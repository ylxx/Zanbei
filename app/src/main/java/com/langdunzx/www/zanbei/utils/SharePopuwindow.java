package com.langdunzx.www.zanbei.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.langdunzx.www.zanbei.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;


public class SharePopuwindow extends PopupWindow implements OnClickListener {

	private Context context;
	private String imgUrl;
	private String url;
	private String content;
	private String title;
	private View view;
	private UMImage image;

	/**
	 * 
	 * @param context
	 * @param Imgurl
	 * @param title
	 * @param content
	 * @param url
	 */
	public SharePopuwindow(Context context, UMImage image, String title,
			String content, String url) {
		this.context = context;
		this.content = content;
		this.image = image;
		this.title = title;
		this.url = url;
		this.context = context;

	}

	public void showSharePopu() {
		View view = LayoutInflater.from(context).inflate(R.layout.popu_share,
				null);
		LinearLayout share_circle = (LinearLayout) view
				.findViewById(R.id.share_circle);
		LinearLayout share_wechat = (LinearLayout) view
				.findViewById(R.id.share_wechat);
		LinearLayout share_qq = (LinearLayout) view.findViewById(R.id.share_qq);

		LinearLayout share_sina = (LinearLayout) view
				.findViewById(R.id.share_sina);
		LinearLayout share_close = (LinearLayout) view
				.findViewById(R.id.share_close);
		LinearLayout share_ll = (LinearLayout) view.findViewById(R.id.share_ll);

		share_circle.setOnClickListener(this);
		share_wechat.setOnClickListener(this);
		share_qq.setOnClickListener(this);

		share_close.setOnClickListener(this);
		share_sina.setOnClickListener(this);
		share_ll.setOnClickListener(this);
		// 添加布局
		this.setContentView(view);
		// 设置SharePopupWindow宽度
		this.setWidth(LayoutParams.FILL_PARENT);
		// 设置SharePopupWindow高度
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// 设置setFocusable可获取焦点
		this.setFocusable(true);
		// 设置setFocusable动画风格
		this.setAnimationStyle(R.style.popuShareAnimation);
		// 画背景
		/*
		 * ColorDrawable dw = new ColorDrawable(0x00000000); // 设置背景
		 * this.setBackgroundDrawable(dw);
		 */
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.share_close:
			this.dismiss();
			break;
		case R.id.share_ll:
			this.dismiss();
			break;
		case R.id.share_wechat:
			new ShareAction((Activity) context).setPlatform(SHARE_MEDIA.WEIXIN)
					.setCallback(uumShareListener).withTitle(title)
					.withText(content).withTargetUrl(url).withMedia(image)
					.share();
			this.dismiss();
			break;
		case R.id.share_circle:
			new ShareAction((Activity) context)
					.setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
					.setCallback(uumShareListener).withTitle(title)
					.withText(content).withTargetUrl(url).withMedia(image)
					.share();
			this.dismiss();
			break;
		case R.id.share_qq:
			new ShareAction((Activity) context).setPlatform(SHARE_MEDIA.QQ)
					.setCallback(uumShareListener).withText("hello umeng")
					.withMedia(image).withTitle("qqshare").withTargetUrl(url)
					.share();
			this.dismiss();
			break;

		case R.id.share_sina:
			new ShareAction((Activity) context).setPlatform(SHARE_MEDIA.SINA)
					.setCallback(uumShareListener).withTitle(title)
					.withText(content).withTargetUrl(url).withMedia(image)
					.share();
			this.dismiss();
			break;
		default:
			break;
		}
	}

	UMShareListener uumShareListener = new UMShareListener() {

		@Override
		public void onResult(SHARE_MEDIA arg0) {
			Toast.makeText(context, "分享成功", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onError(SHARE_MEDIA arg0, Throwable arg1) {
			Toast.makeText(context, "分享失败", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onCancel(SHARE_MEDIA arg0) {
			Toast.makeText(context, "取消分享", Toast.LENGTH_SHORT).show();
		}
	};
}
