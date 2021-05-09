import my.project.gradle.plugin.nodejs.NodeJsPlugin
import my.project.gradle.plugin.nodejs.NodeJsSetupTask
import kotlin.test.Test

internal class NodeJsSetupTaskTest {
    @Test
    fun exec() {
        ProjectBuilder.create(this.javaClass, "nodeJs_plugin_test_project").run {
            println(gradle.gradleUserHomeDir.absolutePath)
            println(gradle.gradleHomeDir?.absoluteFile)
            pluginManager.apply(NodeJsPlugin::class.java)

            val extension = NodeJsPlugin.ext(project)
            println(extension.env)
            tasks.named(NodeJsSetupTask.NAME, NodeJsSetupTask::class.java).get().exec()
        }
    }
}