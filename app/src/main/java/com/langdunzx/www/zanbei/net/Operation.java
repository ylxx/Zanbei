package com.langdunzx.www.zanbei.net;

import android.os.Message;


import com.langdunzx.www.zanbei.R;
import com.langdunzx.www.zanbei.bean.BaseVo;
import com.langdunzx.www.zanbei.config.Command;
import com.langdunzx.www.zanbei.date.JsonVoParser;

import java.util.HashMap;

public class Operation {
	private static final String SUCCESS = "1";
	@SuppressWarnings("unchecked")
	public Message executeGeneral(Command cmd) {
		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
		String  jsonvo=CallServer.getInstance().callServer(cmd.method,hashMap, cmd.context);
		Message msg = Message.obtain();
		msg.what = cmd.commandID;
		BaseVo vo = JsonVoParser.getInstance().getBasevo(jsonvo);

		if((null != jsonvo) && !"".equals(jsonvo) && vo != null){
			if(SUCCESS.equals(vo.getSuccess())){
				cmd.message=vo.getMessage();
				cmd.success=true;
			}else{
				cmd.message=vo.getMessage();
				//	        	cmd.stateCode=vo.getCode();
				cmd.success=false;
			}
		}else{
			cmd.message=cmd.context.getString(R.string.network_is_futility);
			cmd.success=false;
			cmd.stateCode="100001";
		}
		msg.obj = cmd;
		return msg;
	}
	/**
	 * 登录
	 * 
	 * @param cmd
	 * @return
	 * */

	@SuppressWarnings("unchecked")
	public Message executeLogin(Command cmd) {
		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
		String jsonString = CallServer.getInstance().callServer(cmd.method,
				hashMap, cmd.context);
		Message msg = Message.obtain();
		msg.what = cmd.commandID;
//		UserLoginBodyVo vo = JsonVoParser.getInstance().getUserLoginBodyVo(
//				jsonString);
		BaseVo baseVo = JsonVoParser.getInstance().getBasevo(jsonString);
		if ((null != jsonString) && !"".equals(jsonString) && baseVo != null) {
			if (baseVo.getSuccess()) {
				cmd.success = true;
//				cmd.resData = vo;
			} else {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			}
		} else {
			if (baseVo != null) {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			} else {
				cmd.success = false;
				cmd.message = cmd.context
						.getString(R.string.the_network_is_dead);
			}
		}
		msg.obj = cmd;
		return msg;
	}

