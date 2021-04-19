plugins {
    id("org.springframework.boot")
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    archiveClassifier.set("boot") // 打包时代码库和执行包避免重名
}