package com.ssm.commons;

/*Code 业务自定义状态码定义示例
授权相关
1001: 无权限访问
1002: access_token过期
1003: unique_token无效
...
用户相关
2001: 未登录
2002: 用户名或密码错误
2005: 用户已存在


2200 商品

业务1
3001: 业务1XXX
3002: 业务1XXX
*/
public enum StateCode {

    success(200, "成功"),
    error(400, "失败"),
    BAD_REQUEST(400, "请求错误，请修正请求"),
    NOT_FOUND(404, "资源未找到"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误");
    private final Integer code;
    private final String msg;

    StateCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
