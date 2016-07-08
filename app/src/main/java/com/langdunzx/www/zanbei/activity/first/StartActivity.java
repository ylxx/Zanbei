package com.langdunzx.www.zanbei.activity.first;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.langdunzx.www.zanbei.R;
import com.langdunzx.www.zanbei.activity.BaseFragmentActivity;
import com.langdunzx.www.zanbei.activity.home.HomeActivity;
import com.langdunzx.www.zanbei.activity.user.LoginActivity;
import com.langdunzx.www.zanbei.utils.HttpUtils;
import com.langdunzx.www.zanbei.utils.MD5;
import com.langdunzx.www.zanbei.utils.SharePopuwindow;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.utils.Log;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StartActivity extends BaseFragmentActivity {
    TextView textView;
    Button but,share,bbbb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewWithActionBar(R.layout.activity_start,"导航");
        toolbar.inflateMenu(R.menu.menu_toolbar_base);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        textView = (TextView) findViewById(R.id.tv_start);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, HomeActivity.class));
            }
        });
        but = (Button) findViewById(R.id.but);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, LoginActivity.class));
            }
        });
        share = (Button) findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMImage image = new UMImage(StartActivity.this,
                        R.drawable.ic_logo);
                SharePopuwindow popuwindow = new SharePopuwindow(
                        StartActivity.this, image, "赞呗", "赞呗分享",
                        "http://www.baidu.com");
                popuwindow.showSharePopu();
                popuwindow.showAtLocation(View.inflate(StartActivity.this,
                        R.layout.activity_home, null), Gravity.BOTTOM
                        | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });

    }





}
