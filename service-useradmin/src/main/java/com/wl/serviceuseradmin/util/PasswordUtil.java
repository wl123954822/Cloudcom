package com.wl.serviceuseradmin.util;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 密码工具类，封装密码加密，解密
 */
public class PasswordUtil {

    // 16进制的字符数组
    private final static String[] hexDigits = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};


    /**
     * AES 加密
     * @param content
     * @param aseKey
     * @return
     */
    public static byte[] AESEncrypt(String content ,String aseKey) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128,new SecureRandom(aseKey.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat,"AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     * @param content
     * @param aeskey
     * @return
     */
    public static byte[] AESDecrypt(byte[] content ,String aeskey){
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128,new SecureRandom(aeskey.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat,"AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE,key);
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 将二进制 转为16进制
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0 ; i<buf.length;i++){
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1 ) {
                hex = '0' + hex;
            }
            stringBuffer.append(hex.toUpperCase());
        }
        return stringBuffer.toString();
    }

    /**
     * 16转二进制
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    //byte转16进制
    private static String byteArrayToHexString(byte[] bytes) {
         StringBuilder stringBuilder = new StringBuilder();
         for (byte tem : bytes) {
             stringBuilder.append(byteToHexString(tem));

        }
         return stringBuilder.toString();

    }
     //16进制转byte[]
    private static String byteToHexString(byte b) {
         int n = b;
         if (n < 0) {
             n = 256 + n;
        }
         int d1 = n / 16;
         int d2 = n % 16;
         return hexDigits[d1] + hexDigits[d2];
    }


    public static void main(String[]args){
        byte[] jm = PasswordUtil.AESEncrypt("test","test");
        System.out.println("加密"+jm);
        // 转16
        String hex = PasswordUtil.parseByte2HexStr(jm);
        System.out.println("加密16进制"+hex);


        byte[] jiemi = PasswordUtil.AESDecrypt(PasswordUtil.parseHexStr2Byte(hex),"test");
        System.out.println("解密" + jiemi);
        System.out.println("??"+new String(jiemi));
    }
}
