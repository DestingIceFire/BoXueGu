package com.hbtangxun.boxuegu.activity;

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

import com.hbtangxun.boxuegu.R;
import com.hbtangxun.boxuegu.utils.ToolUtils;

/**
 * 修改 个人资料
 */
public class ChangeUserInfoActivity extends AppCompatActivity implements View.OnClickListener {

    //标题栏的返回键
    private ImageView iv_back_title;
    //标题栏的文本
    private TextView title_text, tv_save;
    private RelativeLayout rl_title_bar;

    private EditText et_content;
    private ImageView iv_delete;

    private String title, content;
    private int flag; //flag ==1 昵称  ==2 签名

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_info);
        //竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initView();
        initData();
    }

    private void initView() {
        iv_back_title = findViewById(R.id.iv_back_title);
        title_text = findViewById(R.id.title_text);
        tv_save = findViewById(R.id.tv_save);
        rl_title_bar = findViewById(R.id.rl_title_bar);
        et_content = findViewById(R.id.et_content);
        iv_delete = findViewById(R.id.iv_delete);
    }

    private void initData() {
        //修改标题栏
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        flag = getIntent().getIntExtra("flag", 0);
        title_text.setText(title);

        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_save.setVisibility(View.VISIBLE);

        //监听
        iv_back_title.setOnClickListener(this);
        tv_save.setOnClickListener(this);
        iv_delete.setOnClickListener(this);

        if (!TextUtils.isEmpty(content)) {
            et_content.setText(content);
            et_content.setSelection(content.length());
        }

        contentListener();

    }

    @Override
    public void onClick(View v) {
        if (v == iv_back_title) {
            finish();
        } else if (v == iv_delete) {
            et_content.setText("");
        } else if (v == tv_save) {
            saveListener();
        }
    }

    //保存数据
    private void saveListener() {
        Intent data = new Intent();
        String etContent = et_content.getText().toString().trim();
        switch (flag) {
            case UserInfoActivity.CHANGE_NICKNAME: //昵称
                if (!TextUtils.isEmpty(etContent)) {
                    data.putExtra("nickName", etContent);
                    setResult(RESULT_OK, data);
                    ToolUtils.showToastShort(this, "保存成功");
                    finish();
                } else {
                    ToolUtils.showToastShort(this, "昵称不能为空");
                }

                break;
            case UserInfoActivity.CHANGE_SIGNATURE: //签名
                if (!TextUtils.isEmpty(etContent)) {
                    data.putExtra("signature", etContent);
                    setResult(RESULT_OK, data);
                    ToolUtils.showToastShort(this, "保存成功");
                    finish();
                } else {
                    ToolUtils.showToastShort(this, "签名不能为空");
                }
                break;
        }
    }

    //监听个人资料修改界面输入的文字
    private void contentListener() {

        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Editable editable = et_content.getText();
                int length = editable.length(); // 输入文本的长度
                if (length > 0) {
                    iv_delete.setVisibility(View.VISIBLE);
                } else {
                    iv_delete.setVisibility(View.GONE);
                }

                switch (flag) {
                    case UserInfoActivity.CHANGE_NICKNAME: //昵称
                        //昵称限制最多8个文字，超过8个文字截取掉多余的文字
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
                            //设置新光标所在的位置
                            Selection.setSelection(editable, selectionEnd);
                        }
                        break;
                    case UserInfoActivity.CHANGE_SIGNATURE: //签名
                        //签名限制最多16个文字，超过16个文字截取掉多余的文字
                        if (length > 16) {
                            int selectionEnd = Selection.getSelectionEnd(editable);
                            String str = editable.toString();
                            //截取0-8的字符串
                            String newStr = str.substring(0, 16);
                            et_content.setText(newStr);
                            editable = et_content.getText();
                            int newLen = editable.length();
                            if (selectionEnd > newLen) {
                                selectionEnd = editable.length();
                            }
                            //设置新光标所在的位置
                            Selection.setSelection(editable, selectionEnd);
                        }
                        break;
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

}
