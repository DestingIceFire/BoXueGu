package com.hbtangxun.boxuegu2022.bean;

/**
 * 个人资料 实体类
 */
public class UserBean {

    private String userName;  //用户名
    private String nickName;  //昵称
    private String sex;       //性别
    private String signature; //个性签名

    public String getUserName() {
        return userName == null ? "" : userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName == null ? "" : nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex == null ? "" : sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSignature() {
        return signature == null ? "" : signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sex='" + sex + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}
