package com.wust.ems.pojo;

/*
 *规范响应结果
 * code---请求数据是否成功
 * msg---注释，请求不成功的原因
 * data---返回的数据信息
 */

public class Result {
    private Integer code;
    private String msg;
    private Object data;

    public Result(){}
    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public void setCode(Integer code) {this.code = code;}
    public void setMsg(String msg) {this.msg = msg;}
    public void setData(Object data) {this.data = data;}
    public Integer getCode() {return code;}
    public String getMsg() {return msg;}
    public Object getData() {return data;}

    //成功请求，有返回信息
    public static Result success(Object data) {
        return new Result(1, "success", data);
    }
    //成功请求，无返回信息
    public static Result success() {
        return new Result(1, "success", null);
    }
    //请求失败
    public static Result error(String msg) {
        return new Result(0, msg, null);
    }

    @Override
    public String toString() {
        return "Result [code=" + code + ", msg=" + msg + ", data=" + data + "]";
    }
}
