plugins {
    id("my.project.java-common-conventions")
    id("org.springframework.boot"          )
}

dependencies {
    compileOnly("org.springframework.boot:spring-boot-devtools")

    implementation(project(":vesper-spring-boot-starter"))
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    archiveClassifier.set("executable") // 打包时代码库和执行包避免重名
}