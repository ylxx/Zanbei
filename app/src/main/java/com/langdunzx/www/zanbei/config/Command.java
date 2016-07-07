package com.langdunzx.www.zanbei.config;

import android.content.Context;
import android.os.Handler;

public class Command {
	/**
	 * 命令标识
	 */
	public int commandID;
	
	/**
	 * 句柄
	 */
	public Handler handler;
	
	/**
	 * 是否成功
	 */
	public boolean success;
	
	/**
	 * 返回数据
	 */
	public Object resData = null;
	
	/**
	 * 请求参数
	 */
	public Object param = null;
	
	/**
	 * 返回数据 错误类型/消息
	 */
	public Object message = null;
	
	/**
	 * 加载显示内容
	 */
	public String waitingMsg = null;
	
	/**
	 * 是否显示等待框
	 */
	public boolean showDialog = true;
	
	/**
	 * 是否会话超时
	 */
	public boolean outSession = false;
	
	/**
	 * 在请求结束后，是否隐藏等待框
	 */
	public boolean hidenDialog = true;
	
	/**
	 * 上下文
	 */
	public Context context;
	
	/**
	 * 请求状态码
	 */
	public String stateCode;
	
	/**
	 * 请求方法名
	 */
	public String method;
	
	/**
	 * 是否能够取消请求
	 */
	public boolean canCancelRequest = true;

	public Command(int commandID, Handler handler) {
		super();
		this.commandID = commandID;
		this.handler = handler;
	}
	
}
