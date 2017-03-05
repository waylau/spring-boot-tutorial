# MySQL 安装及使用

## 下载

* MySQL Community Server 5.7.17：
<https://dev.mysql.com/downloads/mysql/>
* MySQL Workbench 6.3.9:<https://dev.mysql.com/downloads/workbench/>

## 安装

* MySQL Community Server: 参考 <https://waylau.com/windows-install-mysql-noinstall-zip/>


## 创建数据库

创建一个名为 `blog` 的数据库：

```
mysql> CREATE DATABASE blog DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
Query OK, 1 row affected (0.03 sec)
```