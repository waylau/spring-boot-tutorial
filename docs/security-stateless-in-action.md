# Spring Security 无状态实战

通过该项目，我们用 Spring Security 来实现对系统的基于无状态以及 token 的方式来进行安全管理。

我们在上一节创建的 `security-in-action`项目的基础上，做一些修改，新建一个`security-stateless-in-action`项目 。

## build.gradle

修改 build.gradle 文件，让我们的`security-stateless-in-action`项目成为一个新的项目。

修改内容也比较简单，修改项目名称及版本即可。

```groovy
jar {
	baseName = 'security-stateless-in-action'
	version = '1.0.0'
}
```
 
依赖

```
	// 添加  JSON Web Token Support For The JVM 依赖
	compile('io.jsonwebtoken:jjwt:0.7.0')
```

## 后台代码

### 创建 Authority


### 修改 User

修改 User.java，实现 `org.springframework.security.core.userdetails.UserDetails` 接口：

```

```

### 修改 UserRepository

修改 UserRepository.java 接口，增加：

```
User findByUsername(String username);
```

### 新增 UserDetailsServiceImpl

UserDetailsServiceImpl 实现 `org.springframework.security.core.userdetails.UserDetailsService` 接口，并重写了  loadUserByUsername 方法：
 
```
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    
    if (user == null) {
        throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
    } else {
        return user;
    }
}
```

### 修改安全配置类

我们修改`com.waylau.spring.boot.security.config.SecurityConfig`配置类 :
 

```java

```


详见 <http://docs.spring.io/spring-security/site/docs/current/apidocs/org/springframework/security/config/annotation/web/builders/HttpSecurity.html>

## 控制器

 

## 前端代码

 
 


