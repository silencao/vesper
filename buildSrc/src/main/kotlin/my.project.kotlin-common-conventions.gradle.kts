plugins {
    kotlin("jvm") // 好像还可以这么写 `kotlin-platform-jvm`
    eclipse
    id("my.project.test-conventions")
}

dependencies {
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.6"
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