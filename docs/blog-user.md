# 博客系统的用户管理实现


在 `security-in-action`项目基础上，我们构建了一个新的项目`blog-user`。项目的包名也做了调整，改为`com.waylau.spring.boot.blog`。
 

## build.gradle

修改 build.gradle 文件，让我们的`blog-user`项目成为一个新的项目。

修改内容也比较简单，修改项目名称及版本即可。

```groovy
jar {
	baseName = 'blog-user'
	version = '1.0.0'
}
```

 

