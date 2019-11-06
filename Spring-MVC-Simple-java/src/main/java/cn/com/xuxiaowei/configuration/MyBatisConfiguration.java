/*
 * Copyright 2019 the original author or authors.
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

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * 配置 MyBatis
 *
 * @author xuxiaowei
 * @see org.springframework.jdbc.datasource.DriverManagerDataSource
 * @see org.springframework.jdbc.datasource.SimpleDriverDataSource
 * @see <a href="https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/jdbc/datasource/SimpleDriverDataSource.html">SimpleDriverDataSource</a>
 * @see <a href="https://docs.spring.io/spring-boot/docs/1.5.12.RELEASE/reference/html/howto-data-access.html#howto-two-datasources">两个数据源</a>
 * @since 0.0.1
 */
@Configuration
@MapperScan("cn.com.xuxiaowei.mapper")
public class MyBatisConfiguration {

    /**
     * 连接工厂
     * <p>
     * 扫描
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws IOException {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:mapper/*.xml");
        sqlSessionFactory.setMapperLocations(resources);

        return sqlSessionFactory;
    }

    /**
     * 数据库连接池
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/xuxiaowei?useSSL=false&serverTimezone=GMT%2B8");
        dataSource.setDriverClassName(com.mysql.cj.jdbc.Driver.class.getName());
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

}
