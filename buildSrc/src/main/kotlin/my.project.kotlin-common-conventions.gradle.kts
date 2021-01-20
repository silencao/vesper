plugins {
    kotlin("jvm")
    eclipse
    id("my.project.spring-dependency-management")
    id("my.project.test-conventions")
}

dependencies {
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}
// STS4完全不支持kotlin项目，模块配置全清
eclipse {
    project.file.whenMerged(Action<org.gradle.plugins.ide.eclipse.model.Project> { 
        buildCommands.clear()
        natures      .clear()
    })
    
    classpath.file.whenMerged(Action<org.gradle.plugins.ide.eclipse.model.Classpath> {
        entries.clear()
    })
}