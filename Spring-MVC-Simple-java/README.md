# Spring-MVC-Simple-java
Spring-MVC-Simple-java 项目配置。

## [Spring Framework Documentation](https://docs.spring.io/spring/docs/current/spring-framework-reference/index.html)

## [Spring Faces](https://docs.spring.io/autorepo/docs/webflow/current/reference/html/spring-faces.html)

## 使用 Apache Tomcat 9

## 项目配置

- 集成 Spring MVC 配置
- JSF
- Hikari 连接池
- MyBatis
- Redis
- MySQL
- log4j2
- Slf4j
- 定时器
- 返回XML数据
    - 根据 Accept 自动请求解析不同类型的数据
    - 根据 URL 后缀名自动请求解析不同类型的数据

## 数据库

- Windows 设置
~~~
[mysql]
default-character-set=utf8mb4

[mysqld]
character-set-server=utf8mb4
~~~
- Linux 设置
~~~
[mysql]
default-character-set=utf8mb4

[mysqld]
character-set-server=utf8mb4
collation_server=utf8mb4_bin
~~~

- 字符集
    - utf8mb4
排序规则
    - utf8mb4_bin
