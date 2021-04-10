rootProject.name = "vesper"
// 示例模块
include("vesper-spring-boot-demo:reactor-netty")
// 应用模块
include("vesper-spring-boot-application:flux-server")
include("vesper-spring-boot-application:hello-kotlin")
include("vesper-spring-boot-application:rest-client")
include("vesper-spring-boot-application:security-mvc")
include("vesper-spring-boot-application:vue-boot")
include("vesper-spring-boot-autoconfigure")
include("vesper-spring-boot-library")
include("vesper-spring-boot-starter")
// 测试模块
include("vesper-spring-boot-test")
// 其他模块
include("vesper-js-application")
include("kotlin-getting-started")

includeBuild("vesper-gradle-plugin")
includeBuild("gradle-kts-initial-project")

dependencyResolutionManagement {
    // 设置中心化管理，在build文件里定义远程仓库链接会输出警告
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/public"       ) }
    }
}