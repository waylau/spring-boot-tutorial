# Spring Data JPA、Hiberate 与 Spring Boot 集成


在 `thymeleaf-in-action`项目基础上，我们构建了一个新的项目`jpa-in-action`。项目的包名也做了调整，改为`com.waylau.spring.boot.jpa`。

## 所需环境

本例子采用的开发环境如下：

* Gradle 3.4.1
* Spring Boot 1.5.2.RELEASE 
* Spring Data JPA 1.11.1.RELEASE


## build.gradle

修改 build.gradle 文件，让我们的`jpa-in-action`项目成为一个新的项目。

修改内容也比较简单，修改项目名称及版本即可。

```groovy
jar {
	baseName = 'jpa-in-action'
	version = '1.0.0'
}
```
 
同时，我们需要添加 Spring Data JPA 的依赖。

```groovy
// 依赖关系
dependencies {
	...
 
	// 添加 Spring Data JPA 的依赖
	compile('org.springframework.boot:spring-boot-starter-data-jpa')
	
 	...
}
```

	
`spring-boot-starter-data-jpa`库同时提供了如下依赖：

* Hibernate
* Spring Data JPA
* Spring ORM

## 升级 Gradle Wrapper

由于近期 Gradle 刚升级到了 3.4.1 版本，所以，我们紧跟潮流，设置 Gradle Wrapper使用 3.4.1 最新版本。

修改`gradle/wrapper/gradle-wrapper.properties`文件：

```
distributionUrl=https\://services.gradle.org/distributions/gradle-3.4.1-bin.zip
```

