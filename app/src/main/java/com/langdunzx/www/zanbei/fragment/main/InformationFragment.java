package com.langdunzx.www.zanbei.fragment.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Message;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.langdunzx.www.zanbei.R;
import com.langdunzx.www.zanbei.activity.secondry.ChannelActivity;
import com.langdunzx.www.zanbei.adapter.NewsFragmentPagerAdapter;
import com.langdunzx.www.zanbei.application.LangDunApplication;
import com.langdunzx.www.zanbei.bean.ChannelManage;
import com.langdunzx.www.zanbei.config.Constants;
import com.langdunzx.www.zanbei.controller.BaseHandler;
import com.langdunzx.www.zanbei.controller.RequestCommant;
import com.langdunzx.www.zanbei.fragment.Information.NewsFragment;
import com.langdunzx.www.zanbei.fragment.Information.NewsHotFragment;
import com.langdunzx.www.zanbei.fragment.Information.NewsInformationFragment;
import com.langdunzx.www.zanbei.newsUtils.ChannelItem;
import com.langdunzx.www.zanbei.utils.BaseTools;
import com.langdunzx.www.zanbei.view.ColumnHorizontalScrollView;

import java.util.ArrayList;
import java.util.HashMap;


public class InformationFragment extends Fragment {
    private View view;
    /** 自定义HorizontalScrollView */
    private ColumnHorizontalScrollView mColumnHorizontalScrollView;
    LinearLayout mRadioGroup_content;
    LinearLayout ll_more_columns;
    RelativeLayout rl_column;
    private ViewPager mViewPager;
    private ImageView button_more_columns;
    /** 用户选择的新闻分类列表 */
    private ArrayList<ChannelItem> userChannelList = new ArrayList<ChannelItem>();
    /** 当前选中的栏目 */
    private int columnSelectIndex = 0;
    /** 左阴影部分 */
    public ImageView shade_left;
    /** 右阴影部分 */
    public ImageView shade_right;
    /** 屏幕宽度 */
    private int mScreenWidth = 0;
    /** Item宽度 */
    private int mItemWidth = 0;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();

    // protected SlidingMenu side_drawer;

