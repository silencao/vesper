package js.nodejs

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class NodeJsSetupTask : DefaultTask() {
    @TaskAction
    fun exec() {
        project.logger.warn(" hello world ")
    }
}