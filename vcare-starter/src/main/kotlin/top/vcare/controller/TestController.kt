package top.vcare.controller

import org.babyfish.jimmer.sql.kt.KSqlClient
import org.redisson.api.RedissonClient
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.vcare.common.dto.ApiResult
import top.vcare.common.enums.DeviceEnum
import top.vcare.entity.VcareConfig

@RequestMapping("/public")
@RestController
class TestController(
    private val sqlClient: KSqlClient,
    private val redissonClient: RedissonClient,
    private val redisTemplate: RedisTemplate<String, Any>
) {


    @GetMapping("/hello")
    fun hello(): ApiResult<VcareConfig> {
        val config = VcareConfig {
            configName = "社区名称"
            configKey = "communityName"
            configValue = "菜籽的炼油厂"
            configType = DeviceEnum.ALL
        }
        val insertIfAbsent = sqlClient.insertIfAbsent(config)
        val findById = sqlClient.findById(VcareConfig::class, 1)!!

//        redissonClient.getBucket<VcareConfig>("config1").set(findById)
//        val map = redissonClient.getMap<String, VcareConfig>("config")
//        map.put(findById.configKey,findById)

        val opsForHash = redisTemplate.boundHashOps<String, VcareConfig>("config")
        opsForHash.put(findById.configKey, findById)

        return ApiResult.ok(findById)
    }
}