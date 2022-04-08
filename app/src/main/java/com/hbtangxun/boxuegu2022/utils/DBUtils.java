package com.hbtangxun.boxuegu2022.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hbtangxun.boxuegu2022.bean.UserBean;
import com.hbtangxun.boxuegu2022.bean.VideoBean;
import com.hbtangxun.boxuegu2022.sqlite.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 保存视频播放记录
     *
     * @param bean
     * @param name
     */
    public void saveVideoPlayList(VideoBean bean, String name) {
        // 判断数据库里是否有存放该数据，有就删，没有直接存
        if (hasVideoPlay(bean.getChapterId(), bean.getVideoId(), name)) {
            boolean isDelete = delVideoPlay(bean.getChapterId(), bean.getVideoId(), name);
            if (!isDelete) {
                //没有删除成功，则需要跳出这个方法，不再执行下列语句
                return;
            }
        }
        // 保存视频播放记录
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
    private boolean hasVideoPlay(int chapterId, String videoId, String name) {
        boolean hasVideo = false;
        String sql = "SELECT * FROM " + SQLiteHelper.U_VIDEO_PLAY_LIST
                + " WHERE chapterId=? AND videoId=? And userName=?";
        Cursor cursor = db.rawQuery(sql, new String[]{chapterId + "", videoId + "", name});
        if (cursor.moveToFirst()) {
            hasVideo = true;
        }
        cursor.close();

        return hasVideo;
    }

    /**
     * 删除已存在的视频
     *
     * @param chapterId
     * @param videoId
     * @param name
     * @return
     */
    private boolean delVideoPlay(int chapterId, String videoId, String name) {
        boolean delS = false;
        int num = db.delete(SQLiteHelper.U_VIDEO_PLAY_LIST, "chapterId=? AND videoId=? And userName=?",
                new String[]{chapterId + "", videoId + "", name});
        if (num > 1) {
            delS = true;
        }
        return delS;
    }

    /**
     * 获取视频记录信息
     *
     * @param userName
     * @return
     */
    public List<VideoBean> getVideoHistory(String userName) {
        String sql = "SELECT * FROM " + SQLiteHelper.U_VIDEO_PLAY_LIST + " WHERE userName=?";
        Cursor cursor = db.rawQuery(sql, new String[]{userName});
        List<VideoBean> vbl = new ArrayList<>();
        VideoBean bean = null;
        while (cursor.moveToNext()) {
            bean = new VideoBean();
            bean.setChapterId(cursor.getInt(cursor.getColumnIndex("chapterId")));
            bean.setVideoId(cursor.getString(cursor.getColumnIndex("videoId")));
            bean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            bean.setSecondTitle(cursor.getString(cursor.getColumnIndex("secondTitle")));
            bean.setVideoPath(cursor.getString(cursor.getColumnIndex("videoPath")));
            vbl.add(bean);
            bean = null;
        }
        return vbl;
    }

}
