package com.hbtangxun.boxuegu2022.utils;

import android.app.Activity;
import android.content.Context;
import android.icu.text.DisplayContext;
import android.util.DisplayMetrics;
import android.view.Display;
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

    /**
     * 读取屏幕宽度
     *
     * @param mActivity
     * @return
     */
    public static int getScreenWidth(Activity mActivity) {
        DisplayMetrics metrics = new DisplayMetrics();
        Display display = mActivity.getWindowManager().getDefaultDisplay();
        display.getMetrics(metrics);
        return metrics.widthPixels;
    }
}
