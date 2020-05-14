package com.hbtangxun.boxuegu.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Xml;
import android.widget.ImageView;

import com.hbtangxun.boxuegu.bean.ExercisesBean;

import org.xmlpull.v1.XmlPullParser;

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
        List<ExercisesBean> exercisesInfos = null;
        ExercisesBean exercisesInfo = null;
        int type = parser.getEventType();
        while (type != XmlPullParser.END_DOCUMENT) {
            switch (type) {
                case XmlPullParser.START_TAG:
                    if ("infos".equals(parser.getName())) {
                        exercisesInfos = new ArrayList<>();
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
                        exercisesInfos.add(exercisesInfo);
                        exercisesInfo = null;
                    }
                    break;
            }
            type = parser.next();
        }
        return exercisesInfos;
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

}
