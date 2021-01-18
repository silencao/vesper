import org.gradle.api.Action
import org.gradle.plugins.ide.eclipse.model.Classpath
import org.gradle.plugins.ide.eclipse.model.SourceFolder

plugins {
    java
    eclipse
    id("io.spring.dependency-management")
}

dependencyManagement {
    imports {
        // spring的依赖管理插件导入springboot当前版本关联的BOM
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
}

/**
 * 修改 eclipse build path
 * 和 gradle build 到一起
 * 默认是生成bin目录
 */
eclipse.classpath {
    defaultOutputDir = buildDir

    val dir = buildDir.toRelativeString(buildDir.parentFile)
    val action = Action<Classpath> {
        entries.filter {
            it.kind == "src"
        }.forEach {
            it as SourceFolder
            it.output = when (it.path) {
                "src/main/java" -> "$dir/classes/java/main"
                "src/test/java" -> "$dir/classes/java/test"
                "src/main/resources" -> "$dir/resources/main"
                "src/test/resources" -> "$dir/resources/test"
                else -> ""
            }
        }
    }
    file.whenMerged(action)
}
configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<Test> {
    useJUnitPlatform()
}