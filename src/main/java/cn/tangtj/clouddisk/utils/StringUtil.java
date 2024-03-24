package cn.tangtj.clouddisk.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class StringUtil {

    public static String str2md5(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            byte[] inputByteArray = str.getBytes();
            //inputByteArray是输入字符串转换得到的字节数组
            messageDigest.update(inputByteArray);
            //转换并返回结果，也是字节数组，包含16个元素
            byte[] resultByteArray = messageDigest.digest();
            //字符数组转换成字符串返回
            return byteArrayToHex(resultByteArray);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //输入的字符串转换成字节数组
        return null;
    }

    public static String byteArrayToHex(byte[] byteArray) {
        //首先初始化一个字符数组，用来存放每个16进制字符
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        //new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符）
        char[] resultCharArray = new char[byteArray.length * 2];
        //遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }

        //字符数组组合成字符串返回
        return new String(resultCharArray);
    }

    public static String createRandStr(int length){
        String allChar = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        Random rd = new Random();
        StringBuilder shareCode = new StringBuilder();
        int allCharLength = allChar.length();
        for (int i = 0; i < length; i++) {
            shareCode.append(allChar.charAt(rd.nextInt(allCharLength)));
        }
        return shareCode.toString();
    }
}
