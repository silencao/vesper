package spring.boot

import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.WriteProperties
import org.gradle.api.tasks.options.Option

open class UpgradeSpringBootTask : WriteProperties() {
    @get: Input
    @set: Option(option = "use-version", description = "升级版本号")
    var version: String = ""

    @Internal
    override fun getGroup(): String { return "build setup" }
    override fun writeProperties() {
        lineSeparator = System.lineSeparator()

        if (version == "")
            throw RuntimeException("未定义版本号！")

        val dependencyManagement = project.extensions.getByType(DependencyManagementExtension::class.java)

        dependencyManagement.imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:$version")
        }

        property("use.spring.boot.version", version)
        property("use.kotlin.version",
            dependencyManagement.importedProperties
                .get("kotlin.version") ?: KotlinVersion.CURRENT
        )

        super.writeProperties()
    }
}