plugins {
    `kotlin-convention`
}

dependencies {
    api(libs.apacheCommonLang3)
    api(libs.apacheTikaCore)

    api(libs.fastexcel)

    api(libs.jimmerSpringBootStarter)

    implementation(libs.minio)

    api(libs.redissonSpringBootStarter)

    api(libs.saTokenSpringBootStarter)
    api(libs.saTokenRedissonJackson)
    api(libs.saTokenJwt)
    api(libs.saTokenTempJwt)

    api(libs.springBootStarterJdbc)
    api(libs.springbootStarterValidation)
    api(libs.springbootStarterWeb)

    api(libs.sqids)
}