plugins {
    `java-gradle-plugin`
    `maven-publish`
    kotlin("jvm") version embeddedKotlinVersion
}

version = "0.2"

gradlePlugin {
    plugins {
        create("nodeJsPlugin") {
            id = "com.github.silencao.vesper"
            implementationClass = "js.nodejs.NodeJsPlugin"
        }
    }
}

tasks.test {
    useTestNG()
}

dependencies {
    testImplementation(kotlin("test-testng"))
}
