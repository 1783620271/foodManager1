package com.foodmanager.utils;


import com.foodmanager.bean.User;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FetchUserInfoListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * bmob二次封装
 */
public class B {

    /**
     * 用户有关的操作的监听
     */
    interface OnUserSuccessListener {
        //成功的监听
        void onUserSuccess(BmobUser bmobUser);

        //失败的监听
        void onUserFaild();
    }

    /**
     * 获取当前登录的用户
     *
     * @return 当前登陆的用户对象
     */
    public static User getUser() {
        return BmobUser.getCurrentUser(User.class);
    }

    /**
     * 根据用户名和密码进行注册
     *
     * @param username 用户名
     * @param password 密码
     * @param listener 注册成功的监听
     */
    public static void registerWithUsernameAndPassword(String username, String password, OnUserSuccessListener listener) {
        final User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                userResultDispose(user, e, listener);
            }
        });
    }

    /**
     * 根据账号密码进行登录
     *
     * @param username 用户名
     * @param password 密码
     * @param listener 登录成功的监听
     */
    public static void loginWithUsernmeAndPassword(String username, String password, OnUserSuccessListener listener) {
        final User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.login(new SaveListener<User>() {
            @Override
            public void done(User bmobUser, BmobException e) {
                userResultDispose(user, e, listener);
            }
        });
    }

    /**
     * 账号密码登录
     *
     * @param username 用户名
     * @param password 密码
     * @param listener 登录成功的监听
     */
    private void loginByAccount(String username, String password, OnUserSuccessListener listener) {
        //此处替换为你的用户名密码
        BmobUser.loginByAccount("username", "password", new LogInListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                userResultDispose(user, e, listener);
            }
        });
    }


    /**
     * 判断当前用户是否已经登录
     *
     * @return true 已经登录,false没有登录
     */
    public static Boolean isLogin() {
        return BmobUser.isLogin();
    }


    /**
     * 同步控制台数据到缓存中
     *
     * @param listener 同步成功的回调
     */
    public static void fetchUserInfo(OnUserSuccessListener listener) {
        BmobUser.fetchUserInfo(new FetchUserInfoListener<BmobUser>() {
            @Override
            public void done(BmobUser user, BmobException e) {
                userResultDispose(user, e, listener);
            }
        });
    }

    /**
     * 用户有关的结果处理
     *
     * @param user     返回的结果,如果需要使用继承BmobUser子类的属性,需要在子类中强转
     * @param e
     * @param listener
     */
    private static void userResultDispose(BmobUser user, BmobException e, OnUserSuccessListener listener) {
        if (e == null) {
            if (listener != null) listener.onUserSuccess(user);
        } else {
            ErrorRec.errorRec(e.getErrorCode());
            if (listener != null) listener.onUserFaild();
        }
    }

    /**
     * 更新用户操作并同步更新本地的用户信息
     *
     * @param user     当前登录用户
     * @param listener 操作结果的监听
     */
    private void updateUser(User user, OnStatusListener listener) {
        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                onResultStatus(e, listener);
            }
        });
    }

    /**
     * 查询所有用户的监听
     */
    interface QueryAllUserListener {

        void onQueryAllUserListener(List<User> users);

        void onFaild();
    }


    /**
     * 查询所有用户
     *
     * @param listener
     */
    public static void queryAllUser(QueryAllUserListener listener) {
        BmobQuery<User> bmobQuery = new BmobQuery<>();
        bmobQuery.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> users, BmobException e) {
                if (e == null) {
                    if (listener != null) listener.onQueryAllUserListener(users);
                } else {
                    ErrorRec.errorRec(e.getErrorCode());
                    if (listener != null) listener.onFaild();
                }
            }
        });
    }

    /**
     * 结果状态监听
     */
  public   interface OnStatusListener {
        //true 为成功,false为失败
        void onStatus(boolean isChangeSuccess);
    }

    /**
     * 通过旧密码修改密码
     *
     * @param oldPwd 老密码
     * @param newPwd 新密码
     */
    private void updatePasswordByOldPassword(String oldPwd, String newPwd, OnStatusListener listeer) {
        BmobUser.updateCurrentUserPassword(oldPwd, newPwd, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                onResultStatus(e, listeer);
            }
        });
    }

    /**
     * 退出登录
     */
    public static void loginOut() {
        BmobUser.logOut();
    }

    /**
     * 邮箱密码登录
     * 邮箱已经激活才可以登录
     *
     * @param email    邮箱
     * @param password 密码
     * @param listener 登录成功的监听
     */
    private void loginWithEmailAndPassword(String email, String password, OnUserSuccessListener listener) {
        BmobUser.loginByAccount(email, password, new LogInListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                userResultDispose(user, e, listener);
            }
        });
    }

    /**
     * 发送验证邮件
     */
    public static void sendEmail(String email, OnStatusListener listener) {
        BmobUser.requestEmailVerify(email, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                onResultStatus(e, listener);
            }
        });
    }

    /**
     * 通过邮箱修改密码
     */
    public static void changePasswordByEmail(String email, OnStatusListener listener) {
        BmobUser.resetPasswordByEmail(email, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                onResultStatus(e, listener);
            }
        });
    }

    /**
     * 进行操作是否成功的返回
     *
     * @param e
     * @param listener
     */
    public static void onResultStatus(BmobException e, OnStatusListener listener) {
        if (e == null) {
            if (listener != null) listener.onStatus(true);
        } else {
            ErrorRec.errorRec(e.getErrorCode());
            if (listener != null) listener.onStatus(false);
        }
    }


    /**
     * 手机号码和密码进行登录
     *
     * @param phone    手机号码
     * @param password 密码
     * @param listener 登录结果的监听
     */
    public static void loginWihtPhoneAndPassword(String phone, String password, OnUserSuccessListener listener) {
        BmobUser.loginByAccount(phone, password, new LogInListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                userResultDispose(user, e, listener);
            }
        });
    }


    /**
     * 发送短信验证码成功
     *
     * @param phone    手机号码
     * @param listener
     */
    public static void sendSMSCode(String phone, B.OnStatusListener listener) {
        BmobSMS.requestSMSCode(phone, null/*如果没有自定义模版可以传null*/, new QueryListener<Integer>() {
            @Override
            public void done(Integer smsId, BmobException e) {
                if (e == null) T.showShort("发送验证码成功，短信ID：" + smsId);
                onResultStatus(e, listener);
            }
        });
    }

    /**
     * 通过手机号码和验证码进行登录
     *
     * @param phone
     * @param code
     * @param listener
     */
    public static void loginWithPhoneAndSmscode(String phone, String code, OnUserSuccessListener listener) {
        BmobUser.loginBySMSCode(phone, code, new LogInListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                userResultDispose(bmobUser, e, listener);
            }
        });
    }


    /**
     * 通过手机号码和验证码进行一键注册或登录
     *
     * @param phone
     * @param code
     * @param listener
     */
    public static void registerAndLoginWithPhoneAndSmscode(String phone, String code, OnUserSuccessListener listener) {
        BmobUser.signOrLoginByMobilePhone(phone, code, new LogInListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                userResultDispose(bmobUser, e, listener);
            }
        });
    }


    /**
     * 一键注册或登录的同时保存其他字段的数据
     *
     * @param user
     * @param phone 手机号码必填
     * @param code
     */
    private void signOrLogin(User user, String phone, String code, OnUserSuccessListener listener) {
        user.signOrLogin(code, new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                userResultDispose(user, e, listener);
            }
        });
    }


    /**
     * 根据验证码绑定解绑手机号
     *
     * @param phone
     * @param code
     * @param b     true 绑定,false 解绑
     */
    public static void updatePhoneStatus(String phone, String code, Boolean b, OnStatusListener listener) {
        BmobSMS.verifySmsCode(phone, code, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    User user = BmobUser.getCurrentUser(User.class);
                    user.setMobilePhoneNumber(phone);
                    user.setMobilePhoneNumberVerified(b);
                    user.update(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            onResultStatus(e, listener);
                        }
                    });
                } else {
                    onResultStatus(e, listener);
                }
            }
        });
    }


    /**
     * 根据手机验证码重置密码
     *
     * @param code        验证码
     * @param newPassword 新密码
     * @param listener    结果监听
     */
    public static void changePasswordWithCode(String code, String newPassword, OnStatusListener listener) {
        BmobUser.resetPasswordBySMSCode(code, newPassword, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                onResultStatus(e, listener);
            }
        });
    }

}
