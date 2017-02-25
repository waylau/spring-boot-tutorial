# Spring Boot 是什么，不是什么

本节我们来讨论下 Spring Boot 的背景

## Spring Boot 是什么

Spring Boot 简化了基于Spring的应用开发，通过少量的代码就能创建一个独立的、产品级别的Spring应用。 Spring Boot为Spring平台及第三方库提供开箱即用的设置，这样你就可以有条不紊地开始。多数Spring Boot应用只需要很少的Spring配置。

你可以使用Spring Boot创建Java应用，并使用 `java -jar`启动它或采用传统的war部署方式。同时 Spring Boot 也提供了一个运行“spring  脚本”的命令行工具。


Spring Boot 主要的目标是：

* 为所有Spring开发提供一个更快更广泛的入门体验；
* 开箱即用，不合适时也可以快速抛弃；
* 提供一系列大型项目常用的非功能性特征，比如：嵌入式服务器、安全性、度量、运行状况检查、外部化配置等；
* 零配置（无冗余代码生成和XML强制配置 ，遵循“约定大于配置”）；

 

简言之，抛弃了传统JavaEE项目繁琐的配置、学习过程，让开发过程变得 so easy!

## Spring Boot 简史

* 2016年 10月 11日，Spring Boot 获得 [JAX Innovation Awards 2016](https://jaxenter.com/winners-jax-innovation-awards-2016-jax-london-129588.html) 大奖

## Spring Boot 不是什么

### 与 Spring 框架的关系

Spring 框架的关系通过 IOC机智来管理bean。Spring Boot 依赖 Spring 框架来管理对象的依赖。 Spring Boot并不是Spring的精简版本，而是为使用Spring做好各种产品级准备。

### 与 SpringMVC 框架的关系

SpringMVC 实现了 Web项目中的 MVC模式。如果Spring Boot是一个 Web项目的话，可以选择采用  SpringMVC 来实现 MVC模式。当然也可以选择其他类似的框架。

### 与 Spring Cloud 框架的关系

 Spring Cloud 框架可以实现一整套分布式系统的解决方案（当然其中也包括微服务架构的方案），包括服务注册、服务发现、监控等，而Spring Boot只是作为开发单一服务的框架的基础。