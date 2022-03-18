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

}
