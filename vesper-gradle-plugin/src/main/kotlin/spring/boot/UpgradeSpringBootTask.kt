package spring.boot

import gradle.kotlin.dsl.accessors._e00424364118eb94b028be99e96b91cf.dependencyManagement
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.WriteProperties
import org.gradle.api.tasks.options.Option

open class UpgradeSpringBootTask : WriteProperties() {
    @get: Input
    @set: Option(option = "use-version", description = "升级版本号")
    var version: String = ""

    override fun writeProperties() {
        group = "build setup"
        lineSeparator = System.lineSeparator()

        if (version == "")
            throw RuntimeException("未定义版本号！")

        project.dependencyManagement.imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:$version")
        }

        property("use.spring.boot.version", version)
        property("use.kotlin.version",
            project.dependencyManagement.importedProperties
                .get("kotlin.version") ?: KotlinVersion.CURRENT
        )

        super.writeProperties()
    }
}