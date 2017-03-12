# Elasticsearch 再 Windows 下的安装和使用

## 环境

* Java 8 +
* Elasticsearch 5.2.2


## 下载安装 Elasticsearch

下载地址：<https://www.elastic.co/downloads/elasticsearch> 解压之后，有一个名为`elasticsearch-5.2.2`的文件夹，我们将其称为`％ES_HOME％` 变量。 在终端窗口中，切换到`％ES_HOME％`目录，例如：

```
cd c:\elasticsearch-5.2.2
```

## 运行

命令行执行：

```
.\bin\elasticsearch
```

执行`Ctrl-C` 用来停止。


## 配置

默认情况下，Elasticsearch从`%ES_HOME%/config/elasticsearch.ym`文件加载其配置。 此配置文件的格式在[配置 Elasticsearch](https://www.elastic.co/guide/en/elasticsearch/reference/current/settings.html) 中进行了说明。

还可以在命令行上使用`-E`语法指定可以在配置文件中指定的任何设置，如下所示：

```
./bin/elasticsearch -Ecluster.name=my_cluster -Enode.name=node_1
```

>注意:包含空格的值必须加上双引号， 例如`-Epath.logs="C:\My Logs\logs"`。

>提示：通常，任何集群范围的设置（如`cluster.name`）都应该添加到 elasticsearch.yml 配置文件中，而任何特定于节点的设置（如`node.name`）都可以在命令行中指定。

## 确认运行情况


您可以通过向 localhost 上的端口 9200 发送 HTTP 请求来测试 Elasticsearch 节点是否正在运行：

```
GET /
```

控制台应能做出如下的响应：

```
{
  "name" : "Cp8oag6",
  "cluster_name" : "elasticsearch",
  "cluster_uuid" : "AT69_T_DTp-1qgIJlatQqA",
  "version" : {
    "number" : "5.2.2",
    "build_hash" : "f27399d",
    "build_date" : "2016-03-30T09:51:41.449Z",
    "build_snapshot" : false,
    "lucene_version" : "6.4.1"
  },
  "tagline" : "You Know, for Search"
}
```

## 作为 Windows 服务


执行 `bin` 目录下 `elasticsearch-service.bat` 即可，允许用户从命令行来安装、删除、管理或配置服务，并能启动和停止服务。。

```
c:\elasticsearch-5.2.2\bin>elasticsearch-service

Usage: elasticsearch-service.bat install|remove|start|stop|manager [SERVICE_ID]
```

安装后，应能看到如下信息：

```
c:\elasticsearch-5.2.2\bin>elasticsearch-service install
Installing service      :  "elasticsearch-service-x64"
Using JAVA_HOME (64-bit):  "c:\jvm\jdk1.8"
The service 'elasticsearch-service-x64' has been installed.
```