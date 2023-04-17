package com.doj.server.infrastructure.function;

import com.doj.server.infrastructure.utils.EmptyUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 类描述：公共处理函数
 *
 * @author kongweikun <kongweikun@baidu.com>
 * @date 2023/4/14
 */
public class CommonFunction {

    /**
     * 根据key排重
     *
     * @param keyExtractor
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> EmptyUtil.isEmpty(seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE));
    }
}
