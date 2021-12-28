import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.util.GFileUtils
import java.io.File
import java.net.URL
import java.util.*

class ProjectBuilder {
    companion object {
        fun create(
            clazz: Class<Any>,
            dirName: String = "${clazz.simpleName}_Resource"
        ): Project {
            return Optional.ofNullable(clazz.getResource("."))
                .map(URL::toURI)
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