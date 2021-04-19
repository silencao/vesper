import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.util.GFileUtils
import java.io.File
import java.util.*

internal class ProjectTest {
    companion object {
        fun project(
            clazz: Class<Any>,
            dirName: String = "${clazz.simpleName}_Resource"
        ): Project {
            return Optional.ofNullable(clazz.getResource("."))
                .map { it.toURI() }
                .map { File(it).resolve(dirName) }
                .map {
                    GFileUtils.mkdirs(it)
                    ProjectBuilder
                        .builder()
                        .withProjectDir(it)
                        .build()
                }.orElseThrow()
        }
    }
}