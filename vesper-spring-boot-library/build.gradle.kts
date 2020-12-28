plugins {
    id("my.project.java-library-conventions")
}

dependencies {
    api("org.springframework.boot:spring-boot-starter-json")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
}