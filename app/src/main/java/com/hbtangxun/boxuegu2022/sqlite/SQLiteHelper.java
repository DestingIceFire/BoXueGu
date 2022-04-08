package com.hbtangxun.boxuegu2022.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * SQLite 工具类
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "hbtangxun.db";
    private static final int DB_VERSION = 1;
    public static final String U_USERINFO = "userinfo"; //个人资料
    public static final String U_VIDEO_PLAY_LIST = "videoplaylist"; //播放记录


    public SQLiteHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建个人信息表
        db.execSQL("CREATE TABLE IF NOT EXISTS " + U_USERINFO + "("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, " // ID 主键 自增长
                + "userName VARCHAR, "  // 用户名
                + "nickName VARCHAR, "  // 昵称
                + "sex VARCHAR, "       // 性别
                + "signature VARCHAR"   // 个性签名
                + ")");
        //创建视频播放记录表
        db.execSQL("CREATE TABLE IF NOT EXISTS " + U_VIDEO_PLAY_LIST + "("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, " // ID 主键 自增长
                + "userName VARCHAR, "  // 用户名
                + "chapterId VARCHAR, "  // 章节ID
                + "videoId VARCHAR, "     // 视频ID
                + "title VARCHAR, "      // 视频章节名称
                + "secondTitle VARCHAR, "     // 视频名称
                + "videoPath VARCHAR"   // 视频地址
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + U_USERINFO);
        db.execSQL("DROP TABLE IF EXISTS " + U_VIDEO_PLAY_LIST);
        onCreate(db);
    }
}
