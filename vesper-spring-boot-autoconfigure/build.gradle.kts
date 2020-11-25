version = "0.0.1"

dependencies {
    compileOnly("org.springframework.boot:spring-boot-autoconfigure")
    implementation(project(":vesper-spring-boot-library"))
    implementation("org.springframework.boot:spring-boot-starter-json")

}
