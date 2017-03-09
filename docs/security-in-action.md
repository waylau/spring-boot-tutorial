# Spring Security 实战

通过该项目，我们用 Spring Security 来实现对系统的安全管理。

我们在上一节创建的 `security-in-action`项目的基础上，做一些修改。


## 修改application.properties

增加下面几项配置：

```
 
```

## 后台代码

增加 `com.waylau.spring.boot.security.config` 包，用于放置项目的配置类。在该包下，我们创建  SecurityConfig.java :


 
## 相关问题解决

### 问题1 

```
Invalid CSRF Token 'null' was found on the request parameter '_csrf' or header 'X-CSRF-TOKEN'.
```

自 Spring Security 3.2 起，启用了 CSRF 保护机制。所以 Form 表单提交必须满足以下条件：

* HTTP 方法必须是 POST;
* CSRF token 必须添加到请求。由于使用了`@EnableWebSecurity` 和 Thymeleaf，CSRF token 将自动添加到一个隐藏的`<input>`里面（查看源码看到）。；类似于`<input type="hidden" name="_csrf" value="f912aef3-f9a2-4c22-852e-db8cecf4175a"/>`
 
解决方法是，加上 Thymeleaf 标签，比如将

```
<form action="/users" method="post">
	......
</form>
```

改为 ：

，将
```
<form th:action="@{/users}" method="post">
	......
</form>
```

### 问题2 

`sec:authorize`和`sec:authentication`属性不起作用

解决方法 1：

确定添加了 `thymeleaf-extras-springsecurity4`依赖，且与Thymeleaf 版本一致。

解决方法 2：

```
<bean id="templateEngine" class="org.thymeleaf.spring4.SpringTemplateEngine">
  ...
  <property name="additionalDialects">
    <set>
      <!-- Note the package would change to 'springsecurity3' if you are using that version -->
      <bean class="org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect"/>
    </set>
  </property>
  ...
</bean>
```


参考：

* https://github.com/thymeleaf/thymeleaf-extras-springsecurity
* http://stackoverflow.com/questions/28904176/thymeleaf-with-spring-security-how-to-check-if-user-is-logged-in-or-not



错误 ：

```
Error: Bootstrap tooltips require Tether (http://tether.io/)
```
解决：安装 Tether
## 运行



