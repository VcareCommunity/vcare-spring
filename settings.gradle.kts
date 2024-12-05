rootProject.name = "vcare-spring"
include(
    "vcare-starter",
    "vcare-common",
    "vcare-service",
    "vcare-manager",
    "vcare-entity"
)


enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        maven { url = uri("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/") }
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
    }
}

