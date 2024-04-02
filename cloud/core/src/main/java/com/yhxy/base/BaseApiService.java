package com.yhxy.base;

import com.yhxy.constant.HttpStatus;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName BaseApiService
 * @Description: TODO
 * @Author alex
 * @DateTime 2022/8/21 14:53
 * @updateDateTime 2022/8/21 14:53
 * @Version 1.0.0
 **/
@Data
@Component
@Slf4j
public class BaseApiService<T> {


    public Result<T> setResultError(Integer code, String msg) {
        return setResult(code, msg, null);
    }

    public Result<T> setResultError(String msg) {
        return setResult(HttpStatus.ERROR, msg, null);
    }

    public Result<T> setResultSuccess(T data) {
        return setResult(HttpStatus.SUCCESS, "操作成功", data);
    }

    public Result<T> setResultSuccess() {
        return setResult(HttpStatus.SUCCESS, "操作成功", null);
    }

//    public Result setResultSuccess(String msg) {
//        return setResult(HttpStatus.SUCCESS,"成功" , msg);
//    }

    public Result<T> setResult(Integer code, String msg, T data) {
       // log.info("interface return info --> code : {}, msg : {}, data : {}", code, msg, JSONUtil.toJsonString(data));
        return new Result(code, msg, data);
    }


}
