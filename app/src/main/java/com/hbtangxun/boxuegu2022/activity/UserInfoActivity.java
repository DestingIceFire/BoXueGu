package com.hbtangxun.boxuegu2022.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hbtangxun.boxuegu2022.R;
import com.hbtangxun.boxuegu2022.bean.UserBean;
import com.hbtangxun.boxuegu2022.utils.AnalysisUtils;
import com.hbtangxun.boxuegu2022.utils.DBUtils;
import com.hbtangxun.boxuegu2022.utils.ToolUtils;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {
    //标题栏
    private TextView tv_back;
    private TextView title_text;
    private RelativeLayout rl_title_bar;

    private RelativeLayout rl_nick_name, rl_sex, rl_signature;
    private TextView tv_userinfo_name, tv_nick_name, tv_userinfo_sex, tv_signature;

    private String spUserName;

    public static final int CHANGES_NICKNAME = 1;
    public static final int CHANGES_SIGNATURE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //从SP中读取用户名
        spUserName = AnalysisUtils.readLoginUserName(this);

        initView();
        initData();

    }

    private void initView() {
        tv_back = findViewById(R.id.tv_back);
        title_text = findViewById(R.id.title_text);
        rl_title_bar = findViewById(R.id.rl_title_bar);
        rl_nick_name = findViewById(R.id.rl_nick_name);
        rl_sex = findViewById(R.id.rl_sex);
        rl_signature = findViewById(R.id.rl_signature);
        tv_userinfo_name = findViewById(R.id.tv_userinfo_name);
        tv_nick_name = findViewById(R.id.tv_nick_name);
        tv_userinfo_sex = findViewById(R.id.tv_userinfo_sex);
        tv_signature = findViewById(R.id.tv_signature);
    }

    private void initData() {
        title_text.setText("个人资料");
        //标题设置背景颜色
        rl_title_bar.setBackgroundColor(Color.parseColor("#30b4ff"));

        initSQLData();

        tv_back.setOnClickListener(this);
        rl_nick_name.setOnClickListener(this);
        rl_sex.setOnClickListener(this);
        rl_signature.setOnClickListener(this);

    }

    /**
     * 操作SQLite数据库
     */
    private void initSQLData() {
        UserBean userBean = null;
        userBean = DBUtils.getInstance(this).getUserInfo(spUserName);
        //判断数据库是否有数据
        if (userBean == null) {
            userBean = new UserBean();
            userBean.setUserName(spUserName);
            userBean.setNickName("问答精灵");
            userBean.setSex("男");
            userBean.setSignature("个性签名");
            //将初始化的数据保存到数据库
            DBUtils.getInstance(this).saveUserInfo(userBean);
        }
        setValue(userBean);
    }

    /**
     * 生命周期  死亡/停止/暂停  到  运行
     * 读取数据库最新的数据，为控件赋值
     */
    @Override
    protected void onStart() {
        super.onStart();
        UserBean userBean = null;
        userBean = DBUtils.getInstance(this).getUserInfo(spUserName);
        setValue(userBean);
    }

    /**
     * 为界面控件设置值
     *
     * @param userBean
     */
    private void setValue(UserBean userBean) {
        tv_nick_name.setText(userBean.getNickName());
        tv_userinfo_sex.setText(userBean.getSex());
        tv_signature.setText(userBean.getSignature());
        tv_userinfo_name.setText(userBean.getUserName());

    }

    @Override
    public void onClick(View v) {
        if (v == tv_back) {
            finish();
        } else if (v == rl_nick_name) {
            //昵称
            ToolUtils.showShortToast(this, "修改昵称");
            String nickName = tv_nick_name.getText().toString();
            Bundle bundle = new Bundle();
            bundle.putString("content", nickName);
            bundle.putInt("flag", CHANGES_NICKNAME);
            bundle.putString("title", "昵 称");
            bundle.putString("userName", spUserName);
            enterActivityForResult(ChangesUserInfoActivity.class, CHANGES_NICKNAME, bundle);
        } else if (v == rl_sex) {
            //性别
            String sex = tv_userinfo_sex.getText().toString();
            sexDialog(sex);
        } else if (v == rl_signature) {
            //个性签名
            ToolUtils.showShortToast(this, "修改签名");
            String signature = tv_signature.getText().toString();
            Bundle bundle = new Bundle();
            bundle.putString("content", signature);
            bundle.putInt("flag", CHANGES_SIGNATURE);
            bundle.putString("title", "签 名");
            bundle.putString("userName", spUserName);
            enterActivityForResult(ChangesUserInfoActivity.class, CHANGES_SIGNATURE, bundle);
        }
    }


    private void enterActivityForResult(Class<?> to, int requestCode, Bundle data) {
        Intent intent = new Intent(this, to);
        intent.putExtras(data);
        this.startActivityForResult(intent, requestCode, data);
    }

    /**
     * 性别弹框
     *
     * @param sex
     */
    private void sexDialog(String sex) {
        int sexFlag = 0;

        if ("男".equals(sex)) {
            sexFlag = 0;
        } else if ("女".equals(sex)) {
            sexFlag = 1;
        }

        final String items[] = {"男", "女"};
        new AlertDialog.Builder(this)
                .setTitle("性别")
                .setSingleChoiceItems(items, sexFlag, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //控件显示
                        tv_userinfo_sex.setText(items[which]);
                        //数据库 保存 性别
                        DBUtils.getInstance(UserInfoActivity.this).updateUserName("sex", items[which], spUserName);
                        ToolUtils.showShortToast(UserInfoActivity.this, "性别修改成功");
                    }
                })
                .create()
                .show();
    }
}