    /** head 头部 的中间的loading */
    private ProgressBar top_progress;
    /** head 头部 中间的刷新按钮 */
    private ImageView top_refresh;
    /** head 头部 的左侧菜单 按钮 */
    private ImageView top_head;
    /** head 头部 的右侧菜单 按钮 */
    private ImageView top_more;
    /** 请求CODE */
    public final static int CHANNELREQUEST = 1;
    /** 调整返回的RESULTCODE */
    public final static int CHANNELRESULT = 10;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_information,null);
        mScreenWidth = BaseTools.getWindowsWidth(getActivity());
        mItemWidth = mScreenWidth / 7;// 一个Item宽度为屏幕的1/7
        initView();
        return view;
    }
    /** 初始化layout控件 */
    private void initView() {
        mColumnHorizontalScrollView = (ColumnHorizontalScrollView) view
                .findViewById(R.id.mColumnHorizontalScrollView);
        mRadioGroup_content = (LinearLayout) view
                .findViewById(R.id.mRadioGroup_content);
        ll_more_columns = (LinearLayout) view
                .findViewById(R.id.ll_more_columns);
        rl_column = (RelativeLayout) view.findViewById(R.id.rl_column);
        button_more_columns = (ImageView) view
                .findViewById(R.id.button_more_columns);
        mViewPager = (ViewPager) view.findViewById(R.id.mViewPager);
        shade_left = (ImageView) view.findViewById(R.id.shade_left);
        shade_right = (ImageView) view.findViewById(R.id.shade_right);
        // top_head = (ImageView) view.findViewById(R.id.top_head);
        // top_more = (ImageView) view.findViewById(R.id.top_more);
        // top_refresh = (ImageView) view.findViewById(R.id.top_refresh);
        // top_progress = (ProgressBar) view.findViewById(R.id.top_progress);
        button_more_columns.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent_channel = new Intent(getActivity()
                        .getApplicationContext(), ChannelActivity.class);
                startActivityForResult(intent_channel, CHANNELREQUEST);
                getActivity().overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });
        // top_head.setOnClickListener(new OnClickListener() {
        //
        // @Override
        // public void onClick(View v) {
        // // TODO Auto-generated method stub
        // if(side_drawer.isMenuShowing()){
        // side_drawer.showContent();
        // }else{
        // side_drawer.showMenu();
        // }
        // }
        // });
        // top_more.setOnClickListener(new OnClickListener() {
        //
        // @Override
        // public void onClick(View v) {
        // // TODO Auto-generated method stub
        // if(side_drawer.isSecondaryMenuShowing()){
        // side_drawer.showContent();
        // }else{
        // side_drawer.showSecondaryMenu();
        // }
        // }
        // });
        setChangelView();
        getinfo();
    }
    /**
     * 当栏目项发生变化时候调用
     * */
    private void setChangelView() {
        initColumnData();
        initTabColumn();
        initFragment();
    }

    /** 获取Column栏目 数据 */
    private void initColumnData() {
        userChannelList = ((ArrayList<ChannelItem>) ChannelManage.getManage(LangDunApplication.getApp().getSQLHelper()).getUserChannel());
    }
    /**
     * 初始化Column栏目项
     * */
    private void initTabColumn() {
        mRadioGroup_content.removeAllViews();
        int count = userChannelList.size();
        mColumnHorizontalScrollView.setParam(getActivity(), mScreenWidth,
                mRadioGroup_content, shade_left, shade_right, ll_more_columns,
                rl_column);
        for (int i = 0; i < count; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    mItemWidth, ViewPager.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 5;
            params.rightMargin = 5;
            // TextView localTextView = (TextView)
            // mInflater.inflate(R.layout.column_radio_item, null);
            TextView columnTextView = new TextView(getActivity());
            columnTextView.setTextAppearance(getActivity(),
                    R.style.top_category_scroll_view_item_text);
            // localTextView.setBackground(getResources().getDrawable(R.drawable.top_category_scroll_text_view_bg));
            columnTextView.setBackgroundResource(R.drawable.radio_buttong_bg);
            columnTextView.setGravity(Gravity.CENTER);
            columnTextView.setPadding(5, 5, 5, 5);
            columnTextView.setId(i);
            columnTextView.setText(userChannelList.get(i).getName());
            columnTextView.setTextColor(getResources().getColorStateList(
                    R.color.top_category_scroll_text_color_day));
            if (columnSelectIndex == i) {
                columnTextView.setSelected(true);
            }
            columnTextView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
                        View localView = mRadioGroup_content.getChildAt(i);
                        if (localView != v)
                            localView.setSelected(false);
                        else {
                            localView.setSelected(true);
                            mViewPager.setCurrentItem(i);
                        }
                    }
                    Toast.makeText(getActivity().getApplicationContext(),
                            userChannelList.get(v.getId()).getName(),
                            Toast.LENGTH_SHORT).show();
                }
            });
            mRadioGroup_content.addView(columnTextView, i, params);
        }
    }

    /**
     * 初始化Fragment
     * */
    private void initFragment() {
        fragments.clear();// 清空
        int count = userChannelList.size();
        for (int i = 0; i < count; i++) {
            if (i == 0) {
                Bundle data = new Bundle();
                data.putString("text", userChannelList.get(i).getName());
                data.putInt("id", userChannelList.get(i).getId());
                NewsHotFragment homeFragment = new NewsHotFragment();
                fragments.add(homeFragment);
            } else {
                Bundle data = new Bundle();
                data.putString("text", userChannelList.get(i).getName());
                data.putInt("id", userChannelList.get(i).getId());
                NewsInformationFragment newfragment = new NewsInformationFragment();
                newfragment.setArguments(data);
                fragments.add(newfragment);
            }
        }
        NewsFragmentPagerAdapter mAdapetr = new NewsFragmentPagerAdapter(
                getChildFragmentManager(), fragments);
        // mViewPager.setOffscreenPageLimit(0);
        mViewPager.setAdapter(mAdapetr);
        mViewPager.setOnPageChangeListener(pageListener);
    }

    /**
     * ViewPager切换监听方法
     * */
    public ViewPager.OnPageChangeListener pageListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            // TODO Auto-generated method stub
            mViewPager.setCurrentItem(position);
            selectTab(position);
        }
    };

    /**
     * 选择的Column里面的Tab
     * */
    private void selectTab(int tab_postion) {
        columnSelectIndex = tab_postion;
        for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
            View checkView = mRadioGroup_content.getChildAt(tab_postion);
            int k = checkView.getMeasuredWidth();
            int l = checkView.getLeft();
            int i2 = l + k / 2 - mScreenWidth / 2;
            // rg_nav_content.getParent()).smoothScrollTo(i2, 0);
            mColumnHorizontalScrollView.smoothScrollTo(i2, 0);
            // mColumnHorizontalScrollView.smoothScrollTo((position - 2) *
            // mItemWidth , 0);
        }
        // 判断是否选中
        for (int j = 0; j < mRadioGroup_content.getChildCount(); j++) {
            View checkView = mRadioGroup_content.getChildAt(j);
            boolean ischeck;
            if (j == tab_postion) {
                ischeck = true;
            } else {
                ischeck = false;
            }
            checkView.setSelected(ischeck);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CHANNELREQUEST:
                if (resultCode == CHANNELRESULT) {
                    setChangelView();
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void getinfo(){
        HashMap<String, String> hashmap = new HashMap<String, String>();
        hashmap.put("classid","11");
        hashmap.put("uid","128");
        new RequestCommant()
                .requestInformationdata(new requetHandle(getActivity()), getActivity(), hashmap);
    }
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
                    Toast.makeText(getContext(), "返回成功",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
