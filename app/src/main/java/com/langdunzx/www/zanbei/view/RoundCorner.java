package com.langdunzx.www.zanbei.view;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.PorterDuff.Mode;
import android.widget.ImageView;

/**
 * @Description: 圆角图片
 * @param
 * @return
 */

public class RoundCorner {
	private CompressImage cpimage;
	private ImageView img;

	/**
	 * @param cpimage
	 * @param img
	 */
	public RoundCorner(CompressImage cpimage, ImageView img) {
		this.cpimage = cpimage;
		this.img = img;
	}

	public Bitmap toRoundCorner(Bitmap bitmap, ImageView img) {
		// 调用压缩图片
		cpimage = new CompressImage(bitmap);
		bitmap = cpimage.compressImage(bitmap);
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		int radius = ((bitmap.getWidth() < bitmap.getHeight()) ? bitmap
				.getWidth() : bitmap.getHeight()) / 2;
		canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
				radius, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, 0, 0, paint);
		img.setImageBitmap(output);
		return output;
	}

	public static Bitmap masterToRoundBitmap(Bitmap bitmap) {
		// 调用压缩图片
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		int radius = ((bitmap.getWidth() < bitmap.getHeight()) ? bitmap
				.getWidth() : bitmap.getHeight()) / 2;
		canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
				radius, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, 0, 0, paint);
		return output;
	}

	/**
	 * 转换图片成圆形
	 *
	 * @param bitmap
	 *            传入Bitmap对象
	 * @return
	 */
	public Bitmap toRoundBitmap(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float roundPx;
		float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
		if (width <= height) {
			roundPx = width / 2;
			top = 0;
			bottom = width;
			left = 0;
			right = width;
			height = width;
			dst_left = 0;
			dst_top = 0;
			dst_right = width;
			dst_bottom = width;
		} else {
			roundPx = height / 2;
			float clip = (width - height) / 2;
			left = clip;
			right = width - clip;
			top = 0;
			bottom = height;
			width = height;
			dst_left = 0;
			dst_top = 0;
			dst_right = height;
			dst_bottom = height;
		}

		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect src = new Rect((int) left, (int) top, (int) right,
				(int) bottom);
		final Rect dst = new Rect((int) dst_left, (int) dst_top,
				(int) dst_right, (int) dst_bottom);
		final RectF rectF = new RectF(dst);

		paint.setAntiAlias(true);

		canvas.drawARGB(122, 10, 159, 163);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, src, dst, paint);

		return output;
	}
}
