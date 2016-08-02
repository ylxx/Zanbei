package com.langdunzx.www.zanbei.activity.user;

import android.app.Activity;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.langdunzx.www.zanbei.R;
import com.langdunzx.www.zanbei.activity.BaseFragmentActivity;
import com.langdunzx.www.zanbei.config.Constants;
import com.langdunzx.www.zanbei.controller.BaseHandler;
import com.langdunzx.www.zanbei.controller.RequestCommant;
import com.langdunzx.www.zanbei.utils.CheckUtil;
import com.langdunzx.www.zanbei.utils.ClickUtil;
import com.langdunzx.www.zanbei.utils.HttpUtils;
import com.langdunzx.www.zanbei.utils.LogUtil;
import com.langdunzx.www.zanbei.utils.MD5;
import com.langdunzx.www.zanbei.utils.OkHttpUtils;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;



/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseFragmentActivity {
    UMShareAPI mShareAPI;
    private EditText etUserName,etCode,etUserPaw;
    private LinearLayout llCode;
    private Button btLogin,login_but,login_but1;
    private String userName, userPw, userCode;
    private ImageView img_wx,img_qq,img_sina;
    private String requestTag = "";
    private TextView tv_data;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1){
//                String s = (String) msg.obj;
//                Toast.makeText(LoginActivity.this,s.toString(),Toast.LENGTH_SHORT).show();
            }else if(msg.what == 2){
//                Map<String, String> map = (Map<String, String>) msg.obj;
                Toast.makeText(LoginActivity.this,"WXdata：",Toast.LENGTH_SHORT).show();
            }
        }
    };

    private class requetHandle extends BaseHandler {
        public requetHandle(Activity activity) {
            super(activity);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);

            if (msg.what == Constants.LOGIN) {
                System.out.println(command.success);

                if (command.success) {
                    Toast.makeText(LoginActivity.this, "登录成功",
                            Toast.LENGTH_SHORT).show();

                } else {
                    showError((String) command.message);
                }
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewWithActionBar(R.layout.activity_login,"登录 | 注册");
        //setContentView(R.layout.activity_login);
        mShareAPI = UMShareAPI.get(this);//初始化友盟分享
        initView();
        // 删除授权接口
        //mShareAPI.deleteOauth(AuthActivity.this, platform, umdelAuthListener);
    }

    private void initView(){
        etUserName = (EditText) findViewById(R.id.et_login_name);
        etCode = (EditText) findViewById(R.id.ed_phone_code);
        etUserPaw = (EditText) findViewById(R.id.et_login_passw);
        btLogin = (Button) findViewById(R.id.bt_login);
        llCode = (LinearLayout) findViewById(R.id.linear_phoneCode);
        img_wx = (ImageView) findViewById(R.id.imageView_weixin);
        img_qq = (ImageView) findViewById(R.id.imageView_qq);
        img_sina = (ImageView) findViewById(R.id.imageView_weibo);
        login_but = (Button) findViewById(R.id.login_but);
        login_but1 = (Button) findViewById(R.id.login_but1);
        tv_data = (TextView) findViewById(R.id.tv_data);
        ClickUtil.setClickListener(listener,btLogin,img_wx,img_qq,img_sina,login_but,login_but1,tv_data);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * 监听事件
     *
     */
    String Codeboolean = "1";
    int i=0;
    private OnClickListener listener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_login:
                    // 登陆请求.
                    confirmLogin();
//                    lOGON();
                    if (i == 0) {
                        i++;
                        llCode.setVisibility(View.VISIBLE);
                    }else{
                        i = 0;
                        llCode.setVisibility(View.GONE);
                    }
                    break;
                case R.id.imageView_weixin:
                    // 授权微信
                    mShareAPI.doOauthVerify(LoginActivity.this, SHARE_MEDIA.WEIXIN,
                            umAuthListeners);
//                    mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.WEIXIN,
//                            umAuthListener);
                    break;
                case R.id.imageView_qq:
                    // 授权腾讯
                    mShareAPI.doOauthVerify(LoginActivity.this, SHARE_MEDIA.QQ,
                            umAuthListener);
                    break;
                case R.id.imageView_weibo:
                    // 授权微博
                    mShareAPI.doOauthVerify(LoginActivity.this, SHARE_MEDIA.SINA,
                            umAuthListener);
                    break;
                case R.id.login_but:
//                    new AsnyckLogin().execute();
                    mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.WEIXIN,
                            umAuthListener);
                    break;
                case R.id.login_but1:
//                    lOGON();
                    break;
                default:
                    break;
            }
        }

    };
