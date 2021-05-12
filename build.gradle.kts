plugins {
    id("my.project.build-setup")
}

allprojects {
    group = "im.silen"
    version = "0.0.5"
}

tasks.withType<my.project.gradle.plugin.task.UpgradeTask> {
    outputDir = "gradle-plugin"
}
