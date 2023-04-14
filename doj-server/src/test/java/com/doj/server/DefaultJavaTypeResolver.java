package com.doj.server;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.sql.Types;
import java.time.LocalDate;
import java.time.OffsetDateTime;

/**
 * 类描述：MBG的类型处理器
 * @author kongweikun@163.com
 * @date 2023/4/13
 * SMALLINT和TINYINT使用Integer接收
 * DATE使用LocalDate接收
 * TIMESTAMP和DATETIME使用OffsetDateTime接收
 */
public class DefaultJavaTypeResolver extends JavaTypeResolverDefaultImpl {

    public DefaultJavaTypeResolver() {
        super();
        typeMap.put(Types.SMALLINT, new JdbcTypeInformation("SMALLINT",
                new FullyQualifiedJavaType(Integer.class.getName())));
        typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT",
                new FullyQualifiedJavaType(Integer.class.getName())));
        typeMap.put(Types.DATE, new JdbcTypeInformation("DATE",
                new FullyQualifiedJavaType(LocalDate.class.getName())));
        typeMap.put(Types.TIMESTAMP, new JdbcTypeInformation("TIMESTAMP",
                new FullyQualifiedJavaType(OffsetDateTime.class.getName())));
    }
}
