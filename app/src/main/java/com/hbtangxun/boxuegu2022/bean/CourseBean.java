package com.hbtangxun.boxuegu2022.bean;

/**
 * 课程 实体类
 */
public class CourseBean {

    private int id;         //每章ID
    private String imgTitle;// 图片上的标题
    private String title;   // 每章标题
    private String intro;   // 每章视频的简介
    private String icon;    // 广告栏上的图片
    private int index;      // 轮播图小圆点个数

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgTitle() {
        return imgTitle == null ? "" : imgTitle;
    }

    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro == null ? "" : intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getIcon() {
        return icon == null ? "" : icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "CourseBean{" +
                "id=" + id +
                ", imgTitle='" + imgTitle + '\'' +
                ", title='" + title + '\'' +
                ", intro='" + intro + '\'' +
                ", icon='" + icon + '\'' +
                ", index=" + index +
                '}';
    }
}
