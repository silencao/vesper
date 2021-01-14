plugins {
    id("my.project.java-library-conventions")
}

dependencies {
    implementation(project(":vesper-spring-boot-library"))
    compileOnly("org.springframework.boot:spring-boot-starter-json")
    compileOnly("org.springframework.boot:spring-boot-starter-data-redis")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}
