package my.project.gradle.plugin.task

import org.gradle.api.Action
import org.gradle.api.tasks.TaskContainer

fun TaskContainer.registerExec(
    taskName: String,
    commandLine: Action<ExecTask> = Action {
        group = "executable"
        executable(taskName)
    }
) = this.register(taskName, ExecTask::class.java, commandLine)
