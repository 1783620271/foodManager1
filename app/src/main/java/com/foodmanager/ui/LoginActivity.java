package com.foodmanager.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.foodmanager.R;
import com.foodmanager.bean.User;
import com.foodmanager.utils.B;
import com.foodmanager.utils.DateUtils;
import com.foodmanager.utils.L;
import com.foodmanager.utils.RegexUtils;
import com.foodmanager.utils.SpUtils;
import com.foodmanager.utils.StaticClass;
import com.foodmanager.utils.T;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.UpdateListener;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 用户登录页面
 */
public class LoginActivity extends BaseActivity {
    private EditText mEtPhone;
    private EditText mEtPwd;
    private CheckBox mCbRemember;
    private CheckBox mCbAutoLogin;
    private Button mBtnRegister;
    private Button mBtnLogin;
    private AlertDialog alertDialog;
    private Disposable disposable;
    private QMUITopBar mTopbar;


    /**
     * 初始化布局
     */
    @Override
    protected void initLayout() {
        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mEtPwd = (EditText) findViewById(R.id.et_pwd);
        mCbRemember = (CheckBox) findViewById(R.id.cb_remember);
        mCbAutoLogin = (CheckBox) findViewById(R.id.cb_auto_login);
        mBtnRegister = (Button) findViewById(R.id.btn_register);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mTopbar = findViewById(R.id.topbar);
        mTopbar.setTitle("登录");
    }

    /**
     * 获取子aitivity的布局ID
     *
     * @return
     */
    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    /**
     * 子类activity的初始化方法
     */
    @Override
    protected void init() {
        initData();
        initEvent();
    }


