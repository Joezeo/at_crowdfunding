package com.joezeo.atcrowdfunding.common.constant;

public class Const {
    // 存入session中用户信息的属性名
    public static final String LOGIN_USER = "user";

    // 添加新用户时的默认初始密码
    public static final String DEFAULT_INIT_PASSWORD = "123";

    // 用户根权限的名称
    public static final String ROOT_PERMISSION = "rootPermission";

    // 存入application时，数据库中所有权限uris的属性名
    public static final String ALL_URIS = "allUris";

    // 存入session时，该用户拥有权限uri的属性名
    public static final String USER_URIS = "userUris";
    public static final String LOGIN_MEMBER = "member";

    // 保存在cookie中的自动登录信息的属性名
    public static final String LOGIN_CODE = "logincode";
}
