package com.langdunzx.www.zanbei.fragment.Information;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.langdunzx.www.zanbei.R;
import com.langdunzx.www.zanbei.adapter.NewsRecycleViewAdapter;
import com.langdunzx.www.zanbei.fragment.BaseBackFragment;
import com.langdunzx.www.zanbei.utils.DataServer;

/**
 * Created by Administrator on 2016/7/8 0008.
 */
public class NewsInformationFragment extends BaseBackFragment implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener{
    private View view;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private static final int TOTAL_COUNTER = 18;
    private static final int PAGE_SIZE = 6;
    private int delayMillis = 1000;
    private int mCurrentCounter = 0;
    private NewsRecycleViewAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news_information,null);
        initView();
        initAdapter();
        addHeadView();
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }



    private void initView(){
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initAdapter() {
        mAdapter = new NewsRecycleViewAdapter(PAGE_SIZE);
        mAdapter.openLoadAnimation();
        /**
         * 添加动画
         */
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
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
        View headView = getActivity().getLayoutInflater().inflate(R.layout.news_head_view, (ViewGroup) mRecyclerView.getParent(), false);
        ((TextView)headView.findViewById(R.id.tv)).setText("click use custom loading view");
        final View customLoading = getActivity().getLayoutInflater().inflate(R.layout.custom_loading, (ViewGroup) mRecyclerView.getParent(), false);
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


    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.setNewData(DataServer.getSampleData(PAGE_SIZE));
                mAdapter.openLoadMore(PAGE_SIZE, true);
                mCurrentCounter = PAGE_SIZE;
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, delayMillis);
    }
}
