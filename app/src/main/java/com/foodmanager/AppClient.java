package com.foodmanager;

import android.app.Application;
import android.content.Context;

import com.foodmanager.utils.StaticClass;
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager;

import cn.bmob.v3.Bmob;

public class AppClient extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        QMUISwipeBackActivityManager.init(this);
        Bmob.initialize(this, StaticClass.APPKEY);
    }
}
