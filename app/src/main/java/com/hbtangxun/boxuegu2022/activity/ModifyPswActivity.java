package com.hbtangxun.boxuegu2022.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hbtangxun.boxuegu2022.R;
import com.hbtangxun.boxuegu2022.utils.AnalysisUtils;
import com.hbtangxun.boxuegu2022.utils.MD5Utils;
import com.hbtangxun.boxuegu2022.utils.ToolUtils;

/**
 * 修改密码 界面
 */
public class ModifyPswActivity extends AppCompatActivity implements View.OnClickListener {

    //标题栏
    private TextView tv_back;
    private TextView title_text;

    //修改密码
    private EditText et_original_psw;
    private EditText et_new_psw;
    private EditText et_new_psw_again;
    private Button btn_save;

    private String oldPsw, newPsw, newPswAgain;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_psw);
        //设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        userName = AnalysisUtils.readLoginUserName(this);

        initView();
        initData();

    }

    private void initView() {
        tv_back = findViewById(R.id.tv_back);
        title_text = findViewById(R.id.title_text);
        et_original_psw = findViewById(R.id.et_original_psw);
        et_new_psw = findViewById(R.id.et_new_psw);
        et_new_psw_again = findViewById(R.id.et_new_psw_again);
        btn_save = findViewById(R.id.btn_save);


    }

    private void initData() {
        title_text.setText("修改密码");
        tv_back.setOnClickListener(this);
        btn_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == tv_back) {
            finish();
        } else if (v == btn_save) {
            modifyPsw();
        }
    }

    /**
     * 修改密码判断情况
     * 1、需要判断 三个输入框是否全部填写
     * 2、输入原始密码是否与存储的密码的一致
     * 3、修改的新密码是否与再次输入的新密码一致
     */
    private void modifyPsw() {
        getEditString();
        //1、需要判断 三个输入框是否全部填写
        if (TextUtils.isEmpty(oldPsw)) {
            ToolUtils.showShortToast(this, "请输入原始密码");
        } else if (TextUtils.isEmpty(newPsw)) {
            ToolUtils.showShortToast(this, "请输入新密码");
        } else if (TextUtils.isEmpty(newPswAgain)) {
            ToolUtils.showShortToast(this, "请再次输入新密码");
        } else if (!MD5Utils.md5(oldPsw).equals(readSPPsw())) {
            //2、输入原始密码是否与存储的密码的一致
            ToolUtils.showShortToast(this, "输入的原始密码与原始密码不一致");
        } else if (!newPsw.equals(newPswAgain)) {
            //3、修改的新密码是否与再次输入的新密码一致
            ToolUtils.showShortToast(this, "两次输入的新密码要一致");
        } else {
            ToolUtils.showShortToast(this, "新密码设置成功");
            //4、需要将新密码保存到对应的SP中
            saveNewPsw(newPsw);
            AnalysisUtils.cleanLoginStatus(this);

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

            ModifyPswActivity.this.finish();
            SettingActivity.instance.finish();
        }
    }

    private void getEditString() {
        oldPsw = et_original_psw.getText().toString();
        newPsw = et_new_psw.getText().toString();
        newPswAgain = et_new_psw_again.getText().toString();
    }

    /**
     * 读取SP中的密码
     *
     * @return
     */
    private String readSPPsw() {
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        return sp.getString(userName, "");
    }

    /**
     * 修改成功后，将密码保存对应的SP中
     */
    private void saveNewPsw(String psw) {
        String md5Psw = MD5Utils.md5(psw);
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(userName, md5Psw);
        edit.commit();
    }

}
