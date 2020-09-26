## 关于本项目
整合vue+springboot的前后端分离项目，只用最新版是我最后的倔强。包括：

- 【前端】vue 3.x, vue-router 4.x, vuex 4.x
- 【后端】springboot 2.3.x

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
导入为gradle项目，然后下载依赖。不会用命令行指令（Java初学者）

PS: 关于gradle，记录一个使用的技巧，在环境变量里配置
GRADLE_USER_HOME为一个路径，可以自定义下载依赖存放的路径

本项目还有一个必须配置的环境变量SPRING_APPLICATION_JSON，根据我的实际配置举例：
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