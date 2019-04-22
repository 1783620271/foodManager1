package com.foodmanager.utils;

import android.content.Context;

import java.lang.reflect.Field;

/**
 * 工具统一类
 */
public class UtilTools {


    /**
     * 获取手机型号
     *
     * @param context 上下文
     * @return String
     */
    public static String getMobileModel(Context context) {
        try {
            String model = android.os.Build.MODEL;
            return model;
        } catch (Exception e) {
            return "未知！";
        }
    }

    /**
     * 获取手机品牌
     *
     * @param context 上下文
     * @return String
     */
    public static String getMobileBrand(Context context) {
        try {
            // android系统版本号
            String brand = android.os.Build.BRAND;
            return brand;
        } catch (Exception e) {
            return "未知";
        }
    }


    //获取状态栏高度
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }


}
