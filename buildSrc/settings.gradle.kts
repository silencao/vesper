pluginManagement {
    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
    }
}
// buildSrc模块获取不到rootProject的settings，所以需要再单独定义
dependencyResolutionManagement {
    // 设置中心化管理，在build文件里定义远程仓库链接会输出警告
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/public"       ) }
    }
}