# 博客系统的搜索实现


在 `blog-tag`项目基础上，我们构建了一个新的项目`blog-search`。
 
## 实现功能

* 首页的分页模糊查询
* 最新文章列表
* 最热文章列表
* 热门标签
* 热门用户
* 热门文章
* 最新发布

## 环境


## build.gradle

修改 build.gradle 文件，让我们的`blog-search`项目成为一个新的项目。

修改内容也比较简单，修改项目名称及版本即可。

```groovy
jar {
	baseName = 'blog-search'
	version = '1.0.0'
}
```



