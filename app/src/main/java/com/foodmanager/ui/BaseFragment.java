package com.foodmanager.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.qmuiteam.qmui.arch.QMUIFragment;

public abstract class BaseFragment extends QMUIFragment {
    /*
     * 上下文对象,即当前依附的Activity
     */
    protected Context mContext;
    protected View mRootView;
    protected View mRoot;

    /**
     * Log标记
     */
    public final String TAG = this.getClass().getSimpleName();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layId = getLayout();
        // 初始化当前的跟布局，但是不在创建时就添加到container里边
        View root = inflater.inflate(layId, container, false);
        initView(root);
        mRoot = root;
        return mRoot;
    }

    /**
     * 获取布局Id
     *
     * @return
     */
    protected abstract int getLayout();

    @Override
    public final void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRootView = view;
        initView(mRootView);
    }

    /**
     * 初始化布局
     *
     * @param view
     */
    protected abstract void initView(View view);

    @Override
    public final void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    /**
     * 初始化数据
     */
    protected abstract void init();

    /**
     * 返回按键触发时调用
     *
     * @return 返回True代表我已处理返回逻辑，Activity不用自己finish。
     * 返回False代表我没有处理逻辑，Activity自己走自己的逻辑
     */
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mRootView != null) {
            unbindDrawables(mRootView);
        }
    }


    private void unbindDrawables(View view) {
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup && !(view instanceof AdapterView)) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
