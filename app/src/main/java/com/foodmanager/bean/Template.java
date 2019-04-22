package com.foodmanager.bean;

import java.util.List;

import cn.bmob.v3.BmobObject;

public class Template extends BmobObject {
    private String name;
    private List<TextBean> textBeanList;
    private boolean fabu;
    private boolean tijiao;
    private String fenshu;

    public String getFenshu() {
        return fenshu;
    }

    public void setFenshu(String fenshu) {
        this.fenshu = fenshu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TextBean> getTextBeanList() {
        return textBeanList;
    }

    public void setTextBeanList(List<TextBean> textBeanList) {
        this.textBeanList = textBeanList;
    }

    public boolean isFabu() {
        return fabu;
    }

    public void setFabu(boolean fabu) {
        this.fabu = fabu;
    }

    public boolean isTijiao() {
        return tijiao;
    }

    public void setTijiao(boolean tijiao) {
        this.tijiao = tijiao;
    }
}
