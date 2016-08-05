package com.langdunzx.www.zanbei.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cuiyinglai on 16/8/4.
 */
public class FriendsDataEntity implements Serializable{


    /**
     * message : 鑾峰彇鎴愬姛
     * friends : [{"id":"146","name":"13718141869","mobile":"13718141869","avatar":"api.anydo.com/Uploads/Home/Avatar/20160804/57a2ddf3e35b3.jpg"},{"id":"9","name":"18664685976","mobile":"18664685976","avatar":"\"\""},{"id":"9","name":"18664685976","mobile":"18664685976","avatar":"\"\""}]
     * success : 0
     */

    private String message;
    private int success;
    /**
     * id : 146
     * name : 13718141869
     * mobile : 13718141869
     * avatar : api.anydo.com/Uploads/Home/Avatar/20160804/57a2ddf3e35b3.jpg
     */

    private List<FriendsBean> friends;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<FriendsBean> getFriends() {
        return friends;
    }

    public void setFriends(List<FriendsBean> friends) {
        this.friends = friends;
    }

    public static class FriendsBean {
        private String id;
        private String name;
        private String mobile;
        private String avatar;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
