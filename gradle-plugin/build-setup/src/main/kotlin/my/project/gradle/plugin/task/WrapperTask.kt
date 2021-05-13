package my.project.gradle.plugin.task

import org.gradle.api.Action
import org.gradle.api.Task
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.wrapper.Wrapper
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

open class WrapperTask: Wrapper() {
    @Suppress("MemberVisibilityCanBePrivate") // Annotated with @Input on private getters are ignored. private and annotated with @Input are ignored
    val updateTime: String
        @Input get() = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss.SSS '['XXX']'")
            .format(OffsetDateTime.now())

    fun copyOutputs() {
        project.run {
            gradle.includedBuilds.forEach { includedBuild ->
                outputs.files.forEach {
                    it.copyTo(includedBuild.projectDir.resolve(it.relativeTo(projectDir)), true)
                }
            }
        }
    }

    override fun doLast(action: Action<in Task>): Task =
        super.doLast {
            propertiesFile.appendText("# updated on $updateTime")

            action.execute(this)
        }

}