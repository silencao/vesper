rootProject.name = "vesper"
apply(from = "gradle/shared.repositories.settings.gradle.kts")

includeBuild("build-logic")
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

enableFeaturePreview("VERSION_CATALOGS")