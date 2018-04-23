package com.yhxy.utils;
import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.simple.JSONObject;


@SuppressWarnings("deprecation")
public class SendMsg {
	
    public static String URL = "http://api01.monyun.cn:7901/sms/v2/std/single_send";
    //测试
	public static void main(String[] args) throws Throwable {
		new SendMsg().sendmsg("15710077317","您的验证码是543253，在1分钟内输入有效。如非本人操作请忽略此短信。");
		//new SendMsg().signTeacherInfo("17319333787","您已成功报名微课大赛，您的登录账号为： 123 密码：123");
	}
	
	/* 发送 */
	@SuppressWarnings("unchecked")
	public void sendmsg(String mobile,String content) throws UnsupportedEncodingException {
		JSONObject jsobj1 = new JSONObject();
		jsobj1.put("apikey", "60b5973ecceaf93026af41260bd7286c");//需要替换自己的发送账号的apikey 
		jsobj1.put("mobile", mobile);
		jsobj1.put("content",URLEncoder.encode(content,"GBK"));//短信内容需要进行 根据GBK编码方式的urlencode 一下
		post(jsobj1);
	}
	
	/** 教师短信通知
	 * @throws Throwable */
	public static void sendTeacherMsg(String mobile,String name,String adminName,String account,String secret) throws Throwable
	{
		String content="尊敬的老师：您的佳讯翻转课堂平台账号已由学校管理员为您创建成功。登录账号为:"+account+" 初始密码："+secret+" 。为了您的账号安全，请及时登录https://www.jiaxunweike.com 修改密码。【佳讯翻转课堂平台】";
		new SendMsg().sendmsg(mobile,content);
	}
	
	  /** 向学员发送初始短信 
	 * @throws Throwable */
	  public static void sendStudentMsg(String mobile,String name,String account,String secret) throws Throwable
	  {
		  String content ="亲爱的同学：您的佳讯翻转课堂平台账号已由系统为您创建成功。登录账号为： "+account+" 初始密码："+secret+" 【佳讯翻转课堂平台】";
		  new SendMsg().sendmsg(mobile,content);
	  }
	  	  
	  /** 报名系统--老师忘记密码
	   * @throws Throwable */
	  public static void signTeacherSendPwd(String mobile,String code) throws Throwable
	  {
		  String content ="您的验证码为:"+code+",您正在进行重置密码操作,请勿将验证码告诉其他人。";
		  new SendMsg().sendmsg(mobile,content);
	  }
	  
	  /** 报名系统--注册提示
	   * @throws Throwable */
	  public static void signTeacherInfo(String mobile,String password) throws Throwable
	  {
		  String content ="您已成功报名微课大赛，您的登录账号为："+mobile+"密码："+password;
		  new SendMsg().sendmsg(mobile,content);
	  }
	  /** 报名系统--修改密码提示
	   * @throws Throwable */
	  public static void signTeacherSendNewPwd(String mobile, String password) throws Throwable {
		  String content ="您已重置密码，您的新密码为："+password;
		  new SendMsg().sendmsg(mobile,content);
		}
	  /** 小程序--重置密码 验证码
	   * @throws Throwable */
	  public static void signSendVcode(String mobile, String code) throws Throwable {
		  String content ="您正在重置密码，验证码为： "+code;
		  new SendMsg().sendmsg(mobile,content);
		}
	
	/**
	 * 短信内容
	 * 2017年6月12日 下午2:31:56
	 */
	  public static String mess(String paramString)
	  {
	    return "您的验证码为 "+paramString+" 请于60秒内完成注册";
	  }

		/* 梦网云短信接口平台发送  */
	    @SuppressWarnings("resource")
		public static String post(JSONObject json) {
	        HttpClient client = new DefaultHttpClient();
	        HttpPost post = new HttpPost(URL);
	        post.setHeader("Content-Type", "application/json");
	        post.addHeader("Authorization", "Basic YWRtaW46");
	        String result = "";
	        try {
	            StringEntity s = new StringEntity(json.toString(), "utf-8");
	            s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
	            post.setEntity(s);
	            // 发送请求
	            HttpResponse httpResponse = client.execute(post);
	            // 获取响应输入流
	            InputStream inStream = httpResponse.getEntity().getContent();
	            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
	            StringBuilder strber = new StringBuilder();
	            String line = null;
	            while ((line = reader.readLine()) != null)
	                strber.append(line + "\n");
	            inStream.close();
	            result = strber.toString();
	            System.out.println(result);
	            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	                    System.out.println("请求服务器成功，做相应处理");
	            } else {
	                System.out.println("请求服务端失败");
	            }
	        } catch (Exception e) {
	            System.out.println("请求异常");
	            throw new RuntimeException(e);
	        }
	        return result;
	    }
	    
	    /**
	     * C端 发送消息
	     * @param mobile
	     * @param code 
	     * @param mbid
	     * @throws UnsupportedEncodingException 
	     */
		public void sendMes(String mobile, String code, int num) throws UnsupportedEncodingException {
			String content ="";
			switch(num) {
			case 1:
				content="您的验证码为"+code+"请于60秒内完成注册。";
				break;
			case 2:
				content="";
				break;
			case 3:
				content="";
				break;
			case 4:
				content="";
				break;
			}
			 new SendMsg().sendmsg(mobile,content);
		}
	
}
