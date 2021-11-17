# butte-flyer

## 1、案例简介

Java分布式系统中，微服务基础组件（Nacos、Feign、Gateway、Seata）等，系统中间件（Quartz、Redis、Kafka、ElasticSearch，Logstash）等，对常用功能、配置、API等，进行二次浅封装并统一集成管理，以满足日常开发中基础环境搭建与临时工具的快速实现。

- **[butte-flyer](https://gitee.com/cicadasmile/butte-flyer-parent)** 组件封装的应用案例；
- **[butte-frame](https://gitee.com/cicadasmile/butte-frame-parent)** 常用技术组件二次封装；

## 2、分层架构

整体划分五分分层：网关层、应用层、业务层、中间件层、基础层，组合成整体的分布式系统。

![](https://images.gitee.com/uploads/images/2021/1114/231023_53b4ecc2_5064118.png "01-1.png")

服务总览

| 服务名 | 分层 | 端口 | 缓存库 | 数据库 | 描述 |
|:---|:---|:---|:---|:---|:---|
| flyer-gateway | 网关层 | 8010 | db1 | nacos   | 路由控制 |
| flyer-facade  | 应用层 | 8082 | db2 | facade  | 门面服务 |
| flyer-admin   | 应用层 | 8083 | db3 | admin   | 后端管理 |
| flyer-account | 业务层 | 8084 | db4 | account | 账户管理 |
| flyer-quartz  | 业务层 | 8085 | db5 | quartz  | 定时任务 |
| kafka         | 中间件 | 9092 | --- | ------  | 消息队列 |
| elasticsearch | 中间件 | 9200 | --- | ------  | 搜索引擎 |
| redis         | 中间件 | 6379 | --- | ------  | 缓存中心 |
| logstash      | 中间件 | 5044 | --- | es6.8.6 | 日志采集 |
| nacos         | 基础层 | 8848 | --- | nacos   | 注册配置 |
| seata         | 基础层 | 8091 | --- | seata   | 分布事务 |
| mysql         | 基础层 | 3306 | --- | ------  | 数据存储 |

## 3、目录结构

在`butte-frame`中对各个技术栈进行二次封装管理，在`butte-flyer`中进行依赖引用。

``` but
butte-frame
├── frame-base          基础代码块
├── frame-jdbc          数据库组件
├── frame-core          服务基础依赖
├── frame-gateway       路由网关
├── frame-nacos         注册与配置中心
├── frame-seata         分布式事务
├── frame-feign         服务间调用
├── frame-security      安全管理
├── frame-search        搜索引擎
├── frame-redis         缓存管理
├── frame-kafka         消息中间件
├── frame-quartz        定时任务
├── frame-swagger       接口文档
└── frame-sleuth        链路日志

butte-flyer
├── flyer-gateway       网关服务：路由控制
├── flyer-facade        门面服务：功能协作接口
├── flyer-account       账户服务：用户账户
├── flyer-quartz        任务服务：定时任务
└── flyer-admin         管理服务：后端管理
```

## 4、技术栈组件

系统常用的技术栈：基础框架、微服务组件、缓存、安全管理、数据库、定时任务、工具依赖等。

|名称| 版本|说明|
|:---|:---|:---|
| spring-cloud     | 2.2.5.RELEASE   |  微服务框架基础          |
| spring-boot      | 2.2.5.RELEASE   |  服务基础依赖            |
| gateway          | 2.2.5.RELEASE   |  路由网关               |
| nacos            | 2.2.5.RELEASE   |  注册中心与配置管理       |
| seata            | 2.2.5.RELEASE   |  分布式事务管理          |
| feign            | 2.2.5.RELEASE   |  微服务间请求调用        |
| security         | 2.2.5.RELEASE   |  安全管理               |
| sleuth           | 2.2.5.RELEASE   |  请求轨迹链路            |
| security-jwt     | 1.0.10.RELEASE  |  JWT加密组件            |
| hikari           | 3.4.2           |  数据库连接池，默认       |
| mybatis-plus     | 3.4.2           |  ORM持久层框架          |
| kafka            | 2.0.1           |  MQ消息队列             |
| elasticsearch    | 6.8.6           |  搜索引擎               |
| logstash         | 5.2             |  日志采集               |
| redis            | 2.2.5.RELEASE   |  缓存管理与加锁控制      |
| quartz           | 2.3.2           |  定时任务管理           |
| swagger          | 2.6.1           |  接口文档               |
| apache-common    | 2.7.0           |  基础依赖包             |
| hutool           | 5.3.1           |  基础工具包             |

## 5、关于作者

| 有问题加：微信号↓ | 支持关注：公众号↓ |
|----|-----|
| <img width="300px" height="300px" src="https://images.gitee.com/uploads/images/2021/0828/182311_7c8ff7e3_5064118.jpeg"/>   |   <img width="300px" height="300px" src="https://images.gitee.com/uploads/images/2021/0828/182332_f1b13009_5064118.jpeg"/>  |