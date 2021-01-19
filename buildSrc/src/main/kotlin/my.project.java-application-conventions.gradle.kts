plugins {
    id("my.project.java-common-conventions")
    id("my.project.spring-boot-conventions")
}

dependencies {
    compileOnly("org.springframework.boot:spring-boot-devtools")

    implementation(project(":vesper-spring-boot-starter"))
//    implementation("org.springframework.boot:spring-boot-starter-webflux")
//    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
