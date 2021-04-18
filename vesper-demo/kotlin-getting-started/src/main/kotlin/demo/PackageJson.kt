package demo

import kotlinx.serialization.Serializable

@Serializable
internal data class PackageJson(
    val scripts: Map<String, String>,
)
