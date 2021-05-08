import my.project.gradle.kit.WrapperTask

tasks.replace("wrapper", WrapperTask::class).apply {
    doFirst {
        distributionType = Wrapper.DistributionType.ALL
        distributionPath = "wrapper/dists"
        distributionUrl  = "https://mirrors.cloud.tencent.com/gradle/" +
                "gradle-$gradleVersion-${distributionType.name.toLowerCase()}.zip"
    }

    doLast {
        gradle.includedBuilds.forEach { includedBuild ->
            outputs.files.forEach {
                it.copyTo(projectDir.resolve("${includedBuild.name}/${it.relativeTo(projectDir)}"), true)
            }
        }
    }
}