package com.langdunzx.www.zanbei.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;


import com.langdunzx.www.zanbei.config.Command;
import com.langdunzx.www.zanbei.config.Constants;

import java.lang.ref.WeakReference;

@SuppressLint("NewApi")
public class BaseHandler extends Handler{
	 
	//implements ETongDaiDialogListener {
		public static final int SESSION_TIMEOUT_RESULT_CODE = 800000;
		public static final int LOGIN_CROWD_OUT_RESULT_CODE = 800001;
		
		/**
		 * 是否被拦截
		 */
		protected boolean isIntercepted = false;
	  
		// 会话超时
		public static final String SESSION_TIMEOUT_CODE = "800000";

		// 用户被挤掉
		protected static final String LOGIN_CROWD_OUT_CODE = "2";

		protected Command command;
		protected WeakReference<Activity> mActivity;
		protected WeakReference<Fragment> mFragment;
		protected Context context;

		public BaseHandler(Activity activity) {
			mActivity = new WeakReference<Activity>(activity);
			context = activity;
		}

		public BaseHandler(Fragment fragment) {
			mFragment = new WeakReference<Fragment>(fragment);
			context = fragment.getActivity();
		}
	     
		@Override
		public void handleMessage(Message msg) {
			command = (Command) msg.obj;
			if (Constants.CANCEL_POST_IDENTIFIER == msg.what) {
				// 取消请求，则返回上个界面
				if (null != mActivity) {
					mActivity.get().finish();
				} else if (null != mFragment) {
					int count = mFragment.get().getFragmentManager()
							.getBackStackEntryCount();   
					if (count > 1){
						mFragment.get().getFragmentManager().popBackStack();
					}
				}

				return;
			}
			
			if (null != mActivity) {
				if (null == mActivity.get() || null == context) {
					// 如果Activity或Fragment已经被回收，则不显示错误信息
//					LogUtil.e(LEVEL, "都是NULL");
					isIntercepted = true;
					return;
				}
			}

			if (null != mFragment) {
				if (null == mFragment.get() || null == context) {
					// 如果Activity或Fragment已经被回收，则不显示错误信息
//					Logge.LogE("都是NULL");
					isIntercepted = true;
					return;
				}
			}

			if ((null != mActivity) && (null != mActivity.get())
					&& mActivity.get().isFinishing()) {
				// 如果Activity当前已经不是最顶层的，那么说明该Acitvity已经被覆盖或销毁，不显示错误提示
//			Logge.LogE("=============activity不是最顶层都是NULL");
				isIntercepted = true;
				return;
			}
			
			if (null != command) {
				if (!command.success) {
					if (null != command.stateCode
							&& (command.stateCode.equals(SESSION_TIMEOUT_CODE) || command.stateCode
									.equals(LOGIN_CROWD_OUT_CODE))) {
						// 如果是登录超时或者被踢出，则告知
//						Logge.LogE("如果是登录超时或者被踢出，则告知");
//						LangDunApplication.timeOutOrLoginCrowdOut = true;
//						whenSessionTimeout(command);
//						whenSessionTimeoutT(command);
						return;
					}
				}
			}
		}

//		protected void whenSessionTimeout(Command cmd) {
//			if (!cmd.success) {
//				// 如果请求失败并且会话超时或者用户被挤掉
//				if (cmd.stateCode.equals(SESSION_TIMEOUT_CODE)
//						|| cmd.stateCode.equals(LOGIN_CROWD_OUT_CODE)) {
//					LangDunApplication application = LangDunApplication.islangdunapplication;
//					application.setUserloginbodyvo(null);
//					application.setSessionId(null);
//					context.getSharedPreferences("person",context.MODE_PRIVATE).edit().clear().commit();
//
//					SPUtils.clear(application);
// 					if (null != mFragment) {
//						if (mFragment.get() != null && mFragment.get().getActivity() != null) {
//                  ShowErrorDialogUtil.showAlertDialog(mFragment.get().getActivity(),(String)command.message, "返回首页", new DCDialogListener() {
// 					@Override
//					public void OnPreviousButtonClicked(LDDialog dialog) {
//						// TODO Auto-generated method stub
//						Intent lIntent = new Intent(mFragment.get().getActivity(), HomeActivity.class);
//						HomeActivity.type = "finish";
//						mFragment.get().getActivity().startActivity(lIntent);
//						dialog.dismiss();
//						LangDunApplication.isShowingDialog = false;
//
// 					}
//					
//					@Override
//					public void OnNextButtonClicked(LDDialog dialog) {
//						// TODO Auto-generated method stub
//						
//					}
//					
//					@Override
//					public void OnMiddleButtonClicked(LDDialog dialog) {
//						// TODO Auto-generated method stub
//						
//					}
//				});
// 						}
//						
//	 				}  
//					if(mActivity!=null){
//						if (mActivity!= null && mActivity.get() != null) {
//                           ShowErrorDialogUtil.showAlertDialog(mActivity.get(),(String)command.message, "返回首页", new DCDialogListener() {
//							
//							@Override
//							public void OnPreviousButtonClicked(LDDialog dialog) {
//								// TODO Auto-generated method stub
//								Intent lIntent = new Intent(mActivity.get(), HomeActivity.class);
//								HomeActivity.type = "finish";
//								mActivity.get().startActivity(lIntent);
//								dialog.dismiss();
//								LangDunApplication.isShowingDialog = false;
//
//							}
//							
//							@Override
//							public void OnNextButtonClicked(LDDialog dialog) {
//								// TODO Auto-generated method stub
//								
//							}
// 							@Override
//							public void OnMiddleButtonClicked(LDDialog dialog) {
//								// TODO Auto-generated method stub
//								
//							}
//						});
//                           }
// 					}
// 				}
//                 
//				isIntercepted = true;
//				LangDunApplication.timeOutOrLoginCrowdOut = false;
//				return;
//			}
//		} 
		

}
