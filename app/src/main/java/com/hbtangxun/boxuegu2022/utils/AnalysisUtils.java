package com.hbtangxun.boxuegu2022.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 用户名 工具类
 */
public class AnalysisUtils {

    /**
     * 用于读取SP中的用户名
     *
     * @param context
     * @return
     */
    public static String readLoginUserName(Context context) {
        SharedPreferences sp = context.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        String userName = sp.getString("loginUserName", "");
        return userName;
    }

    /**
     * 清除SP中登录状态和登录时的用户名
     *
     * @param context
     */
    public static void cleanLoginStatus(Context context) {
        // 获取SP对象
        SharedPreferences sp = context.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        // 获取编辑器
        SharedPreferences.Editor edit = sp.edit();
        // 清除登录状态
        edit.putBoolean("isLogin", false);
        // 清除登录用户名
        edit.putString("loginUserName", "");
        // 提交
        edit.commit();
    }

}
