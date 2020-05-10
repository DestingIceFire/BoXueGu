package com.hbtangxun.boxuegu.bean;

/**
 * 习题实体类
 */

public class ExercisesBean {

    private int id; // 每章习题id
    private String title;   // 每章习题标题
    private String content; // 每章习题的数目
    private int background; // 每章习题前边的序号背景
    private int subjectId;  // 每道习题的Id
    private String subject; // 每道习题的题干
    private String a;   // 每道题的A选项
    private String b;   // 每道题的B选项
    private String c;   // 每道题的C选项
    private String d;   // 每道题的D选项
    private String answer; // 每道题的正确答案
    // 用户选中的那项（0表示所选项对了，1表示A选项错，2表示B选项错，3表示C选项错，4表示D选项错）
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
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
