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
 * Created by cuiyinglai on 16/7/25.
 */
public class NewsHotAdapter extends BaseQuickAdapter<Status>{

    public NewsHotAdapter() {
        super( R.layout.item_news_information, DataServer.getSampleData(100));
    }

    public NewsHotAdapter(int dataSize) {
        super( R.layout.item_news_information, DataServer.getSampleData(dataSize));
    }


    @Override
    protected void convert(BaseViewHolder helper, Status item) {
        helper.setText(R.id.tweetName, item.getUserName())
                .setText(R.id.tweetText, item.getText())
                .setText(R.id.tweetDate, item.getCreatedAt())
                .setVisible(R.id.tweetRT, item.isRetweet())
                .setOnClickListener(R.id.tweetAvatar, new OnItemChildClickListener())
                .setOnClickListener(R.id.tweetName, new OnItemChildClickListener())
                .linkify(R.id.tweetText);

        Glide.with(mContext).load(item.getUserAvatar()).crossFade().placeholder(R.mipmap.def_head).transform(new GlideCircleTransform(mContext)).into((ImageView) helper.getView(R.id.tweetAvatar));
    }
}