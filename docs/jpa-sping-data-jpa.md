# Spring Data JPA 用法介绍

## Spring Data JPA 简介 

Spring Data JPA 是更大的[Spring Data](http://projects.spring.io/spring-data)家族的一部分，使得轻松实现基于 JPA 的存储库变得更容易。 该模块用于处理对基于 JPA 的数据访问层的增强支持。 它使得更容易构建基于使用　Spring　数据访问技术栈的应用程序。


### Spring Data 是什么

Spring Data 是一个用于简化数据库访问，并支持云服务的开源框架。其主要目标是使得对数据的访问变得方便快捷，并支持map-reduce框架和云计算数据服务。 Spring Data 包含多个子项目：

* Spring Data Commons - 提供共享的基础框架，适合各个子项目使用，支持跨数据库持久化
* Spring Data JPA - 简化创建 JPA 数据访问层和跨存储的持久层功能
* Spring Data Hadoop - 基于 Spring 的 Hadoop 作业配置和一个 POJO 编程模型的 MapReduce 作业
* Spring Data KeyValue  - 集成了 Redis 和 Riak ，提供多个常用场景下的简单封装
* Spring Data JDBC Extensions - 支持 Oracle RAD、高级队列和高级数据类型
* ......


### Spring Data JPA 特性

对于普通开发者而言，自己实现应用程序的数据访问层是一件极其繁琐的过程。 开发者必须编写太多的样板代码来执行简单查询、分页和审计。 Spring Data JPA 旨在通过将努力减少到实际需要的量来显着改进数据访问层的实现。 作为开发人员，只需要编写存储库的接口，包括自定义查询方法，而这些接口的实现，Spring 将会自动提供。

Spring Data JPA 包含如下特征：

* 基于 Spring 和 JPA 来构建复杂的存储库
* 支持[Querydsl](http://www.querydsl.com/) 谓词，因此支持类型安全的 JPA 查询
* 域类的透明审计
* 具备分页支持、动态查询执行、集成自定义数据访问代码的能力
* 在引导时验证`@Query`带注释的查询
* 支持基于 XML 的实体映射
* 通过引入`@EnableJpaRepositories` 来实现基于 JavaConfig 的存储库配置


### 如何使用

在项目中使用`spring-data-jpa`的推荐方法是使用依赖关系管理系统。下面是使用 Gradle 构建的示例：

```
dependencies {
    compile 'org.springframework.data:spring-data-jpa:1.11.1.RELEASE'
}
```


## 核心概念

 Spring Data 存储库抽象中的中央接口是`Repository`。 它将域类以及域类的id类型作为类型参数进行管理。 此接口主要作为标记接口捕获要使用的类型，并帮助您发现扩展此接口。 而`CrudRepository`为受管理的实体类提供复杂的CRUD功能。

```java
public interface CrudRepository<T, ID extends Serializable>
	extends Repository<T, ID> {
	
	<S extends T> S save(S entity);  //（1）
	
	T findOne(ID primaryKey);       //（2）
	
	Iterable<T> findAll();          //（3）
	
	Long count();                   //（4）
	
	void delete(T entity);          //（5）
	
	boolean exists(ID primaryKey);  //（6）
	
	// … more functionality omitted.
}
```

CrudRepository 接口中的方法含义如下：

* （1）保存给定实体。
* （2）返回由给定id标识的实体。
* （3）返回所有实体。
* （4）返回实体的数量。
* （5）删除给定的实体。
* （6）指示是否存在具有给定 ID 的实体。

同时还提供其他特定的持久化技术的抽象，比如 `JpaRepository`或`MongoRepository`，这些接口扩展了`CrudRepository`。


在 `CrudRepository` 的顶部有一个`PagingAndSortingRepository`抽象，它增加了额外的方法来简化对实体的分页访问：

```java
public interface PagingAndSortingRepository<T, ID extends Serializable>
  extends CrudRepository<T, ID> {

  Iterable<T> findAll(Sort sort);

  Page<T> findAll(Pageable pageable);
}
```


比如，想访问用户的第二页的页面大小为20，你可以简单地做这样的事情：

```java
PagingAndSortingRepository<User, Long> repository = // … get access to a bean
Page<User> users = repository.findAll(new PageRequest(1, 20));
```


除了查询方法之外，还可以使用计数和删除查询。

派生计数查询：

```java
public interface UserRepository extends CrudRepository<User, Long> {

  Long countByLastname(String lastname);
}
```

派生删除查询：

```java
public interface UserRepository extends CrudRepository<User, Long> {

  Long deleteByLastname(String lastname);

  List<User> removeByLastname(String lastname);

}
```

## 查询方法

对于底层数据存储的管理，我们通常使用标准 CRUD 功能的资源库来实现。使用 Spring Data，声明这些查询将会变得简单，只需要四步过程：

### 1. 声明扩展`Repository`或其子接口之一的接口

声明接口，并输入将处理的域类和ID类型。

```java
interface PersonRepository extends Repository<Person, Long> { … }
```

### 2. 在接口上声明查询方法

```java
interface PersonRepository extends Repository<Person, Long> {
  List<Person> findByLastname(String lastname);
}
```

### 3. 为这些接口创建代理实例

可以通过 JavaConfig 的方式：


```java
interface PersonRepository extends Repository<Person, Long> {
  List<Person> findByLastname(String lastname);
}
```

或通过 XML 配置方式：


```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   xmlns:jpa="http://www.springframework.org/schema/data/jpa"
   xsi:schemaLocation="http://www.springframework.org/schema/beans
     http://www.springframework.org/schema/beans/spring-beans.xsd
     http://www.springframework.org/schema/data/jpa
     http://www.springframework.org/schema/data/jpa/spring-jpa.xsd">

   <jpa:repositories base-package="com.acme.repositories"/>

</beans>
```


在此示例中使用了 JPA 命名空间。如果您使用任何其他存储库的存储库抽象，则需要将其更改为您的存储模块的相应命名空间声。

另外，请注意，JavaConfig 变量不会明确配置包，因为默认情况下使用注释类的包。如果要自定义要扫描的程序包，请使用数据存储特定存储库的`@Enable…`注解。例如：

```
@EnableJpaRepositories(basePackages = "com.acme.repositories.jpa")
@EnableMongoRepositories(basePackages = "com.acme.repositories.mongo")
interface Configuration { }
```


### 4. 获取注入的存储库实例并使用它


```java
public class SomeClient {

  @Autowired
  private PersonRepository repository;

  public void doSomething() {
    List<Person> persons = repository.findByLastname("Matthews");
  }
}
```



## 定义资源库的接口

首先需要定义实体类的接口，接口必须继承资源库并且输入实体类型和ID类型，如果需要用到 CRUD 方法，可以使用`CrudRepository`来替代`Repository`.

### 自定义接口

通常，您的存储库接口将会扩展`Repository`、 `CrudRepository`或`PagingAndSortingRepository`。 另外，如果你不想继承 Spring Data 接口，还可以接口`@RepositoryDefinition`。 扩展`CrudRepository`，将会公开一套完整的方法来操作您的实体。 如果你喜欢其中的方法来调用，你也可以简单地复制`CrudRepository`中的部分方法到你的 repository。

> 这允许您自定义数据库的功能的抽象。

下面是一个有选择地公开 CRUD 方法的例子：

```java
@NoRepositoryBean
interface MyBaseRepository<T, ID extends Serializable> extends Repository<T, ID> {

  T findOne(ID id);

  T save(T entity);
}

interface UserRepository extends MyBaseRepository<User, Long> {
  User findByEmailAddress(EmailAddress emailAddress);
}
```

第一步你定义了一个公共基础的接口 `findOne(…)`和`save(...)`方法,这些方法将会引入到你选择的 Spring Data的实现类中，例如JPA：`SimpleJpaRepository`，因为他们匹配`CrudRepository`的方法签名，所以`UserRepository`将会具备`save(...)`的功能和`findOne(…)`的功能，当然也具备`findByEmailAddress`的功能。

> 注意，如果中间的资源库接口添加了`@NoRepositoryBean`注解，这样运行时，Spring Data 将会不会创建拥有该注解的实例。

### 使用 Spring Data 多模块来创建资源库

使用单个  Spring Data 模块在应用中是非常简单，但有时候我们需要多个 Spring Data 模块，比如：需要定义的资源库需要去区分两种不同的持久化技术，如果在 class path 中发现多个资源库时， Spring Data 会进行严格的配置限制，确保每资源库或者实体决定绑定哪个 Spring Data 模块：

* 如果资源库定义了继承特定的资源库，那么他是一个特定的 Spring Data 模块
* 如果实体注解了一个特定的声明，它是一个特定的 Spring Data 模块。 Spring Data 模块可以接纳第三方的声明（例如：JPA的`@Entity`）或者提供来自 Spring Data MonggoDB/Spring Data Elasticsearch 的 `@Document` 。

下面是自定义特定模块接口的资源库：

```java
interface MyRepository extends JpaRepository<User, Long> { }

@NoRepositoryBean
interface MyBaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
  …
}

interface UserRepository extends MyBaseRepository<User, Long> {
  …
}
```

`MyRepository` 和  `UserRepository` 继承于 `JpaRepository`在这个层级中是对 Spring Data JPA 模块的合法替代

使用一般的接口定义的资源库：

```java
interface AmbiguousRepository extends Repository<User, Long> {
 …
}

@NoRepositoryBean
interface MyBaseRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {
  …
}

interface AmbiguousUserRepository extends MyBaseRepository<User, Long> {
  …
}
```

`AmbiguousRepository`和`AmbiguousUserRepository` 仅在他们的层级来继承`Repository`和`CrudRepostory`。当它们使用单个  Spring Data 模块的时候是完美的，但是如果使用多模块 Spring Data 时， Spring 将无法区分每个资源库的范围。


下面例子是使用实体类注解来定义资源库的使用范围：

```java
nterface PersonRepository extends Repository<Person, Long> {
 …
}

@Entity
public class Person {
  …
}

interface UserRepository extends Repository<User, Long> {
 …
}

@Document
public class User {
  …
}
```

`PersonRepository`所引用的`Person`使用了`@Entity` 注解，所以这个仓库清晰的使用了Sping Data JPA。 `UserRepository`所引用的`User`声明了`@Document`，表明这个仓库将使用 Spring Data MongoDB 模块。

下面是使用混合的注解来定义资源库的例子：

```java
interface JpaPersonRepository extends Repository<Person, Long> {
 …
}

interface MongoDBPersonRepository extends Repository<Person, Long> {
 …
}

@Entity
@Document
public class Person {
  …
}
```

这个例子中实体类`Person` 使用了 JPA 和 Spring Data MongoDB 两种 注解，表明这个实体类既可以用于`JpaPersonRepository`也可以用于`MongoDBPersonRepository`，Spring Data 因不能区分类型而导致未定义的行为。

在同一个域类型上使用多个持久化技术特定的注释可以跨多个持久性技术重用域类型，但是 Spring Data 不再能够确定一个唯一的模块来绑定存储库。

最后一种方法来区分不同的仓库类型，使用包路径来判断。不同的包路径下的仓库使用不同的仓库类型，通过在配置类 Configuration 中声明注解来实现，也可以通过 XML 配置来定义。

通过注解来实现不同包路下使用不同的仓库：

```java
@EnableJpaRepositories(basePackages = "com.acme.repositories.jpa")
@EnableMongoRepositories(basePackages = "com.acme.repositories.mongo")
interface Configuration { }
```


## 定义查询方法

资源库代理有两种方法去查询，可以是根据方法名或者自定义查询。可用的选项取决于实际的存储。但不管如何，必须要有一个策略来决定创建什么实际查询。 让我们来看看可用的选项。

### 查询查找策略

以下策略可供查询库基础设施来解决。您可以配置策略名称空间通过 `query-lookup-strategy`属性的XML配置或通过`queryLookupStrategy`启用的属性`${store}`库注释的Java配置。一些策略可能不支持特定的数据存储。

* `CREATE` 试图构建一个能找到查询的查询方法名称。 通常的做法是把给定的一组注明前缀的方法名的方法一处，接着解析剩余的方法。
* `USE_DECLARED_QUERY`试图找到一个声明查询，没有找到就抛出一个异常。查询可以定义在注解上。
* `CREATE_IF_NOT_FOUND`，如果你不使用任何显式配置它是默认选项，它结合了`CREATE`和`USE_DECLARED_QUERY`。它首先查找已声明的查询，如果未找到已声明的查询，则会创建一个基于名称的自定义查询。  它允许通过方法名称进行快速查询定义，但也可以通过根据需要引入已声明的查询来自定义这些查询。


### 创建查询

内置到 Spring Data 存储库基础结构中的查询构建器机制对于在存储库的实体上构建约束查询很有用。 机制剥离前缀`find…By`、`read…By`、`query…By`、`count…By`和`get…By`从该方法开始解析其余部分。 引入子句可以包含其他表达式，例如在要创建的查询上设置不同标志的区别。 但是，第一个`By`作为分隔符指示实际标准的开始。 在非常基本的层次上，您可以定义实体属性的条件，并将它们与`And`和`Or`连接。

下面是根据方法名创建查询的例子:

```java
public interface PersonRepository extends Repository<User, Long> {

  List<Person> findByEmailAddressAndLastname(EmailAddress emailAddress, String lastname);

  // 启用 distinct 标志
  List<Person> findDistinctPeopleByLastnameOrFirstname(String lastname, String firstname);
  List<Person> findPeopleDistinctByLastnameOrFirstname(String lastname, String firstname);

  // 给独立的属性启用 ignore case
  List<Person> findByLastnameIgnoreCase(String lastname);
  // 给所有合适的属性启用 ignore case
  List<Person> findByLastnameAndFirstnameAllIgnoreCase(String lastname, String firstname);

  // 启用 ORDER BY
  List<Person> findByLastnameOrderByFirstnameAsc(String lastname);
  List<Person> findByLastnameOrderByFirstnameDesc(String lastname);
}
```

实际结果的解析方法取决于你的持久化存储所创建的查询。有一些要注意的事情：

* 表达式通常是可以在运算符组合的属性上进行遍历。您可以组合属性表达式 `AND`和`OR`。您还获得对诸如`Between`，`LessThan`，`GreaterThan`之间的运算符的支持，对于属性表达式。受支持的操作符可能因数据存储方式而异。
* 方法解析器支持为单个属性设置 IgnoreCase 标志（例如`findByLastnameIgnoreCase(...)`）也可以是所有属性（通常为 String 实例，例如`findByLastnameAndFirstnameAllIgnoreCase(...)`）。是否支持 ignore case写取决于具体的存储方式。
* 您可以通过将`OrderBy`子句附加到引用属性的查询方法并提供排序方向（`Asc`或`Desc`）来应用静态排序。 

### 属性表达式

属性表达式只能引用的受管理实体的直接属性，如前面的示例所示。  在查询创建时，您已经确保已解析的属性是受管域类的属性。 但是，您也可以通过遍历嵌套属性来定义约束。 假设一个 `Person` 有一个带有 `ZipCode` 的 `Address`。 在这种情况下，方法名称为

```java
List<Person> findByAddressZipCode(ZipCode zipCode);
```

解析算法从解释整个部分（`AddressZipCode`）作为属性开始，并检查域类中具有该名称（未资本化）的属性。如果算法成功，它使用该属性。如果不是，算法将来自右侧的骆驼情形部分的源分割成头部和尾部，并且尝试找到相应的属性，在我们的示例中，`AddressZip`和`Code`。如果算法找到具有该头部的属性，则它采用尾部并继续从那里构建树，以刚才描述的方式分割尾部。如果第一个分割不匹配，算法将分割点移动到左边（`Address`，`ZipCode`），然后继续。

虽然这应该适用于大多数情况下，算法可能选择错误的属性。假设`Person`类也有一个`addressZip`属性。该算法将在第一个分割循环中匹配，并且基本上选择错误的属性，最后失败（因为`addressZip`的类型可能没有` code`属性）。

要解决这种模糊性，您可以在方法名称中使用下划线`_`手动定义遍历点。所以我们的方法名称最终会这样：:

```java
List<Person> findByAddress_ZipCode(ZipCode zipCode);
```

由于我们将下划线`_`视为保留字符，我们强烈建议遵循标准 Java 命名约定（即不在属性名称中使用下划线，而是使用驼峰案例）。

### 特殊参数处理

要处理查询中的参数，您只需定义方法参数，如上面的示例中所示。 此外，基础设施将识别某些特定类型，如 Pageable 和 Sort，以动态地对查询应用进行分页和排序。

下面使用 `Pageable`、`Slice` 和 `Sort`来查询

```java
Page<User> findByLastname(String lastname, Pageable pageable);

Slice<User> findByLastname(String lastname, Pageable pageable);

List<User> findByLastname(String lastname, Sort sort);

List<User> findByLastname(String lastname, Pageable pageable);
```

第一个方法允许在你的查询方法的静态定义查询中通过一个`org.springframework.data.domain.Pageable`实例来动态的添加分页。分页类知道元素的总数和可用页数。它通过基础库来触发一个统计查询计算所有的总数。由于这个查询可能对存储库消耗巨大，可以使用 `Slice` 来替代。`Slice` 仅仅知道是否有下一个`Slice`可用，这对查询大数据已经足够了。

排序选项和分页的处理方式一样。如果你需要排序，简单的添加一个`org.springframework.data.domain.Sort`参数到你的方法即可。也正如你所见，简单的返回一个列表也是可以的，在这种情况下，将不会创建构建实际页面实例所需的附加元数据（这反过来意味着将不必发出额外的计数查询），而是简单地限制查询以仅查找给定范围 实体。

> 要找出在你的查询中有多少页，你需要触发一个额外的计数查询。按照默认来说这个查询可以从你实际触发查询中衍生出来


### 限制查询结果

查询方法的结果可以通过关键字 `first` 或 `top` 来限制，它们可以互换使用。 可选的数字值可以追加到 top/first，以指定要返回的最大结果大小。 如果省略该数字，则假定结果大小为1。

下面示例用`Top`和`First`查询限制结果大小 ：

``` java
User findFirstByOrderByLastnameAsc();

User findTopByOrderByAgeDesc();

Page<User> queryFirst10ByLastname(String lastname, Pageable pageable);

Slice<User> findTop3ByLastname(String lastname, Pageable pageable);

List<User> findFirst10ByLastname(String lastname, Sort sort);

List<User> findTop10ByLastname(String lastname, Pageable pageable);
```

限制表达式也支持 `Distinct` 关键字。对于限制查询的结果集定义到一个实例中包装这个结果到一个`Optional`中也是被支持的。

如果分页或者切片被应用到一个限制查询分页(计算多少页可用)则它也能应用于限制结果。

> 要注意结合通过Sort参数动态排序的限制结果容许表达查询的方法为“K”最小的，以及“K”最大的元素。

### 流查询结果

可以通过使用Java 8 `Stream<T>`作为返回类型来递增地处理查询方法的结果。 不是简单地将查询结果包装在`Stream`数据存储中，而是使用特定方法来执行流传输。


下面例子是以Java 8 `Stream<T>`来进行查询的流处理结果：

```java
@Query("select u from User u")
Stream<User> findAllByCustomQueryAndStream();

Stream<User> readAllByFirstnameNotNull();

@Query("select u from User u")
Stream<User> streamAllPaged(Pageable pageable);
```

> 一个数据流可能包裹底层数据存储特定资源，因此在使用后必须关闭。 你也可以使用close()方法或者 Java 7 try-with-resources 区块手动关闭数据流。

在try-with-resources 块中操作一个Stream`Stream<T>`的例子：

```java
try (Stream<User> stream = repository.findAllByCustomQueryAndStream()) {
  stream.forEach(…);
}
```

> 当前不是所有的 Spring Data 模块都支持 `Stream<T>`作为返回类型


### 异步查询结果

可以使用 Spring 的异步方法执行能力来异步地执行存储库查询。 这意味着该方法将在调用时立即返回，并且实际的查询执行将在已经提交到 Spring 任务执行器的任务中发生。

```java
@Async
Future<User> findByFirstname(String firstname);               (1)  

@Async
CompletableFuture<User> findOneByFirstname(String firstname);  (2)

@Async
ListenableFuture<User> findOneByLastname(String lastname);     (3) 
```

- (1) 使用 `java.util.concurrent.Future` 作为返回类型
- (2) 使用  Java 8 `java.util.concurrent.CompletableFuture`作为返回类型
- (3) 使用  `org.springframework.util.concurrent.ListenableFuture`作为返回类型


## 创建资源实例

为存储库接口创建实例以及定义 bean 的方式有两种。 一种方法是 Spring 命名空间；另外一种是Java Config 配置方式，该配置方式也是推荐的方式。


### XML配置

每个 Spring Data 模块都包含一个资源库元素，您可以简单地定义 Spring 所要扫描的基础包。

通过 XML 来配置 Spring Data 资源库的例子：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans:beans xmlns:beans="http://www.springframework.org/schema/beans"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns="http://www.springframework.org/schema/data/jpa"
  xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/data/jpa
    http://www.springframework.org/schema/data/jpa/spring-jpa.xsd">

  <repositories base-package="com.acme.repositories" />

</beans:beans>
```

在示例中，指示 Spring 扫描`com.acme.repositories`及其所有子包以扩展 `Repository`或其子接口之一。 对于找到的每个接口，基础结构注册持久化技术特定的`FactoryBean`以创建处理查询方法的调用的适当代理。 每个bean都注册在从接口名派生的bean名称下，因此`UserRepository`的接口将注册在`userRepository`下。 `base-package`属性允许使用通配符，以便您可以定义扫描包的模式。


#### 使用过滤器

可以使用 `<include-filter />`或`<exclude-filter />` 来进行相关的过滤：



```xml
<repositories base-package="com.acme.repositories">
  <context:exclude-filter type="regex" expression=".*SomeRepository" />
</repositories>
```

### JavaConfig 方式


可以在JavaConfig 类上使用`@Enable${store}Repositories`注解，来实现来触发存储库基础结构

示例配置如下所示。



```java
@Configuration
@EnableJpaRepositories("com.acme.repositories")
class ApplicationConfiguration {

  @Bean
  public EntityManagerFactory entityManagerFactory() {
    // …
  }
}
```

> 该示例使用JPA特定的注释，您可以根据实际使用的存储模块更改它。 这同样适用于EntityManagerFactory bean的定义。 


### 独立使用

您还可以使用 Spring 容器之外的存储库基础结构，例如 在CDI环境中。 你仍然需要在你的类路径中有一些 Spring 库，但一般来说你可以通过编程方式设置存储库。 提供存储库支持的 Spring Data 模块提供了一个持久化技术特定的 RepositoryFactory，您可以使用如下。

下面是例子：

```java
RepositoryFactorySupport factory = … // Instantiate factory here
UserRepository repository = factory.getRepository(UserRepository.class);
```
 
## Spring Data 自定义实现


通常有必要为几个存储库方法提供自定义实现。 Spring 数据存储库很容易允许您提供自定义存储库代码，并将其与通用 CRUD 抽象和查询方法功能集成。

### 向单个存储库添加自定义行为

要使用自定义功能丰富存储库，您首先需要定义自定义功能的接口和实现。 使用您提供的存储库接口来扩展自定义接口。

自定义资源库方法的接口：

```java
interface UserRepositoryCustom {
  public void someCustomMethod(User user);
}
```

自定义资源库方法接口的实现：

```java
class UserRepositoryImpl implements UserRepositoryCustom {

  public void someCustomMethod(User user) {
    // Your custom implementation
  }
}
```

实现本身不依赖于 Spring Data，可以是普通的 Spring bean。 因此，您可以使用标准依赖注入行为来注入其他 bean 的引用，如JdbcTemplate 等方面。

```java
interface UserRepository extends CrudRepository<User, Long>, UserRepositoryCustom {

  // Declare query methods here
}
```

让您的标准存储库接口扩展自定义接口。这样做结合了 CRUD 和自定义功能。

#### 配置

如果使用命名空间配置，存储库基础架构尝试通过扫描我们发现存储库的包下面的类来自动检测自定义实现。这些类需要遵循将命名空间元素的属性`repository-impl-postfix`附加到找到的库的命名约定接口名称。此后缀默认为`Impl`。

配置示例:

```xml
<repositories base-package="com.acme.repository" />

<repositories base-package="com.acme.repository" repository-impl-postfix="FooBar" />
```


第一个配置示例将尝试查找类`com.acme.repository.UserRepositoryImpl`作为自定义存储库实现，而第二个示例将尝试查找`com.acme.repository.UserRepositoryFooBar`。

#### 手动装配

如果您的自定义实现仅使用基于注解的配置和自动装配，那么刚刚所示的方法将很有效，因为它将被视为任何其他Spring bean。如果你的自定义实现bean需要特殊的装配，你只需声明 bean 并命名它刚才描述的约定。基础设施然后将通过名称而不是创建一个自身来引用手动定义的 bean 定义。

自定义实现的手动装配的例子：

```xml
<repositories base-package="com.acme.repository" />

<beans:bean id="userRepositoryImpl" class="…">
  <!-- further configuration -->
</beans:bean>
```


### 向所有存储库添加自定义行为

当您要向所有的存储库接口添加单个方法时，上述方法是不可行的。 要向所有存储库添加自定义行为，您首先添加一个中间接口来声明共享行为。

声明自定义共享行为的接口：

```java
@NoRepositoryBean
public interface MyRepository<T, ID extends Serializable>
  extends PagingAndSortingRepository<T, ID> {

  void sharedCustomMethod(ID id);
}
```

现在，您的各个存储库接口将扩展此中间接口，而不是`Repository`接口，以包含已声明的功能。 接下来，创建中间接口的实现，以扩展持久性技术特定的存储库基类。 这个类将作为存储库代理的自定义基类。

自定义存储库基类的例子:


```java
public class MyRepositoryImpl<T, ID extends Serializable>
  extends SimpleJpaRepository<T, ID> implements MyRepository<T, ID> {

  private final EntityManager entityManager;

  public MyRepositoryImpl(JpaEntityInformation entityInformation,
                          EntityManager entityManager) {
    super(entityInformation, entityManager);

    // Keep the EntityManager around to used from the newly introduced methods.
    this.entityManager = entityManager;
  }

  public void sharedCustomMethod(ID id) {
    // implementation goes here
  }
}
```

> 类需要具有特定于存储库的工厂实现使用的超类的构造函数。如果存储库基类具有多个构造函数，则覆盖采用 EntityInformation 加上特定于存储库的基础结构对象（例如 EntityManager 或模板类）的类。


Spring `<repositories />`命名空间的默认行为是为`base-package`下的所有接口提供一个实现。这意味着如果保持在它的当前状态，`MyRepository`的实现实例将由 Spring 创建。这当然不是所期望的，因为它只是作为`Repository`和要为每个实体定义的实际存储库接口之间的中介。要排除将`Repository`从实例化为存储库实例的接口，可以使用`@NoRepositoryBean`（如上所示）对其进行注释，或将其移动到已配置的`base-package`之外。

最后一步是使 Spring Data 基础结构感知自定义的存储库基类。在 JavaConfig 中，这是通过使用`@Enable…Repositories`的 repositoryBaseClass 属性来实现的：

下面例子是使用 JavaConfig 配置自定义存储库基类：

```java
@Configuration
@EnableJpaRepositories(repositoryBaseClass = MyRepositoryImpl.class)
class ApplicationConfiguration { … }
```

相应的属性在 XML 命名空间中可用:

```xml
<repositories base-package="com.acme.repository"
     base-class="….MyRepositoryImpl" />
```

## 从聚合根（aggregate root）发布事件


Spring Data提供了`@DomainEvents`用来发布领域事件（domain event）。

用法如下：

 
```java
class AnAggregateRoot {

    @DomainEvents 
    Collection<Object> domainEvents() {
        // … return events you want to get published here
    }

    @AfterDomainEventsPublication 
    void callbackMethod() {
       // … potentially clean up domain events list
    }
}
```







