package com.langdunzx.www.zanbei.activity.secondry;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jaeger.library.StatusBarUtil;
import com.langdunzx.www.zanbei.R;
import com.langdunzx.www.zanbei.activity.BaseFragmentActivity;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class NewsInforActivity extends BaseFragmentActivity {

    private LinearLayout head_layout;
    private TabLayout toolbar_tab;
    private CoordinatorLayout root_layout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_infor);
        initView();

    }

    private void initView() {
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        head_layout = (LinearLayout) findViewById(R.id.head_layout);
        root_layout = (CoordinatorLayout) findViewById(R.id.root_layout);
        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id
                .toolbar_layout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(verticalOffset <= -head_layout.getHeight() / 2){
                    mCollapsingToolbarLayout.setTitle("个人中心");
                }else{
                    mCollapsingToolbarLayout.setTitle("");
                }
            }
        });

        loadBlurAndSetStatusBar();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /**
     * 设置毛玻璃效果和沉浸状态栏
     */
    private void loadBlurAndSetStatusBar() {
        StatusBarUtil.setTranslucent(NewsInforActivity.this, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
        Glide.with(this).load(R.mipmap.bg).bitmapTransform(new BlurTransformation(this,100))
                .into(new SimpleTarget<GlideDrawable>() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super
                            GlideDrawable> glideAnimation) {
                        head_layout.setBackground(resource);
                        root_layout.setBackground(resource);
                    }
                });

        Glide.with(this).load(R.mipmap.bg).bitmapTransform(new BlurTransformation(this, 100))
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super
                            GlideDrawable> glideAnimation) {
                        mCollapsingToolbarLayout.setContentScrim(resource);
                    }
                });
    }
}
