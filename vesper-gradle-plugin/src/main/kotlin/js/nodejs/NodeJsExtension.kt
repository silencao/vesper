package js.nodejs

import logging.kotlinInfo
import org.gradle.api.Project

open class NodeJsExtension(@Transient val project: Project) {
    private val gradleUserHome = project.gradle.gradleUserHomeDir.also {
        project.logger.kotlinInfo("Storing cached files in $it")
    }

    var nodeVersion = "12.22.1"
    var installationDir = gradleUserHome.resolve("nodejs")
}