## 大型电商购物
> 计划：前后端分离，前端采用vue，后台使用springboot分模块开发，前后端彻底分离，前端部署到nginx服务器，后端使用tomcat服务器部署

>前期以实现基础功能为主，后期考虑高并发的秒杀模块，以及权限管理等功能

> 尝试使用spring5的新引入的响应式编程来实现，即Web Reactive，包含Spring WebFlux与springboot的spring Reactive web来快速构建项目
###各模块说明
 - wmall-web 控制层
 - wmall-service 业务逻辑层
 - wmall-dao 数据库操作层
 - wmall-common 公共工具模块
 - wmall-model 实体类层
