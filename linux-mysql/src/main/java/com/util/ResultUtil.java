package com.util;

import com.enums.ResponseCode;
import lombok.Data;


/**
 * @ClassNmae a
 * @Discription
 * @Author wh
 * @Date 2020/10/1  20:01
 * @Version 1.0
 */
@Data
public class ResultUtil {

    private String code;

    private String msg;

    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResultUtil success(Object data) {
        return ResultUtil(ResponseCode.SUCCESS.val(), ResponseCode.SUCCESS.msg(), data);
    }

    public static ResultUtil success(Object data, String msg) {
        return ResultUtil(ResponseCode.SUCCESS.val(), msg, data);
    }

    public static ResultUtil fail(String code, String msg) {
        return ResultUtil(code, msg, null);
    }

    public static ResultUtil fail(String code, String msg, Object data) {
        return ResultUtil(code, msg, data);
    }

    private static ResultUtil ResultUtil(String code, String msg, Object data) {
        ResultUtil ResultUtil = new ResultUtil();
        ResultUtil.setCode(code);
        ResultUtil.setMsg(msg);
        ResultUtil.setData(data);
        return ResultUtil;
    }
}
