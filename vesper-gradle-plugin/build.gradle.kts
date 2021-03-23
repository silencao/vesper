plugins {
    `java-gradle-plugin`
    `maven-publish`
    kotlin("jvm")
}

gradlePlugin {
    plugins {
        create("nodeJsPlugin") {
            id = "com.github.silencao.vesper.nodejs"
            implementationClass = "js.nodejs.NodeJsPlugin"
        }
    }
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}
