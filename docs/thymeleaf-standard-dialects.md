# Thymeleaf 标准方言

Thymeleaf的可扩展性很强，它支持自定义你自己的模板属性集（或事件标签）、表达式、语法及应用逻辑。它更像一个模板引擎框架（template engine framework）。

然而，秉着“开箱即用”的原则，Thymeleaf提供了满足大多数情况下的默认实现——标准方言（standard dialects），命名为 Standard 和  SpringStandard 方言。你可以在模板中识别出这些被使用的标准方言，因为他们都以`th`属性开头，如

```
<span th:text="...">  
```

值得注意的是，Standard 和 SpringStandard 方言的用法几乎相同，不同之处在于 SpringStandard 包括了 Spring MVC 集成的具体特征（比如用 Spring Expression Language 来替代 OGNL）。

通常，我们在谈论 Thymeleaf 的标准方言时，一般引用的是  Standard， 而不涉及特例。

## Thymeleaf 标准表达式语法

大多数 Thymeleaf 属性允许设值或者包含表达式（expressions），因为它们使用的方言的关系，我们称之为标准表达式（Standard Expressions）。这些标准表达式语法可以有五种类型：

```
${...} : Variable expressions（变量表达式）
*{...} : Selection expressions（选择表达式）
#{...} : Message (i18n) expressions（消息表达式）
@{...} : Link (URL) expressions（链接表达式）
~{...} : Fragment expressions（分段表达式）
```


### 1. Variable expressions（变量表达式）

变量表达式可以是OGNL表达式或者是 Spring EL，如果集成了Spring的话，可以在上下文变量（context variables ）中执行。在Spring术语中，变量表达式也称为模型属性（model attributes）。 他们看起来像这样：

```
${session.user.name}
```

他们作为属性值或作为属性的一部分：

```
<span th:text="${book.author.name}">
```

上面的表达式在在OGNL和SpringEL中等价于：

```
((Book)context.getVariable("book")).getAuthor().getName()
```

这些变量表达式不仅涉及输出，还包括更复杂的处理，如条件判断、迭代等：


```
<li th:each="book : ${books}">
```

这里`${books}`从上下文中选择名为`books`的变量，并将其评估为可在`th:each`循环中使用的迭代器（iterable）。

### 2. Selection expressions（选择表达式）

选择表达式与变量表达式很像，区别在于它们是在当前选择的对象而不是整个上下文变量映射上执行。 他们看起来像这样：


```
*{customer.name}
```

它们所作用的对象由`th:object`属性指定：

```
<div th:object="${book}">
  ...
  <span th:text="*{title}">...</span>
  ...
</div>
```

这等价于：

```
{
  // th:object="${book}"
  final Book selection = (Book) context.getVariable("book");
  // th:text="*{title}"
  output(selection.getTitle());
}
```

### 3. Message (i18n) expressions（消息表达式）

消息表达式（通常称为文本外化、国际化或i18n）允许我们从外部源（`.properties`文件）检索特定于语言环境的消息，通过键引用它们（可选）应用一组参数。

在Spring应用程序中，这将自动与Spring的MessageSource机制集成。

```
#{main.title}

#{message.entrycreated(${entryId})}
```

在模版中的应用如下：

```
<table>
  ...
  <th th:text="#{header.address.city}">...</th>
  <th th:text="#{header.address.country}">...</th>
  ...
</table>
```

请注意，如果希望消息键由上下文变量的值确定，或者要将变量指定为参数，则可以在消息表达式中使用变量表达式：

```
#{${config.adminWelcomeKey}(${session.user.name})}
```

### 4. Link (URL) expressions（链接表达式）


链接表达式旨在构建URL并向其添加有用的上下文和会话信息（通常称为URL重写的过程）。

因此，对于部署在Web服务器的`/myapp`上下文中的Web应用程序，可以使用以下表达式：

```
<a th:href="@{/order/list}">...</a>
```

可以转成：

```
<a href="/myapp/order/list">...</a>
```

cookie没有启用下，如果我们需要保持会话，可以这样：

```
<a href="/myapp/order/list;jsessionid=23fa31abd41ea093">...</a>
```
URL 可以携带参数：

```
<a th:href="@{/order/details(id=${orderId},type=${orderType})}">...</a>
```

