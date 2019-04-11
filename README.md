# es-home
企业级搜索系统案例（已经非常精简），基于现公司项目搭建，可支持千万量级以上搜索。

项目讲解地址：https://blog.csdn.net/u012888052/article/details/81876152

作者：Happy王子乐
QQ：820155406


## 说明

> 基于SpringBoot+MyBatis的搜索系统，包括 同步历史数据、准实时同步数据、条件搜索数据、智能生成代码 四个模块

> 如果该项目对您有帮助，您可以点右上角 "Star" 支持一下 谢谢！

> 或者您可以 "follow" 一下，该项目将持续更新，不断完善功能。

> 项目交流人QQ：[820155406]

> 项目讲解地址：https://blog.csdn.net/u012888052/article/details/81876152

## 前言

`es-home`项目致力于打造一个完整的，智能的，简洁的搜索系统，采用现阶段流行技术实现。

## 项目介绍

`es-home`项目是一套搜索系统，包括 **同步历史数据、准实时同步数据、条件搜索数据、智能生成代码** 四个模块。
采用JAVA语言，并结合elasticsearch、canal、MySQL实现数据搜索、同步数据。

### 项目演示

> 相关截图



### 组织结构

``` lua
es-home
├── data-back -- 智能生成同步代码、搜索代码，功能尚未完善。
├── data-dump -- 准实时同步变动数据，MySQL -> ES
├── data-migration -- 批量同步历史数据
├── data-search -- 提供对外搜索接口
└── stone -- 以上模块公共代码

es-home-web 智能生成各模块代码，vue操作页面

```


## es-home-web 初版原型图
https://www.processon.com/view/link/5caef9a4e4b0a13c9ddb8e5d

## 目前data-back已实现功能
1. 生成data-migration项目代码
2. 生成data-search项目代码


### 技术选型

#### 后端技术

技术 | 说明 | 官网
----|----|----
Spring Boot | 容器+MVC框架 | [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot)
MyBatis | ORM框架  | [http://www.mybatis.org/mybatis-3/zh/index.html](http://www.mybatis.org/mybatis-3/zh/index.html)
MyBatisGenerator | 数据层代码生成 | [http://www.mybatis.org/generator/index.html](http://www.mybatis.org/generator/index.html)
Swagger-UI | 文档生产工具 | [https://github.com/swagger-api/swagger-ui](https://github.com/swagger-api/swagger-ui)
Elasticsearch | 搜索引擎 | [https://github.com/elastic/elasticsearch](https://github.com/elastic/elasticsearch)
Lombok | 简化对象封装工具 | [https://github.com/rzwitserloot/lombok](https://github.com/rzwitserloot/lombok)

#### 前端技术

技术 | 说明 | 官网
----|----|----
Vue | 前端框架 | [https://vuejs.org/](https://vuejs.org/)
Vue-router | 路由框架 | [https://router.vuejs.org/](https://router.vuejs.org/)
Vuex | 全局状态管理框架 | [https://vuex.vuejs.org/](https://vuex.vuejs.org/)
Element | 前端UI框架 | [https://element.eleme.io/](https://element.eleme.io/)
Axios | 前端HTTP框架 | [https://github.com/axios/axios](https://github.com/axios/axios)

## 环境搭建

### 开发工具

工具 | 说明 | 官网
----|----|----
IDEA | 开发IDE | https://www.jetbrains.com/idea/download
Navicat | 数据库连接工具 | http://www.formysql.com/xiazai.html

### 开发环境

工具 | 版本号 | 下载
----|----|----
JDK | 1.8 | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
Mysql | 5.7 | https://www.mysql.com/
Elasticsearch | 6.3.0 | https://www.elastic.co/downloads
Canal | | https://github.com/alibaba/canal/releases
### 搭建步骤

> 本地环境搭建

- 本地安装开发环境中的所有工具并启动，具体参考博客：https://blog.csdn.net/u012888052/article/details/81876152

