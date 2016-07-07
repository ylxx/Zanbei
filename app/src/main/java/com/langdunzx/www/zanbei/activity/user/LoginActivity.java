package com.langdunzx.www.zanbei.activity.user;

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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.langdunzx.www.zanbei.R;
import com.langdunzx.www.zanbei.activity.BaseFragmentActivity;
import com.langdunzx.www.zanbei.utils.CheckUtil;
import com.langdunzx.www.zanbei.utils.ClickUtil;
import com.langdunzx.www.zanbei.utils.HttpUtils;
import com.langdunzx.www.zanbei.utils.MD5;
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
    private Button btLogin,login_but,login_but1   ;
    private String userName, userPw, userCode, Codeboolean;
    private ImageView img_wx,img_qq,img_sina;
    private String requestTag = "";
    private TextView tv_data;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1){
                String s = (String) msg.obj;
                Toast.makeText(LoginActivity.this,s.toString(),Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
    private OnClickListener listener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_login:
                    // 登陆请求
                    confirmLogin();
                    /*if (Codeboolean.equals("1")) {
                        llCode.setVisibility(View.VISIBLE);
                    }*/
                    break;
                case R.id.imageView_weixin:
                    // 授权微信
                    mShareAPI.doOauthVerify(LoginActivity.this, SHARE_MEDIA.WEIXIN,
                            umAuthListener);
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
                    Toast.makeText(LoginActivity.this,"进来了",Toast.LENGTH_SHORT).show();
                    Date date=new Date();
                    DateFormat format = new SimpleDateFormat("yyyyMMdd");
                    String time = format.format(date);
                    String uid = "13810250440";
                    String tokenOne = time + uid;
                    String tokenTwo = MD5.MD5(tokenOne);
                    String token = MD5.MD5(tokenTwo);

                    String url = "http://langdunedu.com/api/"; //这是URL
                    requestTag = "volley_get";
                    Map<String, String> hashMap = new HashMap<String, String>();
                    //参数
                    hashMap.put("action", "reglogin");
                    hashMap.put("token", token);
                    hashMap.put("uid", "13810250440");
                    hashMap.put("password", "");
                    hashMap.put("pw", "");

                    break;
                case R.id.login_but1:
                        new AsnyckLogin().execute();
                    break;
                default:
                    break;
            }
        }

    };

    private class AsnyckLogin extends AsyncTask<String, Void, String> {
        HttpUtils http = new HttpUtils();
        OkHttpClient client = new OkHttpClient();
        String json = null;

        @Override
        protected String doInBackground(String... params) {
            Date date = new Date();
            DateFormat format = new SimpleDateFormat("yyyyMMdd");
            String time = format.format(date);
            String uid = "13810250440";
            String tokenOne = time + uid;
            String tokenTwo = MD5.MD5(tokenOne);
            String token = MD5.MD5(tokenTwo);

            String url = String.format("http://langdunedu.com/api/");
            RequestBody formBody = new FormEncodingBuilder()
                    .add("action", "reglogin")
                    .add("token", token)
                    .add("uid", "13810250440")
                    .add("password", "")
                    .add("pw", "")
                    .build();
            Request request = new Request.Builder().url(url).post(formBody).build();
            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                } else {
                    throw new IOException("Unexpected code " + response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (json != null) {
                Message message = new Message();
                message.what = 1;
                message.obj = json;
                handler.sendMessage(message);
            } else {

            }
            return null;
        }
    }

    /***
     * 登录
     */
    private void confirmLogin() {
        userName = etUserName.getText().toString().trim();
        userPw = etUserPaw.getText().toString().trim();
        // 检验
        if (etUserName.length() == 0) {
            showError("请输入手机号");
            return;
        }
        if (!CheckUtil.isPhoneNum(userName)) {
            showError("请输入正确的手机号码");
            return;
        }
        if (etUserPaw.length() == 0) {
            showError("请输入密码");
            return;
        }

        HashMap<String, String> hashmap = new HashMap<String, String>();
        hashmap.put("name", etUserName.getText().toString());
        hashmap.put("password", etUserName.getText().toString());
//        new RequestCommant()
//                .requestlogin(new requetHandle(this), this, hashmap);
    }


    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            //个人信息在此拿 map-data
            Toast.makeText( getApplicationContext(), "授权成功"+data.toString(), Toast.LENGTH_SHORT).show();
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

