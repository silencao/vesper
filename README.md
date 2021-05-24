## 关于本项目
vesper, 整合vue+spring全家桶，主要包含：

- 【前端】vue 3.x, vue-cli 4.x, vite
- 【后端】spring 5.x, springboot 2.5.x
- 【构建】gradle 6.x with Kotlin-DSL

网上gradle的资料很少，本项目将探索gradle项目最佳实践，已实现的特性
- [x] 构建多模块项目
- [x] 使用buildSrc生成插件，提取通用构建逻辑并应用到模块
- [x] 依赖版本号通过spring-boot-dependencies bom机制自动管理
- [ ] 编写插件初始化前端项目环境读取可运行脚本

##### 导入初始化
导入为gradle项目，然后下载依赖。

PS: 关于gradle，记录一个使用的技巧，在环境变量里配置
GRADLE_USER_HOME为一个路径，可以自定义下载依赖存放的路径，
类似于maven的.m2路径，如果不配置此变量，应该和.m2路径同级

###### 构建工具命令行指令
```shell
# use gradle-wrapper. eg: gradlew [module-name]:[task-name]
# run rest-client springboot application
gradlew vesper-spring-boot-application:rest-client:bootRun
# build executable jar
gradlew vesper-spring-boot-application:rest-client:bootJar
# clean module build directory
gradlew vesper-spring-boot-application:rest-client:clean
```