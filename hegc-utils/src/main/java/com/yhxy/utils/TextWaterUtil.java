package com.yhxy.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 给图片添加文字水印
 * @author hegc
 *
 */
public class TextWaterUtil {//extends Thread{
	
	//初始化参数
//	private String imgurl; 
//	private String name; 
//	public TextWaterUtil(String imgurl,String name) 
//	{ 
//		this.imgurl = imgurl; 
//		this.name=name;
//	} 
//	
//	//线程方法
//	public void run() 
//	{ 
////		PressTitleByStringPath(imgurl,name);
//	} 
	
		/**图片格式：JPG*/
	    private static final String PICTRUE_FORMATE_JPG = "jpg";
	    
	    private static String ziti="微软雅黑";
	    private static int sty=Font.BOLD;//字体样式
	    private static int fontsize = 125; // 字体大小
	    private static Color cr=Color.WHITE;//字体颜色
	    private static Float tm=0.6f;  //蒙板透明度
	    private static Float fonttm=0.9f;//文字透明度
	    
	    /**   调用测试  */
	    public static void main(String[] args) throws IOException {
	    	//1.
//		PressTitleByStringPath("C://333.jpg", "数学微课");
	    	
	    	//2.
	    	//File f=new File("C://Users//樵夫//Pictures//img//8.jpg","永恒之印");
	    	//PressTitleByFile(f,"牛人微课");
	    }
	    
	    /** 给图片添加标题(一) */
	    public static void PressTitleByStringPath(String imgPath,String waterImgPath,String text) {
//		 String wi=TextWaterUtil.class.getResource("water.png").toString().substring(6);
//		String wi = "E://water.png";
		pressImage(imgPath, waterImgPath, -1, -1, tm);
		pressText(imgPath, text, ziti, sty, fontsize, cr, -1, -1, fonttm);
	    }
	    
	    /** 给图片添加标题(二) */
	    public static void PressTitleByFile(File file,String text) {
	    		String wi=TextWaterUtil.class.getResource("water.png").toString().substring(6);
		    	  pressImage2(file,wi, -1, -1, tm);
		          pressText2(file, text, ziti, sty, fontsize, cr, -1, -1, fonttm);
	    }
	    
