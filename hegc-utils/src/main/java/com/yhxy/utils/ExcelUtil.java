package com.yhxy.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * Excel的工具类
 * @author kongxiangming
 *
 */
public class ExcelUtil {
	
	private static final String EXCEL_XLS = "xls";
	private static final String EXCEL_XLSX = "xlsx";

	/**
	 * 判断是否是2003的excel
	 * @param filePath	文件完整路径
	 * @return	true表示是，false表示不是
	 */
	public static boolean isExcel2003(String filePath) {
		return filePath.matches("^.+\\.(?i)(xls)$");
	}
	
	/**
	 * 判断是否是2007的excel
	 * @param filePath	文件完整路径
	 * @return	true表示是，false表示不是
	 */
	public static boolean isExcel2007(String filePath) {
		return filePath.matches("^.+\\.(?i)(xlsx)$");
	}

/*	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws URISyntaxException {
		String finalXlsxPath = "D:\\Tomcat\\apache-tomcat-9.0.0.M22\\webapps\\fcr\\WEB-INF\\classes\\cxcel_template\\account_template.xlsx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("userCode", "ssdx");
		map.put("password", "123456");
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("userCode", "ssdx1");
		map1.put("password", "123456");
		List<Map> list = new ArrayList<Map>();
		list.add(map);
		list.add(map1);
		int cloumnCount = 10;
		writeExcel(list, cloumnCount, finalXlsxPath);
	}*/

	/**
	 * 把数据写入excel
	 */
	@SuppressWarnings("rawtypes")
	public static void writeExcel(List<Map<String, String>> list, int cloumnCount, String finalXlsxPath) {
		OutputStream out = null;
		try {
			// 获取总列数
			int columnNumCount = cloumnCount;
			// 读取Excel文档
			File finalXlsxFile = new File(finalXlsxPath);
			Workbook workBook = getWorkbok(finalXlsxFile);
			// sheet 对应一个工作页
			Sheet sheet = workBook.getSheetAt(0);
			/**
			 * 删除原有数据，除了属性列
			 */
			int rowNumber = sheet.getLastRowNum(); // 第一行从0开始算
			for (int i = 1; i <= rowNumber; i++) {
				Row row = sheet.getRow(i);
				sheet.removeRow(row);
			}

			// 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
			out = new FileOutputStream(finalXlsxPath);
			workBook.write(out);
			/**
			 * 往Excel中写新数据
			 */
			for (int j = 0; j < list.size(); j++) {
				// 创建一行：从第二行开始，跳过属性列
				Row row = sheet.createRow(j + 1);
				// 得到要插入的每一条记录
				Map dataMap = list.get(j);
				String usercode = dataMap.get("userCode").toString();
				String passward = dataMap.get("password").toString();
				for (int k = 0; k <= columnNumCount; k++) {
					// 在一行内循环
					Cell first = row.createCell(0);
					first.setCellValue(usercode);
					Cell second = row.createCell(1);
					second.setCellValue(passward);
				}
			}
			// 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
			out = new FileOutputStream(finalXlsxPath);
			workBook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 判断Excel的版本,获取Workbook
	 * 
	 * @param in
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static Workbook getWorkbok(File file) throws IOException {
		Workbook wb = null;
		FileInputStream in = new FileInputStream(file);
		if (file.getName().endsWith(EXCEL_XLS)) { // Excel 2003
			wb = new HSSFWorkbook(in);
		} else if (file.getName().endsWith(EXCEL_XLSX)) { // Excel 2007/2010
			wb = new XSSFWorkbook(in);
		}
		return wb;
	}
	
	
	public static void main(String[] args) {
		System.out.println("(x+2)^2");
	}
}
