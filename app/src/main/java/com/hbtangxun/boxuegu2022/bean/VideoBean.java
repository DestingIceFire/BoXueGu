package com.hbtangxun.boxuegu2022.bean;

/**
 * 视频 实体类
 */

public class VideoBean {
    /**
     * chapterId : 1
     * videoId : 1
     * title : 第1章 Android 基础入门
     * secondTitle : Android系统简介
     * videoPath : video11.mp4
     */

    private int chapterId;
    private String videoId;
    private String title;
    private String secondTitle;
    private String videoPath;

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getVideoId() {
        return videoId == null ? "" : videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSecondTitle() {
        return secondTitle == null ? "" : secondTitle;
    }

    public void setSecondTitle(String secondTitle) {
        this.secondTitle = secondTitle;
    }

    public String getVideoPath() {
        return videoPath == null ? "" : videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    @Override
    public String toString() {
        return "VideoBean{" +
                "chapterId=" + chapterId +
                ", videoId='" + videoId + '\'' +
                ", title='" + title + '\'' +
                ", secondTitle='" + secondTitle + '\'' +
                ", videoPath='" + videoPath + '\'' +
                '}';
    }
}
