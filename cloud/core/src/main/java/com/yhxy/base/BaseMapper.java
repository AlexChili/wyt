package com.yhxy.base;

import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName BaseMapper
 * @Description: 扩展BaseMapper
 * @Author alex
 * @DateTime 2023/3/16 18:48
 * @updateDateTime 2023/3/16 18:48
 * @Version 1.0.0
 **/
public interface BaseMapper<T> extends MPJBaseMapper<T> {
    int insertBatch(@Param("list") List<T> list);

    int updateBatch(@Param("list") List<T> list);
}
