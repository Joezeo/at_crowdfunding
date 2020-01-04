package com.joezeo.atcrowdfunding.common.constant;

import com.joezeo.atcrowdfunding.bean.Permission;

import java.util.Set;

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
}
