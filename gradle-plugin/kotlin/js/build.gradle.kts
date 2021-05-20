plugins {
    id("my.project.kotlin-dsl")
}

repositories {
    maven { url = uri("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev/") }
    maven { url = uri("https://maven.aliyun.com/repository/public") }
}

dependencies {
    /* https://youtrack.jetbrains.com/issue/KT-41142 */
    implementation(kotlin("gradle-plugin", "1.5.10"))
}