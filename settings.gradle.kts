rootProject.name = "vesper"
apply(from = "gradle/shared.repositories.settings.gradle.kts")

// 构建插件
includeBuild("vesper-gradle-plugin")
// 示例模块
include("vesper-demo:kotlin-getting-started")
include("vesper-demo:reactor-netty")
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

enableFeaturePreview("VERSION_CATALOGS")