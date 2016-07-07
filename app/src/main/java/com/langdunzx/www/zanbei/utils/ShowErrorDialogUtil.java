/*******************************************************************************
 * Copyright (c) 2015 by dennis Corporation all right reserved.
 * 2015年5月13日 
 * 
 *******************************************************************************/
package com.langdunzx.www.zanbei.utils;



import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.langdunzx.www.zanbei.application.LangDunApplication;
import com.langdunzx.www.zanbei.dialog.DksDialog;

/**
 * <pre>
 * 业务名:
 * 功能说明: 提示dialog
 * 编写日期:	2016年3月7日11:45:27
 * 作者:	dc
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class ShowErrorDialogUtil {

	public static void showErrorDialog(Context context, String errString) {
		if (LangDunApplication.isShowingDialog)
			return;
		DksDialog dialog = new DksDialog(context, listener);
		dialog.show();
		dialog.getDialogContentTxt().setText(errString);
		dialog.getPreviousBtn().setText("确定");
		dialog.getMiddleimage().setVisibility(View.GONE);
		// dialog.getDialogImage().setBackgroundResource(
		// R.drawable.dialog_error_icon);

	}

	public static void showErrorDialog(Context context, String errString, String btnString) {
		if (LangDunApplication.isShowingDialog)
			return;
		DksDialog dialog = new DksDialog(context, listener);
		dialog.show();
		dialog.getDialogContentTxt().setText(errString);
		dialog.getPreviousBtn().setText(btnString);
		dialog.getMiddleimage().setVisibility(View.GONE);
		// dialog.getDialogImage().setBackgroundResource(
		// R.drawable.dialog_error_icon);

	}

	public static void showErrorDialog(Context context, String errString, DksDialog.LangDunDialogListener listener) {
		if (LangDunApplication.isShowingDialog)
			return;
		DksDialog dialog = null;
		dialog = new DksDialog(context, listener);
		dialog.show();
		dialog.getDialogContentTxt().setText(errString);
		dialog.getPreviousBtn().setText("确定");
		dialog.getMiddleimage().setVisibility(View.GONE);

		// dialog.getDialogImage().setBackgroundResource(
		// R.drawable.dialog_error_icon);
	}

	public static void showSuccesDialog(Context context, String errString, DksDialog.LangDunDialogListener listener) {
		if (LangDunApplication.isShowingDialog)
			return;
		DksDialog dialog = null;
		dialog = new DksDialog(context, listener);
		dialog.show();
		dialog.getDialogContentTxt().setText(errString);
		dialog.getPreviousBtn().setText("确定");
		dialog.getMiddleimage().setVisibility(View.GONE);

		// dialog.getDialogImage().setBackgroundResource(
		// R.drawable.dialog_error_icon);
	}

	private static DksDialog.LangDunDialogListener listener = new DksDialog.LangDunDialogListener() {

		@Override
		public void OnPreviousButtonClicked(DksDialog dialog) {
			// TODO Auto-generated method stub
			if (null != dialog && dialog.isShowing()) {
				dialog.dismiss();
				dialog = null;
			}
		}

		@Override
		public void OnNextButtonClicked(DksDialog dialog) {
			// TODO Auto-generated method stub

		}

		@Override
		public void OnMiddleButtonClicked(DksDialog dialog) {

		}
	};

	public static void showErrorDialogLoginOut(Context context, String errString,
			final DksDialog.LangDunDialogListener listener) {
		if (LangDunApplication.isShowingDialog)
			return;
		LangDunApplication.isShowingDialog = true;
		DksDialog dialog = null;
		dialog = new DksDialog(context, listener);
		dialog.show();
		dialog.getDialogContentTxt().setText(errString);
		dialog.getPreviousBtn().setText("确定");
		dialog.getMiddleimage().setVisibility(View.GONE);
		// dialog.getDialogImage().setBackgroundResource(
		// R.drawable.dialog_error_icon);
	}

}
