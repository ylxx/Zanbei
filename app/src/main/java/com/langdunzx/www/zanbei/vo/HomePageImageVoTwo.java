package com.langdunzx.www.zanbei.vo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class HomePageImageVoTwo implements Serializable{
	private static final long serialVersionUID = -1629011640663014271L;

	//id
	private int id;
	//轮播图地址
	private String link;
	//点击轮播图跳转路径
	private String pic;
	
	@JsonProperty(value = "id")
	public void setId(int id){
		this.id = id;
	}
	@JsonProperty(value = "id")
	public int getId(){
		return id;
	}
	@JsonProperty(value = "pic")
	public void setUrl(String pic){
		this.pic = pic;
	}
	@JsonProperty(value = "pic")
	public String getUrl(){
		return this.pic;
	}
	@JsonProperty(value = "link")
	public void setPath(String link){
		this.link = link;
	}
	@JsonProperty(value = "link")
	public String getPath(){
		return this.link;
	}
}