	/**
	 *
	 * 首页数据加载
	 * @param cmd
	 * @return

	@SuppressWarnings("unchecked")
	public Message executeHomeData(Command cmd) {
		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
		String jsonString = CallServer.getInstance().callServerldGet(cmd.method,
				hashMap, cmd.context);
		Message msg = Message.obtain();
		msg.what = cmd.commandID;
		HomePageBodyVo vo = JsonVoParser.getInstance().gethoHomePageBodyVo(jsonString);
		BaseVo baseVo = JsonVoParser.getInstance().getBasevo(jsonString);
		if ((null != jsonString) && !"".equals(jsonString) && baseVo != null) {
			if (baseVo.getSuccess()) {
				cmd.success = true;
				cmd.resData = vo;
			} else {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			}
		} else {
			if (baseVo != null) {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			} else {
				cmd.success = false;
				cmd.message = cmd.context
						.getString(R.string.the_network_is_dead);
			}
		}
		msg.obj = cmd;
		return msg;

	}
	 */
	/**
	 *
	 * 助学宝页数据加载
	 * @param cmd
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public Message executeZXBData(Command cmd) {
		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
		String jsonString = CallServer.getInstance().callServerldGet(cmd.method,
				hashMap, cmd.context);
		Message msg = Message.obtain();
		msg.what = cmd.commandID;
		ZxbEntityVo zxbEntityVo = JsonVoParser.getInstance().getzxbEntityVo(jsonString);
		BaseVo baseVo = JsonVoParser.getInstance().getBasevo(jsonString);
		if ((null != jsonString) && !"".equals(jsonString) && baseVo != null) {
			if (baseVo.getSuccess()) {
				cmd.success = true;
				cmd.resData = zxbEntityVo;
			} else {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			}
		} else {
			if (baseVo != null) {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			} else {
				cmd.success = false;
				cmd.message = cmd.context
						.getString(R.string.the_network_is_dead);
			}
		}
		msg.obj = cmd;
		return msg;

	}
	*//**
	 *
	 * 助学宝页item数据加载
	 * @param cmd
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public Message executeZxbItemData(Command cmd) {
		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
		String jsonString = CallServer.getInstance().callServerldGet(cmd.method,
				hashMap, cmd.context);
		Message msg = Message.obtain();
		msg.what = cmd.commandID;
		ZxbItemEntityVo zxbItemEntityVo = JsonVoParser.getInstance().getzxbItemEntityVo(jsonString);
		BaseVo baseVo = JsonVoParser.getInstance().getBasevo(jsonString);
		if ((null != jsonString) && !"".equals(jsonString) && baseVo != null) {
			if (baseVo.getSuccess()) {
				cmd.success = true;
				cmd.resData = zxbItemEntityVo;
			} else {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			}
		} else {
			if (baseVo != null) {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			} else {
				cmd.success = false;
				cmd.message = cmd.context
						.getString(R.string.the_network_is_dead);
			}
		}
		msg.obj = cmd;
		return msg;

	}
	*//**
	 *
	 * 获取摇一摇额度
	 * @param cmd
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public Message executeShakeLimit(Command cmd) {
		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
		String jsonString = CallServer.getInstance().callServerldGet(cmd.method,
				hashMap, cmd.context);
		Message msg = Message.obtain();
		msg.what = cmd.commandID;
		ShakeLimitEntityVo shakeLimitEntityVo = JsonVoParser.getInstance().getShakeLimitEntityVo(jsonString);
		//		ZxbItemEntityVo zxbItemEntityVo = JsonVoParser.getInstance().getzxbItemEntityVo(jsonString);
		BaseVo baseVo = JsonVoParser.getInstance().getBasevo(jsonString);
		if ((null != jsonString) && !"".equals(jsonString) && baseVo != null) {
			if (baseVo.getSuccess()) {
				cmd.success = true;
				cmd.resData = shakeLimitEntityVo;
			} else {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			}
		} else {
			if (baseVo != null) {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			} else {
				cmd.success = false;
				cmd.message = cmd.context
						.getString(R.string.the_network_is_dead);
			}
		}
		msg.obj = cmd;
		return msg;

	}
	//**
	 *
	 * 首页考证分类信息
	 * @param cmd
	 * @return

	@SuppressWarnings("unchecked")
	public Message executeClassyExam(Command cmd) {
		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
		String jsonString = CallServer.getInstance().callServer(cmd.method,
				hashMap, cmd.context);
		Message msg = Message.obtain();
		msg.what = cmd.commandID;
		HomePageBottomEntityVo homePageBottomEntityVo = JsonVoParser.getInstance().getHomePageBottomEntityVo(jsonString);
		BaseVo baseVo = JsonVoParser.getInstance().getBasevo(jsonString);
		if ((null != jsonString) && !"".equals(jsonString) && baseVo != null) {
			if (baseVo.getSuccess()) {
				cmd.success = true;
				cmd.resData = homePageBottomEntityVo;
			} else {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			}
		} else {
			if (baseVo != null) {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			} else {
				cmd.success = false;
				cmd.message = cmd.context
						.getString(R.string.the_network_is_dead);
			}
		}
		msg.obj = cmd;
		return msg;

	}
	 */
	/**
	 *
	 * WebUrl
	 * @param cmd
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public Message executeGetWebUrl(Command cmd) {
		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
		String jsonString = CallServer.getInstance().callServerldGet(cmd.method,
				hashMap, cmd.context);
		Message msg = Message.obtain();
		msg.what = cmd.commandID;
		ExamPageEntityVo expEntityVo = JsonVoParser.getInstance().getExamPageEntityVo(jsonString);
		BaseVo baseVo = JsonVoParser.getInstance().getBasevo(jsonString);
		if ((null != jsonString) && !"".equals(jsonString) && baseVo != null) {
			if (baseVo.getSuccess()) {
				cmd.success = true;
				cmd.resData = expEntityVo;
			} else {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			}
		} else {
			if (baseVo != null) {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			} else {
				cmd.success = false;
				cmd.message = cmd.context
						.getString(R.string.the_network_is_dead);
			}
		}
		msg.obj = cmd;
		return msg;

	}

	/**
	 * 获取手机验证码
	 * 
	 * @param cmd
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Message executeGetPhoneVerifycode(Command cmd) {
		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
		String jsonString = CallServer.getInstance().callServer(cmd.method,
				hashMap, cmd.context);
		Message msg = Message.obtain();
		msg.what = cmd.commandID;
		BaseVo baseVo = JsonVoParser.getInstance().getBasevo(jsonString);
		if ((null != jsonString) && !"".equals(jsonString) && baseVo != null) {
			if (baseVo.getSuccess()) {
				cmd.success = true;
				cmd.message = baseVo;
			} else {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			}
		} else {
			if (baseVo != null) {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			} else {
				cmd.success = false;
				cmd.stateCode = "100001";
				cmd.message = cmd.context
						.getString(R.string.the_network_is_dead);
			}
		}
		msg.obj = cmd;
		return msg;

	}
	/*
	 * 用户注册
	 * 
	 * @param cmd
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Message executeRegist(Command cmd) {
		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
		String jsonString = CallServer.getInstance().callServer(cmd.method,
				hashMap, cmd.context);
		Message msg = Message.obtain();
		msg.what = cmd.commandID;
		BaseVo baseVo = JsonVoParser.getInstance().getBasevo(jsonString);
		if ((null != jsonString) && !"".equals(jsonString) && baseVo != null) {
			if (baseVo.getSuccess()) {
				cmd.success = true;
				cmd.message = baseVo;
			} else {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			}
		} else {
			if (baseVo != null) {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			} else {
				cmd.success = false;
				cmd.stateCode = "100001";
				cmd.message = cmd.context
						.getString(R.string.the_network_is_dead);
			}
		}
		msg.obj = cmd;
		return msg;
		
	}

	/*//**
	 * 忘记密码
	 * 
	 * @param cmd
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public Message executeForgetPwd(Command cmd) {
		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
		String jsonString = CallServer.getInstance().callServer(cmd.method,
				hashMap, cmd.context);
		Message msg = Message.obtain();
		msg.what = cmd.commandID;
		BaseVo baseVo = JsonVoParser.getInstance().getBasevo(jsonString);
		if ((null != jsonString) && !"".equals(jsonString) && baseVo != null) {
			if (baseVo.getSuccess()) {
				cmd.success = true;
				cmd.message = baseVo;
			} else {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			}
		} else {
			if (baseVo != null) {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			} else {
				cmd.success = false;
				cmd.message = cmd.context
						.getString(R.string.the_network_is_dead);
			}
		}
		msg.obj = cmd;
		return msg;

	}

	*//**
	 * 获取所有课程
	 * 
	 * @param cmd
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public Message executeGetAllCourse(Command cmd) {
		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
		String jsonString = CallServer.getInstance().callServer(cmd.method,
				hashMap, cmd.context);
		Message msg = Message.obtain();
		msg.what = cmd.commandID;
		BaseVo baseVo = JsonVoParser.getInstance().getBasevo(jsonString);
		if ((null != jsonString) && !"".equals(jsonString) && baseVo != null) {
			if (baseVo.getSuccess()) {
				cmd.success = true;
				cmd.resData = baseVo;
			} else {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			}
		} else {
			if (baseVo != null) {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			} else {
				cmd.success = false;
				cmd.message = cmd.context
						.getString(R.string.the_network_is_dead);
			}
		}
		msg.obj = cmd;
		return msg;

	}
	*//**
	 * 获取试听课程
	 * 
	 * @param cmd
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public Message executeGetAuditionCourse(Command cmd) {
		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
		String jsonString = CallServer.getInstance().callServer(cmd.method,
				hashMap, cmd.context);
		Message msg = Message.obtain();
		msg.what = cmd.commandID;
		CourseEntityVo courseEntityVo = JsonVoParser.getInstance().getCourseEntityVo(jsonString);
		BaseVo baseVo = JsonVoParser.getInstance().getBasevo(jsonString);
		if ((null != jsonString) && !"".equals(jsonString) && baseVo != null) {
			if (baseVo.getSuccess()) {
				cmd.success = true;
				cmd.resData = courseEntityVo;
			} else {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			}
		} else {
			if (baseVo != null) {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			} else {
				cmd.success = false;
				cmd.message = cmd.context
						.getString(R.string.the_network_is_dead);
			}
		}
		msg.obj = cmd;
		return msg;

	}

	*//**
	 * 获取已购买课程
	 * 
	 * @param cmd
	 * @return

	@SuppressWarnings("unchecked")
	public Message executeGetPurchaseCourse(Command cmd) {
		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
		String jsonString = CallServer.getInstance().callServer(cmd.method,
				hashMap, cmd.context);
		Message msg = Message.obtain();
		msg.what = cmd.commandID;
		MyPurchaseCourseEntityVo purchaseCourseEntityVo = JsonVoParser.getInstance().getMyPurchaseCourseEntityVo(jsonString);
		BaseVo baseVo = JsonVoParser.getInstance().getBasevo(jsonString);
		if ((null != jsonString) && !"".equals(jsonString) && baseVo != null) {
			if (baseVo.getSuccess()) {
				cmd.success = true;
				cmd.resData = purchaseCourseEntityVo;
			} else {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			}
		} else {
			if (baseVo != null) {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			} else {
				cmd.success = false;
				cmd.message = cmd.context
						.getString(R.string.the_network_is_dead);
			}
		}
		msg.obj = cmd;
		return msg;

	}
	 */
	/**
	 * 获取收藏课程
	 * 
	 * @param cmd
	 * @return

	@SuppressWarnings("unchecked")
	public Message executeGetCollectCourse(Command cmd) {
		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
		String jsonString = CallServer.getInstance().callServer(cmd.method,
				hashMap, cmd.context);
		Message msg = Message.obtain();
		msg.what = cmd.commandID;
		CollectCourseEntityVo collectCourseEntityVo = JsonVoParser.getInstance().getCollectCourseEntityVo(jsonString);
		BaseVo baseVo = JsonVoParser.getInstance().getBasevo(jsonString);
		if ((null != jsonString) && !"".equals(jsonString) && baseVo != null) {
			if (baseVo.getSuccess()) {
				cmd.success = true;
				cmd.resData = collectCourseEntityVo;
			} else {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			}
		} else {
			if (baseVo != null) {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			} else {
				cmd.success = false;
				cmd.message = cmd.context
						.getString(R.string.the_network_is_dead);
			}
		}
		msg.obj = cmd;
		return msg;

	}
	 */
	/**
	 * 收藏课程
	 * 
	 * @param cmd
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public Message executeRequestCollectCourse(Command cmd) {
		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
		String jsonString = CallServer.getInstance().callServer(cmd.method,
				hashMap, cmd.context);
		Message msg = Message.obtain();
		msg.what = cmd.commandID;
		BaseVo baseVo = JsonVoParser.getInstance().getBasevo(jsonString);
		if ((null != jsonString) && !"".equals(jsonString) && baseVo != null) {
			if (baseVo.getSuccess()) {
				cmd.success = true;
				cmd.resData = baseVo;
			} else {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			}
		} else {
			if (baseVo != null) {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			} else {
				cmd.success = false;
				cmd.message = cmd.context
						.getString(R.string.the_network_is_dead);
			}
		}
		msg.obj = cmd;
		return msg;

	}
	*//**
	 * 删除收藏item
	 * 
	 * @param cmd
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public Message executeDelFavCourse(Command cmd) {
		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
		String jsonString = CallServer.getInstance().callServer(cmd.method,
				hashMap, cmd.context);
		Message msg = Message.obtain();
		msg.what = cmd.commandID;
		BaseVo baseVo = JsonVoParser.getInstance().getBasevo(jsonString);
		if ((null != jsonString) && !"".equals(jsonString) && baseVo != null) {
			if (baseVo.getSuccess()) {
				cmd.success = true;
				cmd.resData = baseVo;
			} else {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			}
		} else {
			if (baseVo != null) {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			} else {
				cmd.success = false;
				cmd.message = cmd.context
						.getString(R.string.the_network_is_dead);
			}
		}
		msg.obj = cmd;
		return msg;

	}
	*//**
	 * 获取课程详情
	 * 
	 * @param cmd
	 * @return

	@SuppressWarnings("unchecked")
	public Message executeGetParticularCourse(Command cmd) {
		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
		String jsonString = CallServer.getInstance().callServer(cmd.method,
				hashMap, cmd.context);
		Message msg = Message.obtain();
		msg.what = cmd.commandID;
		ParticularCourseEntityVo particularCourseEntityVo = JsonVoParser.getInstance().getParticularCourseEntityvo(jsonString);
		BaseVo baseVo = JsonVoParser.getInstance().getBasevo(jsonString);
		if ((null != jsonString) && !"".equals(jsonString) && baseVo != null) {
			if (baseVo.getSuccess()) {
				cmd.success = true;
				cmd.resData = particularCourseEntityVo;
			} else {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			}
		} else {
			if (baseVo != null) {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			} else {
				cmd.success = false;
				cmd.message = cmd.context
						.getString(R.string.the_network_is_dead);
			}
		}
		msg.obj = cmd;
		return msg;

	}
	 */
	/**
	 * 获取课程讨论
	 * 
	 * @param cmd
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public Message executeGetDiscussCourse(Command cmd) {
		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
		String jsonString = CallServer.getInstance().callServer(cmd.method,
				hashMap, cmd.context);
		Message msg = Message.obtain();
		msg.what = cmd.commandID;
		DiscussCourseEntityVo discussCourseEntityVo = JsonVoParser.getInstance().getDiscussCourseEntityVo(jsonString);
		BaseVo baseVo = JsonVoParser.getInstance().getBasevo(jsonString);
		if ((null != jsonString) && !"".equals(jsonString) && baseVo != null) {
			if (baseVo.getSuccess()) {
				cmd.success = true;
				cmd.resData = discussCourseEntityVo;
			} else {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			}
		} else {
			if (baseVo != null) {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			} else {
				cmd.success = false;
				cmd.message = cmd.context
						.getString(R.string.the_network_is_dead);
			}
		}
		msg.obj = cmd;
		return msg;

	}
	*//**
	 * 发表课程讨论
	 * 
	 * @param cmd
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public Message executeSendDiscussCourse(Command cmd) {
		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
		String jsonString = CallServer.getInstance().callServer(cmd.method,
				hashMap, cmd.context);
		Message msg = Message.obtain();
		msg.what = cmd.commandID;
		//		DiscussCourseEntityVo discussCourseEntityVo = JsonVoParser.getInstance().getDiscussCourseEntityVo(jsonString);
		BaseVo baseVo = JsonVoParser.getInstance().getBasevo(jsonString);
		if ((null != jsonString) && !"".equals(jsonString) && baseVo != null) {
			if (baseVo.getSuccess()) {
				cmd.success = true;
				cmd.resData = baseVo;
			} else {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			}
		} else {
			if (baseVo != null) {
				cmd.message = baseVo.getMessage();
				cmd.success = false;
			} else {
				cmd.success = false;
				cmd.message = cmd.context
						.getString(R.string.the_network_is_dead);
			}
		}
		msg.obj = cmd;
		return msg;

	}*/
	//	/**
	//	 * 注册
	//	 *
	//	 * @param cmd
	//	 * @return
	//	 */
	//	@SuppressWarnings("unchecked")
	//	public Message executeRegist(Command cmd) {
	//		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
	//		String jsonString = CallServer.getInstance().callServer(cmd.method,
	//				hashMap, cmd.context);
	//		Message msg = Message.obtain();
	//		msg.what = cmd.commandID;
	//		BaseVo baseVo = JsonVoParser.getInstance().getBasevo(jsonString);
	//		if ((null != jsonString) && !"".equals(jsonString) && baseVo != null) {
	//			if (SUCCESS.equals(baseVo.getSuccess())) {
	//				cmd.success = true;
	//				cmd.message = baseVo;
	//			} else {
	//				cmd.stateCode = baseVo.getCode();
	//				cmd.message = baseVo.getInfo();
	//				cmd.success = false;
	//			}
	//		} else {
	//			if (baseVo != null) {
	//				cmd.stateCode = baseVo.getCode();
	//				cmd.message = baseVo.getInfo();
	//				cmd.success = false;
	//			} else {
	//				cmd.success = false;
	//				cmd.stateCode = "100001";
	//				cmd.message = cmd.context
	//						.getString(R.string.the_network_is_dead);
	//			}
	//		}
	//		msg.obj = cmd;
	//		return msg;
	//
	//	}
	//	/**
	//	 * 修改手机号
	//	 * 
	//	 * @param cmd
	//	 * @return
	//	 */
	//	@SuppressWarnings("unchecked")
	//	public Message executeChangePhone(Command cmd) {
	//		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
	//		String jsonString = CallServer.getInstance().callServer(cmd.method,
	//				hashMap, cmd.context);
	//		Message msg = Message.obtain();
	//		msg.what = cmd.commandID;
	//		BaseVo baseVo = JsonVoParser.getInstance().getBasevo(jsonString);
	//		if ((null != jsonString) && !"".equals(jsonString) && baseVo != null) {
	//			if (SUCCESS.equals(baseVo.getSuccess())) {
	//				cmd.success = true;
	//				cmd.message = baseVo;
	//			} else {
	//				cmd.stateCode = baseVo.getCode();
	//				cmd.message = baseVo.getInfo();
	//				cmd.success = false;
	//			}
	//		} else {
	//			if (baseVo != null) {
	//				cmd.stateCode = baseVo.getCode();
	//				cmd.message = baseVo.getInfo();
	//				cmd.success = false;
	//			} else {
	//				cmd.success = false;
	//				cmd.stateCode = "100001";
	//				cmd.message = cmd.context
	//						.getString(R.string.the_network_is_dead);
	//			}
	//		}
	//		msg.obj = cmd;
	//		return msg;
	//
	//	}
	/**
	 * 修改密码
	 * 
	 * @param cmd
	 * @return
	 */
	//	@SuppressWarnings("unchecked")
	//	public Message executeChangePwd(Command cmd) {
	//		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
	//		String jsonString = CallServer.getInstance().callServer(cmd.method,
	//				hashMap, cmd.context);
	//		Message msg = Message.obtain();
	//		msg.what = cmd.commandID;
	//		BaseVo baseVo = JsonVoParser.getInstance().getBasevo(jsonString);
	//		if ((null != jsonString) && !"".equals(jsonString) && baseVo != null) {
	//			if (SUCCESS.equals(baseVo.getSuccess())) {
	//				cmd.success = true;
	//				cmd.message = baseVo;
	//			} else {
	//				cmd.stateCode = baseVo.getCode();
	//				cmd.message = baseVo.getInfo();
	//				cmd.success = false;
	//			}
	//		} else {
	//			if (baseVo != null) {
	//				cmd.stateCode = baseVo.getCode();
	//				cmd.message = baseVo.getInfo();
	//				cmd.success = false;
	//			} else {
	//				cmd.success = false;
	//				cmd.stateCode = "100001";
	//				cmd.message = cmd.context
	//						.getString(R.string.the_network_is_dead);
	//			}
	//		}
	//		msg.obj = cmd;
	//		return msg;
	//
	//	}
	/**
	 * 登出
	 * 
	 * @param cmd
	 * @return
	 */
	//	@SuppressWarnings("unchecked")
	//	public Message executeLogOut(Command cmd) {
	//		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
	//		String jsonString = CallServer.getInstance().callServer(cmd.method,
	//				hashMap, cmd.context);
	//		Message msg = Message.obtain();
	//		msg.what = cmd.commandID;
	//		BaseVo baseVo = JsonVoParser.getInstance().getBasevo(jsonString);
	//		if ((null != jsonString) && !"".equals(jsonString) && baseVo != null) {
	//			if (SUCCESS.equals(baseVo.getSuccess())) {
	//				cmd.success = true;
	//				cmd.message = baseVo;
	//			} else {
	//				cmd.stateCode = baseVo.getCode();
	//				cmd.message = baseVo.getInfo();
	//				cmd.success = false;
	//			}
	//		} else {
	//			if (baseVo != null) {
	//				cmd.stateCode = baseVo.getCode();
	//				cmd.message = baseVo.getInfo();
	//				cmd.success = false;
	//			} else {
	//				cmd.success = false;
	//				cmd.stateCode = "100001";
	//				cmd.message = cmd.context
	//						.getString(R.string.the_network_is_dead);
	//			}
	//		}
	//		msg.obj = cmd;
	//		return msg;
	//
	//	}
	/**
	 *自动登录
	 * 
	 * @param cmd
	 * @return
	 */
	//	@SuppressWarnings("unchecked")
	//	public Message executeAutoLogin(Command cmd) {
	//		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
	//		String jsonString = CallServer.getInstance().callServer(cmd.method,
	//				hashMap, cmd.context);
	//		Message msg = Message.obtain();
	//		msg.what = cmd.commandID;
	//		UserLoginBodyVo vo = JsonVoParser.getInstance().getUserLoginBodyVo(
	//				jsonString);
	//		BaseVo baseVo = JsonVoParser.getInstance().getBasevo(jsonString);
	//		if ((null != jsonString) && !"".equals(jsonString) && baseVo != null) {
	//			if (SUCCESS.equals(baseVo.getSuccess())) {
	//				cmd.isSuccess = true;
	//				cmd.resData = baseVo;
	//			} else {
	//				//				cmd.stateCode = baseVo.getCode();
	//				cmd.resData = baseVo.getInfo();
	//				cmd.isSuccess = false;
	//			}
	//		} else {
	//			if (baseVo != null) {
	//				//				cmd.stateCode = baseVo.getCode();
	//				cmd.resData = baseVo.getInfo();
	//				cmd.isSuccess = false;
	//			} else {
	//				cmd.isSuccess = false;
	//				cmd.stateCode = "100001";
	//				cmd.resData = cmd.context
	//						.getString(R.string.the_network_is_dead);
	//			}
	//		}
	//		msg.obj = cmd;
	//		return msg;
	//		
	//	}

