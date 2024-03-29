import my.project.gradle.plugin.BuildSetup
import my.project.gradle.plugin.task.WrapperTask


tasks.replace("wrapper", WrapperTask::class).apply {
    doFirst {
        distributionType = Wrapper.DistributionType.ALL
        distributionPath = "wrapper/dists"
        /* 先不配镜像地址了，还没有官网快
        distributionUrl  = "https://mirrors.cloud.tencent.com/gradle/" +
                "gradle-$gradleVersion-${distributionType.name.toLowerCase()}.zip"
        */
        if (gradleVersion == GradleVersion.current().version) {
            println("版本未变更！将复制文件到以下路径：")
            BuildSetup.showPaths(gradle.includedBuilds)
            copyOutputs()
            throw StopExecutionException()
        }
    }

    doLast {
        copyOutputs()
    }
}