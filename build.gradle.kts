plugins {
    java
    id("org.springframework.boot"       ) version "2.4.0"          apply false
    id("io.spring.dependency-management") version "1.0.10.RELEASE" apply false
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    group = "im.silen"

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-webflux")
        testImplementation("io.projectreactor:reactor-test")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/public"       ) }
        maven { url = uri("https://maven.aliyun.com/repository/google"       ) }
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
        maven { url = uri("https://maven.aliyun.com/repository/spring"       ) }
        maven { url = uri("https://maven.aliyun.com/repository/spring-plugin") }
    }

    configure<JavaPluginConvention> {
        sourceCompatibility = JavaVersion.VERSION_11
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
        archiveClassifier.set("executable") // 打包时代码库和执行包避免重名
    }
}
