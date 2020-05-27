package com.hbtangxun.boxuegu.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Xml;
import android.widget.ImageView;

import com.hbtangxun.boxuegu.bean.CourseBean;
import com.hbtangxun.boxuegu.bean.ExercisesBean;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 课本工具类
 */
public class AnalysisUtils {
    /**
     * 从SP中读取用户名
     *
     * @param context
     * @return
     */
    public static String readLoginUserName(Context context) {
        SharedPreferences sp = context.getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        String loginUserName = sp.getString("loginUserName", "");
        return loginUserName;
    }

    /**
     * 解析每章的习题
     *
     * @param is
     * @return
     * @throws Exception
     */

    public static List<ExercisesBean> getExercisesInfos(InputStream is) throws Exception {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "utf-8");
        List<ExercisesBean> infos = null;
        ExercisesBean exercisesInfo = null;
        int type = parser.getEventType();
        while (type != XmlPullParser.END_DOCUMENT) {
            switch (type) {
                case XmlPullParser.START_TAG:
                    if ("infos".equals(parser.getName())) {
                        infos = new ArrayList<>();
                    } else if ("exercises".equals(parser.getName())) {
                        exercisesInfo = new ExercisesBean();
                        String ids = parser.getAttributeValue(0);
                        exercisesInfo.setSubjectId(Integer.parseInt(ids));
                    } else if ("subject".equals(parser.getName())) {
                        String subject = parser.nextText();
                        exercisesInfo.setSubject(subject);
                    } else if ("a".equals(parser.getName())) {
                        String a = parser.nextText();
                        exercisesInfo.setA(a);
                    } else if ("b".equals(parser.getName())) {
                        String b = parser.nextText();
                        exercisesInfo.setB(b);
                    } else if ("c".equals(parser.getName())) {
                        String c = parser.nextText();
                        exercisesInfo.setC(c);
                    } else if ("d".equals(parser.getName())) {
                        String d = parser.nextText();
                        exercisesInfo.setD(d);
                    } else if ("answer".equals(parser.getName())) {
                        String answer = parser.nextText();
                        exercisesInfo.setAnswer(Integer.parseInt(answer));
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("exercises".equals(parser.getName())) {
                        infos.add(exercisesInfo);
                        exercisesInfo = null;
                    }
                    break;
            }
            type = parser.next();
        }
        return infos;
    }

    /**
     * 设置 A、B、C、D 选项是否可点击
     *
     * @param value
     * @param iv_ex_a
     * @param iv_ex_b
     * @param iv_ex_c
     * @param iv_ex_d
     */
    public static void setABCDEnable(boolean value, ImageView iv_ex_a, ImageView iv_ex_b, ImageView iv_ex_c, ImageView iv_ex_d) {
        iv_ex_a.setEnabled(value);
        iv_ex_b.setEnabled(value);
        iv_ex_c.setEnabled(value);
        iv_ex_d.setEnabled(value);
    }

    public static List<CourseBean> getCourseInfos(InputStream is) throws Exception {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "utf-8");
        List<CourseBean> list = null;
        CourseBean bean = null;
        int type = parser.getEventType();
        while (type != XmlPullParser.END_DOCUMENT) {
            switch (type) {
                case XmlPullParser.START_TAG:
                    if ("infos".equals(parser.getName())) {
                        list = new ArrayList<>();
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
                        list.add(bean);
                        bean = null;
                    }
                    break;
            }
            type = parser.next();
        }
        return list;
    }
}
