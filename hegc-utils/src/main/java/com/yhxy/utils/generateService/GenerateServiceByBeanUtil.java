package com.yhxy.utils.generateService;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 根据Bean 生成 Service
 * @author Alex
 */
public class GenerateServiceByBeanUtil {
	
	//service物理地址
	private static final String SERVICE_PATH = "D:\\WORKSPACE\\base-project\\template-service\\src\\main\\java\\com\\yhxy\\service";
	
	//只需修改【物理地址】即可
	public static void main(String[] args) throws Exception{
		new GenerateServiceByBeanUtil().createBeanService(Admin.class);
	}
	
	//公共部分
	private static final String RT_1 = "\r\n";
	private static final String RT_2 = RT_1+RT_1;
	private static final String BLANK_1 =" ";
	
	//注释部分
	private static final String ANNOTATION_AUTHOR_PARAMTER = "@author ";
	private static final String ANNOTATION_AUTHOR_NAME = "hegc";
	private static final String ANNOTATION_AUTHOR = ANNOTATION_AUTHOR_PARAMTER + ANNOTATION_AUTHOR_NAME;
	private static final String ANNOTATION_DATE = "@date ";
	private static final String ANNOTATION = "/**"+RT_1+BLANK_1+"*"+BLANK_1+ANNOTATION_AUTHOR +RT_1+BLANK_1+"*"+BLANK_1+ANNOTATION_DATE +getDate()+RT_1+BLANK_1+"*/"+RT_1;
	
    /**
     * 创建bean的service
     */
    @SuppressWarnings("rawtypes")
	public void createBeanService(Class c) throws Exception{
    	
    	String cName = c.getName();
		String fileName = SERVICE_PATH + "/" + getLastChar(cName) + "Service.java";
		System.out.println(fileName);
		File f = new File(fileName);
		FileWriter fw = new FileWriter(f);
		fw.write("package "+getPackageByPath(SERVICE_PATH)+";"+RT_1+
						"import org.springframework.stereotype.Service;"+RT_1+
						"import org.springframework.transaction.annotation.Transactional;"+RT_1+
						ANNOTATION+RT_1+
						"@Service"+RT_1+
						"@Transactional"+RT_1+
						"public class " +  getLastChar(cName) + "Service "+"{"+RT_2+"}");
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
