# 数据持久化 实战

在之前的章节中，我们构建了一个项目`thymeleaf-in-action`。通过该项目，我们用 Thymeleaf 来实现一个最简单的“用户管理”功能。 为了简便，我们没有使用数据库管理系统，而是将数据直接保存在了内存，这样只要应用重启，数据就会丢失。本节，我们将通过JPA 来将数据存储到关系型数据库中。

我们在上一节创建的 `jpa-in-action`项目的基础上，做一些修改。


## 修改application.properties

增加下面几项配置：

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

## 定义实体


修改 User 类，参考 JPA 的规范，将其修改成为实体：

* User 类上增加了`@Entity`注解，以标识其为实体
* `@Id`标识id 字段为主键； `@GeneratedValue(strategy=GenerationType.IDENTITY)`标识id使用数据库的自增长字段为新增加的实体的标识。这种情况下需要数据库提供对自增长字段的支持，一般的数据库如 HSQL、SQL Server、MySQL、DB2、Derby 等数据库都能够提供这种支持；
* 应 JPA 的规范要求，设置无参的构造函数`protected User() {}`，并设为 protected 防止直接被使用；
* 重写 `toString`方法，来将 User 信息自定义输出。



## 修改资源库

修改用户资源库的接口，继承自`CrudRepository`：

`public interface UserRepository extends CrudRepository<User, Long>`
 

删除 UserRepositoryImpl.java

## 修改控制器

UserController.java 也要做一些调整：

```java
@Autowired 
private UserRepository userRepository;

/**
 * 从 用户存储库 获取用户列表
 * @return
 */
private List<User> getUserlist() {
	List<User> users = new ArrayList<>();
	for (User user : userRepository.findAll()) {
		users.add(user);
	}
	return users;
}

/**
 * 查询所用用户
 * @return
 */
@GetMapping
public ModelAndView list(Model model) {
	model.addAttribute("userList", getUserlist());
	model.addAttribute("title", "用户管理");
	return new ModelAndView("users/list", "userModel", model);
}
 
/**
 * 根据id查询用户
 * @param message
 * @return
 */
@GetMapping("{id}")
public ModelAndView view(@PathVariable("id") Long id, Model model) {
	User user = userRepository.findOne(id);
	model.addAttribute("user", user);
	model.addAttribute("title", "查看用户");
	return new ModelAndView("users/view", "userModel", model);
}

/**
 * 获取 form 表单页面
 * @param user
 * @return
 */
@GetMapping("/form")
public ModelAndView createForm(Model model) {
	model.addAttribute("user", new User(null, null));
	model.addAttribute("title", "创建用户");
	return new ModelAndView("users/form", "userModel", model);
}

/**
 * 新建用户
 * @param user
 * @param result
 * @param redirect
 * @return
 */
@PostMapping
public ModelAndView create(User user) {
	userRepository.save(user);
	return new ModelAndView("redirect:/users");
}

/**
 * 删除用户
 * @param id
 * @return
 */
@GetMapping(value = "delete/{id}")
public ModelAndView delete(@PathVariable("id") Long id, Model model) {
	userRepository.delete(id);
	model.addAttribute("userList", getUserlist());
	model.addAttribute("title", "删除用户");
	return new ModelAndView("users/list", "userModel", model);
}

/**
 * 修改用户
 * @param user
 * @return
 */
@GetMapping(value = "modify/{id}")
public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
	User user = userRepository.findOne(id);
	model.addAttribute("user", user);
	model.addAttribute("title", "修改用户");
	return new ModelAndView("users/form", "userModel", model);
}
```
## 运行


```
Hibernate: drop table if exists user
Hibernate: create table user (id bigint not null auto_increment, age integer not null, name varchar(255) not null, primary key (id))
```

自动创建表 `user`。


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
+----+-----+---------+
| id | age | name    |
+----+-----+---------+
|  1 |  30 | 老卫    |
|  2 |  29 | Way Lau |
+----+-----+---------+
2 rows in set (0.07 sec)

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
	
