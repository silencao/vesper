subprojects {
    version = "0.1"
    
    afterEvaluate {
        listOf(
            JavaPlugin.TEST_IMPLEMENTATION_CONFIGURATION_NAME
        ).forEach {
            if (configurations.names.contains(it))
                dependencies {
                    add(it, kotlin("test-testng"))
                }
        }

        tasks.withType<Test> {
            useTestNG()
        }
    }
}




