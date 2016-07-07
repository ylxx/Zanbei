package com.langdunzx.www.zanbei.application;


import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap.Config;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import com.langdunzx.www.zanbei.config.Constants;
import com.langdunzx.www.zanbei.db.SQLHelper;
import com.langdunzx.www.zanbei.utils.ImageDownLoader;
import com.langdunzx.www.zanbei.utils.LogUtil;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.socialize.PlatformConfig;




/**
 * <pre>
 * 业务名:
 * 功能说明:  极光推送一般建议在自定义 Application 类里初始化。也可以在主 Activity 里。
 * 编写日期:	2016年3月7日11:46:28
 * 作者:	dc
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */

public class LangDunApplication extends Application {

	public static LangDunApplication instance;
	public static LangDunApplication sApplication;
	private boolean sdCardExist;// 判斷sd卡是否存在
	public static boolean isShowingDialog = false;
	public static boolean timeOutOrLoginCrowdOut = false;
	
	private SQLHelper sqlHelper;

	public static LangDunApplication islangdunapplication;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		sApplication = this;
		init();
		// 获得用户手机分辨率，分别为宽度、高度
		DisplayMetrics metric = getApplicationContext().getResources()
				.getDisplayMetrics();
		Constants.SCREEN_WIDTH = metric.widthPixels;
		Constants.SCREEN_HEIGHT = metric.heightPixels;
		LogUtil.e("屏幕宽度为：", Integer.toString(Constants.SCREEN_WIDTH));

		sdCardExist = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);

		initImageLoader();

		// 获取版本号
		PackageManager mPckManager = this.getPackageManager();

		try {
			PackageInfo info = mPckManager.getPackageInfo(
					this.getPackageName(), 0);
			Constants.VERSIONCODE = info.versionCode;
			Constants.VERSIONNAME = info.versionName;
			LogUtil.e("版本号为：", String.valueOf(Constants.VERSIONNAME));

		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		// 获取系统版本
		Constants.SYSTEM_VERSION = android.os.Build.VERSION.SDK_INT;
		LogUtil.e("系统版本号为：", String.valueOf(Constants.SYSTEM_VERSION));

		getSIMInfo();
	}

	/**
	 * 初始化友盟分享跟登录的appkey
	 * */
	private void init() {
		// 微信初始化
		PlatformConfig.setWeixin("wx3b7777750d25fffb",
				"de3504f9141dd3b472038f281c49fb85");
		// qq跟qq空间初始化
		PlatformConfig.setQQZone("1105131263", "ivBjVphxorUmIMv0");
		// 微博的初始化
		PlatformConfig.setSinaWeibo("3918897028",
				"319ea7808beff80e3bf47911b668bc00");
	}

	/**
	 * 初始化imageLoader
	 */

	private void initImageLoader() {
		DisplayMetrics displayMetrics = getApplicationContext().getResources()
				.getDisplayMetrics();
		DisplayImageOptions options = new DisplayImageOptions.Builder()
		// .showImageOnLoading(R.drawable._0000_6) // 设置图片在下载期间显示的图片
		// .showImageForEmptyUri(R.drawable._0000_6)//
		// 设置图片Uri为空或是错误的时候显示的图片
		// .showImageOnFail(R.drawable._0000_6) // 设置图片加载/解码过程中错误时候显示的图片
				.cacheInMemory(true)// 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true).bitmapConfig(Config.RGB_565)// 设置下载的图片是否缓存在SD卡中
				.build();// 构建完成
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this)
				.memoryCache(new UsingFreqLimitedMemoryCache(4 * 1024 * 1024))
				// You can pass your own memory cache
				// implementation/你可以通过自己的内存缓存实现
				.memoryCacheSize(5 * 1024 * 1024)
				.discCacheSize(50 * 1024 * 1024)
				.memoryCacheExtraOptions(
						(int) (displayMetrics.widthPixels
								/ displayMetrics.density * 0.8),
						(int) (displayMetrics.heightPixels
								/ displayMetrics.density * 0.8))
				.imageDownloader(new ImageDownLoader(getApplicationContext())) // connectTimeout
				.defaultDisplayImageOptions(options).build();// 开始构建
		ImageLoader.getInstance().init(config);// 全局初始化此配置

	}

	/**
	 * 获取运营商
	 * 
	 * void
	 */
	private void getSIMInfo() {
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String operator = tm.getSimOperator();

		if (null != operator) {
			if (operator.equals("46000") || operator.equals("46002")) {
				Constants.OPERATOR = 0;
				// 中国移动
			} else if (operator.equals("46001")) {
				// 中国联通
				Constants.OPERATOR = 1;
			} else if (operator.equals("46003")) {
				// 中国电信
				Constants.OPERATOR = 2;
			}
		}
	}

	public static LangDunApplication getInstance() {
		return instance;
	}
	
	/**获取数据库Helper */
	public SQLHelper getSQLHelper() {
		if (sqlHelper == null)
			sqlHelper = new SQLHelper(sApplication);
		return sqlHelper;
	}
	
	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		if (sqlHelper != null)
			sqlHelper.close();
		super.onTerminate();
		//整体摧毁的时候调用这个方法
	}
	
	/** 获取Application */
	public static LangDunApplication getApp() {
		return sApplication;
	}

}
