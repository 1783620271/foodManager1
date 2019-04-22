package com.foodmanager.ui;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.foodmanager.bean.PageRequestBean;
import com.foodmanager.utils.T;


import java.util.List;

/**
 * 带列表会刷新的Fragment
 *
 * @param <D>
 * @param <A>
 */
public abstract class BaseAdapterFragment<D, A extends BaseQuickAdapter>
        extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener {

    public RecyclerView mRv; //recyclerVIew列表
    public PageRequestBean exhibitionRequest;//携带加载第几页,每页加载几条的数据对象
    public int pageSize = 6;//每页加载几条数据
    public A adapter;//recyclerView 适配器

    /**
     * 初始化布局
     *
     * @param view
     */
    @Override
    protected void initView(View view) {
        mRv = getRecyclerView(view);
        initLayout(view);
    }

    /**
     * 获取布局控件
     *
     * @param view
     */
    protected abstract void initLayout(View view);

    /**
     * 获取recyclerview控件
     *
     * @param view
     * @return
     */
    protected abstract RecyclerView getRecyclerView(View view);

    /**
     * 初始化数据
     */
    @Override
    protected final void init() {
        initAdapterData();
        initOthers();
    }

    /**
     * 初始化其他数据
     */
    protected abstract void initOthers();

    /**
     * 初始化数据
     */
    protected void initAdapterData() {
        //初始化会刊列表
        initAdapter(getSpamCount());
        getListWihtPageAndSize(1);
    }

    /**
     * 获取列表的列数,默认是2列
     *
     * @return
     */
    protected int getSpamCount() {
        return 2;
    }


    /**
     * 初始化会刊列表
     */
    protected void initAdapter(int spanCount) {
        if (spanCount == 1) {
            //单列列表
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRv.setLayoutManager(linearLayoutManager);
        } else {
            //多列列表
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), spanCount);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRv.setLayoutManager(gridLayoutManager);
        }
        adapter = getAdapter();
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mRv.setAdapter(adapter);
        adapter.bindToRecyclerView(mRv);
        adapter.isFirstOnly(false);
        adapter.disableLoadMoreIfNotFullPage();//第一次进入不回掉加载更多
        adapter.setOnLoadMoreListener(this, mRv);
    }

    /**
     * 获取一个适配器,继承BaseQuickAdapter
     *
     * @return
     */
    protected abstract A getAdapter();

    /**
     * 获取会刊列表数据
     *
     * @param page 第几页
     */
    protected void getListWihtPageAndSize(int page) {
        if (exhibitionRequest == null)
            exhibitionRequest = new PageRequestBean();
        exhibitionRequest.setPageNo(page);
        exhibitionRequest.setPageSize(pageSize);
        getListWithDataFromServer(exhibitionRequest);
    }

    /**
     * presenter调用方法获取数据,根据exhibitionRequest携带的页面
     *
     * @param exhibitionRequest 获取第几页,每页几条数据
     */
    protected abstract void getListWithDataFromServer(PageRequestBean exhibitionRequest);

    /**
     * 获取会刊列表成功
     * 数据获取成功的时候,在P层调用该方法,实现数据的添加
     *
     * @param datas 会刊列表的数据集合
     */
    public void getListSuccess(List<D> datas) {
        if (datas.size() < pageSize) {
            adapter.loadMoreEnd(true);
            T.showShort("没有更多数据");
        } else {
            adapter.loadMoreComplete();
        }
        adapter.addData(datas);
    }


    /**
     * 上拉加载更多
     */
    @Override
    public void onLoadMoreRequested() {
        mRv.postDelayed(() -> getListWihtPageAndSize(exhibitionRequest.getPageNo() + 1), 1000);
    }
}
