package com.jiang.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>作者: 沈晨辉
 * <p>邮箱: shenchenhui@vaneqi.com
 * <p>日期: 2016年11月18日
 * <p>描述: 正则表达验证公用类
 */
public class RegexUtil {
    /**
     * 是否是数字
     *
     * @param bit 小数位数
     */
    public static boolean isNumber(String src, int bit) {
        // 判断小数点后2位的数字的正则表达式
        Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0," + bit + "})?$");
        Matcher match = pattern.matcher(src);

        if (match.matches() == false) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    public static boolean isChinaPhoneLegal(String str) {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 是否是合法的电话号码
     */
    public static boolean isTelLegal(String str) {
        String regExp = "(^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$)|(^0{0,1}1[0-9]{10}$)";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 是否是合法的电子邮箱
     */
    public static boolean isEmailLegal(String str) {
        String regExp = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 将string去掉内部空格
     *
     * @param src 原文本
     * @param chr 需要清除的字符
     */
    public static String stringDropSpace(String src, char[] chr) {
        StringBuilder ret = new StringBuilder();

        for (int i = 0; i < src.length(); i++) {
            char c = src.charAt(i);
            boolean flag = false;
            for (int j = 0; j < chr.length; j++) {
                if (chr[i] == c) {
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                ret.append(c);
            }
        }

        return ret.toString();
    }

    /**
     * 是否是合法的账号
     */
    public static boolean isAccountLegal(String str) {
        String regExp = "^[a-zA-Z]\\w{5,17}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();

    }

    /**
     * 是否是合法的密码
     */
    public static boolean isPassLegal(String str) {
        String regExp = "^(?![0-9]+$)(?![a-zA-Z]+$)(?!\\d+$)(?![\\W_]+$)\\S{5,20}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();

    }

    /**
     * 是否是合法的密码
     */
    public static boolean isPassLegalT(String str) {
        String regExp = "^[0-9a-zA-Z]\\S\\w{5,15}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();

    }
}
