package com.foodmanager.utils;

public class ErrorRec {
    public static void errorRec(int rec) {
        switch (rec) {
            case 9001:
                T.showShort("Application Id为空，请初始化.");
                break;
            case 9002:
                T.showShort("解析返回数据出错");
                break;
            case 9003:
                T.showShort("上传文件出错");
                break;
            case 9004:
                T.showShort("文件上传失败");
                break;
            case 9005:
                T.showShort("批量操作只支持最多50条");
                break;
            case 9006:
                T.showShort("objectId为空");
                break;
            case 9007:
                T.showShort("文件大小超过10M");
                break;
            case 9008:
                T.showShort("上传文件不存在");
                break;
            case 9009:
                T.showShort("没有缓存数据");
                break;
            case 9010:
                T.showShort("网络超时");
                break;
            case 9011:
                T.showShort("BmobUser类不支持批量操作");
                break;
            case 9012:
                T.showShort("上下文为空");
                break;
            case 9013:
                T.showShort("BmobObject（数据表名称）格式不正确");
                break;
            case 9014:
                T.showShort("第三方账号授权失败");
                break;
            case 9015:
                T.showShort("其他错误均返回此code");
                break;
            case 9016:
                T.showShort("无网络连接，请检查您的手机网络.");
                break;
            case 9017:
                T.showShort("与第三方登录有关的错误，具体请看对应的错误描述");
                break;
            case 9018:
                T.showShort("参数不能为空");
                break;
            case 9019:
                T.showShort("格式不正确：手机号码、邮箱地址、验证码");
                break;
            case 9020:
                T.showShort("保存CDN信息失败");
                break;
            case 9021:
                T.showShort("文件上传缺少wakelock权限");
                break;
            case 9022:
                T.showShort("文件上传失败，请重新上传");
                break;
            case 9023:
                T.showShort("请调用Bmob类的initialize方法去初始化SDK");
                break;
            case 9024:
                T.showShort("调用BmobUser的fetchUserInfo方法前请先确定用户是已经登录的");
                break;
            case 404:
                T.showShort("没有找到,恭喜你中奖了");
                break;
            case 500:
                T.showShort("unauthorized");
                break;
            case 400:
                T.showShort("object not found for e1kXT22L");
                break;
            case 101:
                T.showShort("查询的 对象或Class 不存在 或者 登录接口的用户名或密码不正确");
                break;
            case 102:
                T.showShort("查询中的字段名是大小写敏感的，且必须以英文字母开头，有效的字符仅限在英文字母、数字以及下划线。，或查询对应的字段值不匹配，或提供的地理位置格式不正确");
                break;
            case 103:
                T.showShort("查询单个对象或更新对象时必须提供objectId 或 非法的 class 名称，class 名称是大小写敏感的，并且必须以英文字母开头，有效的字符仅限在英文字母、数字以及下划线.");
                break;
            case 104:
                T.showShort("关联的class名称不存在");
                break;
            case 105:
                T.showShort("字段名是大小写敏感的，且必须以英文字母开头，有效的字符仅限在英文字母、数字以及下划线 或 字段名是Bmob默认保留的，如objectId,createdAt,updateAt,ACL");
                break;
            case 106:
                T.showShort("不是一个正确的指针类型");
                break;
            case 107:
                T.showShort("输入的json不是正确的json格式");
                break;
            case 108:
                T.showShort("用户名和密码是必需的");
                break;
            case 109:
                T.showShort("登录信息是必需的，如邮箱和密码时缺少其中一个提示此信息");
                break;
            case 111:
                T.showShort("传入的字段值与字段类型不匹配，期望是这样(%s)的，但传过来却是这样(%s)的");
                break;
            case 112:
                T.showShort("requests的值必须是数组");
                break;
            case 113:
                T.showShort("requests数组中每个元素应该是一个像这样子的json对象");
                break;
            case 114:
                T.showShort("requests数组大于50");
                break;
            case 117:
                T.showShort("纬度范围在[-90, 90] 或 经度范围在[-180, 180]");
                break;
            case 120:
                T.showShort("要使用此功能，请在Bmob后台应用设置中打开邮箱认证功能开关");
                break;
            case 131:
                T.showShort("不正确的deviceToken");
                break;
            case 132:
                T.showShort("不正确的installationId");
                break;
            case 133:
                T.showShort("不正确的deviceType");
                break;
            case 134:
                T.showShort("deviceToken已经存在");
                break;
            case 135:
                T.showShort("installationId已经存在");
                break;
            case 136:
                T.showShort("只读属性不能修改 或 android设备不需要设置deviceToken");
                break;
            case 138:
                T.showShort("表是只读的");
                break;
            case 139:
                T.showShort("角色名称是大小写敏感的，并且必须以英文字母开头，有效的字符仅限在英文字母、数字、空格、横线以及下划线。");
                break;
            case 141:
                T.showShort("缺失推送需要的data参数");
                break;
            case 142:
                T.showShort("时间格式应该如下： 2013-12-04 00:51:13");
                break;
            case 143:
                T.showShort("必须是一个数字");
                break;
            case 144:
                T.showShort("不能是之前的时间");
                break;
            case 145:
                T.showShort("文件大小错误");
                break;
            case 146:
                T.showShort("文件名错误");
                break;
            case 147:
                T.showShort("文件分页上传偏移量错误");
                break;
            case 148:
                T.showShort("文件上下文错误");
                break;
            case 149:
                T.showShort("空文件");
                break;
            case 150:
                T.showShort("文件上传错误");
                break;
            case 151:
                T.showShort("文件删除错误");
                break;
            case 160:
                T.showShort("图片错误");
                break;
            case 161:
                T.showShort("图片模式错误");
                break;
            case 162:
                T.showShort("图片宽度错误");
                break;
            case 163:
                T.showShort("图片高度错误");
                break;
            case 164:
                T.showShort("图片长边错误");
                break;
            case 165:
                T.showShort("图片短边错误");
                break;
            case 201:
                T.showShort("缺失数据");
                break;
            case 202:
                T.showShort("用户名已经存在");
                break;
            case 203:
                T.showShort("邮箱已经存在");
                break;
            case 204:
                T.showShort("必须提供一个邮箱地址");
                break;
            case 205:
                T.showShort("没有找到此邮件的用户");
                break;
            case 206:
                T.showShort("登录用户才能修改自己的信息。RestAPI的Http Header中没有提供sessionToken的正确值，不能修改或删除用户");
                break;
            case 207:
                T.showShort("验证码错误");
                break;
            case 208:
                T.showShort("authData不正确");
                break;
            case 209:
                T.showShort("该手机号码已经存在");
                break;
            case 210:
                T.showShort("旧密码不正确");
                break;
            case 301:
                T.showShort("验证错误详细提示，如邮箱格式不正确");
                break;
            case 302:
                T.showShort("Bmob后台设置了应用设置值， 如'不允许SDK创建表");
                break;
            case 310:
                T.showShort("云端逻辑运行错误的详细信息");
                break;
            case 311:
                T.showShort("云端逻辑名称是大小写敏感的，且必须以英文字母开头，有效的字符仅限在英文字母、数字以及下划线。");
                break;
            case 401:
                T.showShort("唯一键不能存在重复的值");
                break;
            case 402:
                T.showShort("查询的wher语句长度大于具体多少个字节");
                break;
            case 601:
                T.showShort("不正确的BQL查询语句");
                break;
            case 1002:
                T.showShort("该应用能创建的表数已达到限制");
                break;
            case 1003:
                T.showShort("该表的行数已达到限制");
                break;
            case 1004:
                T.showShort("该表的列数已达到限制");
                break;
            case 1005:
                T.showShort("每月api请求数量已达到限制");
                break;
            case 1006:
                T.showShort("该应用能创建定时任务数已达到限制");
                break;
            case 1007:
                T.showShort("该应用能创建云端逻辑数已达到限制");
                break;
            case 1500:
                T.showShort("你上传的文件大小已超出限制");
                break;
            case 10010:
                T.showShort("该手机号发送短信达到限制(对于一个应用来说，一天给同一手机号发送短信不能超过10条，一小时给同一手机号发送短信不能超过5条，一分钟给同一手机号发送短信不能超过1条)");
                break;
            case 10011:
                T.showShort("该账户无可用的发送短信条数");
                break;
            case 10012:
                T.showShort("身份信息必须审核通过才能使用该功能");
                break;
            case 10013:
                T.showShort("非法短信内容");
                break;
            default:
                T.showShort("未知错误");
                break;
        }
    }
}
