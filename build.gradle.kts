plugins {
    id("org.springframework.boot"       ) version "2.4.0"          apply false
    id("io.spring.dependency-management") version "1.0.10.RELEASE" apply false
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    group = "im.silen"

    dependencies {
    }

    repositories {
        maven { url = uri("https://maven.aliyun.com/nexus/content/groups/public"             ) }
        maven { url = uri("https://maven.aliyun.com/nexus/content/repositories/google"       ) }
        maven { url = uri("https://maven.aliyun.com/nexus/content/repositories/gradle-plugin") }
        maven { url = uri("https://maven.aliyun.com/nexus/content/repositories/spring"       ) }
        maven { url = uri("https://maven.aliyun.com/nexus/content/repositories/spring-plugin") }
    }

    configure<JavaPluginConvention> {
        sourceCompatibility = JavaVersion.VERSION_11
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
