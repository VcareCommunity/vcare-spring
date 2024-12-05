package top.vcare.common

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("vcare")
class VcareProperties {

    fun setVersion(version: String) {
        VcareProperties.version = version
    }

    fun setAesKey(aesKey: String) {
        VcareProperties.aesKey = aesKey
    }

    companion object {
        private lateinit var version: String
        private lateinit var aesKey: String

        @JvmStatic
        fun getVersion(): String {
            return version
        }

        @JvmStatic
        fun getAesKey(): String {
            return aesKey
        }

    }

}