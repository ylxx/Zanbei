package com.langdunzx.www.zanbei.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.langdunzx.www.zanbei.R;
import com.langdunzx.www.zanbei.fragment.homepage.VpSimpleFragment;
import com.langdunzx.www.zanbei.fragment.homepage.VpSimpleFragmentA;
import com.langdunzx.www.zanbei.fragment.homepage.VpSimpleFragmentB;
import com.langdunzx.www.zanbei.utils.ClickUtil;
import com.langdunzx.www.zanbei.utils.LogUtil;
import com.langdunzx.www.zanbei.view.ViewPagerIndictor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomePageFragment extends Fragment {
    private ViewPager mViewPager;
    private ViewPagerIndictor mIndictor;
    private List<String> mTitles = Arrays.asList("课程分类1", "课程分类2", "课程分类3");
    private List<VpSimpleFragment> mContents = new ArrayList<VpSimpleFragment>();
    //	private FragmentPagerAdapter mAdapter;
    private FragmentPagerAdapter mAdapter;
    private ImageView ivToMain;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_homepage,null);

        initViews();
        initDatas();

        mIndictor.setVisiableTabCount(3);
        mIndictor.setTabItemTitles(mTitles);

        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "position"+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
        mIndictor.setViewPager(mViewPager, 0);
        // 自定义监听
        mIndictor.setOnPageChangeListener(new ViewPagerIndictor.PageOnChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrolledStateChanged(int state) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrolled(int position, float positionOffect,
                                       int positionOffectPixels) {
                // TODO Auto-generated method stub

            }
        });

        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //mTitles.removeAll(mTitles);
        mContents.removeAll(mContents);
        LogUtil.e("Home", "onDestroyView()");
    }
    private void initViews() {
        mViewPager = (ViewPager) view.findViewById(R.id.id_viewPager);
        mIndictor = (ViewPagerIndictor) view.findViewById(R.id.id_indictor);
        //ivToMain = (ImageView) view.findViewById(R.id.fragment_homepage_rightbar);
        ClickUtil.setClickListener(listener, ivToMain);

    }

    /**
     * 监听事件
     */
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                //case R.id.fragment_homepage_rightbar:
                  //  startActivity(new Intent(getActivity(),MyOwnActivity.class));
                   // break;
                default:
                    break;
            }
        }
    };


    /**初始化系统自带adapter*/
    private void initDatas() {
        mAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return  mTitles.size();
            }

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    // 根据 position 设置每个Fragment View
                    case 0:
                        return VpSimpleFragmentA.newInstance("a",1);
                    case 1:
                        return VpSimpleFragmentB.newInstance("b",2);
                    case 2:
                        return VpSimpleFragment.newInstance("c",3);
                    default:
                        return null;
                }
            }
        };
    }
}
