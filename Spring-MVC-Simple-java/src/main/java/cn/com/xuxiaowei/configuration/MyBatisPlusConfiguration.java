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

import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * MyBatis Plus 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
public class MyBatisPlusConfiguration {

    /**
     * 连接池
     *
     * @see MyBatisConfiguration#dataSource()
     */
    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 连接工厂
     */
    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactory() throws IOException {

        // mapperLocations 属性接受多个资源位置。
        // 这个属性可以用来指定 MyBatis 的映射器 XML 配置文件的位置。
        // 属性的值是一个 Ant 风格的字符串，可以指定加载一个目录中的所有文件，或者从一个目录开始递归搜索所有目录。
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        // 加载、配置 Mapper XML
        // MyBatis Mapper 所对应的 XML 文件位置，如果您在 Mapper 中有自定义方法（XML 中有自定义实现），需要进行该配置，告诉 Mapper 所对应的 XML 文件位置。
        // 必须配置（否则出现：org.apache.ibatis.binding.BindingException: Invalid bound statement (not found)）
        Resource[] mapperLocations = resolver.getResources("classpath:mapper/*.xml");

        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        // 缓存
        mybatisConfiguration.setCacheEnabled(true);

        MybatisSqlSessionFactoryBean mybatisSqlSessionFactory = new MybatisSqlSessionFactoryBean();

        mybatisSqlSessionFactory.setDataSource(dataSource);

        // MyBaits 别名包扫描路径，通过该属性可以给包中的类注册别名，注册后在 Mapper 对应的 XML 文件中可以直接使用类名，
        // 而不用使用全限定的类名（即 XML 中调用的时候不用包含包名）。
        mybatisSqlSessionFactory.setTypeAliasesPackage("cn.com.xuxiaowei.entity");
        // 枚举类 扫描路径，如果配置了该属性，会将路径下的枚举类进行注入，让实体类字段能够简单快捷的使用枚举属性，
        // 具体使用请结合 枚举注入（https://mp.baomidou.com/guide/enum.html） 查看。
        mybatisSqlSessionFactory.setConfiguration(mybatisConfiguration);
        mybatisSqlSessionFactory.setMapperLocations(mapperLocations);

        return mybatisSqlSessionFactory;
    }

}