//
//    /**
//     * 封装好的okHttp
//     */
//    private void lOGON(){
//        String url = String.format("http://api.anydo.com/index.php/Home/Api/reglogin/uid/13718141869/password/111111/pw/");
//        Date date = new Date();
//        DateFormat format = new SimpleDateFormat("yyyyMMdd");
//        String time = format.format(date);
//        String uid = etUserName.getText().toString();
//        String tokenOne = time + uid;
//        String tokenTwo = MD5.MD5(tokenOne);
//        String token = MD5.MD5(tokenTwo);
//        List<OkHttpUtils.Param> list = new ArrayList<OkHttpUtils.Param>();
////        list.add(new OkHttpUtils.Param("action","reglogin"));
////        list.add(new OkHttpUtils.Param("token",token));
////        list.add(new OkHttpUtils.Param("uid",uid));
////        list.add(new OkHttpUtils.Param("password",""));
////        list.add(new OkHttpUtils.Param("pw",""+etCode.getText().toString()));
//        OkHttpUtils.post(url, new OkHttpUtils.ResultCallback() {
//            @Override
//            public void onSuccess(Object response) {
//                if(response!=null){
//                    //成功干的事情
//                    handler.obtainMessage(1,response).sendToTarget();
//                }
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//                //失败错误信息
//                LogUtil.e("okerr",""+e.toString());
//                handler.obtainMessage(2,e.toString()).sendToTarget();
//            }
//        },list);
//
//        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback() {
//            @Override
//            public void onSuccess(Object response) {
//                //成功响应
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//                //失败响应
//            }
//        });
//    }


    /***
     * 登录
     */
    private void confirmLogin() {
        // 检验
        if (etUserName.length() == 0) {
            showError("请输入手机号");
            return;
        }
        if (!CheckUtil.isPhoneNum(etUserName.getText().toString())) {
            showError("请输入正确的手机号码");
            return;
        }
        if (etUserPaw.length() == 0) {
            showError("请输入密码");
            return;
        }

        HashMap<String, String> hashmap = new HashMap<String, String>();
        hashmap.put("uid", etUserName.getText().toString());
        hashmap.put("password", etUserPaw.getText().toString());
        hashmap.put("pw", etCode.getText().toString());
        new RequestCommant()
                .requestlogin(new requetHandle(this), this, hashmap);
    }
    private void WXloading(String openid,String nickname,String headimgurl){
        HashMap<String, String> hashmap = new HashMap<String, String>();
        hashmap.put("usid", openid);
        hashmap.put("username", nickname);
        hashmap.put("iconURL",headimgurl);
        new RequestCommant()
                .requestwxlogin(new requetHandle(this), this, hashmap);
    }
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( getApplicationContext(), "授权失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText( getApplicationContext(), "授权取消", Toast.LENGTH_SHORT).show();
        }
    };

    private UMAuthListener umAuthListeners = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText( getApplicationContext(), "授权成功", Toast.LENGTH_SHORT).show();
            mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.WEIXIN,
                    new UMAuthListener() {
                        @Override
                        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                            if(map!=null){
                                String openid = map.get("openid").toString();
                                String nickname = map.get("nickname").toString();
                                String headimgurl = map.get("headimgurl").toString();
                                WXloading(openid,nickname,headimgurl);
                            }else{
                                Toast.makeText(getApplicationContext(),"没有授权", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                        }

                        @Override
                        public void onCancel(SHARE_MEDIA share_media, int i) {

                        }
                    });
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( getApplicationContext(), "授权失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText( getApplicationContext(), "授权取消", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mShareAPI.onActivityResult(requestCode, resultCode, data);//分享需要
    }
}

