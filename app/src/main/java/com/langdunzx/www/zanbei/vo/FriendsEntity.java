package com.langdunzx.www.zanbei.vo;

import java.io.Serializable;

/**
 * Created by cuiyinglai on 16/7/22.
 */
public class FriendsEntity implements Serializable{

    private String name;
    private String head;
    private String tag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
