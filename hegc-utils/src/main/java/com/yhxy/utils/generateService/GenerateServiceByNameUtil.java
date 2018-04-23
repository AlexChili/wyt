package com.yhxy.utils.generateService;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成 Service
 * @author Alex
 */
public class GenerateServiceByNameUtil {
	
	//生成service的物理地址
	private static final String SERVICE_PATH = "C:\\OXYGEN\\workspace\\base-project\\fcr-service\\src\\main\\java\\com\\yhxy\\service";
	private static final String MAPPER_PATH = "C:\\OXYGEN\\workspace\\base-project\\fcr-service\\src\\main\\java\\com\\yhxy\\mapper";
	private static final String MODEL_PATH = "C:\\OXYGEN\\workspace\\base-project\\fcr-service\\src\\main\\java\\com\\yhxy\\model";
	
	
	//只需修改【物理地址】即可
	public static void main(String[] args) throws Exception{
		new GenerateServiceByNameUtil().createService("FMessage","消息 Service测试");// service名      注释
	}
	
	//公共部分
	private static final String RT_1 = "\r\n";
	private static final String RT_2 = RT_1+RT_1;
	private static final String BLANK_1 =" ";
	
    /**
     * 创建bean的service
     */
	public void createService(String name,String comment) throws Exception{
		String fileName = SERVICE_PATH + "/" + name+ "Service.java";
		File f = new File(fileName);
		FileWriter fw = new FileWriter(f);
		fw.write("package "+getPackageByPath(SERVICE_PATH)+";"+RT_1+
						"import "+getPackageByPath(MAPPER_PATH)+"."+name+"Mapper;"+RT_1+
						"import "+getPackageByPath(MODEL_PATH)+"."+name+";"+RT_1+
						"import org.springframework.stereotype.Service;"+RT_1+
						"import org.springframework.transaction.annotation.Transactional;"+RT_1+
						"import org.springframework.beans.factory.annotation.Autowired;"+RT_1+
						"import java.util.List;"+RT_1+
						getAnnotation(comment)+RT_1+
						"@Service"+RT_1+
						"@Transactional"+RT_1+
						"public class " + name +"Service {"+RT_1+
									//根据id查询
									"        @Autowired"+RT_1+
									"        private  "+name+"Mapper mapper;"+RT_2+
									"        /** select by : id */"+RT_1+
							        "        public "+name+" get"+name+"ById(int id) {"+RT_1+
							        "        	return this.mapper.selectByPrimaryKey(id);"+RT_1+
									"        }"+RT_1+
									//select list
									"        /** select list */"+RT_1+
									"        public List<"+name+"> get"+name+"List() {"+RT_1+
									"        	return this.mapper.selectByExample(null);"+RT_1+
									"        }"+RT_1+
									//save
									"        /** save */"+RT_1+
									"        public int save("+name+" m) {"+RT_1+
									"        	if(m.getId()!=null) {"+RT_1+
									"        		return this.mapper.updateByPrimaryKeySelective(m);"+RT_1+
									"        	}else {"+RT_1+
									"        	    return this.mapper.insertSelective(m);"+RT_1+
									"        	}"+RT_1+
									"        }"+RT_1+
									//delete
								    "        /** delete */"+RT_1+
								    "        public int deleteById(int id) {"+RT_1+
								    "        		return this.mapper.deleteByPrimaryKey(id);"+RT_1+
									"        }"+RT_1+
						"}");
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
