package com.yhxy.utils.generateService;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成 controller
 * @author Alex
 */
public class GenerateControllerUtil {
	
	//生成controller的物理地址
	private static final String GENERATE_PATH= "D:\\WORKSPACE\\base-project\\we-course-web\\src\\main\\java\\com\\yhxy\\controller";
	
	//只需修改【物理地址】即可
	public static void main(String[] args) throws Exception{
		new GenerateControllerUtil().createController("SchWeCouController","校园微课 controller");
		new GenerateControllerUtil().createController("PubWeCouController","公开课 controller");
		new GenerateControllerUtil().createController("KnoTreeController","知识树 controller");
		new GenerateControllerUtil().createController("TeaWeCouController","名师微课 controller");
		new GenerateControllerUtil().createController("WeCouRaceController","微课大赛 controller");
	}
	
	//公共部分
	private static final String RT_1 = "\r\n";
	private static final String RT_2 = RT_1+RT_1;
	private static final String BLANK_1 =" ";
	
    /**
     * 创建controller
     */
	public void createController(String name,String comment) throws Exception{
		String fileName = GENERATE_PATH+ "/" + name + ".java";
		File f = new File(fileName);
		FileWriter fw = new FileWriter(f);
		fw.write("package "+getPackageByPath(GENERATE_PATH)+";"+RT_1+
						"import org.springframework.stereotype.Controller;"+RT_1+
						"import org.springframework.transaction.annotation.Transactional;"+RT_1+
						getAnnotation(comment)+RT_1+
						"@Controller"+RT_1+
						"@Transactional"+RT_1+
						"public class " +  name +" {"+RT_2+"}");
		fw.flush();
		fw.close();
		showInfo(fileName);
    }

	/** *********************************************************************************************************************************
	 * 获取路径的最后面字符串<br>
	 * 如：<br>
	 *     <code>str = "com.yhxy.bean.User"</code><br>
	 *     <code> return "User";<code>
	 * @param str
	 * @return
	 */
	public String getLastChar(String str) {
		if ((str != null) && (str.length() > 0)) {
			int dot = str.lastIndexOf('.');
			if ((dot > -1) && (dot < (str.length() - 1))) {
				return str.substring(dot + 1);
			}
		}
		return str;
	}
	
	/**
	 * 获取注释部分
	 * 2017年5月4日 下午2:22:58
	 */
	public String getAnnotation(String comment) {
		   String annotation = "/**"+RT_1+BLANK_1+
											   "*"+BLANK_1+ comment +RT_1+BLANK_1+
				   								"*"+BLANK_1+ "@author hegc " +RT_1+BLANK_1+
				   								"*"+BLANK_1+"@date " +getDate()+RT_1+BLANK_1+
				   								"*/"+RT_1;
		   return annotation;
	}
	
	/**
	 * 根据物理地址获取 包名
	 * 2017年5月2日 下午2:15:24
	 */
	public String getPackageByPath(String path) {
		return path.substring(path.indexOf("com")).replace("\\", ".");
			
	}
	
	/**
	 * 把第一个字母变为小写<br>
	 * 如：<br>
	 *     <code>str = "UserDao";</code><br>
	 *     <code>return "userDao";</code>
	 * @param str
	 * @return
	 */
	public String getLowercaseChar(String str){
		return str.substring(0,1).toLowerCase()+str.substring(1);
	}

	/**
	 * 显示信息
	 * @param info
	 */
	public void showInfo(String info){
		System.out.println("创建文件："+ info+ "成功！");
	}
	
	/**
	 * 获取系统时间
	 * @return
	 */
	public static String getDate(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(new Date());
	}
}
