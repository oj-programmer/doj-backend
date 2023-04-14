package com.doj.server.infrastructure.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.collect.Lists;

import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Objects;

import static com.doj.server.infrastructure.constant.SystemConstant.EMPTY_JSON;

/**
 * Json工具类
 *
 * @author kongweikun@163.com
 * @date 2023/4/13
 */
public class JsonUtil {

    public static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将对象序列化为JSON字符串
     *
     * @param object
     * @return JSON字符串
     */
    public static String serialize(Object object) {
        if (EmptyUtil.isEmpty(object)) {
            return EMPTY_JSON;
        }
        Writer write = new StringWriter();
        try {
            objectMapper.writeValue(write, object);
        } catch (Exception e) {
            throw new RuntimeException("序列化对象失败", e);
        }
        return write.toString();
    }

    /**
     * 将JSON字符串反序列化为对象
     *
     * @return JSON字符串
     */
    public static <T> T deserialize(String json, Class<T> clazz) {
        if (EmptyUtil.isEmpty(json) || Objects.equals(json, EMPTY_JSON)) {
            return null;
        }
        Object object;
        try {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            object = objectMapper.readValue(json, TypeFactory.rawClass(clazz));
        } catch (Exception e) {
            throw new RuntimeException(String.format("反序列化对象失败 -> [%s]", json), e);
        }
        return (T) object;
    }

    /**
     * 将JSON字符串反序列化为对象列表
     *
     * @return JSON字符串
     */
    public static <T> List<T> deserialize(String json, Class tClass, Class<T> clazz) {
        if (EmptyUtil.isEmpty(json) || Objects.equals(json, EMPTY_JSON)) {
            return Lists.newArrayList();
        }
        try {
            return objectMapper.readValue(json, getCollectionType(objectMapper, tClass, clazz));
        } catch (Exception e) {
            throw new RuntimeException("反序列化对象失败", e);
        }
    }

    /**
     * 获取集合参数类型
     *
     * @param mapper
     * @param collectionClass
     * @param elementClasses
     * @return
     */
    public static JavaType getCollectionType(ObjectMapper mapper,
                                             Class<?> collectionClass,
                                             Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
