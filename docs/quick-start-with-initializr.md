# 开启 Spring Boot 的第一个  Web 项目

## 配置环境

本例子采用的开发环境如下：

* JDK 8
* Gradle 3.3
* Eclipse Neon.2 Release (4.6.2)

其中，JDK 的安装，可以参阅 [《Java 编程要点》](https://github.com/waylau/essential-java)；Gradle 的安装可以参阅 [《Gradle 3 用户指南》](https://github.com/waylau/gradle-3-user-guide)

## 通过 Spring Initializr 初始化一个 Spring Boot 原型

访问网站 <https://start.spring.io/>，按照输入提示，我们输入相应的项目元数据（Project Metadata）资料，并选择依赖。由于我们是要初始化一个 Web 项目，所以在依赖搜索框里面，我们输入关键字“web”，并且选择“Web:Full-stack web development with Tomcat and Spring MVC”选项。顾名思义，该项目将会采用 Spring MVC 作为 MVC 的框架，并且集成了 Tomcat 作为内嵌的 Web 容器。

![](../images/initializr.jpg)

这里我们采用 Gradle 作为项目管理工具， Spring Boot 版本选型为 1.4.3，点击“Generate Project”，此时，可以下载到以项目“initializr-start”命名的 zip 包。该压缩包包含了这个原型项目的所有源码以及配置。

## 用 Gradle 编译项目

在项目根目录下，执行 `gradle build`进行构建，构建过程如下：


```
D:\workspaceGithub\spring-boot-tutorial\samples\initializr-start>gradle build
Starting a Gradle Daemon (subsequent builds will be faster)
Download https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-gradle-plugin/1.4.3.RELEASE/spring-boot-gradle-plugin-1.4.3.RELEASE.pom
Download https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-tools/1.4.3.RELEASE/spring-boot-tools-1.4.3.RELEASE.pom
Download https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-parent/1.4.3.RELEASE/spring-boot-parent-1.4.3.RELEASE.pom
Download https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-dependencies/1.4.3.RELEASE/spring-boot-dependencies-1.4.3.RELEASE.pom
Download https://repo1.maven.org/maven2/org/springframework/spring-framework-bom/4.3.5.RELEASE/spring-framework-bom-4.3.5.RELEASE.pom
Download https://repo1.maven.org/maven2/org/springframework/data/spring-data-releasetrain/Hopper-SR6/spring-data-releasetrain-Hopper-SR6.pom
Download https://repo1.maven.org/maven2/org/springframework/data/build/spring-data-build/1.8.6.RELEASE/spring-data-build-1.8.6.RELEASE.pom
Download https://repo1.maven.org/maven2/org/springframework/integration/spring-integration-bom/4.3.6.RELEASE/spring-integration-bom-4.3.6.RELEASE.pom
Download https://repo1.maven.org/maven2/org/springframework/security/spring-security-bom/4.1.4.RELEASE/spring-security-bom-4.1.4.RELEASE.pom
Download https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-loader-tools/1.4.3.RELEASE/spring-boot-loader-tools-1.4.3.RELEASE.pom
Download https://repo1.maven.org/maven2/io/spring/gradle/dependency-management-plugin/0.6.1.RELEASE/dependency-management-plugin-0.6.1.RELEASE.pom
Download https://repo1.maven.org/maven2/org/springframework/spring-core/4.3.5.RELEASE/spring-core-4.3.5.RELEASE.pom
Download https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-gradle-plugin/1.4.3.RELEASE/spring-boot-gradle-plugin-1.4.3.RELEASE.jar
Download https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-loader-tools/1.4.3.RELEASE/spring-boot-loader-tools-1.4.3.RELEASE.jar
Download https://repo1.maven.org/maven2/io/spring/gradle/dependency-management-plugin/0.6.1.RELEASE/dependency-management-plugin-0.6.1.RELEASE.jar
Download https://repo1.maven.org/maven2/org/springframework/spring-core/4.3.5.RELEASE/spring-core-4.3.5.RELEASE.jar
:compileJava
Download https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-starter-parent/1.4.3.RELEASE/spring-boot-starter-parent-1.4.3.RELEASE.pom
Download https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-starter-web/1.4.3.RELEASE/spring-boot-starter-web-1.4.3.RELEASE.pom
Download https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-starters/1.4.3.RELEASE/spring-boot-starters-1.4.3.RELEASE.pom
Download https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-starter/1.4.3.RELEASE/spring-boot-starter-1.4.3.RELEASE.pom
Download https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-starter-tomcat/1.4.3.RELEASE/spring-boot-starter-tomcat-1.4.3.RELEASE.pom
Download https://repo1.maven.org/maven2/org/hibernate/hibernate-validator/5.2.4.Final/hibernate-validator-5.2.4.Final.pom
Download https://repo1.maven.org/maven2/org/hibernate/hibernate-validator-parent/5.2.4.Final/hibernate-validator-parent-5.2.4.Final.pom
Download https://repo1.maven.org/maven2/org/jboss/arquillian/arquillian-bom/1.1.9.Final/arquillian-bom-1.1.9.Final.pom
Download https://repo1.maven.org/maven2/org/jboss/shrinkwrap/shrinkwrap-bom/1.2.2/shrinkwrap-bom-1.2.2.pom
Download https://repo1.maven.org/maven2/org/jboss/shrinkwrap/resolver/shrinkwrap-resolver-bom/2.1.1/shrinkwrap-resolver-bom-2.1.1.pom
Download https://repo1.maven.org/maven2/org/jboss/shrinkwrap/descriptors/shrinkwrap-descriptors-bom/2.0.0-alpha-7/shrinkwrap-descriptors-bom-2.0.0-alpha-7.pom
Download https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.8.5/jackson-databind-2.8.5.pom
Download https://repo1.maven.org/maven2/com/fasterxml/jackson/jackson-parent/2.8/jackson-parent-2.8.pom
Download https://repo1.maven.org/maven2/com/fasterxml/oss-parent/27/oss-parent-27.pom
Download https://repo1.maven.org/maven2/org/springframework/spring-web/4.3.5.RELEASE/spring-web-4.3.5.RELEASE.pom
Download https://repo1.maven.org/maven2/org/springframework/spring-webmvc/4.3.5.RELEASE/spring-webmvc-4.3.5.RELEASE.pom
Download https://repo1.maven.org/maven2/org/springframework/boot/spring-boot/1.4.3.RELEASE/spring-boot-1.4.3.RELEASE.pom
Download https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-autoconfigure/1.4.3.RELEASE/spring-boot-autoconfigure-1.4.3.RELEASE.pom
Download https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-starter-logging/1.4.3.RELEASE/spring-boot-starter-logging-1.4.3.RELEASE.pom
Download https://repo1.maven.org/maven2/org/yaml/snakeyaml/1.17/snakeyaml-1.17.pom
Download https://repo1.maven.org/maven2/org/apache/tomcat/embed/tomcat-embed-core/8.5.6/tomcat-embed-core-8.5.6.pom
Download https://repo1.maven.org/maven2/org/apache/tomcat/embed/tomcat-embed-el/8.5.6/tomcat-embed-el-8.5.6.pom
Download https://repo1.maven.org/maven2/org/apache/tomcat/embed/tomcat-embed-websocket/8.5.6/tomcat-embed-websocket-8.5.6.pom
Download https://repo1.maven.org/maven2/org/jboss/logging/jboss-logging/3.3.0.Final/jboss-logging-3.3.0.Final.pom
Download https://repo1.maven.org/maven2/org/jboss/jboss-parent/15/jboss-parent-15.pom
Download https://repo1.maven.org/maven2/com/fasterxml/classmate/1.3.3/classmate-1.3.3.pom
Download https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.8.5/jackson-annotations-2.8.5.pom
Download https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/2.8.5/jackson-core-2.8.5.pom
Download https://repo1.maven.org/maven2/org/springframework/spring-aop/4.3.5.RELEASE/spring-aop-4.3.5.RELEASE.pom
Download https://repo1.maven.org/maven2/org/springframework/spring-beans/4.3.5.RELEASE/spring-beans-4.3.5.RELEASE.pom
Download https://repo1.maven.org/maven2/org/springframework/spring-context/4.3.5.RELEASE/spring-context-4.3.5.RELEASE.pom
Download https://repo1.maven.org/maven2/org/springframework/spring-expression/4.3.5.RELEASE/spring-expression-4.3.5.RELEASE.pom
Download https://repo1.maven.org/maven2/ch/qos/logback/logback-classic/1.1.8/logback-classic-1.1.8.pom
Download https://repo1.maven.org/maven2/ch/qos/logback/logback-parent/1.1.8/logback-parent-1.1.8.pom
Download https://repo1.maven.org/maven2/org/slf4j/jcl-over-slf4j/1.7.22/jcl-over-slf4j-1.7.22.pom
Download https://repo1.maven.org/maven2/org/slf4j/slf4j-parent/1.7.22/slf4j-parent-1.7.22.pom
Download https://repo1.maven.org/maven2/org/slf4j/jul-to-slf4j/1.7.22/jul-to-slf4j-1.7.22.pom
Download https://repo1.maven.org/maven2/org/slf4j/log4j-over-slf4j/1.7.22/log4j-over-slf4j-1.7.22.pom
Download https://repo1.maven.org/maven2/ch/qos/logback/logback-core/1.1.8/logback-core-1.1.8.pom
Download https://repo1.maven.org/maven2/org/slf4j/slf4j-api/1.7.22/slf4j-api-1.7.22.pom
Download https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-starter-web/1.4.3.RELEASE/spring-boot-starter-web-1.4.3.RELEASE.jar
Download https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-starter/1.4.3.RELEASE/spring-boot-starter-1.4.3.RELEASE.jar
Download https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-starter-tomcat/1.4.3.RELEASE/spring-boot-starter-tomcat-1.4.3.RELEASE.jar
Download https://repo1.maven.org/maven2/org/hibernate/hibernate-validator/5.2.4.Final/hibernate-validator-5.2.4.Final.jar
Download https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.8.5/jackson-databind-2.8.5.jar
Download https://repo1.maven.org/maven2/org/springframework/spring-web/4.3.5.RELEASE/spring-web-4.3.5.RELEASE.jar
Download https://repo1.maven.org/maven2/org/springframework/spring-webmvc/4.3.5.RELEASE/spring-webmvc-4.3.5.RELEASE.jar
Download https://repo1.maven.org/maven2/org/springframework/boot/spring-boot/1.4.3.RELEASE/spring-boot-1.4.3.RELEASE.jar
Download https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-autoconfigure/1.4.3.RELEASE/spring-boot-autoconfigure-1.4.3.RELEASE.jar
Download https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-starter-logging/1.4.3.RELEASE/spring-boot-starter-logging-1.4.3.RELEASE.jar
Download https://repo1.maven.org/maven2/org/yaml/snakeyaml/1.17/snakeyaml-1.17.jar
Download https://repo1.maven.org/maven2/org/apache/tomcat/embed/tomcat-embed-core/8.5.6/tomcat-embed-core-8.5.6.jar
Download https://repo1.maven.org/maven2/org/apache/tomcat/embed/tomcat-embed-el/8.5.6/tomcat-embed-el-8.5.6.jar
Download https://repo1.maven.org/maven2/org/apache/tomcat/embed/tomcat-embed-websocket/8.5.6/tomcat-embed-websocket-8.5.6.jar
Download https://repo1.maven.org/maven2/org/jboss/logging/jboss-logging/3.3.0.Final/jboss-logging-3.3.0.Final.jar
Download https://repo1.maven.org/maven2/com/fasterxml/classmate/1.3.3/classmate-1.3.3.jar
Download https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.8.5/jackson-annotations-2.8.5.jar
Download https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/2.8.5/jackson-core-2.8.5.jar
Download https://repo1.maven.org/maven2/org/springframework/spring-aop/4.3.5.RELEASE/spring-aop-4.3.5.RELEASE.jar
Download https://repo1.maven.org/maven2/org/springframework/spring-beans/4.3.5.RELEASE/spring-beans-4.3.5.RELEASE.jar
Download https://repo1.maven.org/maven2/org/springframework/spring-context/4.3.5.RELEASE/spring-context-4.3.5.RELEASE.jar
Download https://repo1.maven.org/maven2/org/springframework/spring-expression/4.3.5.RELEASE/spring-expression-4.3.5.RELEASE.jar
Download https://repo1.maven.org/maven2/ch/qos/logback/logback-classic/1.1.8/logback-classic-1.1.8.jar
Download https://repo1.maven.org/maven2/org/slf4j/jcl-over-slf4j/1.7.22/jcl-over-slf4j-1.7.22.jar
Download https://repo1.maven.org/maven2/org/slf4j/jul-to-slf4j/1.7.22/jul-to-slf4j-1.7.22.jar
Download https://repo1.maven.org/maven2/org/slf4j/log4j-over-slf4j/1.7.22/log4j-over-slf4j-1.7.22.jar
Download https://repo1.maven.org/maven2/ch/qos/logback/logback-core/1.1.8/logback-core-1.1.8.jar
Download https://repo1.maven.org/maven2/org/slf4j/slf4j-api/1.7.22/slf4j-api-1.7.22.jar
:processResources
:classes
:findMainClass
:jar
:bootRepackage
:assemble
:compileTestJava
Download https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-starter-test/1.4.3.RELEASE/spring-boot-starter-test-1.4.3.RELEASE.pom
Download https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-test/1.4.3.RELEASE/spring-boot-test-1.4.3.RELEASE.pom
Download https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-test-autoconfigure/1.4.3.RELEASE/spring-boot-test-autoconfigure-1.4.3.RELEASE.pom
Download https://repo1.maven.org/maven2/com/jayway/jsonpath/json-path/2.2.0/json-path-2.2.0.pom
Download https://repo1.maven.org/maven2/org/assertj/assertj-core/2.5.0/assertj-core-2.5.0.pom
Download https://repo1.maven.org/maven2/org/assertj/assertj-parent-pom/2.1.4/assertj-parent-pom-2.1.4.pom
Download https://repo1.maven.org/maven2/org/mockito/mockito-core/1.10.19/mockito-core-1.10.19.pom
Download https://repo1.maven.org/maven2/org/skyscreamer/jsonassert/1.3.0/jsonassert-1.3.0.pom
Download https://repo1.maven.org/maven2/org/springframework/spring-test/4.3.5.RELEASE/spring-test-4.3.5.RELEASE.pom
Download https://repo1.maven.org/maven2/net/minidev/json-smart/2.2.1/json-smart-2.2.1.pom
Download https://repo1.maven.org/maven2/org/objenesis/objenesis/2.1/objenesis-2.1.pom
Download https://repo1.maven.org/maven2/org/objenesis/objenesis-parent/2.1/objenesis-parent-2.1.pom
Download https://repo1.maven.org/maven2/org/json/json/20140107/json-20140107.pom
Download https://repo1.maven.org/maven2/net/minidev/accessors-smart/1.1/accessors-smart-1.1.pom
Download https://repo1.maven.org/maven2/net/minidev/minidev-parent/2.2/minidev-parent-2.2.pom
Download https://repo1.maven.org/maven2/org/ow2/asm/asm/5.0.3/asm-5.0.3.pom
Download https://repo1.maven.org/maven2/org/ow2/asm/asm-parent/5.0.3/asm-parent-5.0.3.pom
Download https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-starter-test/1.4.3.RELEASE/spring-boot-starter-test-1.4.3.RELEASE.jar
Download https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-test/1.4.3.RELEASE/spring-boot-test-1.4.3.RELEASE.jar
Download https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-test-autoconfigure/1.4.3.RELEASE/spring-boot-test-autoconfigure-1.4.3.RELEASE.jar
Download https://repo1.maven.org/maven2/com/jayway/jsonpath/json-path/2.2.0/json-path-2.2.0.jar
Download https://repo1.maven.org/maven2/org/assertj/assertj-core/2.5.0/assertj-core-2.5.0.jar
Download https://repo1.maven.org/maven2/org/mockito/mockito-core/1.10.19/mockito-core-1.10.19.jar
Download https://repo1.maven.org/maven2/org/skyscreamer/jsonassert/1.3.0/jsonassert-1.3.0.jar
Download https://repo1.maven.org/maven2/org/springframework/spring-test/4.3.5.RELEASE/spring-test-4.3.5.RELEASE.jar
Download https://repo1.maven.org/maven2/net/minidev/json-smart/2.2.1/json-smart-2.2.1.jar
Download https://repo1.maven.org/maven2/org/objenesis/objenesis/2.1/objenesis-2.1.jar
Download https://repo1.maven.org/maven2/org/json/json/20140107/json-20140107.jar
Download https://repo1.maven.org/maven2/net/minidev/accessors-smart/1.1/accessors-smart-1.1.jar
Download https://repo1.maven.org/maven2/org/ow2/asm/asm/5.0.3/asm-5.0.3.jar
:processTestResources UP-TO-DATE
:testClasses
:test
:check
:build

BUILD SUCCESSFUL

Total time: 7 mins 53.226 secs
```

编译成功后，可以看到根目录下多出了一个 `build`目录，在该目录`build/libs`下可以看到一个 `initializr-start-0.0.1-SNAPSHOT.jar`，该文件就是我们项目编译后的可执行文件。运行该文件：

```
java -jar build/libs/initializr-start-0.0.1-SNAPSHOT.jar
```


成功运行后，可以在控制台看到如下输出：

```
D:\workspaceGithub\spring-boot-tutorial\samples\initializr-start>java -jar build/libs/initializr-start-0.0.1-SNAPSHOT.jar

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.4.3.RELEASE)

2017-01-13 22:34:48.625  INFO 11116 --- [           main] c.w.s.boot.InitializrStartApplication    : Starting InitializrStartApplication on AFOIA-610151209 with PID 11116 (D:\workspaceGithub\spring-boot-tutorial\samples\initializr-start\build\libs\initializr-start-0.0.1-SNAPSHOT.jar started by Administrator in D:\workspaceGithub\spring-boot-tutorial\samples\initializr-start)
2017-01-13 22:34:48.631  INFO 11116 --- [           main] c.w.s.boot.InitializrStartApplication    : No active profile set, falling back to default profiles: default
2017-01-13 22:34:48.777  INFO 11116 --- [           main] ationConfigEmbeddedWebApplicationContext : Refreshing org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@45283ce2: startup date [Fri Jan 13 22:34:48 CST 2017]; root of context hierarchy
2017-01-13 22:34:51.477  INFO 11116 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat initialized with port(s): 8080 (http)
2017-01-13 22:34:51.537  INFO 11116 --- [           main] o.apache.catalina.core.StandardService   : Starting service Tomcat
2017-01-13 22:34:51.539  INFO 11116 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/8.5.6
2017-01-13 22:34:51.746  INFO 11116 --- [ost-startStop-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2017-01-13 22:34:51.747  INFO 11116 --- [ost-startStop-1] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 2974 ms
2017-01-13 22:34:51.995  INFO 11116 --- [ost-startStop-1] o.s.b.w.servlet.ServletRegistrationBean  : Mapping servlet: 'dispatcherServlet' to [/]
2017-01-13 22:34:52.005  INFO 11116 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'characterEncodingFilter' to: [/*]
2017-01-13 22:34:52.006  INFO 11116 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'hiddenHttpMethodFilter' to: [/*]
2017-01-13 22:34:52.006  INFO 11116 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'httpPutFormContentFilter' to: [/*]
2017-01-13 22:34:52.007  INFO 11116 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'requestContextFilter' to: [/*]
2017-01-13 22:34:52.440  INFO 11116 --- [           main] s.w.s.m.m.a.RequestMappingHandlerAdapter : Looking for @ControllerAdvice: org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@45283ce2: startup date [Fri Jan 13 22:34:48 CST 2017]; root of context hierarchy
2017-01-13 22:34:52.556  INFO 11116 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error]}" onto public org.springframework.http.ResponseEntity<java.util.Map<java.lang.String, java.lang.Object>> org.springframework.boot.autoconfigure.web.BasicErrorController.error(javax.servlet.http.HttpServletRequest)
2017-01-13 22:34:52.557  INFO 11116 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error],produces=[text/html]}" onto public org.springframework.web.servlet.ModelAndView org.springframework.boot.autoconfigure.web.BasicErrorController.errorHtml(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
2017-01-13 22:34:52.604  INFO 11116 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/webjars/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-01-13 22:34:52.604  INFO 11116 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-01-13 22:34:52.660  INFO 11116 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**/favicon.ico] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-01-13 22:34:52.877  INFO 11116 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2017-01-13 22:34:53.070  INFO 11116 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
2017-01-13 22:34:53.080  INFO 11116 --- [           main] c.w.s.boot.InitializrStartApplication    : Started InitializrStartApplication in 5.397 seconds (JVM running for 6.512)
```

从输出内容我们可以看到，该项目是使用的 Tomcat 容器，使用的端口号是 8080。

在控制台输入“Ctrl + C”，可以关闭该程序。

## 探索项目

在启动项目后，在浏览器里面输入 <http://localhost:8080/>，我们可以得到如下信息：

```
Whitelabel Error Page

This application has no explicit mapping for /error, so you are seeing this as a fallback.
Wed Jan 25 21:33:34 CST 2017
There was an unexpected error (type=Not Found, status=404).
No message available
```

由于，在我们项目里面，还没有任何对请求的处理程序，所以返回了上述错误提示信息。

我们观察下这个项目的目录结构：

```
initializr-start
│  .gitignore
│  build.gradle
│  gradlew
│  gradlew.bat
│
├─.gradle
│  └─3.3
│      └─taskArtifacts
│              fileHashes.bin
│              fileSnapshots.bin
│              taskArtifacts.bin
│              taskArtifacts.lock
│
├─build
│  ├─classes
│  │  ├─main
│  │  │  └─com
│  │  │      └─waylau
│  │  │          └─spring
│  │  │              └─boot
│  │  │                      InitializrStartApplication.class
│  │  │
│  │  └─test
│  │      └─com
│  │          └─waylau
│  │              └─spring
│  │                  └─boot
│  │                          InitializrStartApplicationTests.class
│  │
│  ├─libs
│  │      initializr-start-0.0.1-SNAPSHOT.jar
│  │      initializr-start-0.0.1-SNAPSHOT.jar.original
│  │
│  ├─reports
│  │  └─tests
│  │      └─test
│  │          │  index.html
│  │          │
│  │          ├─classes
│  │          │      com.waylau.spring.boot.InitializrStartApplicationTests.html
│  │          │
│  │          ├─css
│  │          │      base-style.css
│  │          │      style.css
│  │          │
│  │          ├─js
│  │          │      report.js
│  │          │
│  │          └─packages
│  │                  com.waylau.spring.boot.html
│  │
│  ├─resources
│  │  └─main
│  │          application.properties
│  │
│  ├─test-results
│  │  └─test
│  │      │  TEST-com.waylau.spring.boot.InitializrStartApplicationTests.xml
│  │      │
│  │      └─binary
│  │              output.bin
│  │              output.bin.idx
│  │              results.bin
│  │
│  └─tmp
│      ├─compileJava
│      │  └─emptySourcePathRef
│      ├─compileTestJava
│      │  └─emptySourcePathRef
│      └─jar
│              MANIFEST.MF
│
├─gradle
│  └─wrapper
│          gradle-wrapper.jar
│          gradle-wrapper.properties
│
└─src
    ├─main
    │  ├─java
    │  │  └─com
    │  │      └─waylau
    │  │          └─spring
    │  │              └─boot
    │  │                      InitializrStartApplication.java
    │  │
    │  └─resources
    │          application.properties
    │
    └─test
        └─java
            └─com
                └─waylau
                    └─spring
                        └─boot
                                InitializrStartApplicationTests.java
```

### 1. build.gradle 文件 

在项目的根目录，我们可以看到`build.gradle` 文件，这个是项目的构建脚本。Gradle 是以 Groovy 语言为基础，面向 Java 应用为主。基于DSL（领域特定语言）语法的自动化构建工具。Gradle这个工具集成了构建，测试，发布和其他，比如软件打包，生成注释文档等功能。跟以往 Maven 等构架工具不同，配置文件不需要繁琐的 XML ，而是简洁的 Groovy 语言脚本。

本项目的配置说明，我已经做了解释，如下：

```
// buildscript 代码块中脚本优先执行
buildscript {

	// ext 用于定义动态属
	ext {
		springBootVersion = '1.4.3.RELEASE'
	}
	
	// 使用了 Maven 的中央仓库（你也可以指定其他仓库）
	repositories {
		mavenCentral()
	}
	
	// 依赖关系
	dependencies {
		// classpath 声明说明了在执行其余的脚本时，ClassLoader 可以使用这些依赖项
		classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
	}
}

// 使用插件
apply plugin: 'java'
apply plugin: 'eclipse'
apply plugin: 'org.springframework.boot'

// 打包的类型为 jar，并指定了生成的打包的文件名称和版本
jar {
	baseName = 'initializr-start'
	version = '0.0.1-SNAPSHOT'
}

// 指定编译 .java 文件的 JDK 版本
sourceCompatibility = 1.8

// 使用了 Maven 的中央仓库
repositories {
	mavenCentral()
}

// 依赖关系
dependencies {
	// 该依赖对于编译发行是必须的
	compile('org.springframework.boot:spring-boot-starter-web')
	// 该依赖对于编译测试是必须的，默认包含编译产品依赖和编译时依
	testCompile('org.springframework.boot:spring-boot-starter-test')
}

```

### 2. gradlew 和 gradlew.bat

自动完成  Gradle 环境的脚本，在类似 Unix 的平台上(如 Linux 和 Mac OS)，下直接运行`gradlew`脚本 会自动完成 Gradle 环境的搭建。而在 Windouws 环境下，则执行  `gradlew.bat` 文件。

### 3. build 和 .gradle 目录

`build` 和`.gradle` 目录都是在 Gradle 对项目进行构建后生成的。

### 4. gradle/wrapper

Gradle Wrapper 可以使得项目组成员不必预先安装好 Gradle，就会自动下载 Gradle。好处是便于统一项目所使用的 Gradle 版本。由于本例已经事先安装好了 Gradle，所以并没有用到 Gradle Wrapper。 `gradle-wrapper.properties`是用来 说明 Gradle Wrapper 的配置情况。

```
distributionUrl=https\://services.gradle.org/distributions/gradle-2.13-bin.zip
```

其中 `distributionUrl` 说明了 Gradle 发布包下载的路径。从上述配置可以看出， Spring Boot 采用的是 Gradle 2.13 版本。

### 5. src 目录

如果你用过 Maven，那么肯定对 `src` 目录不默认。约定该目录下的  `main`目录下是 程序的源码，`test`下是测试代码。
