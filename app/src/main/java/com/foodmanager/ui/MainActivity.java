package com.foodmanager.ui;

import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.foodmanager.R;
import com.foodmanager.ui.activity.AnswerActivity;
import com.foodmanager.ui.activity.CreateActivity;
import com.foodmanager.utils.T;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

public class MainActivity extends BaseActivity {
    private QMUITopBar mTopbar;
    private QMUIRoundButton mMainCreat;
    private QMUIRoundButton mMainAnswer;
    public long l = System.currentTimeMillis();

    /**
     * 初始化布局
     */
    @Override
    protected void initLayout() {

        mMainCreat = (QMUIRoundButton) findViewById(R.id.main_creat);
        mMainAnswer = (QMUIRoundButton) findViewById(R.id.main_answer);
        mTopbar = (QMUITopBar) findViewById(R.id.topbar);


        Button button = mTopbar.addLeftTextButton("退出登录", R.id.layout_for_test1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra("Check",false);
                startActivity(intent);
                finish();
            }
        });

        Button button1 = mTopbar.addRightTextButton("问卷查询", R.id.layout_for_test2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra("Check",false);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * 获取子aitivity的布局ID
     *
     * @return
     */
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    /**
     * 子类activity的初始化方法
     */
    @Override
    protected void init() {
        //初始化数据
        iniData();
    }

    @Override
    protected int getContextViewId() {

        return R.id.qmuidemo;
    }
    //UI界面资源
    private void iniData() {
        //创建问卷
        mMainCreat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mMainAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    //再次点击退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long l1 = System.currentTimeMillis();
        if (17 == KeyEvent.KEYCODE_STAR) {
            if (l1 - l > 2000) {
               T.showShort("再点一次退出");
                l = System.currentTimeMillis();
                return false;
            } else {
                MainActivity.this.finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
