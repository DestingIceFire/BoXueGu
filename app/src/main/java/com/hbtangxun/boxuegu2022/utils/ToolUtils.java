package com.hbtangxun.boxuegu2022.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 工具类
 */
public class ToolUtils {

    private Toast toast;

    /**
     * 短吐司
     *
     * @param context
     * @param msg
     */
    public static void showShortToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长吐司
     *
     * @param context
     * @param msg
     */
    public static void showLongToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
