plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        create("nodeJsPlugin") {
            id = "my.project.nodejs.plugin"
            implementationClass = "nodejs.NodeJsPlugin"
        }
    }
}