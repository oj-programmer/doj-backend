package com.doj.server.infrastructure.constant;

/**
 * 类描述：系统常量
 *
 * @author kongweikun <kongweikun@baidu.com>
 * @date 2023/4/14
 */
public class SystemConstant {

    /**
     * empty str
     */
    public static final String EMPTY_STR = "";

    /**
     * empty json
     */
    public static final String EMPTY_JSON = "{}";

    /**
     * 异步回调，超时时间(s)
     */
    public static final Integer ASYNC_CALL_BACK_TIME_OUT = 10;

    /**
     * 唯一索引命名前缀
     */
    public static final CharSequence UK_PREFIX = "uk_";

    /**
     * requestId
     */
    public static final String REQUEST_ID = "requestId";

    /**
     * cookie ticket
     */
    public static final String COOKIE_TICKET = "ticket";
}
