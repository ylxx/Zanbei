package com.langdunzx.www.zanbei.net;

import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import com.langdunzx.www.zanbei.config.Constants;
import com.langdunzx.www.zanbei.utils.Logge;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

public class CallServer {
	private String HTTP_URL = "http://api.anydo.com/Home/Api/";// 外网
//	private String HTTP_URL = "http://www.langdunzx.com/";// 外网
//	private String HTTP_URL_HOME_DATA = "http://www.langdunzx.com/";// 首页轮播图
	private String HTTP_URL_HOME_DATA = "http://un.ehgo.com/u/255/";// 首页轮播图
	private static CallServer _callServer;

	public synchronized static CallServer getInstance() {
		if (null == _callServer) {
			_callServer = new CallServer();
		}
		return _callServer;
	}
	/**
	 * HTTP
	 * 
	 * @return HttpClient
	 * 
	 *         SO_TIMEOUT 表示接受数据时的超时时间
	 */
	private static HttpClient getHttpClient() {
		HttpClient client = new DefaultHttpClient(new BasicHttpParams());
		client.getParams().setIntParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT,
				Constants.CONNECTION_TIME_OUT);
		client.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT,
				Constants.CONNECTION_TIME_OUT);
		return client;
	}

	/**
	 * HTTPS
	 * 
	 * @return
	 */
	private DefaultHttpClient getHttpsClient(Context context) {
		DefaultHttpClient client = new CustomHttpsClient(context);
		client.getParams().setIntParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT,
				Constants.CONNECTION_TIME_OUT);
		client.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT,
				Constants.CONNECTION_TIME_OUT);

		return client;
	}

