# 开启第一个 Spring Boot Web 项目

## 配置环境

本例子采用的开发环境如下：

* JDK 8+
* Gradle 3.3+

其中，JDK 的安装，可以参阅 [《Java 编程要点》](https://github.com/waylau/essential-java)；Gradle 的安装可以参阅 [《Gradle 2 用户指南》](https://github.com/waylau/Gradle-2-User-Guide)

## 通过 Spring Initializr 初始化一个 Spring Boot 原型

访问网站 https://start.spring.io/，安装输入提示，我们输入相应的项目元数据（Project Metadata）资料，并选择依赖。由于我们是要初始化一个 Web 项目，所以在依赖搜索框里面，我们输入关键字“web”，并且选择“Web:Full-stack web development with Tomcat and Spring MVC”选项。顾名思义，该项目将会采用 Spring MVC 作为 MVC 的框架，并且集成了 Tomcat 作为内嵌的 Web 容器。

![](../images/initializr.jpg)

这里我们采用 Gradle 作为项目管理工具， Spring Boot 版本选型为 1.4.3，点击“”，此时，可以下载到以项目“initializr-start”命名的 zip 包。该压缩包包含了这个原型项目的所有源码以及配置。


