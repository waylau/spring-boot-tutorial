# Shiro 集成


在 `bootstrap-in-action`项目基础上，我们构建了一个新的项目`shiro-in-action`。项目的包名也做了调整，改为`com.waylau.spring.boot.shiro`。

## 所需环境

本例子采用的开发环境如下：

* Apache Shiro 1.3.2
* Thymeleaf Shiro Dialect 2.0.0

## build.gradle

修改 build.gradle 文件，让我们的`shiro-in-action`项目成为一个新的项目。

修改内容也比较简单，修改项目名称及版本即可。

```groovy
jar {
	baseName = 'shiro-in-action'
	version = '1.0.0'
}
```
 
同时，我们需要添加  Shiro 的依赖。

```groovy
// 依赖关系
dependencies {
	...
 	// 添加  Shiro 依赖
	compile('org.apache.shiro:shiro-core:1.3.2')
	compile('org.apache.shiro:shiro-web:1.3.2')
	compile('org.apache.shiro:shiro-aspectj:1.3.2')
	compile('org.apache.shiro:shiro-spring:1.3.2') 
	
	// 添加  Thymeleaf Shiro Dialect 依赖
	compile('com.github.theborakompanioni:thymeleaf-extras-shiro:2.0.0')
 	...
}
```
 
