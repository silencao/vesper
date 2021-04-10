plugins {
    `kotlin-dsl`
}

group = "com.github.silencao"
version = "0.0.1"

repositories {
    mavenCentral { url = uri("https://maven.aliyun.com/repository/public") }
}

gradlePlugin {
    plugins {
        create("nodeJsPlugin") {
            id = "$group.vesper.nodejs"
            implementationClass = "js.nodejs.NodeJsPlugin"
        }
    }
}

//tasks.register<Exec>("testExec") {
//    group = js.nodejs.NodeJsPlugin.TASKS_GROUP_NAME
//
//    commandLine = mutableListOf(nodeJs.env.nodeExecutable, "-h")
//}