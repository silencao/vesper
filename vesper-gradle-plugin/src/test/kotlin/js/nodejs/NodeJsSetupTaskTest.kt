package js.nodejs

import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test

open class NodeJsSetupTaskTest {
    @Test
    fun exec() {
        val project = ProjectBuilder.builder().build()

        println(project.gradle.gradleUserHomeDir.absolutePath)
        println(project.gradle.gradleHomeDir?.absoluteFile)
        project.plugins.apply(NodeJsPlugin::class.java)
//        (project.tasks.getByName(NodeJsSetupTask.NAME) as NodeJsSetupTask).exec()
    }
}