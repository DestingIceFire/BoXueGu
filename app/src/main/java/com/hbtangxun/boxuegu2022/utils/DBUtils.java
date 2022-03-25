package com.hbtangxun.boxuegu2022.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hbtangxun.boxuegu2022.bean.UserBean;
import com.hbtangxun.boxuegu2022.sqlite.SQLiteHelper;

/**
 * 用于操作数据库的工具类
 */
public class DBUtils {

    private static DBUtils instance = null;
    private static SQLiteHelper helper;
    private static SQLiteDatabase db;

    public DBUtils(Context context) {
        helper = new SQLiteHelper(context);
        db = helper.getWritableDatabase();
    }

    public static DBUtils getInstance(Context context) {
        if (instance == null) {
            instance = new DBUtils(context);
        }
        return instance;
    }

    /**
     * 保存个人资料
     *
     * @param userBean
     */
    public void saveUserInfo(UserBean userBean) {
        ContentValues cv = new ContentValues();
        cv.put("userName", userBean.getUserName());
        cv.put("nickName", userBean.getNickName());
        cv.put("sex", userBean.getSex());
        cv.put("signature", userBean.getSignature());
        db.insert(SQLiteHelper.U_USERINFO, null, cv);
    }

    /**
     * 获取个人资料
     *
     * @param userName
     * @return
     */
    public UserBean getUserInfo(String userName) {
        String sql = "SELECT * FROM " + SQLiteHelper.U_USERINFO + " WHERE userName=?";
        Cursor cursor = db.rawQuery(sql, new String[]{userName});
        UserBean bean = null;
        while (cursor.moveToNext()) {
            bean = new UserBean();
            bean.setUserName(cursor.getString(cursor.getColumnIndex("userName")));
            bean.setNickName(cursor.getString(cursor.getColumnIndex("nickName")));
            bean.setSex(cursor.getString(cursor.getColumnIndex("sex")));
            bean.setSignature(cursor.getString(cursor.getColumnIndex("signature")));
        }
        return bean;
    }

    /**
     * 修改个人资料
     *
     * @param key
     * @param value
     * @param userName
     */
    public void updateUserName(String key, String value, String userName) {
            ContentValues cv = new ContentValues();
        cv.put(key, value);
        db.update(SQLiteHelper.U_USERINFO, cv, "userName=?", new String[]{userName});
    }

}