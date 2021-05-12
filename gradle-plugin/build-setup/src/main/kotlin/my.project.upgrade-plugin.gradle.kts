import my.project.gradle.plugin.task.UpgradeTask

plugins {
    id("io.spring.dependency-management")
}

tasks.register("upgrade", UpgradeTask::class.java) {
    outputFile = projectDir.resolve(Project.GRADLE_PROPERTIES)
}
