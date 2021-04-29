plugins {
    id("io.spring.dependency-management")
}

tasks.register("upgradeSpringBoot",
    spring.boot.UpgradeSpringBootTask::class.java)
