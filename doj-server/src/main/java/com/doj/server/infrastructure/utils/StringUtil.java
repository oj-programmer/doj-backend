package com.doj.server.infrastructure.utils;

import com.doj.server.infrastructure.constant.SystemConstant;
import com.doj.server.infrastructure.enums.SymbolEnum;

import java.util.Arrays;
import java.util.Optional;

/**
 * 类描述：字符串工具类
 *
 * @author kongweikun <kongweikun@baidu.com>
 * @date 2023/4/14
 */
public class StringUtil {

    /**
     * 字符串连接
     *
     * @param args
     * @return
     */
    public static String concat(String... args) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(args).forEach(sb::append);
        return sb.toString();
    }


    /**
     * 字符串转换
     *
     * @param str
     * @return
     */
    public static String getOrDefaultStr(String str) {
        return Optional.ofNullable(str).orElse(SystemConstant.EMPTY_STR);
    }


    /**
     * 将前缀、标点、后缀拼接
     *
     * @param prefix
     * @param symbolEnum
     * @param suffix
     * @return
     */
    public static <T, S> String combineStr(T prefix, SymbolEnum symbolEnum, S suffix) {
        return prefix.toString() + symbolEnum.getValue() + suffix;
    }


}
