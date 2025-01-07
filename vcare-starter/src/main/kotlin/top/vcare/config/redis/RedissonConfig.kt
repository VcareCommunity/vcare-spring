package top.vcare.config.redis

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder
import com.fasterxml.jackson.module.kotlin.KotlinModule
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
open class RedissonConfig {




    @Bean
    open fun redissonClient(
        @Value("\${redisson.config}") redissonConfig: String,
        objectMapper: ObjectMapper
    ): RedissonClient {
        val config = Config.fromYAML(redissonConfig.byteInputStream())
        config.codec = JsonJacksonCodec(objectMapper)
        return Redisson.create(config)
    }


//    private fun createObjectMapper(): ObjectMapper {
//        val objectMapper = ObjectMapper()
//        objectMapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.activateDefaultTypingAsProperty(
//            objectMapper.polymorphicTypeValidator,
//            ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT, "@type"
//        );
//        // 注册 JavaTimeModule ，以适配 java.time 下的时间类型
//        objectMapper.registerModule(JavaTimeModule());
//        objectMapper.registerModule(ImmutableModule());
//        return objectMapper;
//    }

    @Bean
    open fun redisTemplate(
        redisConnectionFactory: RedisConnectionFactory,
        objectMapper: ObjectMapper
    ): RedisTemplate<String, Any> {
        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.connectionFactory = redisConnectionFactory
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = GenericJackson2JsonRedisSerializer(objectMapper)
        redisTemplate.hashKeySerializer = StringRedisSerializer()
        redisTemplate.hashValueSerializer = GenericJackson2JsonRedisSerializer(objectMapper)
        redisTemplate.afterPropertiesSet()
        return redisTemplate
    }
}