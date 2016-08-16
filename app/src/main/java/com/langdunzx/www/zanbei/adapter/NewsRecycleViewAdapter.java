package com.langdunzx.www.zanbei.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.langdunzx.www.zanbei.R;
import com.langdunzx.www.zanbei.utils.DataServer;
import com.langdunzx.www.zanbei.utils.GlideCircleTransform;
import com.langdunzx.www.zanbei.vo.Status;

/**
 * Created by Administrator on 2016/7/8 0008.
 */
public class NewsRecycleViewAdapter extends BaseQuickAdapter<Status>{

    public NewsRecycleViewAdapter() {
        super( R.layout.item_news_information, DataServer.getSampleData(100));
    }

    public NewsRecycleViewAdapter(int dataSize) {
        super( R.layout.item_news_information, DataServer.getSampleData(dataSize));
    }

    @Override
    protected void convert(BaseViewHolder helper, Status item) {
        helper.setText(R.id.tweetName, "IFMA考试信息管理")
                .setImageResource(R.id.tweetText, R.drawable.friend_icon_message)
                .setImageResource(R.id.tweetText1, R.drawable.icon_eye)
                .setImageResource(R.id.tweetText2, R.drawable.icon_wallet_balance_small)
                .setOnClickListener(R.id.tweetAvatar, new OnItemChildClickListener())
                .setOnClickListener(R.id.tweetName, new OnItemChildClickListener());
//                .linkify(R.id.tweetText);

        Glide.with(mContext).load(item.getUserAvatar()).crossFade().placeholder(R.mipmap.def_head).transform(new GlideCircleTransform(mContext)).into((ImageView) helper.getView(R.id.tweetAvatar));
    }
}
