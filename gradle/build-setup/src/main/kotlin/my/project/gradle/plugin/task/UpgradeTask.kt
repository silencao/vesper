package my.project.gradle.plugin.task

import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import my.project.gradle.plugin.BuildSetup
import org.gradle.api.Project
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.WriteProperties
import org.gradle.api.tasks.options.Option

open class UpgradeTask : WriteProperties() {
    @get: Input
    @set: Option(option = "spring-boot-version", description = "要升级的Spring Boot版本号")
    var version: String = ""

    @Input
    var outputDir: String = ""

    @Internal
    override fun getGroup(): String { return BuildSetup.group }
    override fun writeProperties() {
        lineSeparator = System.lineSeparator()

        if (version == "")
            throw RuntimeException("未定义版本号！")

        val dependencyManagement = project.extensions.getByType(DependencyManagementExtension::class.java)

        dependencyManagement.imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:$version")
        }

        outputFile = project.rootDir.resolve(outputDir).resolve(Project.GRADLE_PROPERTIES)
        property("use_spring_boot_version", version)
        property("use_kotlin_version", dependencyManagement
            .importedProperties["kotlin.version"] ?: KotlinVersion.CURRENT
        )

        super.writeProperties()
    }
}