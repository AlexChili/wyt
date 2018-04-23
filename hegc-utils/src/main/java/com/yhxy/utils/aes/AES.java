package com.yhxy.utils.aes;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.yhxy.utils.decode.DecodeUtil;

import javax.crypto.BadPaddingException;  
import javax.crypto.Cipher;  
import javax.crypto.IllegalBlockSizeException;  
import javax.crypto.NoSuchPaddingException;  
import javax.crypto.spec.IvParameterSpec;  
import javax.crypto.spec.SecretKeySpec;  
import java.security.*;  
public class AES {  
    public static boolean initialized = false;  
    /** 
     * AES解密 
     * @param content 密文 
     * @return 
     * @throws InvalidAlgorithmParameterException 
     * @throws NoSuchProviderException 
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
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return null;  
    }  
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
    
    public static void main(String[] args) throws InvalidAlgorithmParameterException {
		/*String data="3qdECj8E9aB5wMj1GkYs5Qx%2Bxdif01oMswdEqdKbxdbh2ybzC46wd2IJISYagGTkfMf9cKlJBO9dF324kL0aBqDDANO2J32afo5ZtEIh9mVqtOUaVJIcvxLKZu88iUs3fsn6BJNbL9vUCsp8qBpFwVl6uHXZdCkSssbwc36Za%2B68YZA6jSH8sEl6rN2IjDrn74l3Fg9HGla9wa6csX9W7zSxNjvZviX9B2jMzAvy%2Bgmj7374R1l4D4aMDR8KZGNhbuwny8OhWkZiQOJlO7hBazR1z91HKKlynHckl4S3%2BvC9YKG5J2tC4pAjDioLa78MCqhvahwat0YTYjc7%2BLzqoKnv6CzIIvejt02OilNw4wAVUEd7fKcZS3N3%2BZoyiM1vHjRkvQJWxAsuK9pn2F%2FLMXpnOCAKaRWE%2BXj1ZhNb0N69vkfYyNQBu9SgPoB9EKFsqyfwLjLm3auLm0Be9R1KqlAu9GwGuO88aEuZvdMIcN0EqhIgzuTnyUNZ61yJtlfm";
		String iv="B+me5rUd4mIY3aWqXbxUkg==";
		String sessionkey="otjUq6M15im4mNQPCMT67g==";
		
        AES aes = new AES();  
        byte[] resultByte = aes.decrypt(Base64.decodeBase64(data), Base64.decodeBase64(sessionkey),Base64.decodeBase64(iv));  
        if(null != resultByte && resultByte.length > 0){  
            String userInfo = new String(WxPKCS7Encoder.decode(resultByte));  
            System.out.println(userInfo);
        }  */
    	
    	String iv="KbbTQOc30RgaMs5fkeh9xA==";
    	byte[] ivbyte=Base64.decodeBase64(iv);
    	String ivstring=DecodeUtil.byte2HexStr(ivbyte);
    	
    	System.out.println(ivstring);
    }
    
    

    }