plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(project(":javascript"))

    testImplementation(kotlin("test-testng"))
}

tasks.withType<Test> {
    useTestNG()
}