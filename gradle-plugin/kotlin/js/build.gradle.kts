plugins {
    id("my.project.kotlin-dsl")
}

dependencies {
    /* kotlin-dsl插件会在compileOnly配置中引入对应版本的stdlib和reflect
     * 和下面的gradle-plugin依赖的版本发生冲突，所以只在runtimeOnly中引入 */
    runtimeOnly(kotlin("gradle-plugin", "1.5.0"))
}