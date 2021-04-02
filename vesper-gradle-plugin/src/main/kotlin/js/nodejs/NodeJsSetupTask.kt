package js.nodejs

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.net.URI

open class NodeJsSetupTask : DefaultTask() {
    @TaskAction
    fun exec() {
        val repo = project.repositories.ivy {  repo ->
            repo.name = "Node Distributions at https://nodejs.org/dist"
            repo.url = URI("https://nodejs.org/dist")

            repo.patternLayout {
                it.artifact("v[revision]/[artifact](-v[revision]-[classifier]).[ext]")
                it.ivy("v[revision]/ivy.xml")
            }
            repo.metadataSources { it.artifact() }
            repo.content { it.includeModule("org.nodejs", "node") }
        }
        val dep = this.project.dependencies.create("org.nodejs:node:14.16.0:win-x64@zip")
        val conf = this.project.configurations.detachedConfiguration(dep)
        conf.isTransitive = false

        val result = conf.resolve().single()
        project.repositories.remove(repo)


        val resolve = project.gradle.gradleUserHomeDir.resolve("nodeJs")

        println(resolve)
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