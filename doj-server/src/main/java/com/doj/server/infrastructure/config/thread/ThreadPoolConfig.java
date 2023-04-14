package com.doj.server.infrastructure.config.thread;

import com.alibaba.ttl.threadpool.TtlExecutors;
import com.doj.server.infrastructure.enums.SymbolEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import static com.google.common.primitives.UnsignedLongs.divide;

/**
 * 类描述：线程池配置类
 *
 * @author kongweikun <kongweikun@baidu.com>
 * @date 2023/4/14
 */
@Slf4j
@EnableAsync
@Configuration
public class ThreadPoolConfig {

    /**
     * cpu核心数量
     */
    private final int cpuNum = Runtime.getRuntime().availableProcessors();

    private final int queueCapacity = 100;

    private final int keepAliveTime = 10;

    private final String threadNamePrefix = "thread-pool-exec-";

    private static ThreadPoolTaskExecutor executor;

    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        log.info("threadPoolTaskExecutor begin initialization...");
        executor = new ThreadPoolTaskExecutor();
        // 配置核心线程池数量
        executor.setCorePoolSize(cpuNum);
        // 配置最大线程池数量
        executor.setMaxPoolSize(cpuNum << 1);
        // 线程池所使用的缓冲队列
        executor.setQueueCapacity(queueCapacity);
        // 空闲线程存活时间
        executor.setKeepAliveSeconds(keepAliveTime);
        // 线程池名称前缀
        executor.setThreadNamePrefix(threadNamePrefix);
        // 线程池对拒绝任务(无线程可用)的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 线程池初始化
        executor.initialize();
        log.info("threadPoolTaskExecutor finish initialization...");
        return TtlExecutors.getTtlExecutorService(executor.getThreadPoolExecutor());
    }

    /**
     * 获取线程池
     */
    public static Executor getThreadPool() {
        return ExecutorSingleton.threadPool;
    }

    /**
     * 静态内部类
     */
    private static class ExecutorSingleton {
        private static Executor threadPool = new ThreadPoolConfig().threadPoolTaskExecutor();
    }

    /**
     * 线程池日志输出
     */
    public static void printThreadPoolExecutorLogger() {
        ThreadPoolExecutor threadPool = executor.getThreadPoolExecutor();
        BlockingQueue<Runnable> queue = threadPool.getQueue();
        StringBuilder builder = new StringBuilder("线程池详情: ").append(SymbolEnum.OPEN_BRACE.getValue());
        builder.append(" 核心线程数: " + threadPool.getCorePoolSize()).append(SymbolEnum.SEMICOLON.getValue());
        builder.append(" 活动线程数: " + threadPool.getActiveCount()).append(SymbolEnum.SEMICOLON.getValue());
        builder.append(" 最大线程数: " + threadPool.getMaximumPoolSize()).append(SymbolEnum.SEMICOLON.getValue());
        builder.append(" 线程池活跃度: " + divide(threadPool.getActiveCount(), threadPool.getMaximumPoolSize()))
                .append(SymbolEnum.SEMICOLON.getValue());
        builder.append(" 任务完成数: " + threadPool.getCompletedTaskCount()).append(SymbolEnum.SEMICOLON.getValue());
        builder.append(" 队列大小: " + (queue.size() + queue.remainingCapacity())).append(SymbolEnum.SEMICOLON.getValue());
        builder.append(" 当前排队任务数: " + queue.size()).append(SymbolEnum.SEMICOLON.getValue());
        builder.append(" 队列剩余大小: " + queue.remainingCapacity()).append(SymbolEnum.SEMICOLON.getValue());
        builder.append(" 队列使用度: " + divide(queue.size(), queue.size() + queue.remainingCapacity()))
                .append(SymbolEnum.CLOSE_BRACE.getValue());
        log.info(builder.toString());
    }
}
