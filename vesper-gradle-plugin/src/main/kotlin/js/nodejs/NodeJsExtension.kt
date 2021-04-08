package js.nodejs

import logging.kotlinInfo
import org.gradle.api.Project
import java.io.File

open class NodeJsExtension(@Transient val project: Project) {
    private val gradleUserHome = project.gradle.gradleUserHomeDir.also {
        project.logger.kotlinInfo("Storing cached files in $it")
    }

    var nodeVersion = "12.22.1"

    var nodeCommand = "node"
    var nodeDownloadBaseUrl = "https://nodejs.org/dist"
    var nodeDownloadPatternLayout = "v[revision]/[artifact](-v[revision]-[classifier]).[ext]"
    var installationDir = gradleUserHome.resolve("nodejs")
    var download = true

    var env = run {
        val platform = NodeJsPlatform.name
        val architecture = NodeJsPlatform.architecture

        val nodeDirName = "node-v$nodeVersion-$platform-$architecture"
        val nodeDir = File(installationDir.absolutePath).resolve(nodeDirName)
        val isWindows = NodeJsPlatform.name == NodeJsPlatform.WIN
        val nodeBinDir = if (isWindows) nodeDir else nodeDir.resolve("bin")

        fun getExecutable(command: String, customCommand: String, windowsExtension: String): String {
            val finalCommand = if (isWindows && customCommand == command) "$command.$windowsExtension" else customCommand
            return if (download) File(nodeBinDir, finalCommand).absolutePath else finalCommand
        }

        fun getIvyDependency(): String {
            val type = if (isWindows) "zip" else "tar.gz"
            return "org.nodejs:node:$nodeVersion:$platform-$architecture@$type"
        }

        NodeJsEnv(
            nodeDir = nodeDir,
            nodeBinDir = nodeBinDir,
            nodeExecutable = getExecutable("node", nodeCommand, "exe"),
            platformName = platform,
            architectureName = architecture,
            ivyDependency = getIvyDependency()
        )
    }
}