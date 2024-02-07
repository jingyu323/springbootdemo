package com.rain.test.common;

import com.alibaba.fastjson.annotation.JSONType;

/**
 * ClassName: JsonResult
 *
 * @author qzb
 * @Description: TODO
 * @date 2018年5月31日
 * 和ResponseBody注解一起使用，让后台接口返回的数据为Json串。
 */
@JSONType(orders = {"success", "msg", "data"})
public class JsonResult {
    private boolean success;
    private String msg;
    private Object data;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public JsonResult(Object data) {
        super();
        this.data = data;
    }

    public JsonResult(boolean success, String msg, Object data) {
        super();
        this.success = success;
        this.msg = msg;
        this.data = data;
    }
}
