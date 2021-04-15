plugins {
    id("io.spring.dependency-management")
}

tasks.register("upgradeSpringBoot", spring.boot.UpgradeSpringBootTask::class.java) {
    group = "build setup"
    outputFile = projectDir.resolve("buildSrc/${Project.GRADLE_PROPERTIES}")

    doFirst {
        dependencyManagement.imports { mavenBom("org.springframework.boot:spring-boot-dependencies:$version") }

        property(
            "use.kotlin.version",
            dependencyManagement.importedProperties.get("kotlin.version") ?: embeddedKotlinVersion
        )
    }
}