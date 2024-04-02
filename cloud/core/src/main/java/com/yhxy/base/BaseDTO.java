package com.yhxy.base;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @ClassName BaseDTO
 * @Description: TODO
 * @Author alex
 * @DateTime 2022/8/21 14:56
 * @updateDateTime 2022/8/21 14:56
 * @Version 1.0.0
 **/
@Data
public class BaseDTO {
    @TableField(exist = false)
    private Integer page = 1;
    @TableField(exist = false)
    private Integer rows = 10;
    @TableField(exist = false)
    private String sort;
    @TableField(exist = false)
    private String order;
}