    /**
     * 初始化事件
     */
    private void initEvent() {
        //用户登录
        mBtnLogin.setOnClickListener(v -> {
            login();
        });

        //注册
        mBtnRegister.setOnClickListener(v -> {
            showRegisterDialog();
        });

        mCbAutoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    mCbRemember.setChecked(true);
                String phoneStr = mEtPhone.getText().toString().trim();
                String pwd = mEtPwd.getText().toString();
                    savePassword(phoneStr, pwd);
            }
        });


        //记住密码
        mCbRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    String phoneStr = mEtPhone.getText().toString().trim();
                    String pwd = mEtPwd.getText().toString();
                    savePassword(phoneStr, pwd);
                }else {
                    mCbAutoLogin.setChecked(false);
                }

            }
        });


    }

    /**
     * 用户登录
     */
    private void login() {
        String phoneStr = mEtPhone.getText().toString().trim();
        //验证手机号码
        if (!RegexUtils.checkPhone(phoneStr)) {
            T.showShort("手机号码有误");
            return;
        }

        String pwd = mEtPwd.getText().toString();
        if (TextUtils.isEmpty(pwd)) {
            T.showShort("密码不能为空");
            return;
        }


        //进行登录
        BmobUser.loginByAccount(phoneStr, pwd, new LogInListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    SpUtils.put("ObjectId",user.getObjectId());
                    user.getObjectId();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    //登录失败
                    T.showShort("登录失败,请重试");
                    T.showShort(e.getErrorCode() + "------" + e.getMessage());
                    L.e("--------------登录失败错误信息:" + e.getErrorCode() + "----" + e.getMessage());
                }
            }

        });
    }


    /**
     * 保存密码的业务逻辑
     *
     * @param phoneStr 用户名
     * @param pwd      密码
     */
    private void savePassword(String phoneStr, String pwd) {
        boolean isAutoLogin = mCbAutoLogin.isChecked();
        boolean isRememberPassword = mCbRemember.isChecked();
        SpUtils.put(StaticClass.isAutoLogin, isAutoLogin);
        SpUtils.put(StaticClass.isRememberPassword, isRememberPassword);
        if (isAutoLogin || isRememberPassword) {
            SpUtils.put(StaticClass.USERNAME, phoneStr);
            SpUtils.put(StaticClass.PASSWORD, pwd);
        } else {
            SpUtils.put(StaticClass.USERNAME, "");
            SpUtils.put(StaticClass.PASSWORD, "");
        }
    }


    /**
     * 显示注册的对话框
     */
    private void showRegisterDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View.inflate(this, R.layout.layout_register_dialog, null);
        builder.setView(view);
        EditText mEtPhoneNumber;
        EditText mEtVerificationCode;
        Button mBtnVerificationCode;
        EditText mEtPassword;
        Button mBtnDialogCancel;
        Button mBtnDialogRegister;
        mEtPhoneNumber = (EditText) view.findViewById(R.id.et_phone_number);
        mEtVerificationCode = (EditText) view.findViewById(R.id.et_verification_code);
        mBtnVerificationCode = (Button) view.findViewById(R.id.btn_verification_code);
        mEtPassword = (EditText) view.findViewById(R.id.et_password);
        mBtnDialogCancel = (Button) view.findViewById(R.id.btn_dialog_cancel);
        mBtnDialogRegister = (Button) view.findViewById(R.id.btn_dialog_register);

        //发送验证码
        mBtnVerificationCode.setOnClickListener(v -> sendSmsCode(mEtPhoneNumber.getText().toString(), mBtnVerificationCode));
        mBtnDialogCancel.setOnClickListener(v -> alertDialog.dismiss());
        //注册
        mBtnDialogRegister.setOnClickListener(v -> register(
                mEtVerificationCode.getText().toString().trim(), //验证码
                mEtPhoneNumber.getText().toString(),//手机号
                mEtPassword.getText().toString())//密码
        );
        alertDialog = builder.create();
        alertDialog.show();

        Long sendTime = (Long) SpUtils.get(StaticClass.DATE_DIFFERENCE, 0L);
        //发送验证码按钮状态回显
        Date date = new Date();
        Long dateDifference = DateUtils.getDateDifference(date, new Date(sendTime));
        if (dateDifference > 0 && dateDifference < 60) {
            sendCodeStatus(mBtnVerificationCode, 60 - Integer.valueOf(dateDifference.toString()));
        }
    }

    /**
     * 发送验证码的业务
     *
     * @param phone 手机号码
     */
    private void sendSmsCode(String phone, Button mBtnVerificationCode) {
        L.e("----phone-----------" + phone);
        //验证手机号码
        if (!RegexUtils.checkMobile(phone)) {
            T.showShort("手机号码有误");
            return;
        }

        B.sendSMSCode(phone, new B.OnStatusListener() {
            @Override
            public void onStatus(boolean isChangeSuccess) {
                if (isChangeSuccess) {
                    T.showShort("发送验证码成功");
                    //按钮倒计时
                    sendCodeStatus(mBtnVerificationCode, 60);
                    //记录发送验证码的时间
                    SpUtils.put(StaticClass.DATE_DIFFERENCE, new Date().getTime());
                } else {
                    //更详细的以后再说
                    T.showShort("发送验证码失败请稍后重试");

                }
            }
        });

        //请求发送验证码
//        BmobSMS.requestSMSCode(phone, null, new QueryListener<Integer>() {
//            @Override
//            public void done(Integer smsId, BmobException e) {
//                if (e == null) {
//                    T.showShort("发送验证码成功，短信ID：" + smsId + "\n");
//                    //按钮倒计时
//                    sendCodeStatus(mBtnVerificationCode, 60);
//                    //记录发送验证码的时间
//                    SpUtils.put(StaticClass.DATE_DIFFERENCE, new Date().getTime());
//                } else {
//                    //更详细的以后再说
//                    // T.showShort("发送验证码失败请稍后重试");
//                    T.showShort(e.getErrorCode() + "--" + e.getMessage());
//                }
//            }
//        });

    }

    /**
     * 发送短信按钮倒计时
     *
     * @param mBtnVerificationCode
     * @param seconds
     */
    private void sendCodeStatus(Button mBtnVerificationCode, int seconds) {
        mBtnVerificationCode.setEnabled(false);
        disposable = Flowable.interval(0, 1, TimeUnit.SECONDS)
                .take(seconds)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        mBtnVerificationCode.setText((seconds - aLong) + "秒后获取");
                    }
                }).doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        mBtnVerificationCode.setText("获取验证码");
                        mBtnVerificationCode.setEnabled(true);
                    }
                }).subscribe();
    }


    /**
     * 注册的业务逻辑
     *
     * @param code  验证码
     * @param phone 手机号
     */
    private void register(String code, String phone, String pwd) {
        if (checkParams(code, phone, pwd)) return;
        //进行手机号注册
        BmobUser.signOrLoginByMobilePhone(phone, code, new LogInListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                //验证完成后,关闭弹窗
                alertDialog.dismiss();
                if (e == null) {
                    saveUser(phone, pwd);
                } else {
                    T.showShort("验证码验证失败：" + e.getErrorCode());
                }
            }
        });
    }

    /**
     * 参数判断,是否符合要求
     *
     * @param phone
     * @param pwd
     * @return
     */
    private boolean checkParams(String code, String phone, String pwd) {
        if (TextUtils.isEmpty(code)) {
            T.showShort("验证码不能为空");
            return true;
        }
        //验证手机号码
        if (!RegexUtils.checkMobile(phone)) {
            T.showShort("手机号码有误");
            return true;
        }

        if (TextUtils.isEmpty(pwd) || pwd.length() < 6) {
            T.showShort("密码长度最少为6位");
            return true;
        }
        return false;
    }

    /**
     * 保存用户
     *
     * @param phone
     * @param pwd
     */
    private void saveUser(String phone, String pwd) {
        User currentUser = BmobUser.getCurrentUser(User.class);
        currentUser.setMobilePhoneNumber(phone);
        currentUser.setMobilePhoneNumberVerified(true);
        currentUser.setPassword(pwd);
        currentUser.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    T.showShort("注册成功,请使用手机号+密码登录");
                } else {
                    regexError(e);
                }
            }
        });
    }


    /**
     * 错误码识别
     *
     * @param e
     */
    private void regexError(BmobException e) {
        L.e(e.getErrorCode() + "------------------" + e.getMessage());
        switch (e.getErrorCode()) {
            case 202:
                T.showShort("用户名已经存在");
                break;
            case 207:
                T.showShort("验证码错误");
                break;
            case 209:
                T.showShort("该手机号码已经存在");
                break;
            default:
                T.showShort("验证失败，请稍后重试");
                break;
        }
    }


    /**
     * 初始化数据
     */
    private void initData() {
        //用户名和密码回显
        Boolean isRememberPassword = (Boolean) SpUtils.get(StaticClass.isRememberPassword, false);

        Boolean isAutoLogin = (Boolean) SpUtils.get(StaticClass.isAutoLogin, false);
        if (isAutoLogin){
            if (!getIntent().getBooleanExtra("Check",true )){
                isAutoLogin=false;
            }
        }

        mCbRemember.setChecked(isRememberPassword);
        mCbAutoLogin.setChecked(isAutoLogin);
        if (isRememberPassword) {
            mEtPwd.setText((String) SpUtils.get(StaticClass.PASSWORD, ""));
            mEtPhone.setText((String) SpUtils.get(StaticClass.USERNAME, ""));
        }

        if (isAutoLogin) {
            login();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅
        if (disposable != null) {
            if (disposable.isDisposed()) {
                disposable.dispose();
            }
        }
    }

    @Override
    protected int getContextViewId() {
        return 0;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long l1 = System.currentTimeMillis();
        if (17 == KeyEvent.KEYCODE_STAR) {
            LoginActivity.this.finish();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
