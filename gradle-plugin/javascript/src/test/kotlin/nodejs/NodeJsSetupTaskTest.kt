package nodejs

import ProjectTest
import kotlin.test.Test

internal class NodeJsSetupTaskTest {
    @Test
    fun exec() {
        val project = ProjectTest.project(this.javaClass, "nodeJs_plugin_test_project")

        println(project.gradle.gradleUserHomeDir.absolutePath)
        println(project.gradle.gradleHomeDir?.absoluteFile)
        project.pluginManager.apply(NodeJsPlugin::class.java)

        val extension = project.extensions.getByType(NodeJsExtension::class.java)
        println(extension.env)
//        project.tasks.named(NodeJsSetupTask.NAME, NodeJsSetupTask::class.java).get().exec()
    }
}