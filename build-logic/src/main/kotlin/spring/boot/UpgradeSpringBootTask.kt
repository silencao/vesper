package spring.boot

import org.gradle.api.tasks.Input
import org.gradle.api.tasks.WriteProperties
import org.gradle.api.tasks.options.Option

open class UpgradeSpringBootTask : WriteProperties() {
    @get: Input
    @set: Option(option = "use-version", description = "升级版本号")
    var version = "2.3.3.RELEASE"

    override fun writeProperties() {
        property("use.spring.boot.version", version)

        super.writeProperties()
    }
}