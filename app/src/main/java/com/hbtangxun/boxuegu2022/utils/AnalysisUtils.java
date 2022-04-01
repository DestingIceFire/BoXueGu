package com.hbtangxun.boxuegu2022.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Xml;
import android.widget.ImageView;

import com.hbtangxun.boxuegu2022.bean.CourseBean;
import com.hbtangxun.boxuegu2022.bean.ExercisesBean;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户名 工具类
 */
public class AnalysisUtils {

    /**
     * 用于读取SP中的用户名
     *
     * @param context
     * @return
     */
    public static String readLoginUserName(Context context) {
        SharedPreferences sp = context.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        String userName = sp.getString("loginUserName", "");
        return userName;
    }

    /**
     * 清除SP中登录状态和登录时的用户名
     *
     * @param context
     */
    public static void cleanLoginStatus(Context context) {
        // 获取SP对象
        SharedPreferences sp = context.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        // 获取编辑器
        SharedPreferences.Editor edit = sp.edit();
        // 清除登录状态
        edit.putBoolean("isLogin", false);
        // 清除登录用户名
        edit.putString("loginUserName", "");
        // 提交
        edit.commit();
    }

    /**
     * 解析习题详情界面的xml数据
     *
     * @param is
     * @return
     * @throws Exception
     */
    public static List<ExercisesBean> getExercisesInfos(InputStream is) throws Exception {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "utf-8");
        List<ExercisesBean> infos = null;
        ExercisesBean bean = null;
        int type = parser.getEventType();
        while (type != XmlPullParser.END_DOCUMENT) {
            switch (type) {
                case XmlPullParser.START_TAG:
                    if ("infos".equals(parser.getName())) {
                        infos = new ArrayList<>();
                    } else if ("exercises".equals(parser.getName())) {
                        bean = new ExercisesBean();
                        String ids = parser.getAttributeValue(0);
                        bean.setSubjectId(Integer.parseInt(ids));
                    } else if ("subject".equals(parser.getName())) {
                        String subject = parser.nextText();
                        bean.setSubject(subject);
                    } else if ("a".equals(parser.getName())) {
                        String a = parser.nextText();
                        bean.setA(a);
                    } else if ("b".equals(parser.getName())) {
                        String b = parser.nextText();
                        bean.setB(b);
                    } else if ("c".equals(parser.getName())) {
                        String c = parser.nextText();
                        bean.setC(c);
                    } else if ("d".equals(parser.getName())) {
                        String d = parser.nextText();
                        bean.setD(d);
                    } else if ("answer".equals(parser.getName())) {
                        String answer = parser.nextText();
                        bean.setAnswer(Integer.parseInt(answer));
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("exercises".equals(parser.getName())) {
                        infos.add(bean);
                        bean = null;
                    }
                    break;
            }
            type = parser.next();
        }

        return infos;
    }

    /**
     * 判断A、B、C、D 选项是否可选择
     */
    public static void setABCDEnable(boolean value, ImageView iv_ex_a, ImageView iv_ex_b, ImageView iv_ex_c, ImageView iv_ex_d) {

        iv_ex_a.setEnabled(value);
        iv_ex_b.setEnabled(value);
        iv_ex_c.setEnabled(value);
        iv_ex_d.setEnabled(value);

    }


    public static List<CourseBean> getCoursesInfos(InputStream is) throws Exception {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "utf-8");
        List<CourseBean> infos = null;
        CourseBean bean = null;
        int type = parser.getEventType();
        while (type != XmlPullParser.END_DOCUMENT) {
            switch (type) {
                case XmlPullParser.START_TAG:
                    if ("infos".equals(parser.getName())) {
                        infos = new ArrayList<>();
                    } else if ("course".equals(parser.getName())) {
                        bean = new CourseBean();
                        String ids = parser.getAttributeValue(0);
                        bean.setId(Integer.parseInt(ids));
                    } else if ("imgtitle".equals(parser.getName())) {
                        String imgTitle = parser.nextText();
                        bean.setImgTitle(imgTitle);
                    } else if ("title".equals(parser.getName())) {
                        String title = parser.nextText();
                        bean.setTitle(title);
                    } else if ("intro".equals(parser.getName())) {
                        String intro = parser.nextText();
                        bean.setIntro(intro);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("course".equals(parser.getName())) {
                        infos.add(bean);
                        bean = null;
                    }
                    break;
            }
            type = parser.next();
        }

        return infos;
    }

}
