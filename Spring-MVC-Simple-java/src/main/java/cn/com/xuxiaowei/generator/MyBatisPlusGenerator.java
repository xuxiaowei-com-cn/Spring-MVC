package cn.com.xuxiaowei.generator;

import com.baomidou.mybatisplus.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * MyBatis Plus 代码生成器
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class MyBatisPlusGenerator {

    /**
     * 父包名
     */
    private static String parent = "cn.com.xuxiaowei";

    /**
     * 作者
     */
    private static String author = "徐晓伟";

    /**
     * 读取本地项目文件夹路径（Spring Boot）
     */
    private static String projectPath = System.getProperty("user.dir");

    /**
     * 主文件夹
     */
    private static String main = projectPath + "/src/main";

    /**
     * java 文件的路径
     */
    private static String outputDir = main + "/java";

    /**
     * XML 文件夹
     */
    private static String mapper = main + "/resources/mapper/";

    /**
     * 数据库 地址
     * <p>
     * MySQL：
     * jdbc:mysql://127.0.0.1:3306/spring-boot-api?useSSL=false&serverTimezone=GMT%2B8
     * <p>
     * Oracle：
     * jdbc:oracle:thin:@192.168.8.128:1521/orcl
     * <p>
     * SQLServer：
     * jdbc:sqlserver://127.0.0.1:1433;DatabaseName=DATA
     */
    private static String url = "jdbc:mysql://127.0.0.1:3306/xuxiaowei?useSSL=false&serverTimezone=GMT%2B8";

    /**
     * 数据库 用户名
     */
    private static String username = "root";

    /**
     * 数据库 密码
     */
    private static String password = "root";

    /**
     * 数据库 驱动名称
     * <p>
     * MySQL：
     * com.mysql.jdbc.Driver 已过时，被弃用
     * com.mysql.cj.jdbc.Driver 请使用此类（在 pom.xml 中删除 MySQL 的运行范围 <scope>runtime</scope>）
     * <p>
     * Oracle：
     * oracle.jdbc.driver.OracleDriver
     * <p>
     * SQLServer：
     * com.microsoft.sqlserver.jdbc.SQLServerDriver
     */
    private static String driverName = com.mysql.jdbc.Driver.class.getName();

    /**
     * 自定义继承的Entity类全称，带包名
     */
    private static String superEntityClass = null;

    /**
     * 自定义继承的Controller类全称，带包名
     */
    private static String superControllerClass = null;

    /**
     * Controller 名
     */
    private static String controllerName = "%sRestController";

    /**
     * 是否生成 BaseResultMap
     */
    private static boolean baseResultMap = true;

    public static void main(String[] args) {

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 模板路径配置项
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);

        mpg.setTemplate(templateConfig);

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 设置 Controller 名
        gc.setControllerName(controllerName);
        gc.setOutputDir(outputDir);
        gc.setAuthor(author);
        gc.setEnableCache(false);
        gc.setActiveRecord(false);

        String scanner = scanner("是否覆盖：\n输入“f”时，覆盖原文件");

        // 文件是否覆盖
        String fileOverride = "f";

        if (fileOverride.equals(scanner)) {
            gc.setFileOverride(true);
        }

        gc.setOpen(false);
        gc.setBaseResultMap(baseResultMap);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);

        dsc.setDriverName(driverName);
        dsc.setUsername(username);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);

        // 包配置
        final PackageConfig pc = new PackageConfig();

        pc.setController("controller");

        // 模块名
        String moduleName = scanner("是否生成模块名：\n输入“-”时，忽略模块名");

        // 忽略模块名的情况
        String noModuleName = "-";

        if (!noModuleName.equals(moduleName)) {
            // 模块名
            pc.setModuleName(moduleName);
        }

        pc.setParent(parent);

        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();

        // 如果模板引擎是 velocity
        String xmlPath = ConstVal.TEMPLATE_XML;

        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(xmlPath) {
            @Override
            public String outputFile(TableInfo tableInfo) {

                // 模块名
                String moduleName = pc.getModuleName();

                // 模块名是否为空
                if (moduleName == null) {
                    moduleName = "";
                }

                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return mapper + moduleName + "/" + tableInfo.getEntityName() + "Mapper" + ConstVal.XML_SUFFIX;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();

        strategy.setEntityColumnConstant(true);

        // 自定义继承的Entity类全称，带包名
        strategy.setSuperEntityClass(superEntityClass);

        // 自定义继承的Controller类全称，带包名
        strategy.setSuperControllerClass(superControllerClass);

        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        mpg.setStrategy(strategy);
        mpg.execute();

    }

    /**
     * 读取控制台内容
     */
    private static String scanner(String tip) {
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

}
