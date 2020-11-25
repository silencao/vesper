plugins {
    java
    id("org.springframework.boot"       ) version "2.4.0"          apply false
    id("io.spring.dependency-management") version "1.0.10.RELEASE" apply false
}

allprojects {
    group = "im.silen"

    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/public"       ) }
        maven { url = uri("https://maven.aliyun.com/repository/google"       ) }
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
        maven { url = uri("https://maven.aliyun.com/repository/spring"       ) }
        maven { url = uri("https://maven.aliyun.com/repository/spring-plugin") }
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    configure<JavaPluginConvention> {
        sourceCompatibility = JavaVersion.VERSION_11
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
