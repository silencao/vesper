import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    java
    id("org.springframework.boot"       ) version "2.4.0"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
}

group = "im.silen"
version = "0.0.4"

repositories {
    maven { url = uri("https://maven.aliyun.com/nexus/content/groups/public") }
    maven { url = uri("https://maven.aliyun.com/nexus/content/repositories/google") }
    maven { url = uri("https://maven.aliyun.com/nexus/content/repositories/gradle-plugin") }
    maven { url = uri("https://maven.aliyun.com/nexus/content/repositories/spring") }
    maven { url = uri("https://maven.aliyun.com/nexus/content/repositories/spring-plugin") }
}

dependencies {
    compileOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("mysql:mysql-connector-java")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

tasks.getByName<BootJar>("bootJar") {
    archiveClassifier.set("boot")
}