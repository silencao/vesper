plugins {
    id("my.project.kotlin-dsl")
}

dependencies {
    val use_kotlin_version: String by project

    implementation(project(":spring"))
    /**
     * kotlin项目构建必须依赖此插件
     * ps: kotlin("module-name") 引入当前gradle中kotlin对应版本的插件
     */
    implementation(kotlin("gradle-plugin", use_kotlin_version))
    /**
     * springboot项目构建所需
     * ps: gradlew --version 如果kotlin是1.4.20，
     * 此时通过kotlin("allopen")则自动引入1.4.20版本插件
     * 还可以通过下面注释方式指定版本号引入依赖，这个jar会依赖kotlin("allopen")对应插件
     * implementation(
     *     group = "org.jetbrains.kotlin.plugin.spring",
     *     name  = "org.jetbrains.kotlin.plugin.spring.gradle.plugin",
     *     version = "1.4.21"
     * )
     */
    runtimeOnly(kotlin("allopen"      , use_kotlin_version))

}