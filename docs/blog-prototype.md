# 原型设计

`blog-prototype` 项目，有几个作用：

* 需求分析
* 整体界面的原型设计

## 原型界面

* index : 主页，含最新、最热文章，最热标签、最热用户等
	* /blogs
		* order: 排序类型， new/hot ， 默认是 new
		* tag : 博客标签，默认是空
	* ~~/blogs/{id} :具体某篇博客~~
		~~* id ：某篇博客的id~~
* user space : 用户主页空间
	* /u/{username} : 具体某个用户的主页
		* username : 用户账号
	* /u/{username}/profile ： GET 获取个人设置页面
		* username : 用户账号
	* /u/{username}/profile ： POST 保存个人设置
		* username : 用户账号
		* User : 待保存的对象
	* /u/{username}/avatar ：GET 获取个人头像
		* username : 用户账号
	* /u/{username}/avatar ： POST 保存个人头像
		* username : 用户账号
		* User : 待保存的对象
	* /u/{username}/blogs ： 查询用户博客，以下三个条件任选一个
		* order: 排序类型， new/hot ， 默认是 new
		* catalog : 博客分类 Id，默认是空
		* keyword : 搜索关键字。博客的标签，即为关键字
		* async : 是否异步请求页面
		* pageIndex
		* pageSize
	* /u/{username}/blogs/edit : GET 获取新增博客的界面
		* username : 用户账号
	* /u/{username}/blogs/edit : POST 新增、编辑博客
		* username : 用户账号
		* Blog ：待保存的博客对象
	* /u/{username}/blogs/edit/{id} : GET 获取编辑博客的界面
		* username : 用户账号
		* id : 博客的 id
	* /u/{username}/blogs/edit/{id} : DELETE 删除博客
		* username : 用户账号
		* id : 博客的 id
* search ： 搜索
	* /search
		* q : 搜索关键字
	* /keywords : 最热搜索关键字列表
* login : 登录
	* /login  :GET 获取登录的界面
	* /login  :POST 登录
* register ：注册
	* /register :GET 获取注册的界面
	* /register :POST 注册 ， 注册成功跳转至 登录界面
* users : 用户管理
	* /users : GET 用户列表
		* async : 是否异步请求页面
		* pageIndex
		* pageSize
		* name : 用户名称的关键字
	* /users/add : GET 用户新增页面
	* /users : POST 保存用户
		* user : 待保存用户信息
		* authorityId ： 角色 ID
	* /users/{id} : DELETE  删除用户
		* id :  用户 id
	* /users/edit/{id} :获取具体某个用户编辑界面
		* id ：某个用户的id
* comments : 评论管理
	* /comments :  GET 获取评论列表
		* blogId : 博客id
	* /comments :  POST 保存评论
		* blogId : 博客id
		* commentContent ： 评论内容
	* /comments/{id} : DELETE  删除评论
		* id :  评论id
		* blogId : 博客id
* votes : 点赞管理
	* /votes :  POST 保存点赞
		* blogId : 博客id
	* /votes/{id} : DELETE  删除点赞
		* id :  点赞id
		* blogId : 博客id
* catalogs : 分类管理
	* /catalogs :  GET 获取用户分类列表
		* username : 用户账号
	* /catalogs :  POST 保存用户分类
		* username : 用户账号
		* CatalogVO ： 含 username、Catalog
	* /catalogs/edit : GET  获取编辑分类的界面
	* /catalogs/edit/{id} : GET  获取某ID分类的编辑界面
	* /catalogs/{id} : DELETE  删除分类
		* id :  分类id
		* username : 用户账号
