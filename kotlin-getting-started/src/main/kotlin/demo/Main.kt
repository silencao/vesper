package demo

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun main() {
    println("What's your name?")
    val name= readLine()
    println("Hello ${Json.encodeToString(PackageJson(mapOf("test" to "echo $name")))}!")
}