	//	@SuppressWarnings("unchecked")
	//	public Message executeAutoLogin(Command cmd) {
	//		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
	//		String jsonString = CallServer.getInstance().callServer(cmd.method,
	//				hashMap, cmd.context);
	//		Message msg = Message.obtain();
	//		msg.what = cmd.commandID;
	//		UserLoginBodyVo vo = JsonVoParser.getInstance().getUserLoginBodyVo(
	//				jsonString);
	//
	//		BaseVo baseVo = JsonVoParser.getInstance().getBasevo(jsonString);
	//		if ((null != jsonString) && !"".equals(jsonString) && vo != null) {
	//			if (SUCCESS.equals(vo.getSuccess())) {
	//				cmd.isSuccess = true;
	//				cmd.resData = vo;
	//			} else {
	//				cmd.stateCode = vo.getCode();
	//				cmd.resData = vo.getInfo();
	//				cmd.isSuccess = false;
	//			}
	//		} else {
	//			if (baseVo != null) {
	//				cmd.stateCode = baseVo.getCode();
	//				cmd.resData = baseVo.getInfo();
	//				cmd.isSuccess = false;
	//			} else {
	//				cmd.isSuccess = false;
	//				cmd.stateCode = "100001";
	//				cmd.resData = cmd.context
	//						.getString(R.string.the_network_is_dead);
	//			}
	//		}
	//		msg.obj = cmd;
	//		return msg;
	//	}

