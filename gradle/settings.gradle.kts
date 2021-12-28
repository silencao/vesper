rootProject.name = "plugin"

pluginManagement {
    apply(rootDir
        .resolve("build-setup").apply { includeBuild(absolutePath) }
        .resolve("settings.gradle.kts"))
}

include("gradle"    )
include("java"      )
include("javascript")
include("kotlin:jvm")
include("spring"    )
