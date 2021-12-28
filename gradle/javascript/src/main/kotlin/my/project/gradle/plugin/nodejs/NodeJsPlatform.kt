package my.project.gradle.plugin.nodejs

internal object NodeJsPlatform {
    private val props = System.getProperties()
    private fun property(name: String) = props.getProperty(name) ?: System.getProperty(name)

    const val WIN = "win"
    const val LINUX = "linux"

    val name: String = run {
        val name = property("os.name").toLowerCase()
        when {
            name.contains("windows") -> WIN
            name.contains("linux"  ) -> LINUX
            else -> throw IllegalArgumentException("Unsupported OS: $name")
        }
    }

    const val X64 = "x64"
    const val X86 = "x86"
    const val ARM64 = "arm64"

    val architecture: String = run {
        val arch = property("os.arch").toLowerCase()
        when {
            arch.contains("64") -> X64
            arch == "arm" -> {
                // as Java just returns "arm" on all ARM variants, we need a system call to determine the exact arch
                // the node binaries for 'armv8l' are called 'arm64', so we need to distinguish here
                val process = Runtime.getRuntime().exec("uname -m")
                val systemArch = process.inputStream.bufferedReader().use { it.readText() }
                when (systemArch.trim()) {
                    "armv8l" -> ARM64
                    else -> systemArch
                }
            }
            else -> X86
        }
    }
}