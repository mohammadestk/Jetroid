package commons

import dependencies.Dependencies
import extensions.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class JetroidKotlinLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.apply(plugin = "kotlin")
        target.apply(plugin = "kotlin-kapt")
        target.apply(plugin = "kotlin-allopen")

        target.dependencies {
            implementation(Dependencies.KOTLIN)
            implementation(Dependencies.COROUTINES)
            implementation(Dependencies.COROUTINES_ANDROID)
        }
    }
}