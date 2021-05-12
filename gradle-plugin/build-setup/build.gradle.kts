import org.gradle.kotlin.dsl.support.expectedKotlinDslPluginsVersion

plugins {
    `kotlin-dsl`
}

dependencies {
    implementation("org.gradle.kotlin:gradle-kotlin-dsl-plugins:$expectedKotlinDslPluginsVersion")
    /*TODO:
     *  就是用这个插件解析 pom 文件里的版本号
     *  之后看看能不能自己解析 */
    implementation("io.spring.gradle:dependency-management-plugin:1.0.11.RELEASE")
}