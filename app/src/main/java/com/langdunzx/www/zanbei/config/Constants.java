package com.langdunzx.www.zanbei.config;

public class Constants {
	/**
	 * 初始化用户信息键
	 */
	public static final String INIT_USER_INFO = "userInfo";

	public static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDe85G79FkpCymgQ3jPZ5qftwFsuNpMGka8+3/JpVfe728tqAO+DQHrDroaNqu6tW6XdfhBvUir7DgLq0ZE1Zlz/HwLphLhy9a0ZEoeq3SzSw9PFQAYVc9ayIclTgcfaF6T6ENGm1NodnE9YQ24N8KoaheYdc9e1woyHnecBZodBwIDAQAB";

	// 设备(运营商)
	public static int OPERATOR;
	// 屏幕宽高
	public static int SCREEN_WIDTH = 0;
	public static int SCREEN_HEIGHT = 0;
	// 版本号CODE
	public static int VERSIONCODE;
	// 版本号NAME
	public static String VERSIONNAME;
	// 系统版本
	public static int SYSTEM_VERSION;
	// 初始化请求标识
	public static int INIT_POST_IDENTIFIER = 100;
	public static int SHILI= ++INIT_POST_IDENTIFIER;
	public static int CONNECTION_TIME_OUT= 25*1000;
	// 取消请求标识
	public static final int CANCEL_POST_IDENTIFIER = ++INIT_POST_IDENTIFIER;
	public static final int GET_ALL_COURSE = ++INIT_POST_IDENTIFIER;//所有课程
	public static final int GET_AUDITION_COURSE = ++INIT_POST_IDENTIFIER;//试听课程
	public static final int GET_COLLECT_COURSE = ++INIT_POST_IDENTIFIER;//获得收藏课程
	public static final int GET_PURCHASE_COURSE = ++INIT_POST_IDENTIFIER;//获得已购买课程
	public static final int REQUEST_COLLECT_COURSE = ++INIT_POST_IDENTIFIER;//收藏课程
	public static final int GET_PARTICULAR_COURSE = ++INIT_POST_IDENTIFIER;//获得相关课程
	public static final int GET_DISCUSS_COURSE = ++INIT_POST_IDENTIFIER;//获得相关课程讨论
	public static final int SEND_DISCUSS_COURSE = ++INIT_POST_IDENTIFIER;//发表相关课程讨论
	public static final int GET_WEBURL = ++INIT_POST_IDENTIFIER;//获得WEBURL
	public static final int TEXT=++INIT_POST_IDENTIFIER;
	//模块请求标识列表
	public static final int LOGIN=++INIT_POST_IDENTIFIER;
	//忘记密码
	public static final int FORGET_PWD=++INIT_POST_IDENTIFIER;
	//获取验证码
	public static final int GET_PHONE_VERRIFYCODE=++INIT_POST_IDENTIFIER;
	//意见反馈
	public static final int SUGGESTION_FEEDBACK=++INIT_POST_IDENTIFIER;
	//客服信息
	public static final int CALL_SERVER_INFO=++INIT_POST_IDENTIFIER;
	//收益查询
	public static final int QUERY_BENEFIT=++INIT_POST_IDENTIFIER;
	//产品详情
	public static final int PROJECT_INFO=++INIT_POST_IDENTIFIER;
	//注册
	public static final int REGISTER=++INIT_POST_IDENTIFIER;
	//修改手机号(登录名)
	public static final int MODIFY_PHONE=++INIT_POST_IDENTIFIER;
	//修改登录密码
	public static final int MODIFY_PWD=++INIT_POST_IDENTIFIER;
	//赎回审核
	public static final int REDEMPTION_AUDIT=++INIT_POST_IDENTIFIER;
	//赎回债券
	public static final int CALL_BONDS=++INIT_POST_IDENTIFIER;
	//赎回记录查询
	public static final int REDEEM_RECORD=++INIT_POST_IDENTIFIER;
	//债券信息显示
	public static final int CALL_BONDS_INFO=++INIT_POST_IDENTIFIER;
	//登出
	public static final int LOG_OUT=++INIT_POST_IDENTIFIER;
	//账户信息
	public static final int  ACCOUNT_INFO=++INIT_POST_IDENTIFIER;
	//首页购买页即剩余购买份额
	public static final int SURPLUSCREDITOR=++INIT_POST_IDENTIFIER;
	//自动登录
	public static final int AUTOLOGIN=++INIT_POST_IDENTIFIER;
	//交易记录查询
	public static final int TRADE_RECORD=++INIT_POST_IDENTIFIER;
	//宜定投项目
	public static final int E_DINGTOU=++INIT_POST_IDENTIFIER;
	//首页数据
	public static final int HOME_DATA=++INIT_POST_IDENTIFIER;
	//助学宝数据
	public static final int ZXB_DATA=++INIT_POST_IDENTIFIER;
	//助学宝Item数据
	public static final int ZXB_ITEM_DATA=++INIT_POST_IDENTIFIER;
	//摇一摇额度
	public static final int SHAKE_LIMIT=++INIT_POST_IDENTIFIER;
	//首页考证分类
	public static final int CLASSY_EXAM=++INIT_POST_IDENTIFIER;
	//用户信息
	public static final int USER_INFO=++INIT_POST_IDENTIFIER;
	//实名认证
	public static final int CERTIFICATION_INFO=++INIT_POST_IDENTIFIER;
	//购买债权
	public static final int BUY_CREDITOR=++INIT_POST_IDENTIFIER;
	//充值
	public static final int RECHARGE=++INIT_POST_IDENTIFIER;
	//提现
	public static final int WITHDRAW=++INIT_POST_IDENTIFIER;
	//删除收藏
	public static final int DEL_FAVCRS=++INIT_POST_IDENTIFIER;
	//个人头像
	public static final int USER_PHOTO=++INIT_POST_IDENTIFIER;
	//资讯列表
	public static final int INFORMATION=++INIT_POST_IDENTIFIER;
	//用户查询
	public static final int SEARCH=++INIT_POST_IDENTIFIER;
	//用户查询
	public static final int WXLOGIN=++INIT_POST_IDENTIFIER;
	//好友列表
	public static final int GET_FRIENDS_DATA=++INIT_POST_IDENTIFIER;
}
