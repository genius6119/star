package com.yc.zuul.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author oak
 * @date 23/08/2018 20:09
 */
@Data
public class ResponseData {

    private final String message;
    private final int code;
    private Map<String, Object> data = new HashMap<>();

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public ResponseData putKeyValue(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public ResponseData putMap(Map<String,Object> map) {
        data = map;
        return this;
    }

    private ResponseData(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseData ok() {
        return new ResponseData(200, "Ok");
    }

    public static ResponseData serverInternalError() {
        return new ResponseData(500, "Server Internal Error");
    }

    public static ResponseData LoginExpired(String message) {
        return new ResponseData(900, message);
    }

    public static ResponseData error(String message) {
        return new ResponseData(999, message);
    }

    public static ResponseData enterIngError() {
        return new ResponseData(999, "入参不可为空");
    }

}
