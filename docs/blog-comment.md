# 博客系统的评论管理实现


在 `blog-blog`项目基础上，我们构建了一个新的项目`blog-comment`。
 
## 实现功能

* 评论的模型设计
* 发表评论
* 删除评论
* 統計评论數


## build.gradle

修改 build.gradle 文件，让我们的`blog-comment`项目成为一个新的项目。

修改内容也比较简单，修改项目名称及版本即可。

```groovy
jar {
	baseName = 'blog-comment'
	version = '1.0.0'
}
```
