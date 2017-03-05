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


## 定义实体


修改 User 类，参考 JPA 的规范，将其修改成为实体：

* User 类上增加了`@Entity`注解，以标识其为实体
* `@Id`标识id 字段为主键； `@GeneratedValue(strategy=GenerationType.IDENTITY)`标识id使用数据库的自增长字段为新增加的实体的标识。这种情况下需要数据库提供对自增长字段的支持，一般的数据库如 HSQL、SQL Server、MySQL、DB2、Derby 等数据库都能够提供这种支持；
* 应 JPA 的规范要求，设置无参的构造函数`protected User() {}`，并设为 protected 防止直接被使用；
* 重写 `toString`方法，来将 User 信息自定义输出。



## 修改资源库

修改用户资源库的接口;


 
## 运行
