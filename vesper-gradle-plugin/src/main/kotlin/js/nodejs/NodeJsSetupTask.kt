package js.nodejs

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.net.URI

open class NodeJsSetupTask : DefaultTask() {
    private val extension = NodeJsPlugin.ext(project)

    @TaskAction
    fun exec() {
        val repo = project.repositories.ivy {  repo ->
            repo.name = "Node Distributions at ${extension.nodeDownloadBaseUrl}"
            repo.url = URI(extension.nodeDownloadBaseUrl)

            repo.patternLayout {
                it.artifact(extension.nodeDownloadPatternLayout)
            }
            repo.metadataSources { it.artifact() }
            repo.content { it.includeModule("org.nodejs", "node") }
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
                    it.from(project.zipTree(result))
                    it.into(resolve)
                }
            }
            else -> {
                project.copy {
                    it.from(project.tarTree(result))
                    it.into(resolve)
                }
            }
        }
    }

    companion object {
        const val NAME: String = "kotlinNodeJsSetup"
    }
}