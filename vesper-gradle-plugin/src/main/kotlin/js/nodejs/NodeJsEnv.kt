package js.nodejs

import java.io.File

data class NodeJsEnv(
//    val cleanableStore: CleanableStore,
    val nodeDir: File,
    val nodeBinDir: File,
    val nodeExecutable: String,

    val platformName: String,
    val architectureName: String,
    val ivyDependency: String
) {
    val isWindows: Boolean
        get() = platformName == "win"
}
