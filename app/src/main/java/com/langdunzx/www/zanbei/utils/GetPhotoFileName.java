package com.langdunzx.www.zanbei.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @Description: 根据拍照时间来给照片命名
 * @param
 * @return
 */
public class GetPhotoFileName {

	/**
	 *
	 */
	public GetPhotoFileName() {

	}

	public  static String getPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"'IMG'_yyyyMMdd_HHmmss");
		return dateFormat.format(date) + ".jpg";
	}

}
