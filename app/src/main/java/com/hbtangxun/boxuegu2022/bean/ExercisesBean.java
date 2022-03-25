package com.hbtangxun.boxuegu2022.bean;

/**
 * 习题  实体类
 */
public class ExercisesBean {

    // 习题的属性
    private int id;     // 每章习题的ID
    private String title;   //每章习题的标题
    private String content; // 每章习题的题目
    private int background; // 每章习题前边的序号背景
    private int subjectId;  //   每道习题的ID
    private String subject; //每道习题的题目
    private String a;   // 每道习题的A选项
    private String b;   // 每道习题的B选项
    private String c;   // 每道习题的C选项
    private String d;   // 每道习题的D选项
    private int answer; //每道题的正确答案

    //用户选择答案
    //用户选中的选项（0表示选对了，1表示A选项错了，2表示B选项错了，3表示C选项错了，4表示D选项错了）
    private int select;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubject() {
        return subject == null ? "" : subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getA() {
        return a == null ? "" : a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b == null ? "" : b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c == null ? "" : c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d == null ? "" : d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getSelect() {
        return select;
    }

    public void setSelect(int select) {
        this.select = select;
    }

    @Override
    public String toString() {
        return "ExercisesBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", background=" + background +
                ", subjectId=" + subjectId +
                ", subject='" + subject + '\'' +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                ", answer=" + answer +
                ", select=" + select +
                '}';
    }
}
