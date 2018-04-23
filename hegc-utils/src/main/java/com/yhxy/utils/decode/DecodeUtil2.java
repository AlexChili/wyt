package com.yhxy.utils.decode;

import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class DecodeUtil2 {
	
	public static void main(String[] args) throws Exception {
		//加密解密aes128
        String key = "1234567890123456"; // 128 bit key
        byte[] k=Base64.decodeBase64("hlNG6dWNgJ0BiQGiILwJjg==");
        
        String initVector =byte2HexStr(k);
        System.out.println("iv--    "+initVector);
        String data= "内容内容内容内容内容内容";
        
        String en = encrypt(key, "865346E9D58D809D865346E9D58D809D",data);
        System.out.println(en);
        System.out.println(decrypt(key, "865346E9D58D809D865346E9D58D809D", en));
		
		
		
//		String code="0710bvPu1YlLpd0WO6Qu1OwMPu10bvPF";
//		String iv="hlNG6dWNgJ0BiQGiILwJjg==";
		//String data="B6jCNgk%2F3KzbWD3vsuPTo5O1W02Okz9vp4KqDLRdv1R3XJyJlkecZTvErqqCWkD3NMgfu2NBL6CIAngV6KaRO4g9fsD5Z4vuVn0ZKoZZa8GyivcPM9GbkcxU6QdxgjWB2lYTTHVRljQRrEsTtzcIKGJnNW0nKeQFJI%2FaFmV8ctvlcsmLhT%2Fw%2BoVMWYaAKMTlYv7vSX4PBdzdh8DQUd6z%2FRWuwZ4%2FxIX6w5P2mkRAJ6K93iq01okQBkbq5t%2FjiqGjk0b8cdtRTCyyg%2Bzp3cXTO0zMJ74t908i04ZT3f%2BTgn0sOLQ7WpzYko35mT3hKDVIaxpYeV9N4F%2BFd0YwSZl7wj5oDmUazQWm8%2BV82OgkyuhlFHEtTBbOeKryDQM%2BX9CrsRtsVsCXmih%2FhjHYFnZGxljbO%2BLBpnmKxbU%2FQ52461yck8%2BA%2B%2F46sWU0A%2BcTw1vxEFyRkTr19KQpwXQQqZILk47Ro%2B1WqUgfjmOm4AS3uvTNW0wYntUP9o41wNPRGk7e";
		
//		String params = "appid=wxf69052bc6cb5a0a4&secret=041026db8f0a22d74406dd86902be298&js_code="+code+"&grant_type=authorization_code"; 
		//String sr = HttpRequestUtil.sendGet("https://api.weixin.qq.com/sns/jscode2session", params); 
		//System.out.println(sr);
		//String sessionkey=sr.substring(sr.indexOf("session_key")+14, sr.indexOf("expires_in")-3);
		//System.out.println(sessionkey);// lOCpWGNxfXzsvIM31B2Wpw==
		
		//byte[] key=Base64.decodeBase64(sessionkey);
//		byte[] key=Base64.decodeBase64("lOCpWGNxfXzsvIM31B2Wpw==");
//		String aeskey=byte2HexStr(key);
		//System.out.println(aeskey);
		
		//String aesiv=byte2HexStr(Base64.decodeBase64(iv));
		//System.out.println(aesiv);
		//System.out.println(byte2HexStr(Base64.decodeBase64(data)));
		
		
		//String en = encrypt(aeskey, aesiv, data);
		//String  r=decrypt(aeskey,aesiv,en);
		
		//System.out.println(r);
		
		//
		//new DecodeUtil2().decrypt(Base64.decodeBase64(data),Base64.decodeBase64("lOCpWGNxfXzsvIM31B2Wpw=="),Base64.decodeBase64("hlNG6dWNgJ0BiQGiILwJjg=="));
	}
	
	public static String binary(byte[] bytes, int radix){
		return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
	}
	
	//aes加密
    public static String encrypt(String key, String initVector, String value) {
        try {
//            System.out.println("key:\t" + Arrays.toString(key.getBytes("UTF-8")));
//            System.out.println("iv:\t" + Arrays.toString(initVector.getBytes("UTF-8")));
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            //Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            System.out.println(Arrays.toString(encrypted));
            //System.out.println("encrypted string: "
            //        + Base64.encodeBase64String(encrypted));

            return byte2HexStr(encrypted);
            //return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
    // aes解密
    public static String decrypt(String key, String initVector, String encrypted) {
        try {
//            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
        	IvParameterSpec iv = new IvParameterSpec(initVector.getBytes());
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            //byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
            byte[] original = cipher.doFinal(hexStr2Bytes(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
    /**
     * 解密 2
     * @param content
     * @param keyByte
     * @param ivByte
     * @return
     * @throws InvalidAlgorithmParameterException
     */
    public byte[] decrypt(byte[] content, byte[] keyByte, byte[] ivByte) throws InvalidAlgorithmParameterException {
        initialize();
        try {
          Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
          Key sKeySpec = new SecretKeySpec(keyByte, "AES");
     
          cipher.init(Cipher.DECRYPT_MODE, sKeySpec, generateIV(ivByte));// 初始化
          byte[] result = cipher.doFinal(content);
          return result;
        } catch (NoSuchAlgorithmException e) {
          e.printStackTrace();
        } catch (NoSuchPaddingException e) {
          e.printStackTrace();
        } catch (InvalidKeyException e) {
          e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
          e.printStackTrace();
        } catch (BadPaddingException e) {
          e.printStackTrace();
        } catch (NoSuchProviderException e) {
          e.printStackTrace();
        } catch (Exception e) {
          e.printStackTrace();
        }
        return null;
      }
    
    public static final DecodeUtil2 instance = new DecodeUtil2();
    public static boolean initialized = false;
    
    public static void initialize(){
        if (initialized) return;
        Security.addProvider(new BouncyCastleProvider());
        initialized = true;
      }
    //生成iv
    public static AlgorithmParameters generateIV(byte[] iv) throws Exception{
      AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
      params.init(new IvParameterSpec(iv));
      return params;
    }
    
    
    /**
    *
    * 十六进制转换字符串
    */
   public static byte[] hexStr2Bytes(String hexStr) {
       System.out.println("in len :" + hexStr.length());
       String str = "0123456789ABCDEF";
       char[] hexs = hexStr.toCharArray();
       byte[] bytes = new byte[hexStr.length() / 2];
       int n;
       for (int i = 0; i < bytes.length; i++) {
           n = str.indexOf(hexs[2 * i]) * 16;
           n += str.indexOf(hexs[2 * i + 1]);
           bytes[i] = (byte) (n & 0xff);
       }
       System.out.println("out len :" + bytes.length);
       System.out.println("ddd" + Arrays.toString(bytes));
       return bytes;
   }
   
   /**
    * bytes转换成十六进制字符串
    */
   public static String byte2HexStr(byte[] b) {
       String hs = "";
       String stmp = "";
       for (int n = 0; n < b.length; n++) {
           stmp = (Integer.toHexString(b[n] & 0XFF));
           if (stmp.length() == 1)
               hs = hs + "0" + stmp;
           else
               hs = hs + stmp;
           // if (n<b.length-1) hs=hs+":";
       }
       return hs.toUpperCase();
   }
   
   /**
    * 字符串转换成十六进制字符串
    */
   public static String str2HexStr(String str) {

       char[] chars = "0123456789ABCDEF".toCharArray();
       StringBuilder sb = new StringBuilder("");
       byte[] bs = str.getBytes();
       int bit;
       for (int i = 0; i < bs.length; i++) {
           bit = (bs[i] & 0x0f0) >> 4;
           sb.append(chars[bit]);
           bit = bs[i] & 0x0f;
           sb.append(chars[bit]);
       }
       return sb.toString();
   }

}
