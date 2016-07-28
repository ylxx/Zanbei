package com.langdunzx.www.zanbei.utils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

public class MaxLengthWatcher implements TextWatcher {
	private int maxLen = 0;
	private EditText editText = null;
	String mString;
	private static String regEx = "[`~!@#$%^&*()+=|{}':;'\\[\\].<>/~！@#￥%……&*（）――+|{}【】‘；：”“’。，、？]";

	public MaxLengthWatcher(int maxLen, EditText editText) {
		super();
		this.maxLen = maxLen;
		this.editText = editText;

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
								  int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {

	}

	@Override
	public void afterTextChanged(Editable s) {
		//Editable editable = editText.getText();
		Editable editable = s;
		byte[] bytes;
		try {
			bytes = editable.toString().getBytes("GBK");
			int i = bytes.length;
			if (i > maxLen) {
				editText.setText(getSubstringByByte(s.toString(),"GBK",maxLen));
				Selection.setSelection(editText.getText(), editText.getText()
						.length());
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 判断字符串是否包含一些字符 contains
	 */
	public static boolean containsString(String src) {
		boolean flag = false;

		for (int i = 0; i < regEx.length(); i++) {

			String indexOf = regEx.substring(i, i + 1);

			if (src.contains(indexOf)) {
				flag = true;
			}
		}

		return flag;
	}
	/**
	 * 截取字符串前lengthByte个字节的字符串
	 * @param s
	 * @param encoding
	 * @param lengthByte
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getSubstringByByte(String s,String encoding,int lengthByte) throws UnsupportedEncodingException{
		//if(s == null) return s;
		if(s == null||s.getBytes(encoding).length<=lengthByte){
			return s;
		}
		int byteLength = s.getBytes(encoding).length;
		int currentBytes = s.substring(0, byteLength/4).getBytes(encoding).length;
		for(int i = byteLength/4;i<s.length();i++){
			if(s.substring(0, i+1).getBytes(encoding).length>lengthByte){
				return s.substring(0,i);
			}
		}
		return null;
	}

}
