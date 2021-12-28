package my.project.gradle.plugin.nodejs

import my.project.gradle.plugin.task.registerExec
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.BasePlugin

open class NodeJsPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        plugins.apply(BasePlugin::class.java)

        val setupTask = tasks.register(NodeJsSetupTask.NAME, NodeJsSetupTask::class.java) {
            group = TASKS_GROUP_NAME
            description = "Download and install a local node/npm version"
        }

        project.extensions.create("nodeJs", NodeJsExtension::class.java, project)

        val clean = project.tasks.named(BasePlugin.CLEAN_TASK_NAME)

        tasks.register("kotlinNpmInstall") {
            dependsOn(setupTask)
            group = TASKS_GROUP_NAME
            description = "Find, download and link NPM dependencies and projects"

            mustRunAfter(clean)
        }

        tasks.registerExec("node")
        tasks.registerExec("npm" )
        tasks.registerExec("npx" )

    }

    companion object {
        const val TASKS_GROUP_NAME: String = "nodeJs"

        fun ext(project: Project): NodeJsExtension {
            return project.extensions.getByType(NodeJsExtension::class.java)
        }
    }
}