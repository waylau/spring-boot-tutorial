# 博客系统的用户管理实现


在 `security-in-action`项目基础上，我们构建了一个新的项目`blog-user`。项目的包名也做了调整，改为`com.waylau.spring.boot.blog`。
 
## 实现功能

* 新增、删除、编辑用户
* 分页查询；
* 批量删除；
* 用户名模糊搜索；
* 注册


## 环境

* Font-Awesome 4.7.0
    
## build.gradle

修改 build.gradle 文件，让我们的`blog-user`项目成为一个新的项目。

修改内容也比较简单，修改项目名称及版本即可。

```groovy
jar {
	baseName = 'blog-user'
	version = '1.0.0'
}
```


为了方便进行单元测试，我们添加了内嵌的 H2 数据库，并设置该库的范围类型为运行时（runtime）：

	
```groovy
// 依赖关系
dependencies {
	......
	// 添加 H2  的依赖
	runtime('com.h2database:h2:1.4.193')
 	......
}
```


## 问题解决

### 问题1

```
org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration': Unsatisfied dependency expressed through constructor parameter 0; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'dataSource': Invocation of init method failed; nested exception is java.lang.IllegalStateException: Failed to replace DataSource with an embedded database for tests. If you want an embedded database please put a supported one on the classpath or tune the replace attribute of @AutoconfigureTestDatabase.
```

在测试类上，添加 `@AutoconfigureTestDatabase`


### 问题2：Thymeleaf 取值 `${page.isLast}`报错



```
Caused by: org.attoparser.ParseException: Exception evaluating SpringEL expression: "page.isLast" (template: "fragments/page" - line 28, col 53)
	at org.attoparser.MarkupParser.parseDocument(MarkupParser.java:393) ~[attoparser-2.0.2.RELEASE.jar:2.0.2.RELEASE]
	at org.attoparser.MarkupParser.parse(MarkupParser.java:257) ~[attoparser-2.0.2.RELEASE.jar:2.0.2.RELEASE]
	at org.thymeleaf.templateparser.markup.AbstractMarkupTemplateParser.parse(AbstractMarkupTemplateParser.java:230) ~[thymeleaf-3.0.3.RELEASE.jar:3.0.3.RELEASE]
	... 87 common frames omitted
```

返回的  org.springframework.data.domain.Page 对象里面找不到 isLast 属性。

解决： 这个是序列化转化的问题。布尔值 isLast 映射为属性是 last，所以改为 `${page.last}`即可。