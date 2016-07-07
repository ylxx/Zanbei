package com.langdunzx.www.zanbei.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;

import com.langdunzx.www.zanbei.R;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

/**
 * 
 * @author dc
 */
public class ImageDownLoader extends BaseImageDownloader {

	public ImageDownLoader(Context context) {
		super(context);

	}

	protected InputStream getStreamFromOtherSource(String imageUri, Object extra) {
		BitmapDrawable drawable = (BitmapDrawable) context.getResources()
				.getDrawable(R.drawable.lunbotu);
		Bitmap bitmap = drawable.getBitmap();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.PNG, 0, os);
		return new ByteArrayInputStream(os.toByteArray());
	}

}
