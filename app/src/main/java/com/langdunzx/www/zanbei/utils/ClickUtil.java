package com.langdunzx.www.zanbei.utils;

import android.view.View;
import android.view.View.OnClickListener;

public class ClickUtil {

	public static void setClickListener(OnClickListener l, Object... obj) {
		for (int i = 0; i < obj.length; i++) {
			if (obj[i] instanceof View) {
				((View) obj[i]).setOnClickListener(l);
			}
		}
	}
}
