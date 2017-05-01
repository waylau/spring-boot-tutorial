# 数据持久化 实战

在之前的章节中，我们构建了一个项目`thymeleaf-in-action`。通过该项目，我们用 Thymeleaf 来实现一个最简单的“用户管理”功能。 为了简便，我们没有使用数据库管理系统，而是将数据直接保存在了内存，这样只要应用重启，数据就会丢失。本节，我们将通过JPA 来将数据存储到关系型数据库中。

我们在上一节创建的 `jpa-in-action`项目的基础上，做一些修改。

## 定义实体


修改 User 类，参考 JPA 的规范，将其修改成为实体：

* User 类上增加了`@Entity`注解，以标识其为实体
* `@Id`标识id 字段为主键； `@GeneratedValue(strategy=GenerationType.IDENTITY)`标识id使用数据库的自增长字段为新增加的实体的标识。这种情况下需要数据库提供对自增长字段的支持，一般的数据库如 HSQL、SQL Server、MySQL、DB2、Derby 等数据库都能够提供这种支持；
* 应 JPA 的规范要求，设置无参的构造函数`protected User() {}`，并设为 protected 防止直接被使用；
* 重写 `toString`方法，来将 User 信息自定义输出。


```java
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * User 实体.
 * 
 * @since 1.0.0 2017年4月29日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
@Entity // 实体
public class User {

	@Id // 主键
	@GeneratedValue(strategy=GenerationType.IDENTITY) // 自增长策略
	private Long id; // 实体一个唯一标识
	private String name;
	private String email;
	
	protected User() { // 无参构造函数；设为 protected 防止直接使用
	}
	
	public User(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return String.format("User[id=%d,name='%s', email='%s']", id, name, email);
	}
}
```

## 修改资源库

修改用户资源库的接口，继承自`CrudRepository`：

`public interface UserRepository extends CrudRepository<User, Long>`
 

删除 UserRepositoryImpl.java

## 修改控制器

UserController.java 也要做一些调整，将原来 UserRepositoryImpl 实现的方法，全部换成 JPA 的默认实现：

```java
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * 查询所有用户
	 * @param model
	 * @return
	 */
	@GetMapping
	public ModelAndView list(Model model) {
		model.addAttribute("userList", userRepository.findAll());
		model.addAttribute("title", "用户管理");
		return new ModelAndView("users/list","userModel",model);
	}

	/**
	 * 根据 id 查询用户
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Long id, Model model) {
		User user = userRepository.findOne(id);
		model.addAttribute("user", user);
		model.addAttribute("title", "查看用户");
		return new ModelAndView("users/view","userModel",model);
	}
	
	/**
	 * 获取创建表单页面
	 * @param model
	 * @return
	 */
	@GetMapping("/form")
	public ModelAndView createForm(Model model) {
		model.addAttribute("user", new User(null, null, null));
		model.addAttribute("title", "创建用户");
		return new ModelAndView("users/form","userModel",model);
	}
	
	/**
	 * 保存或者修改用户
	 * @param user
	 * @return
	 */
	@PostMapping
	public ModelAndView saveOrUpdateUser(User user) {
		userRepository.save(user);
		return new ModelAndView("redirect:/users");// 重定向到 list页面
	}
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		userRepository.delete(id);
		return new ModelAndView("redirect:/users"); // 重定向到 list页面
	}
	
	/**
	 * 获取修改用户的界面
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/modify/{id}")
	public ModelAndView modify(@PathVariable("id") Long id, Model model) {
		User user = userRepository.findOne(id);
		model.addAttribute("user", user);
		model.addAttribute("title", "修改用户");
		return new ModelAndView("users/form","userModel",model);
	}
}
```

## 查看H2表结构

因为目前，我们是使用的 H2 内存数据库，如果想看下内存数据库的表结构怎么办呢？

修改application.properties,增加下面几项配置：

```
# 使用 H2 控制台
spring.h2.console.enabled=true
```


启动项目，访问<http://localhost:8080/h2-console> 即可，其中，设置 JDBC URL为 `jdbc:h2:mem:testdb`。

## 使用 MySQL 数据库

通过命令行，使用 mysql 客户端来操作数据库：

```shell
$ mysql -u root -p
```

创建名为`blog`的数据库，编码为 UTF-8:

```shell
mysql> DROP DATABASE IF EXISTS blog;
Query OK, 9 rows affected (1.63 sec)

mysql> CREATE DATABASE blog DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
Query OK, 1 row affected (0.00 sec)
```
首先，创建数据库，


修改application.properties，增加下面几项配置：

```
# DataSource 
spring.datasource.url=jdbc:mysql://localhost/blog?useSSL=false&serverTimezone=UTC&characterEncoding=utf-8 
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA
spring.jpa.show-sql = true
spring.jpa.hibernate.ddl-auto=create-drop
```

其中`spring.jpa.hibernate.ddl-auto` 中的`create-drop` 是指每次应用启动，都会自动删除并创建数据库表。在开发时，非常方便。

## 运行查看效果

控制台可以看到 Hibernate 的执行情况：

```
Hibernate: drop table if exists user
Hibernate: create table user (id bigint not null auto_increment, age integer not null, name varchar(255) not null, primary key (id))
```

并自动创建表 `user`。


访问  <localhost:8080/users> 可以看到项目的运行效果。在页面进行一些增删改查的操作。

我们可以在数据库看到我们操作后的数据：

```
mysql> use blog;
Database changed
mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| blog               |
| mysql              |
| performance_schema |
| sys                |
+--------------------+
5 rows in set (0.00 sec)

mysql> show tables;
+----------------+
| Tables_in_blog |
+----------------+
| user           |
+----------------+
1 row in set (0.00 sec)

mysql> select * from user;
+----+---------------------+---------+
| id | email               | name    |
+----+---------------------+---------+
|  1 | waylau521@gmail.com | Way lau |
|  2 | waylau521@163.com   | 老卫    |
+----+---------------------+---------+
2 rows in set (0.00 sec)

mysql>
```
## 相关错误解决

### SSL 连接未验证

```
Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
.
```

解决方式：在数据源的 url 里添加参数`useSSL=false`

### 时区无法确认

```
java.sql.SQLException: The server time zone value '�й���׼ʱ��' is unrecognized or represents more than one time zone. You must configure either the server or JDBC driver (via the serverTimezone configuration property) to use a more specifc time zone value if you want to utilize time zone support.
```

解决方式：在数据源的 url 里添加参数`serverTimezone=UTC`
	
	
	
	
	