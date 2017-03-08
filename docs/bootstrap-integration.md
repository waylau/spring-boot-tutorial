# Bootstrap 与 Spring Boot 集成


在 `jpa-in-action`项目基础上，我们构建了一个新的项目`bootstrap-in-action`。项目的包名也做了调整，改为`com.waylau.spring.boot.bootstrap`。

## 所需环境

本例子采用的开发环境如下：

* Bootstrap 3.3.7 : <http://getbootstrap.com/getting-started/>
* jQuery 3.1.1 : <http://jquery.com/download/>


## build.gradle

修改 build.gradle 文件，让我们的`bootstrap-in-action`项目成为一个新的项目。

修改内容也比较简单，修改项目名称及版本即可。

```groovy
jar {
	baseName = 'bootstrap-in-action'
	version = '1.0.0'
}
```


## 将 Bootstrap 与 jQuery 集成进项目

在项目的 `resources/static`目录下，分别建立 css、js、font 三个目录，其中，将Bootstrap 、 jQuery  发布包中的 样式文件、js 脚本文件、字体图标文件放置于上述相应的目录下。整体目录如下：

```
static
│  favicon.ico
│
├─css
│      bootstrap-grid.css
│      bootstrap-grid.css.map
│      bootstrap-grid.min.css
│      bootstrap-grid.min.css.map
│      bootstrap-reboot.css
│      bootstrap-reboot.css.map
│      bootstrap-reboot.min.css
│      bootstrap-reboot.min.css.map
│      bootstrap.css
│      bootstrap.css.map
│      bootstrap.min.css
│      bootstrap.min.css.map
│
├─fonts
│      glyphicons-halflings-regular.eot
│      glyphicons-halflings-regular.svg
│      glyphicons-halflings-regular.ttf
│      glyphicons-halflings-regular.woff
│      glyphicons-halflings-regular.woff2
│
└─js
        bootstrap.js
        bootstrap.min.js
        jquery-3.1.1.min.js
```

