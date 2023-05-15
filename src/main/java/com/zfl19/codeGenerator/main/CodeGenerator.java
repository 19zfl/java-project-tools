package com.zfl19.codeGenerator.main;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.zfl19.codeGenerator.utils.AuxiliaryUitl;

import java.util.ResourceBundle;

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
    private static final String projectServerPath = baseProjectPath;

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
    private static final String root_package_path = "/com/zfl19";

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

        // TODO: 2023/5/18 配置controller

        // TODO: 2023/5/18 配置service

        // TODO: 2023/5/18 配置domain

        // TODO: 2023/5/18 配置mapper

        // TODO: 2023/5/18 配置xml

        // TODO: 2023/5/18 配置query

        // TODO: 2023/5/18 other配置
    }

}
