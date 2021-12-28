pluginManagement {
    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
    }
}

dependencyResolutionManagement {
    // 设置中心化管理，在build文件里定义远程仓库链接会输出警告
    // repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        mavenCentral { url = uri("https://maven.aliyun.com/repository/public"       ) }
        maven        { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
    }
}