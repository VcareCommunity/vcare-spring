package top.vcare.manager

import org.babyfish.jimmer.sql.kt.KSqlClient
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Component
import top.vcare.entity.VcareConfig
import top.vcare.entity.dto.VcareConfigRedisDto

@Component
class VcareConfigManager(
    private val sqlClient: KSqlClient,
    private val redissonClient: RedissonClient
) {
    private val configKey = "vcare:config"

    fun getConfigMap(): Map<String, VcareConfigRedisDto> {
        val map = redissonClient.getMap<String, VcareConfigRedisDto>(configKey)
        if (map.isEmpty()) {
            val configMap = sqlClient.createQuery(VcareConfig::class) {
                select(table.fetch(VcareConfigRedisDto::class))
            }
                .execute()
                .associateBy { it.configKey }
            map.putAll(configMap)
            return configMap
        }
        return map.readAllMap()
    }
}