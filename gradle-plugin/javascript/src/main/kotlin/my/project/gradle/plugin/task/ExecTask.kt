package my.project.gradle.plugin.task

import my.project.gradle.plugin.nodejs.NodeJsPlugin
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.options.Option

open class ExecTask : Exec() {
    @get: Input
    @set: Option(option = "args", description = "可执行程序的参数")
    var execArgs:String = ""

    override fun executable(executable: Any): Exec {
        val env = NodeJsPlugin.ext(project).env
        return super.executable(
            when (executable) {
                "node" -> env.nodeExecutable
                "npm"  -> env.nodeBinDir.resolve("npm.cmd").absolutePath
                "npx"  -> env.nodeBinDir.resolve("npx.cmd").absolutePath
                else   -> executable
            }
        )
    }

    private fun split(str: String, limit: Int = 0): List<String> {
        return str.split(Regex("\\s+"), limit)
    }

    override fun exec() {
        if (executable == null) {
            val argList = split(execArgs, 2)
            if (argList.size > 1)
                args(split(argList[1]))

            executable(argList[0])
        } else {
            args(split(execArgs))
        }

        isIgnoreExitValue = true
        super.exec()
    }
}
