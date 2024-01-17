package com.starer.util;

/**
 * @Author: pengxiong
 * @Date: 2023/12/28 12:45:14
 * @Version: V1.0
 * @Description:
 **/
public class ResponseResult {

    // 响应的状态码
    private Integer status;
    // 响应中携带的提示
    private String msg;
    // 响应携带的数据
    private Object data;

    private ResponseResult(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private ResponseResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseResult success(String msg) {
        return new ResponseResult(ResponseCodeEnum.SUCCESS.getStatus(), msg);
    }
    public static ResponseResult success(String msg, Object data) {
        return new ResponseResult(ResponseCodeEnum.SUCCESS.getStatus(), msg, data);
    }
    public static ResponseResult error(String msg) {
        return new ResponseResult(ResponseCodeEnum.ERROR.getStatus(), msg);
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "JSONResult{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
