plugins {
    id("my.project.java-common-conventions")
    id("my.project.spring-boot-conventions")
}

dependencies {
    compileOnly("org.springframework.boot:spring-boot-devtools")

    implementation(project(":vesper-spring-boot-starter"))

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
