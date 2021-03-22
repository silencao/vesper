package js.nodejs

import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test

class NodeJsSetupTaskTest {
    @Test
    fun exec() {
        val project = ProjectBuilder.builder().build()

        project.tasks.register("setUp", NodeJsSetupTask::class.java).get().exec()
    }
}