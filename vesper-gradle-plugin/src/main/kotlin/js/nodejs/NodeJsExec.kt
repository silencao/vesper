package js.nodejs

import org.gradle.api.Action
import org.gradle.api.tasks.AbstractExecTask
import org.gradle.api.tasks.TaskProvider
import javax.inject.Inject

open class NodeJsExec
@Inject
constructor(
) : AbstractExecTask<NodeJsExec>(NodeJsExec::class.java) {

    companion object {
        fun create(
            name: String,
            extension: NodeJsExtension,
            configurationAction: Action<NodeJsExec>
        ): TaskProvider<NodeJsExec> {
            val project = extension.project
            val env = extension.env

            return project.tasks.register(name, NodeJsExec::class.java) {
                it.executable = env.nodeExecutable
                it.args?.add("-v")

                configurationAction.execute(it)
            }
        }
    }
}