# Spring-MVC-Simple-java
Spring-MVC-Simple-java 项目配置。

## [Spring Framework Documentation](https://docs.spring.io/spring/docs/current/spring-framework-reference/index.html)

## [Spring Web Services Reference Documentation](https://docs.spring.io/spring-ws/docs/current/reference/index.html)

## [Spring Faces](https://docs.spring.io/autorepo/docs/webflow/current/reference/html/spring-faces.html)

## 使用 Apache Tomcat 7
- 最低支持 Apache Tomcat 7
- apache-tomcat-7.0.96 正常运行
- apache-tomcat-7.0.100 .xhtml 不能正常显示

## 项目配置

- 集成 Spring MVC 配置
- 模板引擎
    - Thymeleaf
    - FreeMarker
    - JSP
    - JSF（Sun）
- Spring Security
    - Thymeleaf 集成 Security
    - FreeMarker 集成 Security
    - JSP 集成 Security
    - JSF（Sun） 集成 Security
- Hikari 连接池
- MyBatis
- Redis
    - Session 共享
    - Redis 注解
    - Redis 集群配置
- MySQL
- log4j2
- Slf4j
- 定时器
- 返回XML数据
    - 根据 Accept 自动请求解析不同类型的数据
    - 根据 URL 后缀名自动请求解析不同类型的数据

## 数据库

- MySQL 5.7.24

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
- 排序规则
    - utf8mb4_bin
