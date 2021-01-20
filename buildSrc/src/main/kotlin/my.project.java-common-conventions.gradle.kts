plugins {
    java
    eclipse
    id("my.project.spring-dependency-management")
    id("my.project.test-conventions")
}
/**
 * 修改 eclipse build path
 * 和 gradle build 到一起
 * 默认是生成bin目录
 */
eclipse.classpath {
    val dir = buildDir.toRelativeString(buildDir.parentFile)
    val action = Action<org.gradle.plugins.ide.eclipse.model.Classpath> {
        entries.filterIsInstance<org.gradle.plugins.ide.eclipse.model.SourceFolder>().forEach {
            it.output = when (it.path) {
                "src/main/java"      -> "$dir/classes/java/main"
                "src/test/java"      -> "$dir/classes/java/test"
                "src/main/resources" -> "$dir/resources/main"
                "src/test/resources" -> "$dir/resources/test"
                else -> throw UnsupportedOperationException("无效的路径：${it.path}")
            }
        }
    }
    file.whenMerged(action)
}