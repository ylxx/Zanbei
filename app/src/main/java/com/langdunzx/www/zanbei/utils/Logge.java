/*******************************************************************************
 * Copyright (c) 2015 by dennis Corporation all right reserved.
 * 2015年11月20日16:39:01 
 * 
 *******************************************************************************/ 
package com.langdunzx.www.zanbei.utils;

import android.util.Log;

public class Logge {
	private static final String TAG = "GUOLIANZICHAN";
	private static boolean flag = true;
   //打印日志 
	public static void LogE(String msg) {
		if (flag) {
			Log.e(TAG, msg);
		}
	}
	//描述状态
	public static void LogD(String msg){
		if(flag){
			Log.e(TAG, msg);
		}
	}
	public static void LogI(String msg) {
		if (flag) {
			Log.i(TAG, msg);
		}
	}
}
