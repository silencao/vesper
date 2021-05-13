plugins {
    id("my.project.custom-wrapper")
    id("my.project.upgrade-plugin")
}

tasks.register("includedBuilds", my.project.gradle.plugin.BuildSetup::class)