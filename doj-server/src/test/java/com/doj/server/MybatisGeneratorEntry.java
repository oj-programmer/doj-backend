package com.doj.server;

import com.doj.server.infrastructure.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：DO生成器
 * @author kongweikun@163.com
 * @date 2023/4/13
 */
@Slf4j
public class MybatisGeneratorEntry {

    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<>();
        File configFile = new File("doj-server/target/classes/generator-doj.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        // 如果已经存在生成过的文件是否进行覆盖
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator generator = new MyBatisGenerator(config, callback, warnings);
        generator.generate(null);
        log.warn("生成结果--------:{}", JsonUtil.serialize(warnings));
    }
}
