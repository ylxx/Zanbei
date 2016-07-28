/*******************************************************************************
 * Copyright (c) 2015 by dennis Corporation all right reserved.
 * 2015年5月8日 
 * 
 *******************************************************************************/
package com.langdunzx.www.zanbei.controller;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.langdunzx.www.zanbei.R;
import com.langdunzx.www.zanbei.config.Command;
import com.langdunzx.www.zanbei.config.Constants;
import com.langdunzx.www.zanbei.net.Operation;
import com.langdunzx.www.zanbei.utils.CommonUtils;

import java.util.HashMap;

public class PostAsynTask extends AsyncTask<Command, Integer, Message> {
	private Dialog dialog;
	private Command cmd;
	private Boolean isok;

	public void setCmd(Command cmd) {
		this.cmd = cmd;
	}

	@SuppressWarnings("unchecked")
	public PostAsynTask(Context context, Command command) {
		// YiTongDaiApplication application = (YiTongDaiApplication) ((Activity)
		// context)
		// .getApplication();
		// if (null != application.getSessionId())
		// paramHashMap.put("sessionId", application.getSessionId());

		HashMap<String, String> paramHashMap = (HashMap<String, String>) command.param;
		//		if(DCApplication.isdekushuapplication.getUserloginbodyvo() != null){
		//		
		//		paramHashMap.put("randomCode",DCApplication.isdekushuapplication.getUserloginbodyvo()
		//				.getBody().getRandomCode());
		//		
		//		}
		command.param = paramHashMap;
		isok = CommonUtils.isNetWorkConnected(context);

		this.cmd = command;

		if (cmd.showDialog) {
			// 如果现实等待框
			dialog = createLoadingDialog(context, cmd.waitingMsg);
			dialog.setCanceledOnTouchOutside(false);
			dialog.setCancelable(cmd.canCancelRequest);
			if (cmd.canCancelRequest) {
				dialog.setOnCancelListener(new OnCancelListener() {
					@Override
					public void onCancel(DialogInterface arg0) {
						Message msg = Message.obtain();
						msg.what = Constants.CANCEL_POST_IDENTIFIER;
						cmd.handler.sendMessage(msg);
						cancel(true);
					}
				});
			}
		}
		// if(isok==false||isok.equals(false)){
		// ShowErrorDialogUtil.showErrorDialog(context, "请检查网络");
		// }
	}

