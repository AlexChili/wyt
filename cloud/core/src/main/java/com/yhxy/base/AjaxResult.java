package com.yhxy.base;

import com.yhxy.constant.HttpStatus;

/**
 * @ClassName AjaxResult
 * @Description:
 * @Author 代超
 * @DateTime 2023/5/18 17:16
 * @Version 1.0.0
 **/
public class AjaxResult extends Result {


    public static Result setResultError(Integer code, String msg) {
        return setResult(code, msg, null);
    }
    public static Result setResultError(String msg) {
        return setResult(HttpStatus.ERROR, msg, null);
    }
    public static Result setResultSuccess(Object data) {
        return setResult(HttpStatus.SUCCESS, HttpStatus.SUCCESS + "", data);
    }
    public static Result<Object> setResultSuccess() {
        return setResult(HttpStatus.SUCCESS, HttpStatus.SUCCESS + "", null);
    }
    public static Result<Object> setResultSuccess(String msg) {
        return setResult(HttpStatus.SUCCESS, msg, null);
    }
    public static Result<Object> setResult(Integer code, String msg, Object data) {
        return new Result(code, msg, data);
    }
}
