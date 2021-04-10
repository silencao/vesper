package demo

fun main() {
    println("What's your name?")
    val name= readLine()
    println("Hello ${PackageJson(mapOf("test" to "echo 1"))}!")
}