	/**
	 * 此方法工作在UI线程
	 */
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		if (cmd.showDialog) {
			dialog.show();
		}
	}

	/**
	 * doInBackground方法里面不可以直接对UI元素进行操作
	 */
	@Override
	protected Message doInBackground(Command... params) {
		Message msg = execute(this.cmd);
		if (isCancelled()) {
			return null;
		}
		return msg;
	}

	/**
	 * doInBackground执行完后执行此方法
	 */
	@Override
	protected void onPostExecute(Message result) {
		if (this.cmd.hidenDialog) {
			if (null != dialog && dialog.isShowing()) {
				dialog.dismiss();
				dialog = null;
			}
		}

		this.cmd.handler.sendMessage(result);
	}

	@Override
	protected void onCancelled() {
		super.onCancelled();
	}

	/**
	 * 自定义加载框
	 * 
	 * @param context
	 * @param msg
	 * @return
	 */
	public Dialog createLoadingDialog(Context context, String msg) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.wait_progress_dialog, null);
		LinearLayout layout = (LinearLayout) view
				.findViewById(R.id.dialog_view);// 加载布局

		TextView tipTextView = (TextView) view.findViewById(R.id.waiting_text);// 提示文字

		if (null != cmd.waitingMsg && !"".equals(cmd.waitingMsg)) {
			tipTextView.setText(this.cmd.waitingMsg);
		} else {
			if ("".equals(msg))
				tipTextView.setText("加载中,请稍侯...");
			else {
				tipTextView.setText(msg);
			}
		}

		Dialog dialog = new Dialog(context, R.style.customDialogTheme);
		dialog.setContentView(layout, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		return dialog;
	}

	private Message execute(Command command) {
		Operation operation = new Operation();
		// 登录
		if (Constants.LOGIN == command.commandID) {
				return operation.executeLogin(command);
		}

		// 微信登录
		if (Constants.WXLOGIN == command.commandID) {
			return operation.executeLogin(command);
		}
		//首页数据加载
		if(Constants.HOME_DATA==command.commandID){
				return operation.executeHomeData(command);
			}
		//个人头像的更换
		//	if(Constants.USER_PHOTO==command.commandID){
			//	return operation.executeUserPhoto(command);
			//}
		//用户搜索
		//if(Constants.SEARCH==command.commandID){
			//	return operation.executeSearch(command);
			//}
		//资讯界面
		//if(Constants.INFORMATION==command.commandID){
			//	return operation.executeInformation(command);
			//}
		//助学宝页数据加载
//		if(Constants.ZXB_DATA==command.commandID){
//			return operation.executeZXBData(command);
//		}
//		//助学宝页item数据加载
//		if(Constants.ZXB_ITEM_DATA==command.commandID){
//			return operation.executeZxbItemData(command);
//		}
//		//摇一摇额度获取
//		if(Constants.SHAKE_LIMIT==command.commandID){
//			return operation.executeShakeLimit(command);
//		}
		//首页考证分类加载
		//if(Constants.CLASSY_EXAM==command.commandID){
		//	return operation.executeClassyExam(command);
		//}
//		// 忘记密码
//		if (Constants.FORGET_PWD == command.commandID) {
//			return operation.executeForgetPwd(command);
//		}
//		// 获取手机短信验证码
		//if (Constants.GET_PHONE_VERRIFYCODE == command.commandID) {
		//	return operation.executeGetPhoneVerifycode(command);
		//}
////		// 注册
//		if (Constants.REGISTER == command.commandID) {
//			return operation.executeRegist(command);
//		}
//		//获取所有课程
//		if(Constants.GET_ALL_COURSE == command.commandID){
//			return operation.executeGetAllCourse(command);
//		}
//		//获取试听课程
//		if(Constants.GET_AUDITION_COURSE == command.commandID){
//			return operation.executeGetAuditionCourse(command);
//		}
		//获取已购买课程
		//	if(Constants.GET_PURCHASE_COURSE == command.commandID){
		//return operation.executeGetPurchaseCourse(command);
		//}
		//获取收藏课程
		//if(Constants.GET_COLLECT_COURSE == command.commandID){
		//return operation.executeGetCollectCourse(command);
		//	}
//		//收藏课程
//		if(Constants.REQUEST_COLLECT_COURSE == command.commandID){
//			return operation.executeRequestCollectCourse(command);
//		}
//		//删除收藏item
//		if(Constants.DEL_FAVCRS==command.commandID){
//			return operation.executeDelFavCourse(command);
//		}
		//获取课程详情
		//if(Constants.GET_PARTICULAR_COURSE == command.commandID){
		//return operation.executeGetParticularCourse(command);
		//}
//		//获取课程评论
//		if(Constants.GET_DISCUSS_COURSE == command.commandID){
//			return operation.executeGetDiscussCourse(command);
//		}
//		//发表课程评论
//		if(Constants.SEND_DISCUSS_COURSE == command.commandID){
//			return operation.executeSendDiscussCourse(command);
//		}
//		//获取WEBURL
//		if(Constants.GET_WEBURL == command.commandID){
//			return operation.executeGetWebUrl(command);
//		}
		// 意见反馈
		//		if (Constants.SUGGESTION_FEEDBACK == command.commandID) {
		//			return operation.executeSuggestionFeedback(command);
		//		}
		// 客服信息
		//		if (Constants.CALL_SERVER_INFO == command.commandID) {
		//			return operation.executeCallServerInfo(command);
		//		}
		// 收益查询
		//		if (Constants.QUERY_BENEFIT == command.commandID) {
		//			return operation.executeGetPhoneVerifycode(command);
		//		}
		// 产品详情
		//		if (Constants.PROJECT_INFO == command.commandID) {
		//			return operation.executeProjectinfo(command);
		//		}
		// 修改手机号
		//		if (Constants.MODIFY_PHONE == command.commandID) {
		//			return operation.executeChangePhone(command);
		//		}
		//		// 赎回审核
		//		if (Constants.REDEMPTION_AUDIT == command.commandID) {
		//			return operation.executeRedemptionAudit(command);
		//		}
		// 赎回债券
		//		if (Constants.CALL_BONDS == command.commandID) {
		//			return operation.executeCallBonds(command);
		//		}
		// 赎回债券信息显示
		//		if (Constants.CALL_BONDS_INFO == command.commandID) {
		//			return operation.executeCallBondsInfo(command);
		//		}
		// 当日剩余债权
		//		if (Constants.SURPLUSCREDITOR == command.commandID) {
		//			return operation.executeCreditor(command);
		//		}
		// 修改登录密码
		//		if (Constants.MODIFY_PWD == command.commandID) {
		//			return operation.executeChangePwd(command);
		//		}
		// 登出
		//		if (Constants.LOG_OUT == command.commandID) {
		//			return operation.executeLogOut(command);
		//		}
		// 账户信息
		//if (Constants.ACCOUNT_INFO == command.commandID) {
		//	return operation.executeAccountInfo(command);
		//}
		//		// 交易记录查询
		//		if (Constants.TRADE_RECORD == command.commandID) {
		//			return operation.executeTradeRecord(command);
		//		}
		//		// 赎回记录查询
		//		if (Constants.REDEEM_RECORD == command.commandID) {
		//			return operation.executeRedeemRecord(command);
		//		}
		//		// 自动登录
		//		if (Constants.AUTOLOGIN == command.commandID) {
		//			return operation.executeAutoLogin(command);
		//		}
		//宜定投项目页面加载时调用的接口
		//		if(Constants.E_DINGTOU==command.commandID){
		//			return operation.executeEdingtou(command);
		//		}

//		用户信息
		//if(Constants.USER_INFO==command.commandID){
		//return operation.executeUserInfo(command);
		//}
		//		//实名认证
		//		if(Constants.CERTIFICATION_INFO==command.commandID){
		//			return operation.executeCertificate(command);
		//		}
		//购买债权接口
		//		if(Constants.BUY_CREDITOR==command.commandID){
		//			return operation.executefengfubuy(command);
		//		}
		//充值
//		if(Constants.RECHARGE==command.commandID){
//			return operation.executeForgetPwd(command);
//		}
		//提现
//		if(Constants.WITHDRAW==command.commandID){
//			return operation.executeForgetPwd(command);
//		}
		return null;

	}
}
