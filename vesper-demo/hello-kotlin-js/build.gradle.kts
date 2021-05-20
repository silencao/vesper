plugins {
    id("my.project.kotlin-js-common")
}

buildscript {
    repositories {
        maven { url = uri("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev/") }
        maven { url = uri("https://maven.aliyun.com/repository/public") }
    }
}

kotlin {
    js {
        browser()
        nodejs()
    }
}