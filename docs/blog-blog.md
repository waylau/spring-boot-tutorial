# 博客系统的博客管理实现


在 `blog-auth`项目基础上，我们构建了一个新的项目`blog-blog`。
 
## 实现功能

* 用户主页“写博客”实现
* blog 模型设计
* 编辑博客界面；
* 发表博客；
* 编辑博客；
* 删除博客；
* 查询用户博客
* 按标题模糊查询
* 最新、最热排序查询
* 阅读量统计

## 环境

* Markdown parser for the JVM ： 0.16  

## build.gradle

修改 build.gradle 文件，让我们的`blog-blog`项目成为一个新的项目。

修改内容也比较简单，修改项目名称及版本即可。

```groovy
jar {
	baseName = 'blog-blog'
	version = '1.0.0'
}
```
添加 Markdown parser 依赖：

```
// 添加 Markdown parser 依赖
compile('es.nitaur.markdown:txtmark:0.16')
```

 