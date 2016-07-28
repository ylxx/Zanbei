package com.langdunzx.www.zanbei.vo;

import com.langdunzx.www.zanbei.bean.BaseVo;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonSetter;

public class HomePageBodyVo extends BaseVo implements Serializable{
	private static final long serialVersionUID = -1629011640663014271L;
	
	private List<HomePageImageVo> adpic;
	private List<HomePageImageVoTwo> indexpic;
	private int news;
	private String time;
	private String userId;
	
	@JsonProperty(value = "indexpic")
	public List<HomePageImageVoTwo> getIndexpic() {
		return indexpic;
	}
	@JsonProperty(value = "indexpic")
	public void setIndexpic(List<HomePageImageVoTwo> indexpic) {
		this.indexpic = indexpic;
	}
	@JsonProperty(value = "userId")
	public String getUserId() {
		return userId;
	}
	@JsonProperty(value = "userId")
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@JsonProperty(value = "news")
	public int getNews() {
		return news;
	}
	@JsonProperty(value = "news")
	public void setNews(int news) {
		this.news = news;
	}
	@JsonProperty(value = "time")
	public String getTime(){
		return this.time;
	}
	@JsonProperty(value = "time")
	public void setTime(String time){
		this.time = time;
	}

	@JsonProperty(value = "adpic")
	public List<HomePageImageVo> getBody() {
		return this.adpic;
	}
	@JsonSetter(value = "adpic")
	public void setBody(List<HomePageImageVo> adpic) {
		this.adpic = adpic;
	}
	
	
}
