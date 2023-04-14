package com.doj.server.infrastructure.utils;

import com.doj.server.infrastructure.config.thread.ThreadPoolConfig;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.doj.server.infrastructure.constant.SystemConstant.ASYNC_CALL_BACK_TIME_OUT;

/**
 * 类描述：异步任务工具类
 *
 * @author kongweikun <kongweikun@baidu.com>
 * @date 2023/4/14
 */
@Slf4j
public class FutureTaskUtil {

    /**
     * 生成异步任务, 并获取结果信息
     *
     * @param supplier
     * @param <T>
     * @return
     */
    private static <T> T doGenerateTaskAndCallBackResult(Supplier<T> supplier, int timeout) {
        Executor executor = ThreadPoolConfig.getThreadPool();
        ThreadPoolConfig.printThreadPoolExecutorLogger();
        List<CompletableFuture<T>> completableFutureList = Lists.newArrayList();
        completableFutureList.add(CompletableFuture.supplyAsync(supplier, executor));
        T res = null;
        for (CompletableFuture<T> task : completableFutureList) {
            try {
                res = (T) task.get(timeout, TimeUnit.SECONDS);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        ThreadPoolConfig.printThreadPoolExecutorLogger();
        return res;
    }

    /**
     * 生成多个异步任务, 通过map的consumer处理supplier查询的信息
     *
     * @param map
     * @return
     */
    private static void doGenerateMultiTaskAndConsume(Map<Supplier, Consumer> map) {
        Executor executor = ThreadPoolConfig.getThreadPool();
        List<CompletableFuture<Void>> completableFutureList = Lists.newArrayList();
        // 从map中取出函数对应关系, 生成异步任务
        ThreadPoolConfig.printThreadPoolExecutorLogger();
        map.forEach((supplier, consumer) -> {
            CompletableFuture<Void> task = CompletableFuture.supplyAsync(supplier, executor).thenAccept(consumer);
            completableFutureList.add(task);
        });
        ThreadPoolConfig.printThreadPoolExecutorLogger();
        CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[0])).join();
    }

    /**
     * 生成多个异步任务, 无返回值类型
     *
     * @param map key一般为数据查询函数, value为数据处理函数
     */
    public static void generateMultiTaskAndConsume(Map<Supplier, Consumer> map) {
        doGenerateMultiTaskAndConsume(map);
    }

    /**
     * 默认超时的异步任务
     *
     * @param supplier
     * @param <T>
     * @return
     */
    public static <T> T generateTaskAndCallBackResult(Supplier<T> supplier) {
        return doGenerateTaskAndCallBackResult(supplier, ASYNC_CALL_BACK_TIME_OUT);
    }

    /**
     * 自定义超时的异步任务
     *
     * @param supplier
     * @param timeout
     * @param <T>
     * @return
     */
    public static <T> T generateTaskAndCallBackResult(Supplier<T> supplier, int timeout) {
        return doGenerateTaskAndCallBackResult(supplier, timeout);
    }
}
