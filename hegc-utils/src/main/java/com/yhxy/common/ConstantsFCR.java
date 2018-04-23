package com.yhxy.common;

public class ConstantsFCR {
	
	//自定义配置文件
	public static final String ENVIRONMENT_PROPERTIES_PATH = "config.properties";
	
	/** 学校首页类型 */
	public static String SCHOOL_DEFAULT = "1";                     //普通学校  推荐首页图片
	public static String SCHOOL_CUSTOM= "2";                     //定制学校  校方定制图片
	
	/** 数据来源 */
	public static String SOURCE_PLATFORM = "1";                     //平台
	public static String SOURCE_ZYDJ= "2";                     //中央电教馆
	public static String SOURCE_SCHOOL = "3";                     //学校
	public static String SOURCE_NB_TEACHER= "4";                     //名师
	
	
	/** 消息类型  */
	public static String MSG_SYS_1 = "1";                     //系统消息
	public static String MSG_PLATFORM_2 = "2";       //平台消息
	public static String MSG_SCHOOL_3 = "3";          //校园消息
	public static String MSG_CLASS_4 = "4";             //班级消息
	public static String MSG_CLASSROOM_5 = "5";   //课堂消息
	
	public static String MSG_EX_6 = "6";   //练习提醒
	public static String MSG_COURSE_7 = "7";   //新课提醒
	public static String MSG_QA_8 = "8";   //问答提醒
	
	/** 学段 */
	public static Integer STAGE_SMALL=1;//小学
	public static Integer STAGE_MIDDLE=2;//初中
	public static Integer STAGE_HIGH=3;//高中
	
	/** 角色 */
	public static String ROLE_SUPER_ADMIN="1";//系统超级管理员
	public static String ROLE_SCHOOL_ADMIN="2";//学校管理员
	public static String ROLE_MOJOR="3";//班主任
	public static String ROLE_TEACHER="4";//老师
	public static String ROLE_STUDENT="5";//学生
	
	/** 练习题类型 */
	public static String EXERCISE_TYPE_ONLYONE="1";//单选
	public static String EXERCISE_TYPE_MORE="2";//多选
	public static String EXERCISE_TYPE_JURDGE="3";//判断
}
