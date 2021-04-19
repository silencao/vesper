plugins {
    id("my.project.java-spring-boot-conventions")
}

dependencies {
    // 非启动器内依赖
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-csv")

    implementation("org.springframework.boot:spring-boot-starter-json")

    testImplementation("org.springframework.boot:spring-boot-starter-webflux")
}

// 单元测试编译不排除资源文件
tasks.processTestResources {
    from("src/test/java") { include("**/**.json") }
}