package com.langdunzx.www.zanbei.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.langdunzx.www.zanbei.R;
import com.langdunzx.www.zanbei.utils.DataServer;
import com.langdunzx.www.zanbei.utils.GlideCircleTransform;
import com.langdunzx.www.zanbei.vo.FriendsEntity;

/**
 * Created by cuiyinglai on 16/7/22.
 */
public class FriendsAdapter extends BaseQuickAdapter<FriendsEntity>{

    public FriendsAdapter() {
        super( R.layout.item_friends_information, DataServer.getFriendsData(100));
    }

    public FriendsAdapter(int dataSize) {
        super( R.layout.item_friends_information, DataServer.getFriendsData(dataSize));
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendsEntity item) {

        helper.setText(R.id.friends_name,item.getName()).
                setOnClickListener(R.id.friends_rl,new OnItemChildClickListener());
//        Glide.with(mContext).load(item.getUserAvatar()).crossFade().placeholder(R.mipmap.def_head).transform(new GlideCircleTransform(mContext)).into((ImageView) helper.getView(R.id.tweetAvatar));
    }
}
