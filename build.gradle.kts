plugins {
    id("my.project.spring-boot-upgrade-plugin")
}

allprojects {
    group = "im.silen"
    version = "0.0.5"
}

tasks.withType<spring.boot.UpgradeSpringBootTask> {
    outputFile = projectDir.resolve("vesper-gradle-plugin/${Project.GRADLE_PROPERTIES}")
}

tasks.wrapper {
    doFirst {
        distributionType = Wrapper.DistributionType.ALL
        distributionPath = "wrapper/dists"
        distributionUrl  = "https://mirrors.cloud.tencent.com/gradle/" +
                "gradle-$gradleVersion-${distributionType.name.toLowerCase()}.zip"

        gradle.includedBuilds.forEach { includedBuild ->
            outputs.files.forEach {
                it.copyTo(projectDir.resolve("${includedBuild.name}/${it.relativeTo(projectDir)}"), true)
            }
        }
    }
}
