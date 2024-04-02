package com.yhxy.base;

import com.yhxy.constant.HttpStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Result
 * @Description: TODO
 * @Author alex
 * @DateTime 2022/8/21 14:54
 * @updateDateTime 2022/8/21 14:54
 * @Version 1.0.0
 **/
@Data
@NoArgsConstructor
public class Result<T> {

    private Integer code;

    private String message;

    private T data;

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = (T) data;
    }

    public Boolean isSuccess() {
        return code == HttpStatus.SUCCESS;
    }
}
