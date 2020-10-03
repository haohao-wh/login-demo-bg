package com.enums;

/**
 * @ClassNmae a
 * @Discription
 * @Author wh
 * @Date 2020/10/1  20:00
 * @Version 1.0
 */
/**
 * @Package com.server.config
 * @Author wuzy
 * @Date 2019/10/30 14:47
 * @Version V1.0
 * @Description: code码封装枚举类
 */

public enum ResponseCode {
    /** 成功 */
    SUCCESS("200", "成功"),

    /** 操作失败 */
    ERROR("500", "操作失败");

    private ResponseCode(String value, String msg){
        this.val = value;
        this.msg = msg;
    }

    public String val() {
        return val;
    }

    public String msg() {
        return msg;
    }

    private String val;
    private String msg;
}
