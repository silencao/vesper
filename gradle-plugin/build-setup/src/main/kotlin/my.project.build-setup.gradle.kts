plugins {
    id("my.project.custom-wrapper")
    id("my.project.upgrade-plugin")
}

tasks.register("includedBuilds") {
    group = my.project.gradle.plugin.BuildSetup.group

    doLast {
        gradle.includedBuilds.run {
            val maxLength = maxOf { it.name.length }

            forEach {
                println("Included build ':${it.name.padEnd(maxLength)}' > ${it.projectDir.absolutePath}")
            }
        }
    }
}