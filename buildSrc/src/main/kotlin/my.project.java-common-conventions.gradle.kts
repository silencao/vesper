plugins {
    java
    id("io.spring.dependency-management")
}

dependencyManagement {
    imports {
        // spring的依赖管理插件导入springboot当前版本关联的BOM
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<Test> {
    useJUnitPlatform()
}