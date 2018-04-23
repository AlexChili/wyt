package com.yhxy.utils;

import java.util.LinkedList;  
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;  
  
/**  
 *  JSON数据处理工具类  
 */  
public class JsonUtil  
{  
    /**  
     * 实体类型的对象  
     */  
    public static final int OBJECT_TYPE_BEAN = 1;
  
    /**  
     * 集合类型对象  
     */  
    public static final int OBJECT_TYPE_LIST = 2;
    
  
    /**  
     * 方法名称：parseJsonObjectToBean  
     * 内容摘要：将一个JSON对象转换成指定类型的Bean  
     * @param json  任意实体，包括Json格式字符串  
     * @param clazz 需要转换的bean的Class  
     * @return  
     */  
    @SuppressWarnings("rawtypes")
	public static Object parseJsonObjectToBean(Object json, Class clazz)  
    {  
        Object bean = null;  
        try  
        {  
            JSONObject jsonObject = JSONObject.fromObject(json);  
  
            bean = JSONObject.toBean(jsonObject, clazz);  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  

        return bean;  
    }  
  
    /**  
     * 方法名称：parseJsonArrayToBean  
     * 内容摘要：将一个JSON对象转换成指定类型的Bean集合  
     * @param json  任意实体，包括Json格式字符串  
     * @param clazz 需要转换的bean的Class  
     * @return  
     */  
    @SuppressWarnings("rawtypes")
	public static List<Object> parseJsonArrayToBean(Object json, Class clazz)  
    {  
        List<Object> list = new LinkedList<Object>();  
        try  
        {  
            JSONArray jsonArray = JSONArray.fromObject(json);  
  
            Object[] beans = jsonArray.toArray();  
  
            Object bean = null;  
            for(int i=0 ; i< beans.length; i++)  
            {  
                bean = parseJsonObjectToBean(beans[i], clazz);  
                list.add(bean);  
            }  
  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
  
        return list;  
    }  
  
    /**  
     * 方法名称：getJsonString  
     * 内容摘要：将对象转换为JSON字符串  
     * @param object  
     * @param objType  
     * @return  
     */  
    public static String getJsonString(Object object, int objType)  
    {  
        JSON json = null;  
        try  
        {  
            json = null;  
  
            if (objType == OBJECT_TYPE_BEAN)  
            {  
                json = JSONObject.fromObject(object);  
            }  
            else if (objType == OBJECT_TYPE_LIST)  
            {  
                json = JSONArray.fromObject(object);  
            }  
            else  
            {  
                return "待写入实体的对象类型不正确";  
            }  
        }  
        catch (Exception e)  
        {  
            return "转换JSON字符串出错";  
        }  
  
        return json.toString();  
    }  
  
  
}  