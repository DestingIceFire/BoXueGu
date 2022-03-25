package com.hbtangxun.boxuegu2022.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hbtangxun.boxuegu2022.R;
import com.hbtangxun.boxuegu2022.utils.DBUtils;
import com.hbtangxun.boxuegu2022.utils.ToolUtils;

public class ChangesUserInfoActivity extends AppCompatActivity implements View.OnClickListener {

    //标题栏
    private TextView tv_back;
    private TextView title_text;
    private TextView title_save;
    private RelativeLayout rl_title_bar;

    private EditText et_content;
    private ImageView iv_delete;
    private TextView tv_changes_length;

    private String content, spUserName;
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changes_user_info);

        //设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initView();
        initData();

    }

    private void initView() {
        tv_back = findViewById(R.id.tv_back);
        title_text = findViewById(R.id.title_text);
        title_save = findViewById(R.id.title_save);
        rl_title_bar = findViewById(R.id.rl_title_bar);
        et_content = findViewById(R.id.et_content);
        iv_delete = findViewById(R.id.iv_delete);
        tv_changes_length = findViewById(R.id.tv_changes_length);
    }

    private void initData() {
        title_text.setText(getIntent().getStringExtra("title"));
        rl_title_bar.setBackgroundColor(Color.parseColor("#30b4ff"));
        title_save.setVisibility(View.VISIBLE);

        content = getIntent().getStringExtra("content");
        flag = getIntent().getIntExtra("flag", 0);
        spUserName = getIntent().getStringExtra("userName");

        tv_back.setOnClickListener(this);
        title_save.setOnClickListener(this);
        iv_delete.setOnClickListener(this);

        if (!TextUtils.isEmpty(content)) {
            et_content.setText(content);
            et_content.setSelection(content.length());
            if (flag == UserInfoActivity.CHANGES_NICKNAME) {
                tv_changes_length.setText(content.length() + "/8");
            } else {
                tv_changes_length.setText(content.length() + "/16");
            }

        }

        editListener();

    }

    /**
     * 对EditText控件的监听
     * 监听个人资料修改界面输入的文字
     */
    private void editListener() {
        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 此方法为 输入文字 之前
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 此方法为 输入文字 之中
                Editable editable = et_content.getText();
                int length = editable.length();//获取 输入文本的长度
                // 当文本的长度大于0时，删除按钮显示
                // 当文本的长度小于0时，删除按钮隐藏
                if (length > 0) {
                    iv_delete.setVisibility(View.VISIBLE);
                } else {
                    iv_delete.setVisibility(View.GONE);
                }

                switch (flag) {
                    case UserInfoActivity.CHANGES_NICKNAME:
                        //昵称
                        if (length > 8) {
                            int selectionEnd = Selection.getSelectionEnd(editable);
                            String str = editable.toString();
                            //截取0-8的字符串
                            String newStr = str.substring(0, 8);
                            et_content.setText(newStr);
                            editable = et_content.getText();
                            int newLen = editable.length();
                            if (selectionEnd > newLen) {
                                selectionEnd = editable.length();
                            }
                            //设置新光标的所在的位置
                            Selection.setSelection(editable, selectionEnd);

                        }
                        break;
                    case UserInfoActivity.CHANGES_SIGNATURE:
                        //签名
                        if (length > 16) {
                            int selectionEnd = Selection.getSelectionEnd(editable);
                            String str = editable.toString();
                            //截取0-16的字符串
                            String newStr = str.substring(0, 16);
                            et_content.setText(newStr);
                            editable = et_content.getText();
                            int newLen = editable.length();
                            if (selectionEnd > newLen) {
                                selectionEnd = editable.length();
                            }
                            //设置新光标的所在的位置
                            Selection.setSelection(editable, selectionEnd);
                        }
                        break;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // 此方法为 输入文字 之后
                //提示用户 已经输入的文字长度 以及 最大的长度
                int selectionEnd = Selection.getSelectionEnd(s);
                if (flag == UserInfoActivity.CHANGES_NICKNAME) {
                    if (selectionEnd > 8) {
                        int eight = 8;
                        tv_changes_length.setText(eight + "/8");
                        ToolUtils.showShortToast(ChangesUserInfoActivity.this, "您输入的文字达到最大限度");
                    } else {
                        tv_changes_length.setText(selectionEnd + "/8");
                    }
                } else {
                    if (selectionEnd > 16) {
                        int sixteen = 16;
                        tv_changes_length.setText(sixteen + "/16");
                    } else {
                        tv_changes_length.setText(selectionEnd + "/16");
                    }
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == tv_back) {
            finish();
        } else if (v == title_save) {
            saveData();
        } else if (v == iv_delete) {
            //删除按钮
            et_content.setText("");
        }
    }

    /**
     * 保存数据
     */
    private void saveData() {
        Intent data = new Intent();
        //获取输入的数据
        String content = et_content.getText().toString().trim();
        switch (flag) {
            case UserInfoActivity.CHANGES_NICKNAME:
                //保存昵称
                if (!TextUtils.isEmpty(content)) {
                    data.putExtra("nickName", content);
                    setResult(RESULT_OK, data);
                    ToolUtils.showShortToast(this, "保存成功");
                    DBUtils.getInstance(this).updateUserName("nickName", content, spUserName);
                    finish();
                } else {
                    ToolUtils.showShortToast(this, "昵称不能为空");
                }
                break;
            case UserInfoActivity.CHANGES_SIGNATURE:
                //保存签名
                if (!TextUtils.isEmpty(content)) {
                    data.putExtra("signature", content);
                    setResult(RESULT_OK, data);
                    ToolUtils.showShortToast(this, "保存成功");
                    DBUtils.getInstance(this).updateUserName("signature", content, spUserName);
                    finish();
                } else {
                    ToolUtils.showShortToast(this, "签名不能为空");
                }
                break;

        }
    }
}
