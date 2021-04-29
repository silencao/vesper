import org.gradle.plugins.ide.eclipse.model.Classpath
import org.gradle.plugins.ide.eclipse.model.SourceFolder

plugins {
    java
    eclipse
    id("my.project.spring-dependency-management")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
/**
 * 修改 eclipse build path
 * 和 gradle build 到一起
 * 默认是生成bin目录
 */
eclipse.classpath.file {
    beforeMerged(Action<Classpath> {
        entries.removeAll {it is SourceFolder}
    })

    whenMerged(Action<Classpath> {
        val dir = buildDir.toRelativeString(buildDir.parentFile)
        entries.filterIsInstance<SourceFolder>().forEach {
            it.output = when (it.path) {
                "src/main/java"      -> "$dir/classes/java/main"
                "src/test/java"      -> "$dir/classes/java/test"
                "src/main/resources" -> "$dir/resources/main"
                "src/test/resources" -> "$dir/resources/test"
                else -> throw UnsupportedOperationException("无效的路径：${it.path}")
            }
        }
    })
}