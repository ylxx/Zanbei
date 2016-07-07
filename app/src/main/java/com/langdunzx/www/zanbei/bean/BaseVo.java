package com.langdunzx.www.zanbei.bean;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonSetter;

import java.io.Serializable;

public class BaseVo implements Serializable{
	private static final long serialVersionUID = -1629011640663014271L;

	private boolean success;
	// 错误信息
	private String message;

	@JsonSetter(value = "message")
	public String getMessage() {
		return message;
	}
	@JsonSetter(value = "message")
	public void setMessage(String message) {
		this.message = message;
	}
	@JsonProperty(value = "success")
	public boolean getSuccess() {
		return success;
	}
	@JsonSetter(value = "success")
	public void setSuccess(boolean success) {
		this.success = success;
	}

}
