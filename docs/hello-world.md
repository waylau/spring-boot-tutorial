# Hello World

按照惯例，我们来编写一个最简单的 Web 项目。当我们访问项目时，界面会打印出 “Hello World”字样。

## 编写项目构建信息

我们复制我们在上一节用到的样例程序“initializr-start”，在该项目上做一点小变更，就能生成一个新项目的构建信息。

我们打开`build.gradle` 文件，做了如下变更。

### 1. 自定义依赖的版本

我们可以自定义 Spring Boot 的版本，比如，我们使用了目前最新的 1.5.1.RELEASE 版本。

```
ext {
	springBootVersion = '1.5.1.RELEASE'
}
```

### 2. 修改项目的名称

修改项目的名称为“hello-world”：

```
jar {
	baseName = 'hello-world'
	version = '0.0.1-SNAPSHOT'
}
```

### 3. 修改项目的仓库地址

为了加快构建速度，我们自定义了一个国内的仓库镜像地址：

```
repositories {
	maven {
        url 'http://maven.aliyun.com/nexus/content/groups/public/'
    }
}
```

好了，我们尝试执行 `gradle build`来对 “hello-world” 程序进行构建。如果构建成功，则说明构建信息一切正常。


## 编写程序代码

好了，现在终于可以进入编写代码的时间了。我们进入 src 目录下，我们应该能够看到 `com.waylau.spring.boot`包以及 `InitializrStartApplication.java` 文件。为了规范，我们将该包名改为 `com.waylau.spring.boot.hello`，将`InitializrStartApplication.java`更名为`Application.java`

## 1. 观察 Application.java

打开 Application.java 文件，我们首先看到的是`@SpringBootApplication`注解。对于经常使用 Spring 用户而言，很多开发者总是使用`@Configuration`、`@EnableAutoConfiguration`和`@ComponentScan`注解他们的 main 类。由于这些注解被如此频繁地一块使用，Spring Boot提供一个方便的`@SpringBootApplication`选择。该 
`@SpringBootApplication`注解等同于默认属性使用 `@Configuration`、 `@EnableAutoConfiguration` 和 `@ComponentScan` 的默认属性。

```
@SpringBootApplication = (默认属性)@Configuration + @EnableAutoConfiguration + @ComponentScan 
```

*  `@Configuration`：提到`@Configuration`经常与`@Bean`组合使用，使用这两个注解就可以创建一个简单的 Spring 配置类，可以用来替代相应的 XML 配置文件。`@Configuration`的注解类标识这个类可以使用Spring IoC 容器作为 bean 定义的来源。`@Bean`注解告诉Spring，一个带有`@Bean`的注解方法将返回一个对象，该对象应该被注册为在Spring应用程序上下文中的bean。
* `@EnableAutoConfiguration`：能够自动配置 Spring 的上下文，试图猜测和配置你想要的bean类，通常会自动根据你的类路径和你的bean定义自动配置。
* `@ComponentScan`：会自动扫描指定包下的全部标有`@Component`的类，并注册成bean，当然也包括`@Component`下的子注解`@Service`、`@Repository`、`@Controller`。这些bean一般是结合`@Autowired`构造函数来注入。


## 2. 编写控制器 HelloController

创建`com.waylau.spring.boot.hello.controller`包，用于放置控制器类。

HelloController.java 的代码非常简单。当请求到`/hello` 路径时，将会响应`"Hello World!`字样的字符串。代码如下：

```java
@RestController
public class HelloController {

	@RequestMapping("/hello")
	public String hello() {
	    return "Hello World! Welcome to visit waylau.com!";
	}
}
```

其中`@RestController`等价于`@Controller`与`@ResponseBody`的组合。

```
@RestController = @Controller + @ResponseBody 
```

* `@ResponseBody`:该注解指示方法返回值的应绑定到 Web 响应正文。
* `@RequestMapping`:是一个用来处理请求地址映射的注解，可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。根据方法的不同，还可以用`GetMapping`、`PostMapping`、`PutMapping`、`DeleteMapping`、`PatchMapping`代替。

## 编写测试用例

我们进入 test 目录下，将`com.waylau.spring.boot`包名改为`com.waylau.spring.boot.hello`，并将 `InitializrStartApplicationTests.java` 更名为`ApplicationTests.java`文件。为

### 1. 编写 HelloControllerTest.java 测试类

对源程序一一对应，我们创建`com.waylau.spring.boot.hello.controller`包，用于放置控制器的测试类。

测试类 HelloControllerTest.java 代码如下：

```java
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
    @Test
    public void testHello() throws Exception {
    	mockMvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello World! Welcome to visit waylau.com!")));
    }
}
```

### 2. 运行测试类

用 JUnit 运行该测试，绿色代码测试通过。

## 配置 Gradle Wrapper

Gradle 项目可以使用 Gradle 的安装包进行构建，也可以使用  Gradle Wrapper 来进行构建。使用  Gradle Wrapper 的好处是可以使项目的构建工具版本得到统一。

我们修改 Wrapper 属性文件（位于  gradle/wrapper/gradle-wrapper.properties）中的 distributionUrl 属性，将其改为指定的 Gradle 版本，这里是采用了 Gradle 3.3 版本。

```
distributionUrl=https\://services.gradle.org/distributions/gradle-3.3-bin.zip
```

