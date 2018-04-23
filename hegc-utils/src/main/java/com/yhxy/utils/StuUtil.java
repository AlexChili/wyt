package com.yhxy.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class StuUtil {
	
	public static void main(String[] args) {
		
		String s="<img src=\"http://fcr.oss-cn-31y%3D\">";
		List<ExUtilVO> l=subString(s);
		for(int i=0;i<l.size();i++) {
			System.out.println(l.get(i).getName());
			
		}

	}
	
	
	/**  
     * 计算一个字符串中包含另一个字符串的个数  
     */  
    public static int countStr(String one, String two){  
        int counter=0;  
        if (one.indexOf(two) == -1) {  
            return 0;  
        }  
            while(one.indexOf(two)!=-1){  
                counter++;  
                one=one.substring(one.indexOf(two)+two.length());  
            }  
            return counter;  
    }  
    
    /**  
     * 截取字符串
     */  
    public static List<ExUtilVO> subString(String s){  
    	List<String> l=new ArrayList<String>();
    	while(s.length()!=0) {
    		if(!s.substring(0,1).equals("<")) {
				String one=StringUtils.substringBefore(s, "<");
				s=s.substring(one.length());
				l.add(one);
			}else {
				String two=StringUtils.substringBetween(s, "img src=\"", "\">");
				s=s.substring(two.length()+12);
				l.add(two);
			}
    	}
    	
		List<ExUtilVO> list=new ArrayList<ExUtilVO>();
		for(int i=0;i<l.size();i++) {
			ExUtilVO vo=new ExUtilVO();
		    if(l.get(i).length()>4&&l.get(i).substring(0, 4).equals("http")) {
		    	vo.setType("1");
		    	vo.setName(l.get(i));
			}else {
				vo.setType("2");
		    	vo.setName(l.get(i));
			}
		    list.add(vo);
		}
    	return list;
    }  
}
