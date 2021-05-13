package my.project.gradle.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.initialization.IncludedBuild
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction

open class BuildSetup: DefaultTask() {
    companion object {
        const val group = "build setup"
        fun showPaths(includedBuilds: Collection<IncludedBuild>) = includedBuilds.run {
            val maxLength = maxOf { it.name.length }

            forEach {
                println("Included build ':${it.name.padEnd(maxLength)}' > ${it.projectDir.absolutePath}")
            }
        }
    }

    @Internal
    override fun getGroup(): String { return BuildSetup.group }

    @TaskAction
    fun print() {
        showPaths(project.gradle.includedBuilds)
    }

}