package com.hbtangxun.boxuegu.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hbtangxun.boxuegu.R;
import com.hbtangxun.boxuegu.Utils.AnalysisUtils;
import com.hbtangxun.boxuegu.Utils.DBUtils;
import com.hbtangxun.boxuegu.Utils.ToolUtils;
import com.hbtangxun.boxuegu.bean.UserBean;

/**
 * 个人资料
 */

public class UserInfoActivity extends Activity implements View.OnClickListener {
    //标题栏的返回键
    private ImageView iv_back_title;
    //标题栏的文本
    private TextView title_text;
    private RelativeLayout rl_title_bar;

    private RelativeLayout rl_head, rl_account, rl_nick_name, rl_sex, rl_signature;
    private TextView tv_user_name, tv_nick_name, tv_sex, tv_signature;
    private ImageView iv_head_icon;

    private String spUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //从SP中获取用户名
        spUserName = AnalysisUtils.readLoginUserName(this);

        initView();
        initData();
    }

    private void initView() {
        iv_back_title = findViewById(R.id.iv_back_title);
        title_text = findViewById(R.id.title_text);
        rl_title_bar = findViewById(R.id.rl_title_bar);

        rl_head = findViewById(R.id.rl_head);
        rl_account = findViewById(R.id.rl_account);
        rl_nick_name = findViewById(R.id.rl_nick_name);
        rl_sex = findViewById(R.id.rl_sex);
        rl_signature = findViewById(R.id.rl_signature);

        iv_head_icon = findViewById(R.id.iv_head_icon);
        tv_user_name = findViewById(R.id.tv_user_name);
        tv_nick_name = findViewById(R.id.tv_nick_name);
        tv_sex = findViewById(R.id.tv_sex);
        tv_signature = findViewById(R.id.tv_signature);

    }

    private void initData() {
        title_text.setText("个人资料");
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));

        initSQLData();

        iv_back_title.setOnClickListener(this);
        rl_nick_name.setOnClickListener(this);
        rl_sex.setOnClickListener(this);
        rl_signature.setOnClickListener(this);
    }

    private void initSQLData() {
        UserBean userBean = null;
        userBean = DBUtils.getInstance(this).getUserInfo(spUserName);
        //判断userBean是否有数据
        if (userBean == null) {
            userBean = new UserBean();
            userBean.setUserName(spUserName);
            userBean.setNickName("问答精灵");
            userBean.setSex("男");
            userBean.setSignature("这是一条个性签名");
            //将用户数据保存到数据库
            DBUtils.getInstance(this).saveUserInfo(userBean);
        }
        setValue(userBean);

    }

    /**
     * 为界面控件设置值
     *
     * @param bean
     */
    private void setValue(UserBean bean) {
        tv_user_name.setText(bean.getUserName());
        tv_nick_name.setText(bean.getNickName());
        tv_sex.setText(bean.getSex());
        tv_signature.setText(bean.getSignature());
    }

    @Override
    public void onClick(View v) {
        if (v == iv_back_title) {
            finish();
        } else if (v == rl_nick_name) {
            //昵称
            ToolUtils.showToastShort(UserInfoActivity.this, "修改昵称");
        } else if (v == rl_sex) {
            //性别
            String sex = tv_sex.getText().toString();
            sexDialog(sex);
        } else if (v == rl_signature) {
            //签名
            ToolUtils.showToastShort(UserInfoActivity.this, "修改签名");
        }
    }

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
                        ToolUtils.showToastShort(UserInfoActivity.this, items[which]);
                        tv_sex.setText(items[which]);
                        DBUtils.getInstance(UserInfoActivity.this).updateUserInfo("sex", items[which], spUserName);
                    }
                })
                .create()
                .show();

    }
}
