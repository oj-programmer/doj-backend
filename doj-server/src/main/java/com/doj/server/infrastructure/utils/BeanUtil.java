package com.doj.server.infrastructure.utils;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

/**
 * 类描述：Bean操作工具类
 *
 * @author kongweikun <kongweikun@baidu.com>
 * @date 2023/4/14
 */
public class BeanUtil {

    /**
     * 根据需要拷贝的属性数组对源对象属性集合取差集
     *
     * @param source             源对象
     * @param requiredProperties 需要拷贝的属性数组
     * @return 源对象忽略拷贝的属性数组
     */
    private static String[] getIgnoredFields(Object source, String... requiredProperties) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        // 源对象所有属性
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
        // 临时属性集合
        Set<String> tempFields = new HashSet<>();
        if (propertyDescriptors.length > 0) {
            for (PropertyDescriptor p : propertyDescriptors) {
                // 源对象属性全部放入临时属性集合
                tempFields.add(p.getName());
            }
        }
        if (!Objects.isNull(requiredProperties)) {
            for (String requiredProperty : requiredProperties) {
                // 在临时属性集合删除需要拷贝的属性
                tempFields.remove(requiredProperty);
            }
        }

        String[] nullField = new String[tempFields.size()];
        return tempFields.toArray(nullField);
    }

    /**
     * 获取非空属性
     *
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames(Object source) {
        BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (Objects.isNull(srcValue)) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 排除为null的字段copy
     *
     * @param src
     * @param target
     */
    public static void copyPropertiesIgnoreNull(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    /**
     * 排除为null的字段copy
     *
     * @param src
     * @param target
     */
    public static <S, T> T copyPropertiesIgnoreNull(S src, Supplier<T> target) {
        return copyPropertiesIgnoreNull(src, target, null);
    }

    /**
     * 排除为null的字段copy
     *
     * @param src
     * @param target
     */
    public static <S, T> T copyPropertiesIgnoreNull(S src, Supplier<T> target,
                                                    BeanCopyUtilCallBack<S, T> callBack) {
        if (EmptyUtil.isEmpty(src)) {
            return (T) src;
        }
        T t = target.get();
        copyPropertiesIgnoreNull(src, t);
        if (!Objects.isNull(callBack)) {
            // 回调
            callBack.callBack(src, t);
        }
        return t;
    }

    /**
     * 集合类型的拷贝(sources中的引用属性(如List或自定义类型)不会创建新对象, 维持原有引用)
     *
     * @param sources
     * @param target
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        return copyListProperties(sources, target, null);
    }

    /**
     * 带回调函数的集合数据的拷贝（可自定义字段拷贝规则）
     *
     * @param sources:  数据源类
     * @param target:   目标类::new(eg: ClassName::new)
     * @param callBack: 回调函数
     * @return
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target,
                                                    BeanCopyUtilCallBack<S, T> callBack) {
        if (EmptyUtil.isEmpty(sources)) {
            return Lists.newArrayList();
        }
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T t = target.get();
            copyPropertiesIgnoreNull(source, t);
            list.add(t);
            if (!Objects.isNull(callBack)) {
                // 回调
                callBack.callBack(source, t);
            }
        }
        return list;
    }
}
