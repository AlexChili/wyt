package com.yhxy.utils;

/**
 * 文件类型获取工具类
 * @author kongxiangming
 *
 */
public class FileTypeUtils {
	
	/**
	 * 图片格式
	 */
	public static String IMAGE_TYPE = "bmp,jpg,tiff,gif,pcx,tga,exif,fpx,svg,psd,cdr,pcd,dxf,ufo,eps,ai,raw";
	/**
	 * 视频格式
	 */
	public static String VIDEO_TYPE = "mp4,avi,3gp,rmvb,wmv,mkv,mpg,vob,mov,flv";
	/**
	 * 音频格式
	 */
	public static String AUDIO_TYPE = "cd,ogg,mp3,asf,wma,wav,mp3pro,rm,real,ape,module,midi,vqf";
	/**
	 * 文档格式
	 */
	public static String DOCUMENT_TYPE = "txt,pdf,doc,xls,ppt,csv,vsd,docx,xlsx,pptx,csvx,vsdx";
	/**
	 * 压缩文件
	 */
	public static String RAR_TYPE = "rar,zip,tar,7Z";
	
	/**
	 * 通过文件后缀获取文件类型
	 * @param fileSuffix
	 * @return 【1：图片；2：视频；3：音频；4：文档；5：压缩文件；0：其它】
	 */
	public static String getFileTypeBySuffix(String fileSuffix) {
		String fileType = "0";
		fileSuffix = fileSuffix.trim().toLowerCase();
		if(IMAGE_TYPE.indexOf(fileSuffix)>=0) {	//图片类型
			fileType = "1";
		}else if(VIDEO_TYPE.indexOf(fileSuffix)>=0) {
			fileType = "2";
		}else if(AUDIO_TYPE.indexOf(fileSuffix)>=0) {
			fileType = "3";
		}else if(DOCUMENT_TYPE.indexOf(fileSuffix)>=0) {
			fileType = "4";
		}else if(RAR_TYPE.indexOf(fileSuffix)>=0) {
			fileType = "5";
		}
		return fileType;
	}
	
	

}
