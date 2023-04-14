package com.doj.server.infrastructure.utils;

import com.doj.server.infrastructure.exception.JsonException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TODO 类描述
 *
 * @author kongweikun@163.com
 * @date 2023/4/13
 */
public enum JsonUtil {

    /**
     * 单例
     */
    X;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }

    public static String format(Object content) {
        try {
            return MAPPER.writeValueAsString(content);
        } catch (Exception e) {
            throw new JsonException(e);
        }
    }

    public <T> T parse(String content, Class<T> targetClass) {
        try {
            return MAPPER.readValue(content, targetClass);
        } catch (Exception e) {
            throw new JsonException(e);
        }
    }

    public <T> T parse(String content, TypeReference<T> typeReference) {
        try {
            return MAPPER.readValue(content, typeReference);
        } catch (Exception e) {
            throw new JsonException(e);
        }
    }
}
