package com.imooc.mall.enums;

import lombok.Getter;

@Getter
public enum ResponseEnum {

    ERROR(-1, "服务端异常"),

    SUCCESS(0, "成功"),

    PASSWORD_R_ERROR(1, "密码错误"),

    USERNAME_EXIST(2, "用户名已存在"),

    PARAM_ERROR(3, "参数错误"),

    EMAIL_EXIST(4, "邮箱已存在"),

    NEED_LOGIN(10, "用户未登录,无法获取当前用户信息"),

    USERNAME_OR_PASSWORD_ERROR(11, "用户名或密码错误"),

    PRODUCT_OFF_OR_DELETE(12, "商品下架或删除"),

    PRODUCT_NOT_EXIST(13, "商品不存在"),

    PRODUCT_STOCK_ERROR(14, "库存有误"),

    CART_PRODUCT_NOT_EXIST(15, "购物车无此商品"),

    SHIPPING_NOT_EXIST(16, "收货地址不存在"),

    CART_SELECTED_IS_EMPTY(17, "没有选中的商品"),

    ORDER_NOT_EXIST(18, "订单不存在"),

    ORDER_STATUS_ERROR(19, "订单状态有误"),
    ;

    Integer code;

    String desc;

    ResponseEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
