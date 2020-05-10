package com.hbtangxun.boxuegu.utils;

import android.content.Context;
import android.widget.Toast;
/**
 * 常用工具
 */
public class ToolUtils {

    /**
     * 短吐司
     *
     * @param str
     */
    public static void showToastShort(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长吐司
     *
     * @param str
     */
    public void showToastLong(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }

}
