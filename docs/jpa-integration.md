# Spring Data JPA、Hiberate 与 Spring Boot 集成


在 `thymeleaf-in-action`项目基础上，我们构建了一个新的项目`jpa-in-action`。项目的包名也做了调整，改为`com.waylau.spring.boot.jpa`。

## 所需环境

本例子采用的开发环境如下：

* Gradle 3.4.1
* Spring Boot 1.5.2.RELEASE 
* Spring Data JPA 1.11.1.RELEASE
* MySQL Connector/J  6.0.5

## build.gradle

修改 build.gradle 文件，让我们的`jpa-in-action`项目成为一个新的项目。

修改内容也比较简单，修改项目名称及版本即可。

```groovy
jar {
	baseName = 'jpa-in-action'
	version = '1.0.0'
}
```
 
同时，我们需要添加 Spring Data JPA 及 MySQL 连接器的依赖。

```groovy
// 依赖关系
dependencies {
	...
 
	// 添加 Spring Data JPA 和 MySQL Connector/J  的依赖
	compile('org.springframework.boot:spring-boot-starter-data-jpa')
	
	// 添加 MySQL连接驱动 的依赖
	compile('mysql:mysql-connector-java:6.0.5')
 	...
}
```

	
`spring-boot-starter-data-jpa`库同时提供了如下依赖：

* Hibernate
* Spring Data JPA
* Spring ORM


由于`spring-boot-starter-data-jpa`库所依赖的 Hibernate 版本为 5.0.12.Final，为了尝试新技术，我们版本升级到 5.2.8.Final。


```
buildscript {
	...
	// 自定义  Hibernate 的版本
	ext['hibernate.version'] = '5.2.8.Final'
 	...	
}
```
## 升级 Gradle Wrapper

由于近期 Gradle 刚升级到了 3.4.1 版本，所以，我们紧跟潮流，设置 Gradle Wrapper使用 3.4.1 最新版本。

修改`gradle/wrapper/gradle-wrapper.properties`文件：

```
distributionUrl=https\://services.gradle.org/distributions/gradle-3.4.1-bin.zip
```

