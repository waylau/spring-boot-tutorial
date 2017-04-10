# 博客系统的标签管理实现


在 `blog-catalog`项目基础上，我们构建了一个新的项目`blog-tag`。
 
## 实现功能

* 标签的模型设计
* 创建标签
* 删除标签
* 博客与标签关联
* 按照标签查询博客:在之前关键字搜索的基础上，扩展，对 tags 字段进行查询


## 环境

* jQuery Tags Input Plugin:<https://github.com/xoxco/jQuery-Tags-Input>

## build.gradle

修改 build.gradle 文件，让我们的`blog-tag`项目成为一个新的项目。

修改内容也比较简单，修改项目名称及版本即可。

```groovy
jar {
	baseName = 'blog-tag'
	version = '1.0.0'
}
```


