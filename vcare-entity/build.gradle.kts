import tech.argonariod.gradle.jimmer.JimmerLanguage

plugins {
    `kotlin-convention`
    alias(libs.plugins.ksp)
    alias(libs.plugins.jimmerPlugin)
}


jimmer {
    version = "latest.release"
    language = JimmerLanguage.KOTLIN
}

dependencies {
    api(projects.vcareCommon)
}