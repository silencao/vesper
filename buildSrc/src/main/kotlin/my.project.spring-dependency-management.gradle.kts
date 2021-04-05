/* 使用当前springboot版本提供的依赖版本号管理
 * 此插件应用于java所有项目
 * 而kotlin只有springboot项目使用，因为开发gradle插件时，应使用跟gradle配套的版本
 * 而springboot-application得用spring官方推荐的版本 */
listOf(
    JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME,
    JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME
).forEach {
    dependencies {
        add(it, platform(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES))
    }
}