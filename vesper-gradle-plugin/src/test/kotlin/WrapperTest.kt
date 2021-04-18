import org.gradle.api.tasks.wrapper.Wrapper
import org.gradle.util.DistributionLocator
import org.gradle.util.GradleVersion
import java.net.URI
import java.util.*
import kotlin.test.Test

open class WrapperUpgradeTask : Wrapper() {
    override fun getDistributionUrl(): String {
        val locator = object : DistributionLocator() {
            override fun getDistributionFor(version: GradleVersion, type: String): URI {

                return super.getDistributionFor(version, type)
            }
        }

        return locator.getDistributionFor(gradleVersion, distributionType.name.toLowerCase(Locale.ENGLISH)).toString()
    }
}

class WrapperTest {
    @Test
    fun task() {
        println("hello world")
    }
}