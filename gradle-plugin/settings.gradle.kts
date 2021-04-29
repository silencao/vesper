rootProject.name = "gradle-plugin"
apply(from = "../gradle/shared.repositories.settings.gradle.kts")

include("java"      )
include("javascript")
include("kotlin"    )
include("spring"    )
include("testng"    )
