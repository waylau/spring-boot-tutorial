# Elasticsearch 与 Spring Boot 集成

在 `media-type`项目基础上，我们构建了一个新的项目`elasticsearch-in-action`。项目的包名也做了调整，改为`com.waylau.spring.boot.elasticsearch`。

## 环境

* ~~Elasticsearch 5.2.2~~
* JNA 4.3.0


## build.gradle

修改 build.gradle 文件，让我们的`elasticsearch-in-action`项目成为一个新的项目。

修改内容也比较简单，修改项目名称及版本即可。

```groovy
jar {
	baseName = 'elasticsearch-in-action'
	version = '1.0.0'
}
```

同时，我们需要添加 Elasticsearch、JNA 的依赖。

```groovy
// 依赖关系
dependencies {
	...
 
	// 添加  Spring Data Elasticsearch 的依赖
	compile('org.springframework.boot:spring-boot-starter-data-elasticsearch')
	
	// 添加  JNA 的依赖
	compile('net.java.dev.jna:jna:4.3.0')
 	...
}
```
	
	
由于 `spring-boot-starter-data-elasticsearch`库，默认使用的是 Elasticsearch 2.4.4 版本。为了学习最前沿的技术，我们采用了 Elasticsearch 最新的版本[Elasticsearch 5.2.2](https://www.elastic.co)。我们要使用 Elasticsearch 5.2.2去替换 Spring Boot 依赖库中的 Elasticsearch 版本号，如下：


```groovy
buildscript {
	......
	
	// 自定义 Elasticsearch 的版本
	ext['elasticsearch.version'] = '5.2.2'

	......
}
```


## 升级 Gradle Wrapper

由于近期 Gradle 刚升级到了 3.4.1 版本，所以，我们紧跟潮流，设置 Gradle Wrapper使用 3.4.1 最新版本。

修改`gradle/wrapper/gradle-wrapper.properties`文件：

```
distributionUrl=https\://services.gradle.org/distributions/gradle-3.4.1-bin.zip


```