		/**
		 *
		 * 判断是否实名认证
		 * @param cmd
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public Message executeAccountInfo(Command cmd) {
			HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
			String jsonString = CallServer.getInstance().callServer(cmd.method,
					hashMap, cmd.context);
			Message msg = Message.obtain();
			msg.what = cmd.commandID;
//			CertificateInfoBodyVo vo=JsonVoParser.getInstance().getCertificateInfoBodyVo(jsonString);
//			BaseVo baseVo = JsonVoParser.getInstance().getBasevo(jsonString);
//			if ((null != jsonString) && !"".equals(jsonString) && vo != null) {
//				if (SUCCESS.equals(baseVo.getSuccess())) {
//					cmd.success = true;
//					cmd.resData = baseVo;
//					cmd.message = baseVo.getMessage();
//				} else {
//					cmd.success = false;
//					cmd.message = baseVo.getMessage();
//				}
//			} else {
//				if (baseVo != null) {
//					cmd.message = baseVo.getMessage();
//					cmd.success = false;
//				} else {
//					cmd.success = false;
//					cmd.stateCode = "100001";
//					cmd.resData = cmd.context
//							.getString(R.string.the_network_is_dead);
//					cmd.message = baseVo.getMessage();
//				}
//			}
			msg.obj = cmd;
			return msg;

		}
	//	/**
	//	 *
	//	 * 进行实名认证
	//	 * @param cmd
	//	 * @return
	//	 */
	//	@SuppressWarnings("unchecked")
	//	public Message executeCertificate(Command cmd) {
	//		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
	//		String jsonString = CallServer.getInstance().callServer(cmd.method,
	//				hashMap, cmd.context);
	//		Message msg = Message.obtain();
	//		msg.what = cmd.commandID;
	//
	//		BaseVo baseVo = JsonVoParser.getInstance().getBasevo(jsonString);
	//		if ((null != jsonString) && !"".equals(jsonString) && baseVo != null) {
	//			if (SUCCESS.equals(baseVo.getSuccess())) {
	//				cmd.isSuccess = true;
	//				cmd.resData = baseVo;
	//			} else {
	//				cmd.stateCode = baseVo.getCode();
	//				cmd.resData = baseVo.getInfo();
	//				cmd.isSuccess = false;
	//			}
	//		} else {
	//			if (baseVo != null) {
	//				cmd.stateCode = baseVo.getCode();
	//				cmd.resData = baseVo.getInfo();
	//				cmd.isSuccess = false;
	//			} else {
	//				cmd.isSuccess = false;
	//				cmd.stateCode = "100001";
	//				cmd.resData = cmd.context
	//						.getString(R.string.the_network_is_dead);
	//			}
	//		}
	//		msg.obj = cmd;
	//		return msg;
	//
	//	}
	/**获取账户信息
	public Message executeUserInfo(Command cmd){
		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
		String jsonString = CallServer.getInstance().callServer(cmd.method,
								hashMap, cmd.context);
		Message msg = Message.obtain();
		msg.what = cmd.commandID;
		MineEntity mineEntity = JsonVoParser.getInstance().getMineEntity(jsonString);
		if ((null != jsonString) && !"".equals(jsonString) && mineEntity != null) {
			if (mineEntity.isSuccess()) {
						cmd.success = true;
						cmd.resData = mineEntity;
						cmd.message = mineEntity.getMessage();
			} else {
						cmd.success = false;
						cmd.message = mineEntity.getMessage();
			}
		} else {
			if (mineEntity != null) {
						cmd.success = false;
						cmd.message = mineEntity.getMessage();
			} else {
						cmd.success= false;
						cmd.stateCode = "100001";
						cmd.resData = cmd.context
								.getString(R.string.the_network_is_dead);
						cmd.message = mineEntity.getMessage();
			}
		}
		msg.obj = cmd;
		return msg;
	}
	 */
	/*个人头像修改*/
	@SuppressWarnings("unchecked")
	public Message executeUserPhoto(Command cmd) {
		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
		String jsonString = CallServer.getInstance().callServer(cmd.method,
				hashMap, cmd.context);
		Message msg = Message.obtain();
		msg.what = cmd.commandID;
		BaseVo baseVo = JsonVoParser.getInstance().getBasevo(jsonString);
//		if ((null != jsonString) && !"".equals(jsonString) && baseVo != null) {
//			if (baseVo.getSuccess()) {
//				cmd.success = true;
//				cmd.resData = homePageBottomEntityVo;
//			} else {
//				cmd.message = baseVo.getMessage();
//				cmd.success = false;
//			}
//		} else {
//			if (baseVo != null) {
//				cmd.message = baseVo.getMessage();
//				cmd.success = false;
//			} else {
//				cmd.success = false;
//				cmd.message = cmd.context
//						.getString(R.string.the_network_is_dead);
//			}
//		}
		msg.obj = cmd;
		return msg;

	}
	/**资讯新闻列表
	public static Message executeInformation(Command command) {
		HashMap<String, String> hashMap = (HashMap<String, String>) command.param;
		String jsonString = CallServer.getInstance().callServers(command.method,
				hashMap, command.context);
		Message msg = Message.obtain();
		msg.what = command.commandID;
		infor infor = JsonVoParser.getInstance().getInformationEntityVo(jsonString);
		if ((null != jsonString) && !"".equals(jsonString) && infor != null) {
//			if (infor.isSuccess()) {
				command.success = true;
				command.resData = infor;
//			} else {
//				command.message = infor.getMessage();
//				command.success = false;
//			}
		} else {
			if (infor != null) {
//				command.message = infor.getMessage();
				command.success = false;
			} else {
				command.success = false;
				command.message = command.context
						.getString(R.string.the_network_is_dead);
			}
		}
		msg.obj = command;
		return msg;
	}
	 */
	/*个人头像修改
	@SuppressWarnings("unchecked")
	public Message executeSearch(Command cmd) {
		HashMap<String, String> hashMap = (HashMap<String, String>) cmd.param;
		String jsonString = CallServer.getInstance().callServer(cmd.method,
				hashMap, cmd.context);
		Message msg = Message.obtain();
		msg.what = cmd.commandID;
		SearchEntity searchEntity = JsonVoParser.getInstance().getSearchEntity(jsonString);
		if ((null != jsonString) && !"".equals(jsonString) && searchEntity != null) {
			if (searchEntity.isSuccess()) {
				cmd.success = true;
				cmd.resData = searchEntity;
			} else {
				cmd.message = searchEntity.getMessage();
				cmd.success = false;
			}
		} else {
			if (searchEntity != null) {
				cmd.message = searchEntity.getMessage();
				cmd.success = false;
			} else {
				cmd.success = false;
				cmd.message = cmd.context
						.getString(R.string.the_network_is_dead);
			}
		}
		msg.obj = cmd;
		return msg;
	}
	*/
}
