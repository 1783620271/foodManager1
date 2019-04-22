package com.foodmanager.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListViewHeightUtils {


//计算ListView的高度终极版，考虑到多行textview的情况
//CSDN:  https://blog.csdn.net/memoryjs/article/details/77219630

    public  void setListViewHeightBasedOnChildren(Context context,ListView pull) {

        ListAdapter listAdapter = pull.getAdapter();
        if (listAdapter == null) {
            return;
        }

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;

        int totalHeight = 0;
        int listViewWidth = w_screen - dip2px(context, 16);                                         //listView在布局时的宽度
        int widthSpec = View.MeasureSpec.makeMeasureSpec(listViewWidth, View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, pull);
            listItem.measure(widthSpec, 0);

            int itemHeight = listItem.getMeasuredHeight();
            totalHeight += itemHeight;
        }
        // 减掉底部分割线的高度
        int historyHeight = totalHeight
                + (pull.getDividerHeight() * listAdapter.getCount() - 1);

        ViewGroup.LayoutParams params = pull.getLayoutParams();
        params.height = historyHeight;
        pull.setLayoutParams(params);

    }



    public static int dip2px(Context context, float dipValue){

        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }
}
