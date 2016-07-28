package com.langdunzx.www.zanbei.controller;

import android.content.Context;


import com.langdunzx.www.zanbei.config.Command;
import com.langdunzx.www.zanbei.config.Constants;

import java.util.HashMap;

public class RequestCommant {
	//测试 请求列表
	//6.1 登录验证接口
	public void requestlogin(BaseHandler handler, Context context,
			HashMap<String, String> paramHashMap) {
		Command command = new Command(Constants.LOGIN, handler);
		command.param = paramHashMap;
		command.method = "reglogin";
		command.waitingMsg = "加载中，请稍候..."; 
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();
	}
	//6.1 登录验证接口
	public void requestwxlogin(BaseHandler handler, Context context,
							 HashMap<String, String> paramHashMap) {
		Command command = new Command(Constants.WXLOGIN, handler);
		command.param = paramHashMap;
		command.method = "weixinLogin";
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();
	}
	//6.3 首页数据接口
	public void requestHomeData(BaseHandler handler,Context context,HashMap<String, String> paramHashMap){
		Command command = new Command(Constants.HOME_DATA, handler); 
		command.param = paramHashMap;
		command.method = "api";  
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();    	     	 
	}
	//助学宝页数据接口
	public void requestZXBData(BaseHandler handler,Context context,HashMap<String, String> paramHashMap){
		Command command = new Command(Constants.ZXB_DATA, handler); 
		command.param = paramHashMap;
		command.method = "zxb";  
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();    	     	 
	}
	//助学宝页item数据接口
	public void requestZxbItemData(BaseHandler handler,Context context,HashMap<String, String> paramHashMap){
		Command command = new Command(Constants.ZXB_ITEM_DATA, handler); 
		command.param = paramHashMap;
		command.method = "zxb";  
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();    	     	 
	}
	//摇一摇额度获取
	public void requestLimit(BaseHandler handler,Context context,HashMap<String, String> paramHashMap){
		Command command = new Command(Constants.SHAKE_LIMIT, handler); 
		command.param = paramHashMap;
		command.method = "jf";  
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();    	     	 
	}
	// 首页考证分类接口
	public void requestClassyExam(BaseHandler handler,Context context,HashMap<String, String> paramHashMap){
		Command command = new Command(Constants.CLASSY_EXAM, handler); 
		command.param = paramHashMap;
		command.method = "appallcourselist";  
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();    	     	 
	}
	//所有课程
	public void requestAllCourse(BaseHandler handler, Context context,
			HashMap<String, String> paramHashMap) {
		Command command = new Command(Constants.GET_ALL_COURSE, handler); 
		command.param = paramHashMap;
		command.method = "appallcourselist";
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();
	}

	//试听课程
	public void requestAuditionCourse(BaseHandler handler, Context context,
			HashMap<String, String> paramHashMap) {
		Command command = new Command(Constants.GET_AUDITION_COURSE, handler); 
		command.param = paramHashMap;
		command.method = "appallcourselist";
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();
	}

	//获得已购买课程
	public void getPurchaseCourse(BaseHandler handler, Context context,
			HashMap<String, String> paramHashMap) {
		Command command = new Command(Constants.GET_PURCHASE_COURSE, handler); 
		command.param = paramHashMap;
		command.method = "querycusbuycourselist";
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();
	}
	//获得收藏课程
	public void getCollectCourse(BaseHandler handler, Context context,
			HashMap<String, String> paramHashMap) {
		Command command = new Command(Constants.GET_COLLECT_COURSE, handler); 
		command.param = paramHashMap;
		command.method = "usercollectionlist";
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();
	}
	//收藏课程
	public void requestCollectCourse(BaseHandler handler, Context context,
			HashMap<String, String> paramHashMap) {
		Command command = new Command(Constants.REQUEST_COLLECT_COURSE, handler); 
		command.param = paramHashMap;
		command.method = "collectioncourse";
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();
	}
	//收藏删除
	public void requestFavCrsDel(BaseHandler handler,Context context,HashMap<String, String> paramHashMap){
		Command command = new Command(Constants.DEL_FAVCRS, handler); 
		command.param = paramHashMap;
		command.method = "deletehouse";  
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();    	     	 
	}
	//获得课程详情
	public void requestParticularCourse(BaseHandler handler, Context context,
			HashMap<String, String> paramHashMap) {
		Command command = new Command(Constants.GET_PARTICULAR_COURSE, handler); 
		command.param = paramHashMap;
		command.method = "showcourseinfo";
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();
	}
	//获得课程讨论
	public void requestDiscussCourse(BaseHandler handler, Context context,
			HashMap<String, String> paramHashMap) {
		Command command = new Command(Constants.GET_DISCUSS_COURSE, handler); 
		command.param = paramHashMap;
		String courseId = paramHashMap.get("courseId");
		command.method = "course/assess/list"+"/"+courseId;
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();
	}
	//发表课程讨论
	public void requestSendDiscuss(BaseHandler handler, Context context,
			HashMap<String, String> paramHashMap) {
		Command command = new Command(Constants.SEND_DISCUSS_COURSE, handler); 
		command.param = paramHashMap;
		command.method = "course/assess/add";
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();
	}
	//获取WebUrl
	public void requestWebUrl(BaseHandler handler, Context context,
			HashMap<String, String> paramHashMap) {
		Command command = new Command(Constants.GET_WEBURL, handler); 
		command.param = paramHashMap;
		command.method = "rz";
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();
	}

