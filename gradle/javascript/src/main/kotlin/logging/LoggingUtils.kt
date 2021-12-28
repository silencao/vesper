package logging

import org.gradle.api.logging.Logger

internal fun Logger.kotlinInfo(message: String) {
    this.info("[KOTLIN] $message")
}