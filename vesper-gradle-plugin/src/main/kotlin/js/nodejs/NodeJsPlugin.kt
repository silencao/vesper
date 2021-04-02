package js.nodejs

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.BasePlugin

open class NodeJsPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        plugins.apply(BasePlugin::class.java)

        val setupTask = tasks.register(NodeJsSetupTask.NAME, NodeJsSetupTask::class.java) {
            it.group = TASKS_GROUP_NAME
            it.description = "Download and install a local node/npm version"
        }

        project.extensions.create("nodeJs", NodeJsExtension::class.java)
//        val rootClean = project.rootProject.tasks.named(BasePlugin.CLEAN_TASK_NAME)
        val clean = project.tasks.named(BasePlugin.CLEAN_TASK_NAME)

        tasks.register("kotlinNpmInstall") {
            it.dependsOn(setupTask)
            it.group = TASKS_GROUP_NAME
            it.description = "Find, download and link NPM dependencies and projects"

            it.mustRunAfter(clean)
        }
    }

    companion object {
        const val TASKS_GROUP_NAME: String = "nodeJs"
    }
}