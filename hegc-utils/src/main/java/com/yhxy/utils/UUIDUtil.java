package com.yhxy.utils;

import java.util.UUID;
public class UUIDUtil {

	/**
	 * 
	 * 功能说明：取得UUID，36位
	 *
	 * @return
	 *
	 */
	public static String getUUID() {
		  UUID uuid = UUID.randomUUID();
		  String a = uuid.toString();
		  
		  return a;
	}
}
