package nodejs

import org.gradle.api.Action
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.TaskProvider

open class NodeJsExec : Exec() {
    companion object {
        fun create(
            name: String,
            extension: NodeJsExtension,
            configurationAction: Action<NodeJsExec>
        ): TaskProvider<NodeJsExec> {
            val project = extension.project
            val env = extension.env

            return project.tasks.register(name, NodeJsExec::class.java) {
                commandLine = listOf(env.nodeExecutable, "-h")

                configurationAction.execute(this)
            }
        }
    }
}