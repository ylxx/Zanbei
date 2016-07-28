package com.langdunzx.www.zanbei.fragment.Information;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.langdunzx.www.zanbei.R;
import com.langdunzx.www.zanbei.adapter.NewsHotAdapter;
import com.langdunzx.www.zanbei.config.Constants;
import com.langdunzx.www.zanbei.controller.BaseHandler;
import com.langdunzx.www.zanbei.controller.RequestCommant;
import com.langdunzx.www.zanbei.fragment.BaseBackFragment;
import com.langdunzx.www.zanbei.utils.DataServer;
import com.langdunzx.www.zanbei.utils.ShowErrorDialogUtil;
import com.langdunzx.www.zanbei.view.FlashView;
import com.langdunzx.www.zanbei.view.FlashViewListener;
import com.langdunzx.www.zanbei.vo.HomePageBodyVo;
import com.langdunzx.www.zanbei.vo.HomePageImageVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewsHotFragment extends BaseBackFragment implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{

    private View view;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private static final int TOTAL_COUNTER = 18;
    private static final int PAGE_SIZE = 6;
    private int delayMillis = 1000;
    private int mCurrentCounter = 0;
    private NewsHotAdapter mAdapter;

    private FlashView mFlashView;
    //轮播图地址
    private List<String> urls;
    //点击轮播图跳转路径
    private List<String> gotoUrls;
    private List<HomePageImageVo> lsBanner;
    private String userId;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news_hot,null);
        initView();
        initAdapter();
        addHeadView();
        requestHeadView();
        return view;
    }

    private void initView() {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        urls = new ArrayList<String>();
        gotoUrls = new ArrayList<String>();


    }

    private void initAdapter() {
        mAdapter = new NewsHotAdapter(PAGE_SIZE);
        mAdapter.openLoadAnimation();
        /**
         * 添加动画
         */
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        /**
         * 动画加载方式
         */
        mAdapter.isFirstOnly(true);
        mRecyclerView.setAdapter(mAdapter);
        mCurrentCounter = mAdapter.getData().size();
        mAdapter.setOnLoadMoreListener(this);
        mAdapter.openLoadMore(PAGE_SIZE, true);//or call mQuickAdapter.setPageSize(PAGE_SIZE);  mQuickAdapter.openLoadMore(true);
        addHeadView();
        mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addHeadView() {
        View headView = getActivity().getLayoutInflater().inflate(R.layout.head_view_news_hot, (ViewGroup) mRecyclerView.getParent(), false);
//        ((TextView)headView.findViewById(R.id.tv)).setText("click use custom loading view");
        final View customLoading = getActivity().getLayoutInflater().inflate(R.layout.custom_loading, (ViewGroup) mRecyclerView.getParent(), false);
        mFlashView = (FlashView) headView.findViewById(R.id.flashview);
        mFlashView.setOnFlashViewListener(new FlashViewListener() {
                                              @Override
                                              public void onClick(int position) {
                                                  String url = gotoUrls.get(position);

                                                  if (!url.contains("http://")) {
                                                      url = "http://" + url;
                                                  }
                                                  Uri content_url = Uri.parse(url);
                /*Intent intent = new Intent(getActivity(), WebEmbedActivity.class);
                intent.putExtra("url", String.valueOf(content_url));
                intent.putExtra("title", "助学宝");
                startActivity(intent);*/
                                              }
                                          }
        );



        headView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.setLoadingView(customLoading);
                mRecyclerView.setAdapter(mAdapter);
                Toast.makeText(getActivity(),"use ok!",Toast.LENGTH_LONG).show();
            }
        });
        mAdapter.addHeaderView(headView);
    }

    @Override
    public void onRefresh() {
        urls.clear();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.setNewData(DataServer.getSampleData(PAGE_SIZE));
                mAdapter.openLoadMore(PAGE_SIZE, true);
                mCurrentCounter = PAGE_SIZE;
                mSwipeRefreshLayout.setRefreshing(false);
                requestHeadView();
            }
        }, delayMillis);
    }

    @Override
    public void onLoadMoreRequested() {
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                if (mCurrentCounter >= TOTAL_COUNTER) {
                    mAdapter.notifyDataChangedAfterLoadMore(false);
                    View view = getActivity().getLayoutInflater().inflate(R.layout.not_loading, (ViewGroup) mRecyclerView.getParent(), false);
                    mAdapter.addFooterView(view);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.notifyDataChangedAfterLoadMore(DataServer.getSampleData(PAGE_SIZE), true);
                            mCurrentCounter = mAdapter.getData().size();
                        }
                    }, delayMillis);
                }
            }


        });
    }


    public void requestHeadView(){
        HashMap<String, String>  hashmap = new HashMap<String, String>();
        hashmap.put("userId", "1111");
        new RequestCommant().requestHomeData(new ReauestHandler(this), getActivity(), hashmap);
    }


    private class ReauestHandler extends BaseHandler {

        public ReauestHandler(Fragment fragment) {
            super(fragment);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            NewsHotFragment fragment = (NewsHotFragment) mFragment
                    .get();
            if (null != fragment) {

                if (msg.what == Constants.HOME_DATA) {
                    if (command.success) {
                        if(null != command.resData){
                            HomePageBodyVo body = (HomePageBodyVo)command.resData;
                            //轮播图
                            lsBanner =body.getBody();
                            if(lsBanner!=null){
                                for (int i = 0; i < lsBanner.size(); i++) {
                                    //轮播图地址
                                    String url = lsBanner.get(i).getUrl();
                                    urls.add(url);
                                    //跳转地址
                                    String gotoUrl=lsBanner.get(i).getPath();
                                    gotoUrls.add(gotoUrl);
                                }
                                mFlashView.setImageUris(urls);
                                //								mFlashView.setImagePaths(gotoUrls);
                            }

                        }
                    } else {
                        ShowErrorDialogUtil.showErrorDialog(getActivity(),(String) command.message);; // 請求失敗
                    }
                }
            }
        }
    }
}
