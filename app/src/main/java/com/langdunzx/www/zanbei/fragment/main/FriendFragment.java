package com.langdunzx.www.zanbei.fragment.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.langdunzx.www.zanbei.R;
import com.langdunzx.www.zanbei.adapter.FriendsAdapter;
import com.langdunzx.www.zanbei.adapter.SortAdapter;
import com.langdunzx.www.zanbei.config.Constants;
import com.langdunzx.www.zanbei.controller.BaseHandler;
import com.langdunzx.www.zanbei.controller.RequestCommant;
import com.langdunzx.www.zanbei.fragment.BaseBackFragment;
import com.langdunzx.www.zanbei.utils.DataServer;
import com.langdunzx.www.zanbei.utils.linkman.AsyncTaskBase;
import com.langdunzx.www.zanbei.utils.linkman.CharacterParser;
import com.langdunzx.www.zanbei.utils.linkman.ConstactUtil;
import com.langdunzx.www.zanbei.utils.linkman.PinyinComparator;
import com.langdunzx.www.zanbei.view.linkman.LoadingView;
import com.langdunzx.www.zanbei.view.linkman.SideBar;
import com.langdunzx.www.zanbei.vo.FriendsDataEntity;
import com.langdunzx.www.zanbei.vo.SortModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendFragment extends BaseBackFragment implements BaseQuickAdapter.RequestLoadMoreListener,SwipeRefreshLayout.OnRefreshListener{


    private View view;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private static final int TOTAL_COUNTER = 42;
    private static final int PAGE_SIZE = 10;
    private int delayMillis = 1000;
    private int mCurrentCounter = 0;
    private FriendsAdapter mAdapter;
    private Map<String, String> callRecords;
    private List<FriendsDataEntity.FriendsBean> friendsBeens = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_friends_information,null);
        initView();
        return view;
    }


    private void initView() {
        requestFriendsData();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initAdapter() {
        mAdapter = new FriendsAdapter(friendsBeens);
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
                Toast.makeText(getActivity(),Integer.toString(position),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void addHeadView() {
        View headView = getActivity().getLayoutInflater().inflate(R.layout.head_view_friends, (ViewGroup) mRecyclerView.getParent(), false);
//        ((TextView)headView.findViewById(R.id.tv)).setText("click use custom loading view");
        sideBar = (SideBar) headView.findViewById(R.id.sidrbar);
        dialog = (TextView) headView.findViewById(R.id.dialog);
        sortListView = (ListView)headView.findViewById(R.id.country_lvcountry);
        initData();
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
    private SideBar sideBar;
    private TextView dialog;
    private ListView sortListView;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<SortModel> SourceDateList;

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;
    private String numberLinkman;
    private SortAdapter adapter;

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.setNewData(friendsBeens);
                mAdapter.openLoadMore(PAGE_SIZE, true);
                mCurrentCounter = PAGE_SIZE;
                mSwipeRefreshLayout.setRefreshing(false);
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
                            mAdapter.notifyDataChangedAfterLoadMore(friendsBeens, true);
                            mCurrentCounter = mAdapter.getData().size();
                        }
                    }, delayMillis);
                }
            }


        });
    }
    /**
     * 获取好友列表
     */
    private void requestFriendsData() {
        friendsBeens.clear();
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("","");
        new RequestCommant().requestFriendsData(new RequestHandler(this),getActivity(),hashMap);
    }

    private class RequestHandler extends BaseHandler{

        public RequestHandler(Fragment fragment) {
            super(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            FriendFragment fragment = (FriendFragment) mFragment.get();
            if(null != fragment){
                if(msg.what == Constants.GET_FRIENDS_DATA){
                    if(command.success){
                        FriendsDataEntity friendsEntity = (FriendsDataEntity) command.resData;
                        for (int i = 0;i < friendsEntity.getFriends().size();i ++){
                            friendsBeens.add(friendsEntity.getFriends().get(i));
                        }
                        initAdapter();
                        addHeadView();
                        mRecyclerView.setAdapter(mAdapter);
                    }
                }
            }

        }
    }
    private void initData() {

        // 实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();

        pinyinComparator = new PinyinComparator();

        sideBar.setTextView(dialog);

        // 设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @SuppressLint("NewApi")
            @Override
            public void onTouchingLetterChanged(String s) {
                // 该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    sortListView.setSelection(position);
                }
            }
        });

        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // 这里要利用adapter.getItem(position)来获取当前position所对应的对象
                //				Toast.makeText(getApplication(),
                //						((SortModel)adapter.getItem(position)).getName(),
                //						Toast.LENGTH_SHORT).show();
                numberLinkman = callRecords.get(((SortModel) adapter
                        .getItem(position)).getName());
                //弹出拨号类型界面
//                popupWindowType.showAtLocation(root, Gravity.CENTER, 0, 0);

            }
        });

    }

}
