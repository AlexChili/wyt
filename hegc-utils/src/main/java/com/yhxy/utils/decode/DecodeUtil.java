package com.yhxy.utils.decode;

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

public class DecodeUtil {
	
	public static void main(String[] args) throws Exception {
		//加密解密aes128
//        String k = "8D0606841FA8A824C60E223C57EA5C27"; // 128 bit key
//        String initVector = "1234567890abcdef"; // 16 bytes IV
//        String en = encrypt(k, initVector, "密文内容jofsjfoa就都几个个奥机房附近哦分解机将房屋交付");
//        System.out.println(decrypt(k, initVector, en));
		
		
	
//		String code="071bQAuh0WxK1z1prnth0nbLuh0bQAuX";
		String iv="fg7LmdjrLfy4eIHlmueHyw==";
		String data="QZfn4xXUel+5/QBPB5oG3OlodxZVabGDIzhdPj70/77J/c5dg+eDT4nwrK3hZyk9SB/99ilU2YLX0J1BMFUODShhW13TjAujArebZ3F8VSbhUhtEOfyWemiPLDv47baElvWAxUnA079UYAg+jYVCl7ahz1rvI4BMaFyEhDv/Hp5c1A7V3AnZQSRjqkwcuKLOedtTuSr2nV1CEi8krrw81P2qTVo2DGnQrT9u+qRo5mLOoPzC+3qryrF1vg/ZgOkO2q1RN0oy90/4hPfmNAw6q3SaKj0+CvEXz4rB4atxGRv93TMyPVYhyFTa/CH6OtoPISlIMR0gj2Lr9nM3U/T/CwFIMJub1r222jq7zhXOZnO1DqLhGLItY0Ck9et89hkTgAuUsfK7G9OwtrHB6W396N6TgSSYNyiY68F5yyy1X8XUFF8m5lkqQsq5HkoMYkwy7shFsnIQslCRozKlJOTOn81euRjq4q6Rh1bgBUbxSbAkVSgDT9xLH8Xxp7OVeayn";
		
//		String params = "appid=wxf69052bc6cb5a0a4&secret=041026db8f0a22d74406dd86902be298&js_code="+code+"&grant_type=authorization_code"; 
//		String sr = HttpRequestUtil.sendGet("https://api.weixin.qq.com/sns/jscode2session", params); 
//		System.out.println(sr);
//		String sessionkey=sr.substring(sr.indexOf("session_key")+14, sr.indexOf("expires_in")-3);
//		System.out.println("sessionkey：   "+sessionkey);// Ff3x7YlHnsNtmZHOhJkmvA==
		
		byte[] key=Base64.decodeBase64("dnFTyPv0MafCE8b0as\\/yog==");
		String aeskey=byte2HexStr(key);
		System.out.println("key:"+aeskey);
		
		String aesiv=byte2HexStr(Base64.decodeBase64(iv));
		System.out.println("i   v:"+aesiv);
		
		byte[] r=new DecodeUtil().decrypt(Base64.decodeBase64(data),key,Base64.decodeBase64(iv));
	   System.out.println(new String(r));
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
    
    public static final DecodeUtil instance = new DecodeUtil();
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
