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
package cn.com.xuxiaowei.configuration;

import cn.com.xuxiaowei.properties.JdbcProperties;
import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * 配置 MyBatis
 * <p>
 * 映射器
 * base-package 属性允许你设置映射器接口文件的基础包。
 * 通过使用逗号或分号分隔，你可以设置多个包。
 * 并且会在你所指定的包中递归搜索映射器。
 * <p>
 * EnableTransactionManagement 使用事务驱动管理器
 *
 * @author xuxiaowei
 * @see <a href="http://mybatis.org/spring/zh/mappers.html">注入映射器</a>
 * @since 0.0.1
 */
@Configuration
@MapperScan("cn.com.xuxiaowei.mapper")
@EnableTransactionManagement
public class MyBatisConfiguration implements TransactionManagementConfigurer {

    /**
     * JDBC 属性文件
     */
    private JdbcProperties jdbcProperties;

    @Autowired
    public void setJdbcProperties(JdbcProperties jdbcProperties) {
        this.jdbcProperties = jdbcProperties;
    }

    @Override
    @Bean
    public TransactionManager annotationDrivenTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource());
        return dataSourceTransactionManager;
    }

    /**
     * 连接工厂
     * <p>
     * 扫描 xml
     * <p>
     * 由于使用了 MyBatis Plus，连接工厂交付给 MyBatis Plus
     * 该方法未启用
     * <p>
     * {@link Bean}
     *
     * @see <a href="http://mybatis.org/spring/zh/factorybean.html">SqlSessionFactoryBean</a>
     * @see <a href="http://mybatis.org/spring/zh/getting-started.html">mybatis-spring</a>
     */
    public SqlSessionFactoryBean sqlSessionFactory() throws IOException {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());

        // mapperLocations 属性接受多个资源位置。
        // 这个属性可以用来指定 MyBatis 的映射器 XML 配置文件的位置。
        // 属性的值是一个 Ant 风格的字符串，可以指定加载一个目录中的所有文件，或者从一个目录开始递归搜索所有目录。
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        // 加载、配置 Mapper XML
        Resource[] mapperLocations = resolver.getResources("classpath:mapper/*.xml");
        sqlSessionFactory.setMapperLocations(mapperLocations);

        // 加载、配置 MyBatis 配置文件
        Resource configLocation = resolver.getResource("classpath:mybatis-config.xml");
        sqlSessionFactory.setConfigLocation(configLocation);

        return sqlSessionFactory;
    }

    /**
     * 事务
     *
     * @see <a href="http://mybatis.org/spring/zh/transactions.html">事务</a>
     */
    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    /**
     * 数据库连接池
     * <p>
     * JDBC 连接池：
     * DriverManagerDataSource dataSource = new DriverManagerDataSource();
     * dataSource.setUrl(jdbcProperties.getUrl());
     * dataSource.setDriverClassName(jdbcProperties.getDriverClassName());
     * dataSource.setUsername(jdbcProperties.getUsername());
     * dataSource.setPassword(jdbcProperties.getPassword());
     *
     * @see org.springframework.jdbc.datasource.DriverManagerDataSource
     * @see org.springframework.jdbc.datasource.SimpleDriverDataSource
     * @see <a href="https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/jdbc/datasource/SimpleDriverDataSource.html">SimpleDriverDataSource</a>
     * @see <a href="https://docs.spring.io/spring-boot/docs/1.5.12.RELEASE/reference/html/howto-data-access.html#howto-two-datasources">两个数据源</a>
     */
    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(jdbcProperties.getUrl());
        dataSource.setDriverClassName(jdbcProperties.getDriverClassName());
        dataSource.setUsername(jdbcProperties.getUsername());
        dataSource.setPassword(jdbcProperties.getPassword());
        return dataSource;
    }

}
