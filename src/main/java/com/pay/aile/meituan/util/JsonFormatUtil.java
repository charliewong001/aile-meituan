package com.pay.aile.meituan.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 统一返回json格式，主要用于接口使用 {errorCode:返回code,errorMsg:返回msg,returnData:返回数据}
 */

public class JsonFormatUtil {

    public static final String CODE = "code";
    public static final String MSG = "msg";
    public static final String DATA = "returnData";

    public static final String CODE_OK = "0";
    public static final String CODE_OK_MSG = "success";
    public static final String CODE_ERROR = "99999";
    public static final String CODE_ERROR_MSG = "系统错误";

    public static JSONObject getFailureJson() {
        JSONObject jsonObj = new JSONObject(true);
        jsonObj.put(CODE, CODE_ERROR);
        jsonObj.put(MSG, CODE_ERROR_MSG);
        jsonObj.put(DATA, "");
        return jsonObj;
    }

    public static JSONObject getFailureJson(Object data) {
        JSONObject jsonObj = new JSONObject(true);
        jsonObj.put(CODE, CODE_ERROR);
        jsonObj.put(MSG, CODE_ERROR_MSG);
        jsonObj.put(DATA, data);
        return jsonObj;
    }

    public static JSONObject getFailureJson(String msg) {
        JSONObject jsonObj = new JSONObject(true);
        jsonObj.put(CODE, CODE_ERROR);
        jsonObj.put(MSG, msg);
        jsonObj.put(DATA, "");
        return jsonObj;
    }

    public static JSONObject getFailureJson(String msg, Object data) {
        JSONObject jsonObj = new JSONObject(true);
        jsonObj.put(CODE, CODE_ERROR);
        jsonObj.put(MSG, msg);
        jsonObj.put(DATA, data);
        return jsonObj;
    }

    public static JSONObject getJson(String code, String msg, Object data) {
        JSONObject jsonObj = new JSONObject(true);
        jsonObj.put(CODE, code);
        jsonObj.put(MSG, msg);
        jsonObj.put(DATA, data);
        return jsonObj;
    }

    public static String getJsonString(JSONObject jsonObject) {
        return jsonObject == null ? "" : JSON.toJSONString(jsonObject);
    }

    public static JSONObject getSuccessJson() {
        JSONObject jsonObj = new JSONObject(true);
        jsonObj.put(CODE, CODE_OK);
        jsonObj.put(MSG, CODE_OK_MSG);
        jsonObj.put(DATA, "");
        return jsonObj;
    }

    public static JSONObject getSuccessJson(Object data) {
        JSONObject jsonObj = new JSONObject(true);
        jsonObj.put(CODE, CODE_OK);
        jsonObj.put(MSG, CODE_OK_MSG);
        jsonObj.put(DATA, data);
        return jsonObj;
    }

    public static JSONObject getSuccessJson(String msg) {
        JSONObject jsonObj = new JSONObject(true);
        jsonObj.put(CODE, CODE_OK);
        jsonObj.put(MSG, msg);
        jsonObj.put(DATA, "");
        return jsonObj;
    }

    public static JSONObject getSuccessJson(String msg, Object data) {
        JSONObject jsonObj = new JSONObject(true);
        jsonObj.put(CODE, CODE_OK);
        jsonObj.put(MSG, msg);
        jsonObj.put(DATA, data);
        return jsonObj;
    }

    public static String toJSONString(Object obj) {
        return JSON.toJSONString(obj);
    }
}
