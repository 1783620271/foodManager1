package com.foodmanager.bean;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * 用户表
 */
public class User extends BmobUser {

    private String uName;//姓名

    private String HuanXinPwd;//登录环信的密码


    //头像
    private BmobFile avatar;



    public String getHuanXinPwd() {
        return HuanXinPwd;
    }

    public void setHuanXinPwd(String huanXinPwd) {
        HuanXinPwd = huanXinPwd;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public BmobFile getAvatar() {
        return avatar;
    }

    public void setAvatar(BmobFile avatar) {
        this.avatar = avatar;
    }
}