这样，Gradle Wrapper 会自动安装 Gradle 的版本。

不同平台，执行不同的命令脚本

* gradlew（Unix Shell脚本）
* gradlew.bat（Windows批处理文件）

有关 Gradle Wrapper 的安装可以参阅 [《Gradle 3 用户指南》](https://github.com/waylau/gradle-3-user-guide)

## 运行程序

### 1. 使用  Gradle Wrapper 程序

执行`gradlew`来对 “hello-world” 程序进行构建。如果是首次使用，首先会下载 Gradle 发布包。你可以在`$USER_HOME/.gradle/wrapper/dists`下的用户主目录中找到它们。

```
D:\workspaceGithub\spring-boot-tutorial\samples\hello-world>gradlew build
:compileJava
:processResources
:classes
:findMainClass
:jar
:bootRepackage
:assemble
:compileTestJava
:processTestResources UP-TO-DATE
:testClasses
:test
:check
:build

BUILD SUCCESSFUL
```

### 2. 运行程序

执行 `java -jar build/libs/hello-world-0.0.1-SNAPSHOT.jar`来运行程序

```java
D:\workspaceGithub\spring-boot-tutorial\samples\hello-world>java -jar build/libs/hello-world-0.0.1-SNAPSHOT.jar

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.5.1.RELEASE)

2017-02-01 11:09:26.330  INFO 9108 --- [           main] c.waylau.spring.boot.hello.Application   : Starting Application on DESKTOP-L4SAS32 with PID 9108 (D:\workspaceGithub\spring-boot-tutorial\samples\hello-world\build\libs\hello-world-0.0.1-SNAPSHOT.jar started by AAA in D:\workspaceGithub\spring-boot-tutorial\samples\hello-world)
2017-02-01 11:09:26.344  INFO 9108 --- [           main] c.waylau.spring.boot.hello.Application   : No active profile set, falling back to default profiles: default
2017-02-01 11:09:26.519  INFO 9108 --- [           main] ationConfigEmbeddedWebApplicationContext : Refreshing org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@5f4da5c3: startup date [Wed Feb 01 11:09:26 CST 2017]; root of context hierarchy
2017-02-01 11:09:28.572  INFO 9108 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration' of type [class org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
2017-02-01 11:09:28.790  INFO 9108 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'validator' of type [class org.springframework.validation.beanvalidation.LocalValidatorFactoryBean] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
2017-02-01 11:09:29.702  INFO 9108 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat initialized with port(s): 8080 (http)
2017-02-01 11:09:29.735  INFO 9108 --- [           main] o.apache.catalina.core.StandardService   : Starting service Tomcat
2017-02-01 11:09:29.739  INFO 9108 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/8.5.11
2017-02-01 11:09:30.193  INFO 9108 --- [ost-startStop-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2017-02-01 11:09:30.197  INFO 9108 --- [ost-startStop-1] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 3685 ms
2017-02-01 11:09:30.443  INFO 9108 --- [ost-startStop-1] o.s.b.w.servlet.ServletRegistrationBean  : Mapping servlet: 'dispatcherServlet' to [/]
2017-02-01 11:09:30.452  INFO 9108 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'characterEncodingFilter' to: [/*]
2017-02-01 11:09:30.457  INFO 9108 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'hiddenHttpMethodFilter' to: [/*]
2017-02-01 11:09:30.458  INFO 9108 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'httpPutFormContentFilter' to: [/*]
2017-02-01 11:09:30.460  INFO 9108 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'requestContextFilter' to: [/*]
2017-02-01 11:09:30.973  INFO 9108 --- [           main] s.w.s.m.m.a.RequestMappingHandlerAdapter : Looking for @ControllerAdvice: org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@5f4da5c3: startup date [Wed Feb 01 11:09:26 CST 2017]; root of context hierarchy
2017-02-01 11:09:31.114  INFO 9108 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/hello]}" onto public java.lang.String com.waylau.spring.boot.hello.controller.HelloController.hello()
2017-02-01 11:09:31.123  INFO 9108 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error]}" onto public org.springframework.http.ResponseEntity<java.util.Map<java.lang.String, java.lang.Object>> org.springframework.boot.autoconfigure.web.BasicErrorController.error(javax.servlet.http.HttpServletRequest)
2017-02-01 11:09:31.124  INFO 9108 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error],produces=[text/html]}" onto public org.springframework.web.servlet.ModelAndView org.springframework.boot.autoconfigure.web.BasicErrorController.errorHtml(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
2017-02-01 11:09:31.179  INFO 9108 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/webjars/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-02-01 11:09:31.180  INFO 9108 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-02-01 11:09:31.250  INFO 9108 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**/favicon.ico] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-02-01 11:09:31.503  INFO 9108 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2017-02-01 11:09:31.683  INFO 9108 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
2017-02-01 11:09:31.693  INFO 9108 --- [           main] c.waylau.spring.boot.hello.Application   : Started Application in 6.107 seconds (JVM running for 6.932)
```



### 3. 访问程序

在浏览器，我们访问<http://localhost:8080/hello> ，可以到页面打印出了“Hello World! Welcome to visit waylau.com!”字样。

看到没有，编写一个 Spring Boot 程序就是这么简单！

