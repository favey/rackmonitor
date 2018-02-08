package com.greywanchuang.rackmonitor.util;

/**
 * 常量
 * @author ScienJus
 * @date 2015/7/31.
 */
public final class Constants {

    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    /**
     * token有效期（小时）
     */
    public static final int TOKEN_EXPIRES_HOUR = 72;

    /**
     * 存放Authorization的header字段
     */
    public static final String AUTHORIZATION = "Authorization";

    enum DATE_RANGE
    {
        WEEK,
        HALF_MONTH,
        MONTH;
    }
}
