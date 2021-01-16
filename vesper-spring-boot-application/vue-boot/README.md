## 关于本项目
- 【前端】vue 3.x, vue-cli 4.x
- 【后端】springboot 2.4.x

目标是想做一个统计增长率的应用，比如某游戏每天输入战斗力，然后通过图表看到成长率，虽然幼稚，但是我觉得以我的脑子只能想到这种创意了。
##### 前端初始化
```shell script
$ cd src/main/webapp
$ npm install
$ npm run serve
$ npm run build
$ npm run lint
```

##### 后端初始化
导入为gradle项目，命令行执行时自动下载依赖。
```shell
# change dir to project-root
cd xxx/vesper
# build executable jar
gradlew vesper-spring-boot-application:vue-boot:bootJar
# clean build dir
gradlew vesper-spring-boot-application:vue-boot:clean
```

本项目启动前还有一个必须配置的环境变量SPRING_APPLICATION_JSON，根据我的实际配置举例：
```json
{
  "aliyun": {
    "ip": "xx.xx.xx.xx"
  },
  "spring": {
    "datasource": {
      "url": "jdbc:mysql://${aliyun.ip:localhost}:3306/ADatabase",
      "username": "ASuperman",
      "password": "none"
    },
    "redis": {
      "host": "${aliyun.ip:localhost}",
      "password": "meiyou"
    }
  }
}
```
这样就可以不暴露敏感信息也托管代码了（你们休想连接我服务器搞事，就算翻我提交记录也没用，我当然会改密码呀，不过还是求放过2333）。
配置后启动springboot项目时会把application.yml和环境变量的配置相融合。