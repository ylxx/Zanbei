/*******************************************************************************
 * Copyright (c) 2015 by dennis Corporation all right reserved.
 * 2015年5月13日 
 * 
 *******************************************************************************/ 
package com.langdunzx.www.zanbei.dialog;



 


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.langdunzx.www.zanbei.R;

/**
 * <pre>
 * 业务名:
 * 功能说明: dialog 工具类
 * 编写日期:	2015年8月13日
 * 作者:	zgz
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class DksDialog extends Dialog {
	private LangDunDialogListener listener;

	private TextView dialogTitleTxt;
	private TextView dialogContentTxt;
	private LinearLayout dialogContent;
	private Button previousBtn;
	private Button middleBtn;
	private Button nextBtn;
	private ImageView dialogImage;
	private ImageView  middleimage;
 	private Object tag;
	private Object contentDescription;

	
	
	public ImageView getMiddleimage() {
		return middleimage;
	}

	public void setMiddleimage(ImageView middleimage) {
		this.middleimage = middleimage;
	}

	public Object getTag() {
		return tag;
	}
   
	public void setTag(Object tag) {
		this.tag = tag;
	}

	public Object getContentDescription() {
		return contentDescription;
	}

	public void setContentDescription(Object contentDescription) {
		this.contentDescription = contentDescription;
	}

	public TextView getDialogTitleTxt() {
		dialogTitleTxt.setVisibility(View.VISIBLE);
		return dialogTitleTxt;
	}

	public TextView getDialogContentTxt() {
		
		return dialogContentTxt;
	}

	public LinearLayout getDialogContent() {
		dialogContent.setVisibility(View.VISIBLE);
		return dialogContent;
	}

	public Button getPreviousBtn() {
		previousBtn.setVisibility(View.VISIBLE);
		return previousBtn;
	}

	public Button getMiddleBtn() {
		middleBtn.setVisibility(View.VISIBLE);
		return middleBtn;
	}

	public Button getNextBtn() {
		nextBtn.setVisibility(View.VISIBLE);
		return nextBtn;
	}
	
	public ImageView getDialogImage() {
		dialogImage.setVisibility(View.VISIBLE);
		return dialogImage;
	}

	public DksDialog(Context context, LangDunDialogListener listener) {
		super(context, R.style.myDialogTheme);
		this.listener = listener;
		this.setCancelable(false); 
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showdialog);
		dialogTitleTxt = (TextView) findViewById(R.id.dialogTitleTxt);
		dialogContentTxt = (TextView) findViewById(R.id.dialogContentTxt);
		dialogContent = (LinearLayout) findViewById(R.id.dialogContent);

		dialogImage = (ImageView) findViewById(R.id.dialogImage);
		
		previousBtn = (Button) findViewById(R.id.previousBtn);
		previousBtn.setOnClickListener(clicked);
 		middleBtn = (Button) findViewById(R.id.middleBtn);
		middleBtn.setOnClickListener(clicked);
        middleimage=(ImageView) findViewById(R.id.middleimage);
		nextBtn = (Button) findViewById(R.id.nextBtn);
		nextBtn.setOnClickListener(clicked);
		this.setCanceledOnTouchOutside(false);
	}

	private Button.OnClickListener clicked = new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.previousBtn:
				// 第一个按钮
				if (null != listener) {
					listener.OnPreviousButtonClicked(DksDialog.this);
				}
				break;
			case R.id.middleBtn:
				// 中间按钮
				if (null != listener) {
					listener.OnMiddleButtonClicked(DksDialog.this);
				}
				break;
			default:
				// 最后一个按钮
				DksDialog.this.dismiss();
				if (null != listener) {
					listener.OnNextButtonClicked(DksDialog.this);
				}
				break;
			}
		}
	};

	public interface LangDunDialogListener {
		abstract void OnPreviousButtonClicked(DksDialog dialog);

		abstract void OnMiddleButtonClicked(DksDialog dialog);

		abstract void OnNextButtonClicked(DksDialog dialog);
	}
 
}
