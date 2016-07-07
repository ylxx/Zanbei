package com.langdunzx.www.zanbei.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.langdunzx.www.zanbei.R;


/*********************************************
 * @author 作者 ：dc
 * @date 创建时间：2016年3月4日17:09:36
 * @version 1.0
 * @Description: TODO
 * @parameter
 * @since
 * @return
 ********************************************** 
 */

public class CustomBar extends RelativeLayout{
	
	private ImageView left_bar;
	private TextView middle_bar;
	public TextView right_bar;
	private ImageView line;

	public CustomBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public CustomBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	public void ininbar(ViewGroup root) {
		// TODO Auto-generated method stub
		left_bar = (ImageView) root.findViewById(R.id.left_bar);
		middle_bar = (TextView) root.findViewById(R.id.middle_bar);
		right_bar = (TextView) root.findViewById(R.id.right_bar);
		// line = (ImageView) root.findViewById(R.id.line);
	}
	
	public void setTitleBar(String title) {
		middle_bar.setText(title);
	}

	public ImageView getLeftBar() {
		return left_bar;
	}

	public void setRightBar(String right) {
		right_bar.setVisibility(View.VISIBLE);
		right_bar.setText(right);
	}

	public TextView getRightBar() {
		right_bar.setVisibility(View.VISIBLE);
		return right_bar;
	}
	
	/**
	 * R.id.right_bar
	 * @param isToVisible
	 * @return
	 */
	public TextView getRightBar(boolean isToVisible){
		if(isToVisible){
			right_bar.setVisibility(View.VISIBLE);
			return right_bar;
		}else{
			return right_bar;
		}
	}

	public void setLineVisible(boolean isShow) {
		if (isShow) {
			line.setVisibility(View.GONE);
		} else {
			line.setVisibility(View.VISIBLE);
		}
	}

}
