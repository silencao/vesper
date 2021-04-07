plugins {
    /* 必须执行
     $ gradlew vesper-gradle-plugin:publishToMavenLocal
     将插件jar包发布到本机仓库后才能引入 */
    //id("com.github.silencao.vesper.nodejs") version "0.0.5"
}

//nodeJs {
//    nodeVersion = "14.2.1"
//}

group = "com.github.silencao"
version = "0.0.1"

repositories {
    mavenCentral { url = uri("https://maven.aliyun.com/repository/public") }
}
