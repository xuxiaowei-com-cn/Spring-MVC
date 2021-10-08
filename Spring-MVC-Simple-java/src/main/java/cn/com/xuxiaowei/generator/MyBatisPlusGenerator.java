/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.com.xuxiaowei.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * MyBatis Plus 代码生成器
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class MyBatisPlusGenerator {

    /**
     * 父包名
     */
    private String parent = "cn.com.xuxiaowei";

    /**
     * 作者
     */
    private String author;

    /**
     * 读取本地项目文件夹路径（Spring Boot）
     */
    private String projectPath = System.getProperty("user.dir");

    /**
     * 主文件夹
     */
    private String main;

    /**
     * 获取 java 文件的路径
     *
     * @return 返回 java 文件的路径
     */
    public String getOutputDir() {
        main = projectPath + "/src/main";

        return main + "/java";
    }

    /**
     * 获取 XML 文件的路径
     *
     * @return 返回 XML 文件的路径
     */
    public String getMapper() {
        return main + "/resources/mapper/"
                // + subproject
                + "/" + moduleName + "/";
    }

    /**
     * 数据库 驱动名称
     */
    private String driverName = com.p6spy.engine.spy.P6SpyDriver.class.getName();

    /**
     * 自定义继承的Entity类全称，带包名
     */
    private String superEntityClass = null;

    /**
     * 自定义继承的Controller类全称，带包名
     */
    private String superControllerClass = null;

    /**
     * Controller 名
     */
    private String controllerName = "%sRestController";

    /**
     * 是否生成 BaseResultMap
     */
    private boolean baseResultMap = true;

    /**
     * 逻辑删除属性名称
     */
    private String logicDeleteFieldName = "DELETED";

    /**
     * 默认模块名
     */
    private String moduleName = "";

    /**
     * 数据库序号
     */
    private int datasourceNum;

    public static void main(String[] args) {

        MyBatisPlusGenerator myBatisPlusGenerator = new MyBatisPlusGenerator();

        myBatisPlusGenerator.getAutoGenerator();

    }

    /**
     * 数据库
     */
    enum Datasource {

        /**
         * MySQL 开发
         */
        MySQL_Dev("jdbc:p6spy:mysql://127.0.0.1:3306/xuxiaowei?useSSL=false&serverTimezone=GMT%2B8&characterEncoding=UTF-8",
                "root", "root", "MySQL 开发 数据库 xuxiaowei"),

        ;

        /**
         * 数据库连接串
         */
        private final String url;

        /**
         * 数据库用户名
         */
        private final String username;

        /**
         * 数据库密码
         */
        private final String password;

        /**
         * 数据库说明
         */
        private final String explain;

        Datasource(String url, String username, String password, String explain) {
            this.url = url;
            this.username = username;
            this.password = password;
            this.explain = explain;
        }

    }

    /**
     * 代码生成器
     */
    public void getAutoGenerator() {

        Datasource[] values = Datasource.values();

        for (int i = 0; i < values.length; i++) {
            Datasource value = values[i];
            System.out.printf("数据库序号：%s 数据库说明：%s 数据库连接串：%s 数据库用户名：%s%n",
                    i, value.explain, value.url, value.username);
        }

        String datasource = scanner("请选择数据库序号");

        try {
            int integer = Integer.parseInt(datasource);

            if (integer >= 0 && integer < values.length) {
                datasourceNum = integer;
            } else {
                System.err.println("输入数据库序号不在有效范围内");
                getAutoGenerator();
                return;
            }

        } catch (Exception e) {
            System.err.println("输入数据库序号不正确");
            e.printStackTrace();
            getAutoGenerator();
            return;
        }

        // 创建 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        // 创建 全局配置
        GlobalConfig globalConfig = getGlobalConfig();

        // 代码生成器 设置 全局配置
        autoGenerator.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dataSourceConfig = getDataSourceConfig();

        // 代码生成器 设置 数据源配置
        autoGenerator.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig packageConfig = getPackageConfig();

        // 设置 包配置
        autoGenerator.setPackageInfo(packageConfig);

        // 自定义配置
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 输出文件配置
        List<FileOutConfig> fileOutConfigLists = getFileOutConfigList();

        // 自定义配置 设置 自定义输出文件
        injectionConfig.setFileOutConfigList(fileOutConfigLists);

        // 代码生成器 设置 自定义配置
        autoGenerator.setCfg(injectionConfig);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
//        <code>templateConfig.setEntity("templates/entity2.java");</code>
//        <code>templateConfig.setService("/templates/service2.java");</code>
//        <code>templateConfig.setServiceImpl("/templates/serviceImpl2.java");</code>
//        <code>templateConfig.setController("/templates/controller2.java");</code>

        templateConfig.setXml(null);

        // 代码生成器 设置 模板
        autoGenerator.setTemplate(templateConfig);

        // 设置 策略配置项
        StrategyConfig strategyConfig = getStrategyConfig(packageConfig);

        // @Accessors(chain = true)
        strategyConfig.setChainModel(true);

        // 数据库表配置
        autoGenerator.setStrategy(strategyConfig);

        // 模板引擎
        // FreemarkerTemplateEngine
        // VelocityTemplateEngine

        // 生成代码
        autoGenerator.execute();

    }

    /**
     * 输出文件配置
     *
     * @return 输出文件配置
     * @see PackageConfig 跟包相关的配置项
     */
    private List<FileOutConfig> getFileOutConfigList() {
        // 如果模板引擎是 freemarker
        // String xmlPath = ConstVal.TEMPLATE_XML + ".ftl";

        // 如果模板引擎是 velocity
        String xmlPath = ConstVal.TEMPLATE_XML + ".vm";

        // 自定义输出配置
        List<FileOutConfig> fileOutConfigs = new ArrayList<>();

        // 自定义配置会被优先输出
        fileOutConfigs.add(new FileOutConfig(xmlPath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                // 取消 Mapper XML DO 后缀名
                String entityName = tableInfo.getEntityName();
                return getMapper() + "/" + entityName
                        // .substring(0, entityName.length() - 2)
                        + "Mapper" + StringPool.DOT_XML;
            }
        });

        return fileOutConfigs;
    }


    /**
     * 获取 跟包相关的配置项
     *
     * @return 跟包相关的配置项
     */
    private PackageConfig getPackageConfig() {

        PackageConfig packageConfig = new PackageConfig();

        // 模块名
        moduleName = scanner("生成模块名");

        // 模块名
        packageConfig.setModuleName(moduleName);

        // 设置 父包名
        packageConfig.setParent(parent);

        return packageConfig;
    }

    /**
     * 设置 全局配置
     */
    private GlobalConfig getGlobalConfig() {

        GlobalConfig globalConfig = new GlobalConfig();

        // java8 新的时间类型
        globalConfig.setDateType(DateType.TIME_PACK);

        // scanner(String.format("子项目名(已默认前缀：%s)", parent))
//        subproject = "passport";

//        setMain(subproject);

//        setParent(parent + "." + subproject);

        // 设置生成文件的输出目录
        globalConfig.setOutputDir(getOutputDir());

        author = scanner("作者");

//        mapper += parent;

        // 设置 作者
        globalConfig.setAuthor(author);

        // 是否打开输出目录
        globalConfig.setOpen(false);

        String scanner = scanner("是否覆盖：\n输入“f”时，覆盖原文件");

        // 文件是否覆盖
        String fileOverride = "f";

        if (fileOverride.equals(scanner)) {
            globalConfig.setFileOverride(true);
        }

        // 实体属性 Swagger2 注解
        // <code>globalConfig.setSwagger2(true);</code>

        // 开启 BaseResultMap
        globalConfig.setBaseResultMap(baseResultMap);

        // 设置 Controller 名
        globalConfig.setControllerName(controllerName);

        // 设置 Entity 后缀名

        return globalConfig;
    }

    /**
     * 设置 数据库配置
     */
    private DataSourceConfig getDataSourceConfig() {

        DataSourceConfig dataSourceConfig = new DataSourceConfig();

        Datasource[] values = Datasource.values();
        Datasource value = values[datasourceNum];

        // 设置 数据库 地址
        dataSourceConfig.setUrl(value.url);
        // 设置 驱动名称
        dataSourceConfig.setDriverName(driverName);
        // 设置 数据库 用户名
        dataSourceConfig.setUsername(value.username);
        // 设置 数据库 密码
        dataSourceConfig.setPassword(value.password);

        return dataSourceConfig;
    }

    /**
     * 设置 策略配置项
     *
     * @param packageConfig 跟包相关的配置项
     */
    private StrategyConfig getStrategyConfig(PackageConfig packageConfig) {

        StrategyConfig strategyConfig = new StrategyConfig();

        // 数据库表映射到实体的命名策略
        // underline_to_camel：下划线转驼峰命名
        // no_change：不做任何改变，原样输出
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);

        // 数据库表字段映射到实体的命名策略
        // underline_to_camel：下划线转驼峰命名
        // no_change：不做任何改变，原样输出
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);

        // 自定义继承的Entity类全称，带包名
        strategyConfig.setSuperEntityClass(superEntityClass);

        // 自定义基础的Entity类，公共字段
        Set<String> superEntityColumns = strategyConfig.getSuperEntityColumns();
        for (String superEntityColumn : superEntityColumns) {
            strategyConfig.setSuperEntityColumns(superEntityColumn);
        }

        // 【实体】是否为lombok模型（默认 false）
        strategyConfig.setEntityLombokModel(true);

        // 【实体】是否生成字段常量（默认 false）
        strategyConfig.setEntityColumnConstant(true);

        // 生成 <code>@RestController</code> 控制器
        strategyConfig.setRestControllerStyle(true);

        // 是否生成实体时，生成字段注解
        strategyConfig.setEntityTableFieldAnnotationEnable(true);

        // 逻辑删除属性名称
        strategyConfig.setLogicDeleteFieldName(logicDeleteFieldName);

        // 自定义继承的Controller类全称，带包名
        strategyConfig.setSuperControllerClass(superControllerClass);

        // 需要包含的表名，允许正则表达式（与exclude二选一配置）
        strategyConfig.setInclude(scanner("表名，多个英文逗号分割").split(","));

        // 驼峰转连字符
        strategyConfig.setControllerMappingHyphenStyle(true);

        // 表前缀
        strategyConfig.setTablePrefix(packageConfig.getModuleName() + "_");

        return strategyConfig;
    }

    /**
     * 读取控制台内容
     */
    private String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(("请输入" + tip + "："));
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * 设置 主文件夹
     *
     * @param subproject 子项目名
     */
    public void setMain(String subproject) {
        this.main = projectPath + "/" + subproject + "/src/main";
    }

}
