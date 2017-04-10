# 博客系统的点赞管理实现


在 `blog-comment`项目基础上，我们构建了一个新的项目`blog-vote`。
 
## 实现功能

* 点赞的模型设计
* 发表点赞
* 取消点赞
* 統計点赞數
 

## build.gradle

修改 build.gradle 文件，让我们的`blog-vote`项目成为一个新的项目。

修改内容也比较简单，修改项目名称及版本即可。

```groovy
jar {
	baseName = 'blog-vote'
	version = '1.0.0'
}
```
