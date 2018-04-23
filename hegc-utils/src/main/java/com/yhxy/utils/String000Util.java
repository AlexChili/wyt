package com.yhxy.utils;

import com.yhxy.utils.String000Util.Type;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class String000Util {

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
		System.out.println(new String000Util().GetString000(4));
		System.out.println(new String000Util().GetString000(44));
		System.out.println(new String000Util().GetString000(444));
		System.out.println(new String000Util().toPinYin("End", "", Type.LOWERCASE));
		
		//汉字转拼音
		String000Util tool = new String000Util();  
        System.out.println("刘亚壮的运行测试结果为====" + tool.toPinYin("何广成", "", Type.LOWERCASE));  
        
        //数字转字母
        char c1=(char) (2+64);
        System.out.println(c1);
	}
	
	/* 三位补0 */
	public static final String GetString000(Integer num) {
		if(num<10) {
			return "00"+num;
		}else if(num<100 && num>9){
			return "0"+num;
		}else return num+"";
	}
	
	
	HanyuPinyinOutputFormat format = null;  
    public static enum Type {  
        UPPERCASE,              //全部大写  
        LOWERCASE,              //全部小写  
        FIRSTUPPER              //首字母大写  
    }  
  
    public String000Util(){  
        format = new HanyuPinyinOutputFormat();  
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);  
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
    }  
  
    public String toPinYin(String str) throws BadHanyuPinyinOutputFormatCombination{  
        return toPinYin(str, "", Type.UPPERCASE);  
    }  
  
    public String toPinYin(String str,String spera) throws BadHanyuPinyinOutputFormatCombination{  
        return toPinYin(str, spera, Type.UPPERCASE);  
    }  
  
    /** 
     * 将str转换成拼音，如果不是汉字或者没有对应的拼音，则不作转换 
     * 如： 明天 转换成 MINGTIAN 
     * @param str：要转化的汉字 
     * @param spera：转化结果的分割符 
     * @return 
     * @throws BadHanyuPinyinOutputFormatCombination 
     */  
    public String toPinYin(String str, String spera, Type type) throws BadHanyuPinyinOutputFormatCombination {  
        if(str == null || str.trim().length()==0)  
            return "";  
        if(type == Type.UPPERCASE)  
            format.setCaseType(HanyuPinyinCaseType.UPPERCASE);  
        else  
            format.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
  
        String py = "";  
        String temp = "";  
        String[] t;  
        for(int i=0;i<str.length();i++){  
            char c = str.charAt(i);  
            if((int)c <= 128)  
                py += c;  
            else{  
                t = PinyinHelper.toHanyuPinyinStringArray(c, format);  
                if(t == null)  
                    py += c;  
                else{  
                    temp = t[0];  
                    if(type == Type.FIRSTUPPER)  
                        temp = t[0].toUpperCase().charAt(0)+temp.substring(1);  
                    py += temp+(i==str.length()-1?"":spera);  
                }  
            }  
        }  
        return py.trim();  
    }  

}
