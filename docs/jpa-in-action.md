# 数据持久化 实战

在之前的章节中，我们构建了一个项目`thymeleaf-in-action`。通过该项目，我们用 Thymeleaf 来实现一个最简单的“用户管理”功能。 为了简便，我们没有使用数据库管理系统，而是将数据直接保存在了内存，这样只要应用重启，数据就会丢失。本节，我们将通过JPA 来将数据存储到关系型数据库中。

我们在上一节创建的 `jpa-in-action`项目的基础上，做一些修改。


## 修改application.properties

增加下面几项配置：

```
# DataSource 
spring.datasource.url=jdbc:mysql://localhost/blog
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.jdbc.Driver

# Tomcat connection pool
spring.datasource.tomcat.max-wait=10000
spring.datasource.tomcat.max-active=50
spring.datasource.tomcat.test-on-borrow=true
```


## 后台编码
 