	    /**
	     * 添加图片水印 1
	     * @param targetImg 目标图片路径，如：C://myPictrue//1.jpg
	     * @param waterImg 水印图片路径，如：C://myPictrue//logo.png
	     * @param x 水印图片距离目标图片左侧的偏移量，如果x<0, 则在正中间
	     * @param y 水印图片距离目标图片上侧的偏移量，如果y<0, 则在正中间
	     * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
	     */
	    public final static void pressImage(String targetImg, String waterImg, int x, int y, float alpha) {
	            try {
	                File file = new File(targetImg);
	                
	                Image image = ImageIO.read(file);
	                int width = image.getWidth(null);
	                int height = image.getHeight(null);
	                BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	                Graphics2D g = bufferedImage.createGraphics();
	                g.drawImage(image, 0, 0, width, height, null);
	             
	                Image waterImage = ImageIO.read(new File(waterImg));    // 水印文件
	                int width_1 = waterImage.getWidth(null);
	                int height_1 = waterImage.getHeight(null);
	                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
	                 
	                int widthDiff = width - width_1;
	                int heightDiff = height - height_1;
	                if(x < 0){
	                    x = widthDiff / 2;
	                }else if(x > widthDiff){
	                    x = widthDiff;
	                }
	                if(y < 0){
	                    y = heightDiff / 2;
	                }else if(y > heightDiff){
	                    y = heightDiff;
	                }
	                g.drawImage(waterImage, x, y, width_1, height_1, null); // 水印文件结束
	                g.dispose();
	                ImageIO.write(bufferedImage, PICTRUE_FORMATE_JPG, file);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	    }
	    
	     /**
	     * 添加文字水印 1
	     * @param targetImg 目标图片路径，如：C://myPictrue//1.jpg
	     * @param pressText 水印文字， 如：中国证券网
	     * @param fontName 字体名称，    如：宋体
	     * @param fontStyle 字体样式，如：粗体和斜体(Font.BOLD|Font.ITALIC)
	     * @param fontSize 字体大小，单位为像素
	     * @param color 字体颜色
	     * @param x 水印文字距离目标图片左侧的偏移量，如果x<0, 则在正中间
	     * @param y 水印文字距离目标图片上侧的偏移量，如果y<0, 则在正中间
	     * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
	     */
		public static void pressText(String targetImg, String pressText, String fontName, int fontStyle, int fontSize, Color color, int x, int y, float alpha) {
			try {
						File file = new File(targetImg);
						Image image = ImageIO.read(file);
						int width = image.getWidth(null);
						int height = image.getHeight(null);
						BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
						Graphics2D g = bufferedImage.createGraphics();
						g.drawImage(image, 0, 0, width, height, null);
						g.setFont(new Font(fontName, fontStyle, fontSize));
						g.setColor(color);
						g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
						int width_1 = fontSize * getLength(pressText);
						int height_1 = fontSize;
						int widthDiff = width - width_1;
						int heightDiff = height - height_1;
						if(x < 0){
							x = widthDiff / 2;
						}else if(x > widthDiff){
							x = widthDiff;
						}
						if(y < 0){
							y = heightDiff / 2;
						}else if(y > heightDiff){
							y = heightDiff;
						}
						g.drawString(pressText, x, y + height_1);
						g.dispose();
						ImageIO.write(bufferedImage, PICTRUE_FORMATE_JPG, file);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		
	    
	    /**
	     * 添加图片水印 2
	     * @param file 目标图片
	     * @param waterImg 水印图片路径，如：C://myPictrue//logo.png
	     * @param x 水印图片距离目标图片左侧的偏移量，如果x<0, 则在正中间
	     * @param y 水印图片距离目标图片上侧的偏移量，如果y<0, 则在正中间
	     * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
	     */
	    public final static void pressImage2(File file, String waterImg, int x, int y, float alpha) {
	            try {
	                Image image = ImageIO.read(file);
	                int width = image.getWidth(null);
	                int height = image.getHeight(null);
	                BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	                Graphics2D g = bufferedImage.createGraphics();
	                g.drawImage(image, 0, 0, width, height, null);
	             
	                Image waterImage = ImageIO.read(new File(waterImg));    // 水印文件
	                int width_1 = waterImage.getWidth(null);
	                int height_1 = waterImage.getHeight(null);
	                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
	                 
	                int widthDiff = width - width_1;
	                int heightDiff = height - height_1;
	                if(x < 0){
	                    x = widthDiff / 2;
	                }else if(x > widthDiff){
	                    x = widthDiff;
	                }
	                if(y < 0){
	                    y = heightDiff / 2;
	                }else if(y > heightDiff){
	                    y = heightDiff;
	                }
	                g.drawImage(waterImage, x, y, width_1, height_1, null); // 水印文件结束
	                g.dispose();
	                ImageIO.write(bufferedImage, PICTRUE_FORMATE_JPG, file);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	    }
	    
	     /**
	     * 添加文字水印 2
	     * @param file 目标图片
	     * @param pressText 水印文字， 如：中国证券网
	     * @param fontName 字体名称，    如：宋体
	     * @param fontStyle 字体样式，如：粗体和斜体(Font.BOLD|Font.ITALIC)
	     * @param fontSize 字体大小，单位为像素
	     * @param color 字体颜色
	     * @param x 水印文字距离目标图片左侧的偏移量，如果x<0, 则在正中间
	     * @param y 水印文字距离目标图片上侧的偏移量，如果y<0, 则在正中间
	     * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
	     */
		public static void pressText2(File file, String pressText, String fontName, int fontStyle, int fontSize, Color color, int x, int y, float alpha) {
			try {
						Image image = ImageIO.read(file);
						int width = image.getWidth(null);
						int height = image.getHeight(null);
						BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
						Graphics2D g = bufferedImage.createGraphics();
						g.drawImage(image, 0, 0, width, height, null);
						g.setFont(new Font(fontName, fontStyle, fontSize));
						g.setColor(color);
						g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
						int width_1 = fontSize * getLength(pressText);
						int height_1 = fontSize;
						int widthDiff = width - width_1;
						int heightDiff = height - height_1;
						if(x < 0){
							x = widthDiff / 2;
						}else if(x > widthDiff){
							x = widthDiff;
						}
						if(y < 0){
							y = heightDiff / 2;
						}else if(y > heightDiff){
							y = heightDiff;
						}
						g.drawString(pressText, x, y + height_1);
						g.dispose();
						ImageIO.write(bufferedImage, PICTRUE_FORMATE_JPG, file);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		
	    /**
	     * 获取字符长度，一个汉字作为 1 个字符, 一个英文字母作为 0.5 个字符
	     * @param text
	     * @return 字符长度，如：text="中国",返回 2；text="test",返回 2；text="中国ABC",返回 4.
	     */
	    public static int getLength(String text) {
	        int textLength = text.length();
	        int length = textLength;
	        for (int i = 0; i < textLength; i++) {
	            if (String.valueOf(text.charAt(i)).getBytes().length > 1) {
	                length++;
	            }
	        }
	        return (length % 2 == 0) ? length / 2 : length / 2 + 1;
	    }
	    

	    
}
