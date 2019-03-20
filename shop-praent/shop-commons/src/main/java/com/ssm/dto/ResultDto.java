package com.ssm.dto;

import com.ssm.commons.StateCode;

/**
 *
 */
public class ResultDto {
    private Integer code;
    private String msg;
    private Object data;

    public static ResultDto restlt(Boolean flag) {
        return flag ? success() : error();
    }

    public static ResultDto restlt(Boolean flag, Object data) {
        return flag ? success(StateCode.success.getCode(), StateCode.success.getMsg(), data) : error();
    }

    public static ResultDto success() {
        return new ResultDto(StateCode.success.getCode(), StateCode.success.getMsg(), null);
    }

    /**
     * 成功
     *
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static ResultDto success(Integer code, String msg, Object data) {
        return new ResultDto(code, msg, data);

    }

    /**
     * 失败
     *
     * @return
     */
    public static ResultDto error() {
        return new ResultDto(1000, "error", null);

    }

    /**
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static ResultDto error(Integer code, String msg, Object data) {
        return new ResultDto(code, msg, data);

    }


    public ResultDto() {
        super();
    }

    public ResultDto(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultDto{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
}
