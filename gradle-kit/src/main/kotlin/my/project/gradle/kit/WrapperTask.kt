package my.project.gradle.kit

import org.gradle.api.Action
import org.gradle.api.Task
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.wrapper.Wrapper
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

open class WrapperTask: Wrapper() {
    val updateTime: String
        @Input get() = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss.SSS '['XXX']'")
            .format(OffsetDateTime.now()) + System.lineSeparator()

    override fun doLast(action: Action<in Task>): Task =
        super.doLast {
            propertiesFile.appendText("# updated on $updateTime")

            action.execute(this)
        }

}