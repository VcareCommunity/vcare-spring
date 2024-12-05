plugins {
    `kotlin-convention`
    alias(libs.plugins.springbootPlugin)
}

dependencies {
    implementation(libs.postgresql)
    implementation(projects.vcareService)
}