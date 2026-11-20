<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">share-power 共享充电宝微服务平台</h1>
<h4 align="center">基于 RuoYi-Cloud v3.6.3 的 Spring Cloud & Alibaba 分布式微服务架构</h4>

## 平台简介

本项目是一套面向共享充电宝业务的分布式微服务系统，在若依微服务框架基础上扩展了设备接入、计费规则、订单、支付、数据统计等业务能力。

* 采用前后端分离模式，前端工程（Vue/Element UI）单独维护，不在本仓库内。
* 后端采用 Spring Boot 3、Spring Cloud & Alibaba，JDK 17。
* 注册中心、配置中心选型 Nacos，权限认证使用 Redis + JWT。
* 设备侧通过 EMQX（MQTT）接入，订单结束等异步流程使用 RabbitMQ。
* 计费规则基于 Drools 规则引擎，支付对接微信支付 V3，统计模块支持 AI 生成 SQL 查询报表。

## 技术栈

| 组件 | 版本 | 说明 |
| --- | --- | --- |
| JDK | 17 | 编译与运行环境 |
| Spring Boot | 3.0.5 | 基础框架 |
| Spring Cloud | 2022.0.2 | 微服务治理 |
| Spring Cloud Alibaba | 2022.0.0.0-RC2 | Nacos / Sentinel 等 |
| Nacos | 2.x | 注册中心 + 配置中心 |
| MySQL | 8.x | 业务数据存储 |
| Redis | 6.x+ | 缓存、会话、分布式锁 |
| MongoDB | 5.x+ | 门店/站点地理位置存储 |
| RabbitMQ | 3.x | 订单结束等异步消息 |
| EMQX | 4.x/5.x | MQTT 消息服务器，用于设备接入 |
| MyBatis-Plus | 3.5.3.1 | 持久层 |
| Druid / dynamic-datasource | 1.2.21 / 4.2.0 | 连接池与多数据源 |
| Knife4j | 4.1.0 | 接口文档 |
| Drools | 8.41.0.Final | 计费规则引擎 |
| Redisson | 3.23.3 | 分布式锁 |
| MinIO / FastDFS | - | 文件服务（按配置启用） |
| 微信支付 SDK | 0.2.11 | 微信支付 V3 |
| Paho MQTT | 1.2.2 | 设备 MQTT 通信 |

## 系统模块

~~~
com.share
├── share-gateway         // 网关模块 [18080]
├── share-auth            // 认证中心 [9200]
├── share-api             // 接口模块（Feign 远程调用）
│       └── share-api-system                          // 系统服务接口
│       └── share-api-user                            // 用户服务接口
│       └── share-api-order                           // 订单服务接口
│       └── share-api-rule                            // 计费规则服务接口
├── share-common          // 通用模块
│       └── share-common-core                         // 核心模块
│       └── share-common-datascope                    // 数据权限
│       └── share-common-datasource                   // 多数据源
│       └── share-common-log                          // 日志记录
│       └── share-common-rabbit                       // 消息队列封装
│       └── share-common-redis                        // 缓存服务
│       └── share-common-seata                        // 分布式事务
│       └── share-common-security                     // 安全模块
├── share-modules         // 业务模块
│       └── share-system                              // 系统模块 [9201]
│       └── share-gen                                 // 代码生成 [9202]
│       └── share-job                                 // 定时任务 [9203]
│       └── share-device                              // 设备模块 [9205]
│       └── share-user                                // 用户模块 [9209]
│       └── share-payment                             // 支付模块 [9211]
│       └── share-order                               // 订单模块 [19207]
│       └── share-rule                                // 计费规则 [19208]
│       └── share-stastics                            // 数据统计 [9505]
│       └── share-file                                // 文件服务 [9300]
├── share-visual          // 图形化管理模块
│       └── share-monitor                             // 监控中心 [9100]
└── pom.xml               // 公共依赖
~~~

## 业务功能

1. 设备管理：机柜（Cabinet）、机柜类型、充电宝（PowerBank）、门店（Station）、区域（Region）维护，支持地图选点与门店位置检索。
2. 设备接入：基于 EMQX/MQTT 与机柜通信，接收设备上报消息并下发指令，处理弹仓、归还等回调。
3. 用户管理：C 端用户信息维护与统计。
4. 计费规则：基于 Drools 配置分时段、封顶等计费策略，供订单模块远程调用。
5. 订单管理：下单、归还、结束订单全流程，通过 RabbitMQ 异步结束订单并做幂等处理。
6. 支付中心：对接微信支付 V3，创建预支付单、处理支付回调与支付记录。
7. 数据统计：订单统计、用户统计，支持调用 AI 服务将自然语言转换为 SQL 生成报表数据（依赖外部 AI 服务，默认地址 `http://localhost:8899/ai/generateSql`）。

## 内置功能（系统模块）

1. 用户管理、部门管理、岗位管理、角色管理、菜单管理
2. 字典管理、参数管理、通知公告
3. 操作日志、登录日志、在线用户
4. 定时任务、代码生成、系统接口文档
5. 服务监控、连接池监视

## 环境准备

| 依赖 | 说明 |
| --- | --- |
| JDK 17 + Maven 3.8+ | 后端编译运行 |
| MySQL 8.x | 导入 `sql/share-system.sql`（业务库），`sql/quartz.sql`（定时任务表） |
| Redis | 缓存与登录状态 |
| MongoDB | 导入 `sql/share-mongo.js`（门店地理位置集合） |
| RabbitMQ | 订单异步消息 |
| EMQX | 设备 MQTT 接入，需在 Nacos 中配置 `emqx.client.*` 连接信息 |
| Nacos 2.x | 注册与配置中心，需创建对应 namespace 并导入各服务配置 |

## 快速启动

1. 启动 MySQL、Redis、MongoDB、RabbitMQ、EMQX。
2. 导入 `sql` 目录下的初始化脚本。
3. 启动 Nacos，创建 namespace，并导入各服务的 `application-dev.yml` 配置（数据库、Redis、MQ、EMQX、微信支付等）。
4. 修改各服务 `bootstrap.yml` 中的 Nacos 地址与 namespace（默认 `192.168.136.128:8848`，账号密码 `nacos/nacos`）。
5. 编译打包：

   ~~~bash
   mvn clean package -DskipTests
   ~~~

6. 按依赖顺序启动：`share-gateway`、`share-auth`、`share-system`、其余业务服务。
7. 统一通过网关访问：`http://localhost:18080`，接口文档由 Knife4j 提供。
8. 系统默认账号：`admin / admin123`（首次登录后请修改密码）。

## 配置说明

* 配置以 Nacos 为准，`bootstrap.yml` 只保留服务名、端口、Nacos 地址与 namespace。
* 网关是唯一对外入口，前端请求统一走 `18080`，由网关转发到各微服务。
* `share-stastics` 使用 `application.yml` 注册到 Nacos，端口为 `9505`。
* AI 统计能力需要额外部署 AI 服务（默认 `8899` 端口），未部署时不影响其他功能。

## 致谢

本项目基于 [RuoYi-Cloud](https://gitee.com/y_project/RuoYi-Cloud) 扩展开发，遵循其开源协议，感谢若依团队。
