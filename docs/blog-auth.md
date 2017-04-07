# 博客系统的权限管理实现


在 `blog-user`项目基础上，我们构建了一个新的项目`blog-auth`。。
 
## 实现功能

* 角色划分
* 记住我；
* 个人资料设置；
* 个人头像更换
 
    
## build.gradle

修改 build.gradle 文件，让我们的`blog-auth`项目成为一个新的项目。

修改内容也比较简单，修改项目名称及版本即可。

```groovy
jar {
	baseName = 'blog-auth'
	version = '1.0.0'
}
```

 


 