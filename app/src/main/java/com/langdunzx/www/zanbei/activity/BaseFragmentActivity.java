package com.langdunzx.www.zanbei.activity;


import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.jaeger.library.StatusBarUtil;
import com.langdunzx.www.zanbei.R;
import com.langdunzx.www.zanbei.application.LangDunApplication;
import com.langdunzx.www.zanbei.config.Constants;
import com.langdunzx.www.zanbei.utils.ShowErrorDialogUtil;


public class BaseFragmentActivity extends AppCompatActivity implements
        OnClickListener{

    protected FragmentManager mFragmentManager;
    protected FragmentTransaction mFragmentTransaction;

    protected LangDunApplication mApplication;
    protected SharedPreferences userInfo;

    public Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFragmentManager = getSupportFragmentManager();
//        mApplication = (LangDunApplication) getApplication();
        userInfo = getSharedPreferences(Constants.INIT_USER_INFO,
                Context.MODE_PRIVATE);

    }

    protected void showError(int errResource) {
        ShowErrorDialogUtil.showErrorDialog(this, getString(errResource));
    }

    protected void showError(String errStr) {
        ShowErrorDialogUtil.showErrorDialog(this, errStr);
    }

    /**
     * @Description: 自带titlebar
     * @param
     * @return
     */
    protected void setContentViewWithActionBar(int layoutResID, String title) {
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup contentV = (ViewGroup) inflater.inflate(
                R.layout.activity_base, null);
        inflater.inflate(layoutResID, contentV);
        setContentView(contentV);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);

        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);

        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseFragmentActivity.this.finish();
            }
        });

//        StatusBarUtil.setTranslucent(BaseFragmentActivity.this, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);


//        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/


// App Logo
//        toolbar.setLogo(R.drawable.app_refresh_goods_0);
// Title
//        toolbar.setTitle("");
// Sub Title
//        toolbar.setSubtitle("返回");

//        setSupportActionBar(toolbar);

// Navigation Icon 要設定在 setSupoortActionBar 才有作用
// 否則會出現 back button
//        toolbar.setNavigationIcon(R.drawable.app_refresh_people_0);


        /*bar = (CustomBar) findViewById(R.id.titlebar);
        bar.ininbar(contentV);
        bar.setTitleBar(title);
        bar.getLeftBar().setOnClickListener(this);*/
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v.getId() == R.id.left_bar) {
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateRightBar();
    }

    void updateRightBar() {
        //		ImageView view = (ImageView) getWindow().getDecorView().findViewById(
        //				R.id.pic_my_title);
        //		if (view != null) {
        //			if (LangDunApplication.sApplication.getUserlogininfo() != null
        //					&& DeKuShuApplication.sApplication.getUserlogininfo()
        //							.getUserlogininfo().getMessageCount() != null) {
        //				// 患者
        //				if (Integer.parseInt(DeKuShuApplication.sApplication
        //						.getUserlogininfo().getUserlogininfo()
        //						.getMessageCount()) > 0) {
        //					// 有新消息
        //					view.setImageResource(R.drawable._0000_6_1);
        //				} else {
        //					view.setImageResource(R.drawable._0000_6);
        //				}
        //
        //			}
        //			if (DeKuShuApplication.sApplication.getDoclogbody() != null
        //					&& DeKuShuApplication.sApplication.getDoclogbody()
        //							.getDoclogininfovo().getMessageCount() != null) {
        //				// 医生
        //				if (Integer.parseInt(DeKuShuApplication.sApplication
        //						.getDoclogbody().getDoclogininfovo().getMessageCount()) > 0) {
        //					view.setImageResource(R.drawable._0000_6_1);
        //				} else {
        //					view.setImageResource(R.drawable._0000_6);
        //				}
        //			}
        //		}
    }

    private boolean isShowing() {
        boolean result = false;
        ActivityManager am = (ActivityManager) this
                .getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        if (cn != null) {
            System.out.println("this.getLocalClassName()::"
                    + this.getLocalClassName());
            System.out.println("cn.getClassName()::" + cn.getClassName());
            if (this.getLocalClassName().equals(cn.getClassName())) {
                result = true;
            }
        }
        return result;
    }

}
