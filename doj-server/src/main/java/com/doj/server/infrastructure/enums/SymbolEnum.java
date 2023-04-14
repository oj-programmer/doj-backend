package com.doj.server.infrastructure.enums;

/**
 * 类描述：标点符号枚举
 *
 * @author kongweikun <kongweikun@baidu.com>
 * @date 2023/4/14
 */
public enum SymbolEnum {

    /**
     * 点
     */
    DOT(".", "点"),

    /**
     * 分隔号
     */
    SEPARATOR("/", "分隔号"),

    /**
     * 冒号
     */
    COLON(":", "冒号"),

    /**
     * 下划线
     */
    UNDER_LINE("_", "下划线"),

    /**
     * 等于号
     */
    EQUALS_SIGN("=", "等于号"),

    /**
     * 问号
     */
    QUESTION_MASK("?", "问号"),

    /**
     * 分号
     */
    SEMICOLON(";", "分号"),

    /**
     * 空格
     */
    SPACE(" ", "空格"),

    /**
     * 与
     */
    AND("&", "与"),

    /**
     * 换行
     */
    LF("\n", "换行"),

    /**
     * 中划线
     */
    STRIKE("-", "中划线"),

    /**
     * 左括号
     */
    LF_OPEN("(", "左括号"),

    /**
     * 右括号
     */
    RG_OPEN(")", "右括号"),

    /**
     * 井号
     */
    POUND("#", "井号"),

    /**
     * 左花括号
     */
    OPEN_BRACE("{", "左花括号"),

    /**
     * 右花括号
     */
    CLOSE_BRACE("}", "右花括号"),

    /**
     * 逗号
     */
    COMMA(",", "逗号"),

    /**
     * 竖线
     */
    VERTICAL_BAR("|", "竖线"),

    /**
     * 连接符
     */
    DASH("-", "连接符");

    private String value;

    private String description;

    SymbolEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }
}
