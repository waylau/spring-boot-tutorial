# 博客系统的权限管理实现


在 `blog-vote`项目基础上，我们构建了一个新的项目`blog-catalog`。
 
## 实现功能

* 分类的模型设计
* 创建分类
* 删除分类
* 博客与分类关联
* 按照分类查询博客


## 环境

* Markdown parser for the JVM ： 0.16  

## build.gradle

修改 build.gradle 文件，让我们的`blog-catalog`项目成为一个新的项目。

修改内容也比较简单，修改项目名称及版本即可。

```groovy
jar {
	baseName = 'blog-catalog'
	version = '1.0.0'
}
```
