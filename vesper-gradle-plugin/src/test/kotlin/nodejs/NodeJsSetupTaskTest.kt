package nodejs

import org.gradle.testfixtures.ProjectBuilder
import org.gradle.util.GFileUtils
import java.io.File
import java.util.*
import kotlin.test.Test

internal class NodeJsSetupTaskTest {
    @Test
    fun exec() {
        val project = Optional.ofNullable(this.javaClass.getResource("."))
            .map { it.toURI() }
            .map { File(it).resolve("nodeJs_plugin_test_project") }
            .map {
                println(it)
                GFileUtils.mkdirs(it)
                ProjectBuilder
                    .builder()
                    .withProjectDir(it)
                    .build()
            }.orElseThrow()

        println(project.gradle.gradleUserHomeDir.absolutePath)
        println(project.gradle.gradleHomeDir?.absoluteFile)
        project.pluginManager.apply(NodeJsPlugin::class.java)

        val extension = project.extensions.getByType(NodeJsExtension::class.java)
        println(NodeJsPlatform.name)
        println(extension.env)
//        project.tasks.named(NodeJsSetupTask.NAME, NodeJsSetupTask::class.java).get().exec()
    }
}