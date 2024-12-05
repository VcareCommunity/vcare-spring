plugins {
    `java-library`
}

extensions.configure<JavaPluginExtension> {
    sourceCompatibility = JavaVersion.VERSION_21
    withSourcesJar()
    withJavadocJar()
}

tasks.test {
    useJUnitPlatform()
}
tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.add("-parameters")
}
tasks.withType<Javadoc>().configureEach {
    options.encoding = "UTF-8"
}