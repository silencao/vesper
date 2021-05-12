apply(from = "../gradle/shared.repositories.settings.gradle.kts")

pluginManagement {
    includeBuild("./build-setup")
}

include("gradle"    )
include("java"      )
include("javascript")
include("kotlin:js" )
include("kotlin:jvm")
include("spring"    )