结果如下：

```
<!-- Note ampersands (&) should be HTML-escaped in tag attributes... -->
<a href="/myapp/order/details?id=23&amp;type=online">...</a>
```


链接表达式可以是相对的，在这种情况下，应用程序上下文将不会作为URL的前缀：

```
<a th:href="@{../documents/report}">...</a>
```

也 可以是服务器相对（同样，没有应用程序上下文前缀）：

```
<a th:href="@{~/contents/main}">...</a>
```

和协议相对（就像绝对URL，但浏览器将使用在显示的页面中使用的相同的HTTP或HTTPS协议）：

```
<a th:href="@{//static.mycompany.com/res/initial}">...</a>
```

当然，Link表达式可以是绝对的：

```
<a th:href="@{http://www.mycompany.com/main}">...</a>
```


*在绝对（或协议相对）的URL等里面，Thymeleaf链接表达式添加的是什么值？*

*答案是，可能是由响应过滤器定义的URL重写。在基于Servlet的Web应用程序中，对于每个输出的URL（上下文相对、相对、绝对...）Thymeleaf将总是调用HttpServletResponse.encodeUrl(...) 机制 在显示URL之前。 这意味着过滤器可以通过包装HttpServletResponse对象（通常使用的机制）来为应用程序执行定制的URL重写。


### 5. Fragment expressions（分段表达式）

分段表达式是 3.x 版本新增的内容。

分段段表达式是一种表示标记片段并将其移动到模板周围的简单方法。 正是由于这些表达式，片段可以被复制，或者作为参数传递给其他模板等等。

最常见的用法是使用`th:insert`或`th:replace:`插入片段：

```
<div th:insert="~{commons :: main}">...</div>
```

但是它们可以在任何地方使用，就像任何其他变量一样：

```
<div th:with="frag=~{footer :: #main/text()}">
  <p th:insert="${frag}">
</div>
```


分段表达式可以有参数。

### 6. 字面量和操作

Thymeleaf有一组可用的字面量和操作。

* 字面量:
	* 文本：`'one text'`、`'Another one!'`等；
	* 数值：0、34、3.0、12.3等；
	* 布尔：true、false
	* Null：null
	* token: one、sometext、 main等；
* 文本操作:
	* 字符串拼接：`+`
	* 文本替换：`|The name is ${name}|`
* 算术操作:
	* 二元运算符：`+`、`-`、 `*`、`/`、`%`
	* 减号（单目运算符）：`-`
* 布尔操作：
	* 二元运算符：`and`、`or`
	* 布尔否定（一元运算符）：`!`、`not`
* 比较和等价：
	* 比较：`>`、`<`、`>=`、`<=`（`gt`、`lt`、`ge`、`le`）
	* 等价:`==`、`!=`（`eq`、`ne`）
* 条件运算符：
	* If-then：`(if) ? (then)`
	* If-then-else：`(if) ? (then) : (else)`
	* Default：`(value) ?: (defaultvalue)`
	
### 7. 表达式预处理

表达式预处理（expression preprocessing），它被定义在下划线`_`之间：

```
#{selection.__${sel.code}__}
```

我们看到的变量表达式`${sel.code}`将先被执行，假如结果是`"ALL"`，那么_之间的值`"ALL"`将被看做表达式的一部分被执行，在这里会变成`selection.ALL`。	


## 基本属性

 
让我们看一组最基本的标准方言属性，以`th:text`开头，替换标签中的文字内容（这里再次强调Thymeleaf的原型支持能力）。

```
<p th:text="#{msg.welcome}">Welcome everyone!</p>
```


`th:each`将循环 array 或 list 中的元素并重复打印一组标签，语法相当于 Java foreach 表达式：

```
<li th:each="book : ${books}" th:text="${book.title}">En las Orillas del Sar</li>
```

Thymeleaf 有很多`th`属性来定义 XHTML 或者 HTML5 属性，这些属性只是执行表达式并把值设置成HTML的属性值：

```
<form th:action="@{/createOrder}">

<input type="button" th:value="#{form.submit}" />

<a th:href="@{/admin/users}">
```

## 参考文献

* http://www.thymeleaf.org/doc/articles/standarddialect5minutes.html