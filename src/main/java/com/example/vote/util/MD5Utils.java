package com.example.vote.util;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author white matter
 */
public class MD5Utils {
    /**
     * 对输入字符串进行 MD5
     * @param src
     * @return
     */
    public static String md5(String src){
        return DigestUtils.md2Hex(src);
    }
    /**
     * 固定的盐
     */
    private static final String salt = "1a2b3c4d";

    /**
     * 使用固定的盐对给定字符串进行 md5加密
     * ---可以模拟前端md5后的值
     * @param inputPass
     * @return
     */
    public static String inputPassToFormPass(String inputPass){
        String str = "" + salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    /**
     * 第二步加密
     * 使用salt 给字符串md5加密
     * @param formPass 字符串（应该是加密后的md5）
     * @param salt     随机的盐
     * @return
     */
    public static String formPassToDBPass(String formPass,String salt){
        String str = ""+salt.charAt(0)+salt.charAt(2)+formPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    /**
     * 对给定的字符串进行两次md5
     * @param inputPass 待加密的字符串
     * @param saltDB    记录到db的，随机的盐
     * @return          两次md5之后 可以写到数据库中的密码
     */
    public static String inputPassToDbPass(String inputPass,String saltDB){
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass,saltDB);
        return dbPass;
    }

    public static void main(String[] args) {
        System.out.println(inputPassToFormPass("123456"));
        System.out.println(formPassToDBPass(inputPassToFormPass("123456"), "1a2b3c4d"));
        //68156ba662e422b83001bdc017eb83bc
        System.out.println(inputPassToDbPass("123456", "1a2b3c4d"));
    }
}

