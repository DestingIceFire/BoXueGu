package com.hbtangxun.boxuegu.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hbtangxun.boxuegu.bean.UserBean;
import com.hbtangxun.boxuegu.bean.VideoBean;
import com.hbtangxun.boxuegu.sqlite.SQLiteHelper;

/**
 * 用于操作数据库
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
     * 获取个人资料
     *
     * @param userName
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
    public void updateUserInfo(String key, String value, String userName) {
        ContentValues cv = new ContentValues();
        cv.put(key, value);
        db.update(SQLiteHelper.U_USERINFO, cv, "userName=?", new String[]{userName});
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
     * 保存视频播放记录
     *
     * @param bean
     * @param name
     */
    public void saveVideoPlayList(VideoBean bean, String name) {
        //判断如果里面有此播放记录则需要重新存放
        if (hasVideoPlay(bean.getChapterId(), bean.getVideoId(), name)) {
            boolean isDelete = delVideoPlay(bean.getChapterId(), bean.getVideoId(), name);
            if (!isDelete) {
                //没有删除成功，则需跳出此方法，不再执行以下语句
                return;
            }
        }
        ContentValues cv = new ContentValues();
        cv.put("userName", name);
        cv.put("chapterId", bean.getChapterId());
        cv.put("videoId", bean.getVideoId());
        cv.put("videoPath", bean.getVideoPath());
        cv.put("title", bean.getTitle());
        cv.put("secondTitle", bean.getSecondTitle());
        db.insert(SQLiteHelper.U_VIDEO_PLAY_LIST, null, cv);
    }

    /**
     * 判断视频记录是否存在
     *
     * @param chapterId
     * @param videoId
     * @param name
     * @return
     */
    private boolean hasVideoPlay(int chapterId, int videoId, String name) {
        boolean hasVideo = false;
        String sql = "SELECT * FROM " + SQLiteHelper.U_VIDEO_PLAY_LIST
                + " WHERE chapterId=? AND videoId=? AND userName=?";
        Cursor cursor = db.rawQuery(sql, new String[]{chapterId + "", videoId + "", name});
        if (cursor.moveToFirst()) {
            hasVideo = true;
        }
        cursor.close();

        return hasVideo;
    }

    /**
     * 删除已经存在的数据
     *
     * @param chapterId
     * @param videoId
     * @param name
     * @return
     */
    private boolean delVideoPlay(int chapterId, int videoId, String name) {
        boolean delS = false;
        int i = db.delete(SQLiteHelper.U_VIDEO_PLAY_LIST, "chapterId=? AND videoId=? AND userName=?",
                new String[]{chapterId + "", videoId + "", name});
        if (i > 0) {
            delS = true;
        }
        return delS;
    }
}
