package com.zfl19.codeGenerator.main;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.zfl19.codeGenerator.utils.AuxiliaryUitl;

import java.util.*;

/**
 * @author 19zfl
 * @date 2023/5/17
 */
public class CodeGenerator {

    // 加载配置文件
    static ResourceBundle rb = ResourceBundle.getBundle("mybatis-plus-code-generator-configs");

    // 存储代码文件的根目录
    private static String baseProjectPath = "";

    // 存放controller，service，service.impl，mapper，domain等
    private static final String projectServerPath = baseProjectPath + "spring-boot-template";

    // 作者名称
    private static String author = "";

    // 需要生成表的前缀
    private static String tableNamePrefix = "";

    // 数据库连接参数
    private static String db_url = "";

    // 数据库驱动
    private static String db_driver_name = "";

    // 数据库用户名
    private static String db_username = "";

    // 数据库密码
    private static String db_password = "";

    // 根包名
    private static final String root_package = "com.zfl19";

    // 根包路径
    private static final String root_package_path = "/com/zfl19/kernel";

    // 分类包名
    private static String module_name = "";

    static{
        baseProjectPath = rb.getString("baseProjectPath");
        author = rb.getString("author");
        module_name = rb.getString("moduleName");
        tableNamePrefix = rb.getString("tableNamePrefix");
        db_driver_name = rb.getString("jdbc.driver");
        db_url = rb.getString("jdbc.url");
        db_username = rb.getString("jdbc.user");
        db_password = rb.getString("jdbc.pwd");
    }

    /* 程序入口 */
    public static void main(String[] args) {
        String tableNames = rb.getString("tableNames");
        String[] tableArray = tableNames.split(",");
        for (int i = 0; i < tableArray.length; i++) {
            generatorCode(tableArray[i]);
        }
    }

    /* 生成代码的方法 */
    public static void generatorCode(String tableNames) {
        // 获取自动生成器对象
        AutoGenerator autoGenerator = new AutoGenerator();
        // 实体类名称
        String entityName = AuxiliaryUitl.getEntityName(tableNames);

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(projectServerPath + "/src/main/java");
        globalConfig.setAuthor(author);
        globalConfig.setServiceName("%sService");
        globalConfig.setIdType(IdType.AUTO);
        globalConfig.setFileOverride(true);//是否覆盖（第二次生成代码是否要覆盖第一次生成的代码）
        globalConfig.setSwagger2(false);//是否开启Swagger
        globalConfig.setActiveRecord(false);// 开启 activeRecord 模式
        globalConfig.setEnableCache(false);// XML 二级缓存
        globalConfig.setBaseResultMap(true);// XML ResultMap
        globalConfig.setBaseColumnList(true);// XML columList
        globalConfig.setOpen(false);//生成完成后是否打开文件夹
        autoGenerator.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig sourceConfig = new DataSourceConfig();
        sourceConfig.setUrl(db_url);
        sourceConfig.setDriverName(db_driver_name);
        sourceConfig.setUsername(db_username);
        sourceConfig.setPassword(db_password);
        autoGenerator.setDataSource(sourceConfig);

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(root_package);
        autoGenerator.setPackageInfo(packageConfig);

        // 注入自定义配置，可以在 VM 中使用 cfg.basePath 【可无】
        String finalEntityName = entityName;
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("entityName", finalEntityName.toLowerCase());
                map.put("urlPrefix", "/spring-boot-template");
                map.put("basePath", root_package+".kernel");
                map.put("moduleName", module_name);
                this.setMap(map);
            }
        };

        List<FileOutConfig> fileOutConfigs = new ArrayList<>();

        // 配置controller
        fileOutConfigs.add(new FileOutConfig("/templates/controller.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectServerPath + "/src/main/java" + root_package_path + "/controller" + "/" + module_name + "/" + tableInfo.getEntityName() + "Controller" + StringPool.DOT_JAVA;
            }
        });

        // 配置service
        fileOutConfigs.add(new FileOutConfig("/templates/serviceImpl.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectServerPath + "/src/main/java" + root_package_path + "/service" + "/" + module_name + "/" + tableInfo.getEntityName() + "Service" + StringPool.DOT_JAVA;
            }
        });

        // 配置domain
        fileOutConfigs.add(new FileOutConfig("/templates/entity.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectServerPath + "/src/main/java" + root_package_path + "/domain" + "/" + module_name + "/" + tableInfo.getEntityName() + StringPool.DOT_JAVA;
            }
        });

        // 配置mapper
        fileOutConfigs.add(new FileOutConfig("/templates/mapper.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectServerPath + "/src/main/java" + root_package_path + "/mapper" + "/" + module_name + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_JAVA;
            }
        });

        // 配置xml
        fileOutConfigs.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectServerPath + "/src/main/resources" + root_package_path + "/mapper" + "/" + module_name + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        // 配置query
        fileOutConfigs.add(new FileOutConfig("/templates/query.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectServerPath + "/src/main/java" + root_package_path + "/query" + "/" + module_name + "/" + tableInfo.getEntityName() + "Query" + StringPool.DOT_JAVA;
            }
        });

        // other配置
        TemplateConfig tc = new TemplateConfig();
        tc.setEntity(null);
        tc.setXml(null);
        tc.setController(null);
        tc.setMapper(null);
        tc.setService(null);
        tc.setServiceImpl(null);
        cfg.setFileOutConfigList(fileOutConfigs);
        autoGenerator.setCfg(cfg);
        autoGenerator.setTemplate(tc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(new String[]{"t_"});//忽略的表头
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(tableNames);

        autoGenerator.setStrategy(strategy);
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        autoGenerator.execute();

    }

}
