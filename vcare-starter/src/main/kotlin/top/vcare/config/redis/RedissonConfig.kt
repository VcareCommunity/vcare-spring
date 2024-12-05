package top.vcare.config.redis

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.babyfish.jimmer.jackson.ImmutableModule
import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.codec.JsonJacksonCodec
import org.redisson.config.Config
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
open class RedissonConfig(
    @Value("\${redisson.config}") private val redissonConfig: String,
    private val objectMapper: ObjectMapper
) {


    @Bean
    open fun redissonClient(): RedissonClient {
        val config = Config.fromYAML(redissonConfig.byteInputStream())
        config.codec = JsonJacksonCodec(objectMapper)
        return Redisson.create(config)
    }


    @Bean
    open fun redisTemplate(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<String, Any> {

        val redisTemplate = RedisTemplate<String, Any>()

        val objectMapper = ObjectMapper().apply {
            registerModules(JavaTimeModule())
            registerModules(ImmutableModule())
        }
        redisTemplate.connectionFactory = redisConnectionFactory
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = GenericJackson2JsonRedisSerializer(objectMapper)
        redisTemplate.hashKeySerializer = StringRedisSerializer()
        redisTemplate.hashValueSerializer = GenericJackson2JsonRedisSerializer(objectMapper)

        redisTemplate.afterPropertiesSet()
        return redisTemplate
    }
}