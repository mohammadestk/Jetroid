package extensions

import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.api.BaseVariant
import org.gradle.api.DomainObjectSet
import org.gradle.api.GradleException
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

fun Project.android(): LibraryExtension {
    val android = project.extensions.findByType(LibraryExtension::class.java)
    if (android != null) {
        return android
    } else {
        throw GradleException("Project $name is not an Android project")
    }
}

fun LibraryExtension.kotlinOptions(): KotlinJvmOptions {
    val android = (this as org.gradle.api.plugins.ExtensionAware).extensions.findByType(KotlinJvmOptions::class.java)
    if (android != null) {
        return android
    } else {
        throw GradleException("Project is not an Kotlin project")
    }
}

fun BaseExtension.variants(): DomainObjectSet<out BaseVariant> {
    return when (this) {
        is AppExtension -> {
            applicationVariants
        }

        is LibraryExtension -> {
            libraryVariants
        }

        else -> throw GradleException("Unsupported BaseExtension type!")
    }
}