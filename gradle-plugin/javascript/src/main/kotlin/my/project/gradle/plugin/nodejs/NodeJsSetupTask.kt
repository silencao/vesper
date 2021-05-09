package my.project.gradle.plugin.nodejs

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.net.URI

open class NodeJsSetupTask : DefaultTask() {
    private val extension = NodeJsPlugin.ext(project)

    @TaskAction
    fun exec() {
        val repo = project.repositories.ivy {
            name = "Node Distributions at ${extension.nodeDownloadBaseUrl}"
            url = URI(extension.nodeDownloadBaseUrl)

            patternLayout {
                artifact(extension.nodeDownloadPatternLayout)
            }
            metadataSources { artifact() }
            content { includeModule("org.nodejs", "node") }
        }
        val dep  = this.project.dependencies.create(extension.env.ivyDependency)
        val conf = this.project.configurations.detachedConfiguration(dep)
        conf.isTransitive = false

        val result = conf.resolve().single()
        project.repositories.remove(repo)

        val resolve = project.gradle.gradleUserHomeDir.resolve("nodeJs")
        when {
            result.name.endsWith("zip") -> {
                project.copy {
                    from(project.zipTree(result))
                    into(resolve)
                }
            }
            else -> {
                project.copy {
                    from(project.tarTree(result))
                    into(resolve)
                }
            }
        }
    }

    companion object {
        const val NAME: String = "kotlinNodeJsSetup"
    }
}