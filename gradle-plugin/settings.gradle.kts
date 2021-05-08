rootProject.name = "gradle-plugin"
apply(from = "../gradle/shared.repositories.settings.gradle.kts")

include("gradle"    )
include("java"      )
include("javascript")
include("kotlin"    )
include("spring"    )
