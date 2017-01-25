# 开启 Spring Boot 的第一个  Web 项目

## 配置环境

本例子采用的开发环境如下：

* JDK 8
* Gradle 3.3
* Eclipse Neon.2 Release (4.6.2)

其中，JDK 的安装，可以参阅 [《Java 编程要点》](https://github.com/waylau/essential-java)；Gradle 的安装可以参阅 [《Gradle 2 用户指南》](https://github.com/waylau/Gradle-2-User-Guide)

## 通过 Spring Initializr 初始化一个 Spring Boot 原型

访问网站 <https://start.spring.io/>，按照输入提示，我们输入相应的项目元数据（Project Metadata）资料，并选择依赖。由于我们是要初始化一个 Web 项目，所以在依赖搜索框里面，我们输入关键字“web”，并且选择“Web:Full-stack web development with Tomcat and Spring MVC”选项。顾名思义，该项目将会采用 Spring MVC 作为 MVC 的框架，并且集成了 Tomcat 作为内嵌的 Web 容器。

![](../images/initializr.jpg)

这里我们采用 Gradle 作为项目管理工具， Spring Boot 版本选型为 1.4.3，点击“Generate Project”，此时，可以下载到以项目“initializr-start”命名的 zip 包。该压缩包包含了这个原型项目的所有源码以及配置。

## 用 Gradle 编译项目

在项目根目录下，执行 `gradle build`进行编译，编辑过程如下：


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
