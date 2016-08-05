package com.langdunzx.www.zanbei.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.langdunzx.www.zanbei.R;
import com.langdunzx.www.zanbei.vo.FriendsDataEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuiyinglai on 16/7/22.
 */
public class FriendsAdapter extends BaseQuickAdapter<FriendsDataEntity.FriendsBean>{

    private List<FriendsDataEntity.FriendsBean> friendsBeens = new ArrayList<>();

    public FriendsAdapter(List<FriendsDataEntity.FriendsBean> friendsBeens) {
        super( R.layout.item_friends_information, friendsBeens);
    }

   /* public FriendsAdapter(int dataSise, FriendsDataEntity  friendsDataEntity){
        super(R.layout.item_friends_information,);

    }*/

    @Override
    protected void convert(BaseViewHolder helper, FriendsDataEntity.FriendsBean item) {

        helper.setText(R.id.friends_name,item.getName()).
                setOnClickListener(R.id.friends_rl,new OnItemChildClickListener());
//        Glide.with(mContext).load(item.getUserAvatar()).crossFade().placeholder(R.mipmap.def_head).transform(new GlideCircleTransform(mContext)).into((ImageView) helper.getView(R.id.tweetAvatar));
    }
}
