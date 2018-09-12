# 基于 Spring Cloud 微服务架构

本项目基于SpringBoot 2.0.4.RELEASE、SpringCloud Finchley.SR1体系实现，支持Java、Scala混编，项目初期孵化中...

利用Spring Boot Admin 来监控各个独立Service的运行状态；利用Hystrix Dashboard来实时查看接口的运行状态和调用频率等。

## 一、项目开发环境&工具
- MacOS Sierra / Windows 10
- CentOS 7
- JDK 1.8
- Scala 2.11.12
- MySql 5.7
- Redis 4.0.9
- RabbitMQ 3.7.7
- IntelliJ IDEA 2018.1.4 / Eclipse 4.7.3
- Maven 3.3.9

## 二、组件情况
- 服务注册、发现: eureka
- 配置中心: spring config
- 服务监控: spring boot admin
- 路由网关服务: zuul
- 负载均衡: feign / ribbon
- 断路器: hystrix
- 集群监控: turbine
- 链路追踪: spring cloud sletuh -> zipkin
- 消息总线：spring cloud bus
- 安全认证: spring security
- web模板引擎：thymeleaf
- ORM框架：jpa + mybatis
- 数据源监控：druid
- api文档输出: swagger2
- 分布式锁: redis
- 消息队列: rabbitmq（待实现）
- 分布式事物：3PC+TCC（待实现）

## 三、服务启动顺序
以下服务需按顺序启动
- cycfc-eureka-server（端口：8601、8602）
- cycfc-config-server（端口：9000）
- cycfc-admin-server（端口：8800）
- cycfc-zipkin-server（端口：9411）
- cycfc-zuul-service（端口：8088）
- cycfc-turbine-server（端口：8900）
    - 访问地址：http://localhost:8900/hystrix
    - 监控流：http://localhost:8900/turbine.stream
- cycfc-demo（示例模块）
    - cycfc-demo-provider（端口：8861、8862、8863...）
    - cycfc-demo-consumer（端口：8701、8702、8703...）
    - cycfc-demo-ribbon（端口：8765）
    - cycfc-demo-scala（端口：8766）
