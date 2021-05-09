plugins {
    `kotlin-dsl`
}

dependencies {
    testImplementation(project(":gradle"))
}

gradlePlugin {
    plugins {
        create("nodeJsPlugin") {
            id = "my.project.nodejs.plugin"
            implementationClass = "my.project.gradle.plugin.nodejs.NodeJsPlugin"
        }
    }
}