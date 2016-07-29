package com.langdunzx.www.zanbei.activity.home;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.util.Log;

import com.jaeger.library.StatusBarUtil;
import com.langdunzx.www.zanbei.R;
import com.langdunzx.www.zanbei.activity.BaseFragmentActivity;
import com.langdunzx.www.zanbei.fragment.main.DiscoverPageFragment;
import com.langdunzx.www.zanbei.fragment.main.FriendFragment;
import com.langdunzx.www.zanbei.fragment.main.HomePageFragment;
import com.langdunzx.www.zanbei.fragment.main.InformationFragment;

import java.util.ArrayList;
import java.util.List;

import me.majiajie.pagerbottomtabstrip.Controller;
import me.majiajie.pagerbottomtabstrip.PagerBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.TabItemBuilder;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectListener;


public class HomeActivity extends BaseFragmentActivity {
    int[] testColors = {00000000};

    private Controller controller;

    private List<Fragment> mFragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        StatusBarUtil.setTranslucent(HomeActivity.this, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
//        StatusBarUtil.createStatusBarView(HomeActivity.this, Color.BLUE);
//        StatusBarUtil.setColor(HomeActivity.this, Color.BLUE,StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
//        StatusBarUtil.createStatusBarView(HomeActivity.this,R.color.red);
        setupActionBar();
        initFragment();
        BottomTabTest();
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initFragment() {
        mFragments = new ArrayList<>();

        mFragments.add(createFragment1(""));
        mFragments.add(createFragment2(""));
        mFragments.add(createFragment3(""));
        mFragments.add(createFragment4(""));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.push_up_in,R.anim.push_up_out);
        transaction.add(R.id.frameLayout,mFragments.get(0));
        transaction.commit();
    }
    private void BottomTabTest()
    {
        PagerBottomTabLayout pagerBottomTabLayout = (PagerBottomTabLayout) findViewById(R.id.tab);

        //用TabItemBuilder构建一个导航按钮
        TabItemBuilder tabItemBuilder = new TabItemBuilder(this).create()
                .setDefaultIcon(R.drawable.tabbar_home_shipin)
                .setDefaultColor(0xFF000000)
                .setText("课程")
                .setSelectedColor(0xFF20B2AA)//16进制的颜色
                .setTag("A")
                .build();

        //构建导航栏,得到Controller进行后续控制
        controller = pagerBottomTabLayout.builder()
                .addTabItem(tabItemBuilder)
                .addTabItem(R.drawable.tabbar_home_zixun, "资讯",0xFF20B2AA)
                .addTabItem(R.drawable.tabbar_home_tongxun, "人脉",0xFF20B2AA)
                .addTabItem(R.drawable.tabbar_home_faxian, "发现",0xFF20B2AA)
                .setDefaultColor(0xFF000000)
//              .setMode(TabLayoutMode.HIDE_TEXT)
//              .setMode(TabLayoutMode.CHANGE_BACKGROUND_COLOR)
                .build();
//        controller.setMessageNumber("A",2);
//        controller.setDisplayOval(0,true);

        controller.addTabItemClickListener(listener);
    }
    /**点击监听**/
    OnTabItemSelectListener listener = new OnTabItemSelectListener() {
        @Override
        public void onSelected(int index, Object tag) {
            Log.i("asd","onSelected:"+index+"   TAG: "+tag.toString());

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.push_up_in,R.anim.push_up_out);
            transaction.replace(R.id.frameLayout,mFragments.get(index));
            transaction.commit();
        }

        @Override
        public void onRepeatClick(int index, Object tag) {
            Log.i("asd","onRepeatClick:"+index+"   TAG: "+tag.toString());
        }
    };

    private Fragment createFragment1(String content) {
        HomePageFragment fragment = new HomePageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("content",content);
        fragment.setArguments(bundle);
        return fragment;
    }
    private Fragment createFragment2(String content) {
        InformationFragment fragment = new InformationFragment();
        Bundle bundle = new Bundle();
        bundle.putString("content",content);
        fragment.setArguments(bundle);
        return fragment;
    }
    private Fragment createFragment3(String content) {
        FriendFragment fragment = new FriendFragment();
        Bundle bundle = new Bundle();
        bundle.putString("content",content);
        fragment.setArguments(bundle);
        return fragment;
    }
    private Fragment createFragment4(String content) {
        DiscoverPageFragment fragment = new DiscoverPageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("content",content);
        fragment.setArguments(bundle);
        return fragment;
    }

}
