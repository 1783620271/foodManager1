package com.foodmanager.ui;

import android.app.Activity;
import android.os.Bundle;


import com.foodmanager.utils.L;
import com.qmuiteam.qmui.arch.QMUIFragmentActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;


public abstract class BaseActivity extends QMUIFragmentActivity {

    /**
     * Log标记
     */
    public final String TAG = this.getClass().getSimpleName();

    /**
     * 上下文对象
     */
    protected Activity mContext;


    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.i(TAG, "--->onCreate()");
        /*
        布局设置
         */
        setContentView(getLayout());
        QMUIStatusBarHelper.translucent(this);
        initLayout();
        init();
    }


    /**
     * 初始化布局
     */
    protected abstract void initLayout();


    /**
     * 获取子aitivity的布局ID
     *
     * @return
     */
    protected abstract int getLayout();

    /**
     * 子类activity的初始化方法
     */
    protected abstract void init();


    @Override
    protected void onStart() {
        super.onStart();
        L.i(TAG, "----------->onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        L.i(TAG, "----------->onResume()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        L.i(TAG, "----------->onRestart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        L.i(TAG, "----------->onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        L.i(TAG, "----------->onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.i(TAG, "----------->onDestroy()");
    }
}
