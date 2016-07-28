package com.langdunzx.www.zanbei.view;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

public class CompressImage {
	private Bitmap image;

	/**
	 * @param image
	 */
	public CompressImage(Bitmap image) {
		this.image = image;
	}

	public Bitmap compressImage(Bitmap image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 70;
		while (baos.toByteArray().length / 1024 > 400) { // 循环判断如果压缩后图片是否大于400kb,大于继续压缩
			baos.reset();// 重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
			options -= 10;// 每次都减少10
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
		return bitmap;
	}

	// 获取bitmap大小
	@SuppressLint("NewApi")
	public int getBitmapSize(Bitmap bitmap) {
		int size;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { // API 19

			size = bitmap.getAllocationByteCount() / 1024;
			return size;
		}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {// API
			size = bitmap.getByteCount() / 1024;
			return size; // 12

		}
		int imgsize = bitmap.getRowBytes() * bitmap.getHeight();
		size = imgsize / 1024;
		return size; // earlier version
	}
}
