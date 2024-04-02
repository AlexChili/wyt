package com.yhxy.util;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CimCustomUtil
 * @Description: 自己封装的工具类
 * @Author alex
 * @DateTime 2023/3/16 15:47
 * @updateDateTime 2023/3/16 15:47
 * @Version 1.0.0
 **/
public class CustomUtil {
    public static <T> T copyProperties(Object source, Class<T> clazz) {

        if (null == source) {
            return null;
        }
        if (null == clazz) {
            return null;
        }

        try {
            T t2 = clazz.newInstance();
            BeanUtils.copyProperties(source, t2);
            return t2;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    //将一个list的对象转换成另一个list的对象
    public static <T> List<T> copyListProperties(List<?> source, Class<T> clazz) {
        if (null == source) {
            return null;
        }
        if (null == clazz) {
            return null;
        }
        List<T> list = new ArrayList<>();
        for (Object o : source) {
            T t = copyProperties(o, clazz);
            list.add(t);
        }
        return list;
    }
    //将一个list的对象转换为List<Map<String,Object>>对象
    public static <T> List<Map<String, Object>> listConvert(List<T> list) {
        List<Map<String, Object>> list_map = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(item -> {
                Map<String, Object> map = null;
                try {
                    map = (Map<String, Object>) PropertyUtils.describe(item);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                list_map.add(map);
            });
        }
        return list_map;
    }


}
