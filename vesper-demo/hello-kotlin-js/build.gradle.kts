plugins {
    id("my.project.kotlin-js-common")
}

kotlin {
    js {
        browser()
        nodejs()
    }
}