	//	public void 	requestSendSMSCode(BaseHandler handler,Context context,HashMap<String, String> paramHashMap){
	//		Command command = new Command(Constants.INVESTMENTS_LIST, handler); 
	//		command.param = paramHashMap;
	//		command.method = "service/user/login";  
	//		command.waitingMsg = "加载中，请稍候...";
	//		command.showDialog = true;
	//		command.context = context;
	//		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
	//		mPostAsynTask.execute();    	 
	//	}
	//6.17 忘记密码
	public void requestForgetPwd(BaseHandler handler,Context context,HashMap<String, String> paramHashMap){
		Command command = new Command(Constants.FORGET_PWD, handler); 
		command.param = paramHashMap;
		command.method = "findBackPSWD";  
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();       	 
	}
	//6.16获取短信验证码
	public void requestGetPhoneCode(BaseHandler handler,Context context,HashMap<String, String> paramHashMap){
		Command command = new Command(Constants.GET_PHONE_VERRIFYCODE, handler); 
		command.param = paramHashMap;
		String phone = paramHashMap.get("phone");
		command.method = "register/verification"+"/"+phone;  
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();    	     	 
	}
	//6.21 意见反馈
	public void requestSuggestionFeedback(BaseHandler handler,Context context,HashMap<String, String> paramHashMap){
		Command command = new Command(Constants.SUGGESTION_FEEDBACK, handler); 
		command.param = paramHashMap;
		command.method = "userFeedBack";  
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();    	     	 
	}
	//6.2 注册接口
	public void requestRegiste(BaseHandler handler,Context context,HashMap<String, String> paramHashMap){
		Command command = new Command(Constants.REGISTER, handler); 
		command.param = paramHashMap;
		command.method = "appregister";  
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();    	     	 
	}
	//6.15 修改手机号(即登录名)接口
	public void requestChangePhone(BaseHandler handler,Context context,HashMap<String, String> paramHashMap){
		Command command = new Command(Constants.MODIFY_PHONE, handler); 
		command.param = paramHashMap;
		command.method = "modifyMobil";  
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();    	     	 
	}
	//6.25 赎回审核接口
	public void requestRedemptionAudit(BaseHandler handler,Context context,HashMap<String, String> paramHashMap){
		Command command = new Command(Constants.REDEMPTION_AUDIT, handler); 
		command.param = paramHashMap;
		command.method = "submitRedeemAudit";  
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();    	     	 
	}
	//6.10 赎回债权接口
	public void requestCallBonds(BaseHandler handler,Context context,HashMap<String, String> paramHashMap){
		Command command = new Command(Constants.CALL_BONDS, handler); 
		command.param = paramHashMap;
		command.method = "redeemAudit";  
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();    	     	 
	}
	//6.11 赎回时获取赎回信息的接口
	public void requestCallBondsInfo(BaseHandler handler,Context context,HashMap<String, String> paramHashMap){
		Command command = new Command(Constants.CALL_BONDS_INFO, handler); 
		command.param = paramHashMap;
		command.method = "redeemMessage";  
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();    	     	 
	}

	//6.13自动登录
	public void requestAutoLogin(BaseHandler handler,Context context,HashMap<String, String> paramHashMap){
		Command command = new Command(Constants.AUTOLOGIN, handler); 
		command.param = paramHashMap;
		command.method = "autoLoginValidate";  
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();    	     	 
	}



	//6.14 修改密码接口
	public void requestChangePwd(BaseHandler handler,Context context,HashMap<String, String> paramHashMap){
		Command command = new Command(Constants.MODIFY_PWD, handler); 
		command.param = paramHashMap;
		command.method = "modifyPSWD";  
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();    	     	 
	}
	//6.23 登出接口
	public void requestLogOut(BaseHandler handler,Context context,HashMap<String, String> paramHashMap){
		Command command = new Command(Constants.LOG_OUT, handler); 
		command.param = paramHashMap;
		command.method = "loginOut";  
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();    	     	 
	}
	//6.18 用户信息查询接口
	public void requestUserInfo(BaseHandler handler,Context context,HashMap<String, String> paramHashMap){
		Command command = new Command(Constants.USER_INFO, handler); 
		command.param = paramHashMap;
		command.method = "user/info?";
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();    	     	 
	}
	//用户头像
	public void requestUserPhoto(BaseHandler handler,Context context,HashMap<String, String> paramHashMap){
		Command command = new Command(Constants.USER_PHOTO, handler);
		command.param = paramHashMap;
		command.method = "user/avatar?";
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();
	}
	//用户头像
	public void requestInformation(BaseHandler handler,Context context,HashMap<String, String> paramHashMap){
		Command command = new Command(Constants.INFORMATION, handler);
		command.param = paramHashMap;
		command.method = "";
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();
	}
	//查询内容
	public void requestSearch(BaseHandler handler,Context context,HashMap<String, String> paramHashMap){
		Command command = new Command(Constants.SEARCH, handler);
		command.param = paramHashMap;
		command.method = "appallcourselist?";
		command.waitingMsg = "加载中，请稍候...";
		command.showDialog = true;
		command.context = context;
		PostAsynTask mPostAsynTask = new PostAsynTask(context, command);
		mPostAsynTask.execute();
	}
}
