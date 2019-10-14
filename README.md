## 大型电商购物
### 开发计划
- 计划：前后端分离，前端采用vue，后台使用springboot分模块开发，前后端彻底分离，前端部署到nginx服务器，后端使用tomcat服务器部署

- 前期以实现基础功能为主，后期考虑高并发的秒杀模块，以及多服务（dubbo）的引入以及权限管理(Spring Security)，以及高并发的处理等
### 各分支介绍
- master 主分支
    - develop 开发分支
    - ~~web-flux~~(**暂时搁置**) 使用spring响应式编程

 #### 关于web-flux
- spring5的新引入的响应式编程来实现，即Web Reactive，包含Spring WebFlux与spring Boot的spring Reactive web来快速构建项目，具体的技术比较新，比如spring-data-r2dbc，就是实现关系型数据库的非阻塞异步，spring官方提供的解决方案。现在暂时停止开发，但这块的技术后期一定会深入的去学习。说白了就是异步非阻塞的java后台实现。
### 各模块说明
 - wmall-web 控制层
 - wmall-service 业务逻辑层
 - wmall-dao 数据库操作层
 - wmall-common 公共工具模块
 - wmall-model 实体类层

