package com.langdunzx.www.zanbei.view;

import android.view.View;

/**
 * 工具类
 * 控制view是否可见
 * 16/4／15
 * @author cuiyinglai
 *
 */

public class VisibilityUtil {

	public static void setVisible(int v, Object... obj){

		for (int i = 0; i < obj.length; i++) {
			if (obj[i] instanceof View) {
				if(v == 1){
					((View) obj[i]).setVisibility(View.VISIBLE);
				}else if(v == 0){
					((View) obj[i]).setVisibility(View.GONE);
				}

			}
		}
	}


}
