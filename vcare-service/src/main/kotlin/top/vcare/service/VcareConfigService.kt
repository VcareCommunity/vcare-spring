package top.vcare.service

import org.babyfish.jimmer.sql.kt.KSqlClient
import org.springframework.stereotype.Service
import top.vcare.common.VcareProperties
import top.vcare.common.enums.ConfigItemEnum
import top.vcare.entity.dto.VcareConfigMobileResp
import top.vcare.entity.dto.VcareConfigRedisDto
import top.vcare.manager.VcareConfigManager

@Service
class VcareConfigService(private val sqlClient: KSqlClient, private val configManager: VcareConfigManager) {

    fun getMobileConfig(): VcareConfigMobileResp {
        val map = configManager.getConfigMap()
        val config = map[ConfigItemEnum.COMMUNITY_NAME.key]!!
        return VcareConfigMobileResp(config.configValue, VcareProperties.getVersion())
    }


    fun getPcConfig(): Map<String, VcareConfigRedisDto> {
        return configManager.getConfigMap()
    }

}