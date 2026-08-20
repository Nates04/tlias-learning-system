##tlias-learning-system

##项目简介
基于 Spring Boot + MyBatis + MySQL 的 TLIAS 智能学习辅助系统后端项目。

##技术栈
- Java 11
- Spring Boot 2.7.x
- MyBatis
- MySQL
- JWT
- Lombok
- PageHelper
- Fastjson2
- Spring AOP

##开发工具
- IntelliJ IDEA
- Postman（接口测试）
- Git / GitHub

##功能模块
部门管理（增删改查）
员工管理（增删改查 + 分页查询）
登录认证（JWT）
文件上传
Filter / Interceptor 请求处理与登录校验
操作日志记录（AOP + 自定义注解 @Log）
声明式事务管理（@Transactional）

##运行方式
1.使用 IDEA 导入项目
2.配置 MySQL 数据库（修改 application.properties）
3.导入项目所需数据库
4.运行 EmsApplication.java

##作者
Nates04
