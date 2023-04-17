package com.doj.server.infrastructure.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 类描述：短ID生成工具
 *
 * @author kongweikun <kongweikun@baidu.com>
 * @date 2023/4/14
 */
public class ShortIDUtil {

    /**
     * 短id位数
     */
    private static final Integer DIGIT = 8;

    /**
     * 算法ID前缀
     */
    private static final String ALGORITHM_SHORT_ID_PREFIX = "algorithm-";

    /**
     * 讨论帖子D前缀
     */
    private static final String DISCUSSION_SHORT_ID_PREFIX = "discussion-";


    /**
     * 算法短ID生成器
     * @return
     */
    public static String generateAlgorithmShortID() {
        return ALGORITHM_SHORT_ID_PREFIX + RandomStringUtils.randomAlphanumeric(DIGIT);
    }


    /**
     * 讨论帖子短ID生成器
     * @return
     */
    public static String generateDiscussionShortID() {
        return DISCUSSION_SHORT_ID_PREFIX + RandomStringUtils.randomAlphanumeric(DIGIT);
    }

}