//	/**
//	 * 使用HTTPS POST方式
//	 * 
//	 * @param reqParmas
//	 * @return
//	 */
//	private String goHttpsPost(String method,
//			HashMap<String, String> reqParmas, Context context) {
//		Logge.LogI("http is : " + HTTPS_URL);
//		Logge.LogI("method is : " + method);
//
//		String uri = HTTPS_URL + method;
//		Logge.LogI("uri is : " + uri);
//		String result = null;
//		HttpPost post = new HttpPost(uri);
//		HttpResponse response;
//		try {
//			List<org.apache.http.NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//			Set<String> paramsKeySet = reqParmas.keySet(); 
//			Iterator<String> ite = paramsKeySet.iterator();
//			while (ite.hasNext()) {
//				String key = ite.next();
//				Logge.LogI("param is : " + key + " = " + reqParmas.get(key));
//				nameValuePairs.add(new BasicNameValuePair(key, reqParmas
//						.get(key)));
//			}
//			System.out.println(nameValuePairs);
//			//FastJsonHelp.hashMapToJson(reqParmas);
//			post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));
//			//   			post.setEntity(new StringEntity(reqParmas.toString(), "utf-8"));
//			/**
//			 * 创建Http Header
//			 */
//			createPostHeader(context, post);
//			DefaultHttpClient httpClient = (DefaultHttpClient) getHttpsClient(context);
//			response = httpClient.execute(post);
//			Logge.LogI("发出https请求----------了");
//			if (response.getStatusLine().getStatusCode() != 404) {
//				result = EntityUtils.toString(response.getEntity(), "utf-8");
//			} else {
//				Logge.LogI("" + response.getStatusLine().getStatusCode());
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		} finally {
//			post.abort();
//		}
//		Logge.LogI("https请求服务器返回数据 : " + result);
//		return result;
//	}

	/**
	 * HTTP POST请求(268)
	 * 
	 * @param method
	 * @param reqParams
	 * @param context
	 * @return
	 */
	private String goHttpPost(String method, HashMap<String, String> reqParams,
			Context context) {
		Logge.LogI("url is : " + HTTP_URL);
		Logge.LogI("method is : " + method);
		String result = null;
		String uri = HTTP_URL + method;
		Logge.LogI("uri is : " + uri);
		HttpPost post = new HttpPost(uri);
		HttpResponse response;
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			Set<String> paramKeySet = reqParams.keySet();
			Iterator<String> iterator = paramKeySet.iterator();
			while (iterator.hasNext()) {

				String key = iterator.next();
				Logge.LogI("param is : " + key + " = " + reqParams.get(key));
				nameValuePairs.add(new BasicNameValuePair(key, reqParams
						.get(key)));
			}
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));
			Logge.LogI(post.getURI().toString());
			/**
			 * 创建Http Header
			 */
			// createPostHeader(context, post);

			DefaultHttpClient httpClient = (DefaultHttpClient) getHttpClient();
			response = httpClient.execute(post);
			Logge.LogI("postUrl | " +post.getURI().toString());
			if (response.getStatusLine().getStatusCode() != 404) {
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			} else {
				Logge.LogI("" + response.getStatusLine().getStatusCode());
			}
		} catch (IOException e) { 
			e.printStackTrace();
			Logge.LogI("CallServer | " + e.getMessage());
			return null;
		} finally {
			post.abort();
		}
		Logge.LogI("请求服务器返回数据 : " + result);
		return result;
	}



	/**
	 * HTTP POST请求(朗顿)
	 * 
	 * @param method
	 * @param reqParams
	 * @param context
	 * @return
	 */
	private String goHttpPostld(String method, HashMap<String, String> reqParams,
			Context context) {
		Logge.LogI("url is : " + HTTP_URL_HOME_DATA);
		Logge.LogI("method is : " + method);
		String result = null;
		String uri = HTTP_URL_HOME_DATA + method;
		Logge.LogI("uri is : " + uri);
		HttpPost post = new HttpPost(uri);
		HttpResponse response;

		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			Set<String> paramKeySet = reqParams.keySet();
			Iterator<String> iterator = paramKeySet.iterator();
			while (iterator.hasNext()) {

				String key = iterator.next();
				Logge.LogI("param is : " + key + " = " + reqParams.get(key));
				nameValuePairs.add(new BasicNameValuePair(key, reqParams
						.get(key)));

			}

			post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));
			Logge.LogI(post.getURI().toString());
			/**
			 * 创建Http Header
			 */
			// createPostHeader(context, post);

			DefaultHttpClient httpClient = (DefaultHttpClient) getHttpClient();
			response = httpClient.execute(post);

			if (response.getStatusLine().getStatusCode() != 404) {
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			} else {
				Logge.LogI("" + response.getStatusLine().getStatusCode());
			}
		} catch (IOException e) { 
			e.printStackTrace();
			Logge.LogI("CallServer | " + e.getMessage());
			return null;
		} finally {
			post.abort();
		}
		Logge.LogI("请求服务器返回数据 : " + result);
		return result;
	}
	/**
	 * HTTP GET请求(朗顿)
	 * 
	 * @param method
	 * @param reqParams
	 * @param context
	 * @return
	 */
	private String goHttpGetld(String method, HashMap<String, String> reqParams,
			Context context) {
		Logge.LogI("url is : " + HTTP_URL_HOME_DATA);
		Logge.LogI("method is : " + method);
		String result = null;
		String APIuri = HTTP_URL_HOME_DATA + method + "?";
		Set<String> paramKeySet = reqParams.keySet();
		Iterator<String> iterator = paramKeySet.iterator();
		while (iterator.hasNext()) {
			
			String key = iterator.next();
			Logge.LogI("param is : " + key + " = " + reqParams.get(key));
			APIuri = (APIuri + key + "=" + reqParams.get(key) + "&&");
			
		}
		APIuri = APIuri.substring(0,APIuri.length()-2);
		Logge.LogI("uri is : " + APIuri);
//		HttpPost post = new HttpPost(uri);
		HttpGet get = new HttpGet(APIuri);
		HttpResponse response;
		
		try {
//			post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));
//			Logge.LogI(post.getURI().toString());
			/**
			 * 创建Http Header
			 */
			// createPostHeader(context, post);
			
			DefaultHttpClient httpClient = (DefaultHttpClient) getHttpClient();
			response = httpClient.execute(get);
			
			if (response.getStatusLine().getStatusCode() != 404) {
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			} else {
				Logge.LogI("" + response.getStatusLine().getStatusCode());
			}
		} catch (IOException e) { 
			e.printStackTrace();
			Logge.LogI("CallServer | " + e.getMessage());
			return null;
		} finally {
			get.abort();
		}
		Logge.LogI("请求服务器返回数据 : " + result);
		return result;
	}
	
	


	/**
	 * 请求服务器接收返回(268)
	 * 
	 * @param method
	 * @param reqParams
	 * @param context
	 * @return
	 */
	public String callServer(String method, HashMap<String, String> reqParams,
			Context context) {
		String responseString = goHttpPost(method, reqParams, context);
//		String responseString = goHttpPostssssss(method, reqParams, context);
		return responseString;
	}
	public String callServers(String method, HashMap<String, String> reqParams,
							 Context context) {
		String responseString = goHttpPost(method, reqParams, context);
		return responseString;
	}
	/**
	 * 请求服务器接收返回(朗顿)
	 * 	get
	 * @param method
	 * @param reqParams
	 * @param context
	 * @return
	 */
	public String callServerldGet(String method, HashMap<String, String> reqParams,
			Context context) {
		String responseString = goHttpGetld(method, reqParams, context);
//		String responseString = goHttpsPost(method, reqParams, context);
		return responseString;
	}
	/**
	 * 请求服务器接收返回(朗顿)
	 * 	post
	 * @param method
	 * @param reqParams
	 * @param context
	 * @return
	 */
	public String callServerld(String method, HashMap<String, String> reqParams,
			Context context) {
		 String responseString = goHttpPostld(method, reqParams, context);
//		String responseString = goHttpsPost(method, reqParams, context);
		return responseString;
	}
	

	/**
	 * 创建头数据
	 * 
	 * @param context
	 */
	@SuppressWarnings("unused")
	private void createPostHeader(Context context, HttpPost post) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String imei = tm.getSimSerialNumber();
		String imsi = tm.getSubscriberId();

		DisplayMetrics metric = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
		.getMetrics(metric);
		int width = metric.widthPixels; // 屏幕宽度（像素）
		int height = metric.heightPixels; // 屏幕高度（像素）
		float density = metric.density; // 屏幕密度（0.75 / 1.0 / 1.5）
		int densityDpi = metric.densityDpi; // 屏幕密度DPI（120 / 160 / 240）

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss",
				Locale.CHINESE);
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String clientDate = formatter.format(curDate);

		Logge.LogI("imei is : " + imei);
		Logge.LogI("imsi is : " + imsi);
		Logge.LogI("width is : " + width);
		Logge.LogI("height is : " + height);
		Logge.LogI("density is : " + density);
		Logge.LogI("densityDpi is : " + densityDpi);
		Logge.LogI("Product Model: " + android.os.Build.MODEL + ","
				+ android.os.Build.VERSION.RELEASE + ","
				+ android.os.Build.MANUFACTURER);
		Logge.LogI("current date is : " + clientDate);

		post.setHeader("uniquecode", getMyUUID(context, tm));
		post.setHeader("imsi", imsi);
		post.setHeader("imei", imei);
		// post.setHeader("ipaddr", getLocalIpAddress());
		post.setHeader("provider", tm.getSimOperatorName());
		post.setHeader("screenheight", String.valueOf(height));
		post.setHeader("screenwidth", String.valueOf(width));
		post.setHeader("ostype", "A");
		post.setHeader("osversion", android.os.Build.VERSION.RELEASE);
		post.setHeader("mobilefac", android.os.Build.MANUFACTURER);
		post.setHeader("mobilemod", android.os.Build.MODEL);
		post.setHeader("clientdate", clientDate);
	}

	/**
	 * 获取唯一标识码
	 * 
	 * @param context
	 * @param tm
	 * @return
	 */
	private String getMyUUID(Context context, TelephonyManager tm) {
		final String tmDevice, tmSerial, androidId;
		tmDevice = "" + tm.getDeviceId();
		tmSerial = "" + tm.getSimSerialNumber();
		androidId = ""
				+ android.provider.Settings.Secure.getString(
						context.getContentResolver(),
						android.provider.Settings.Secure.ANDROID_ID);
		UUID deviceUuid = new UUID(androidId.hashCode(),
				((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());

		return deviceUuid.toString();
	}
}
