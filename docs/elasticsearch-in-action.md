
```
curl -i -X GET -H "'Content-type':'application/xml', 'dataType':'xml','charset':'utf-8', 'Accept': 'text/plain'"   http://localhost:8080/blogs?title=aa&content=HTML
```

```
java.lang.ClassNotFoundException: com.sun.jna.Native
	at java.net.URLClassLoader.findClass(Unknown Source) ~[na:1.8.0_112]
	at java.lang.ClassLoader.loadClass(Unknown Source) ~[na:1.8.0_112]
	at sun.misc.Launcher$AppClassLoader.loadClass(Unknown Source) ~[na:1.8.0_112]
	at java.lang.ClassLoader.loadClass(Unknown Source) ~[na:1.8.0_112]
	at java.lang.Class.forName0(Native Method) ~[na:1.8.0_112]
	at java.lang.Class.forName(Unknown Source) ~[na:1.8.0_112]
```

安装 `JNA` 的依赖：

```
// 添加  JNA 的依赖
compile('net.java.dev.jna:jna:4.3.0')
```