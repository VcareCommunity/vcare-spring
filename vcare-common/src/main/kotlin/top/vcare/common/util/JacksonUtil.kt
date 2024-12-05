package top.vcare.common.util

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer
import org.babyfish.jimmer.jackson.ImmutableModule
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object JacksonUtil {

    private val MAPPER: ObjectMapper = createObjectMapper()

    fun parse(text: String): JsonNode {
        return try {
            MAPPER.readTree(text)
        } catch (e: JsonProcessingException) {
            throw RuntimeException(e)
        }
    }

    fun createObjectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper()
        objectMapper.registerModule(ImmutableModule())

        val javaTimeModule = JavaTimeModule()
        javaTimeModule.addSerializer(
            LocalDateTime::class.java,
            LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        )
        javaTimeModule.addDeserializer(
            LocalDateTime::class.java,
            LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        )
        javaTimeModule.addSerializer(
            LocalDate::class.java,
            LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        )
        javaTimeModule.addDeserializer(
            LocalDate::class.java,
            LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        )
        javaTimeModule.addSerializer(
            LocalTime::class.java,
            LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss"))
        )
        javaTimeModule.addDeserializer(
            LocalTime::class.java,
            LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss"))
        )
        objectMapper.registerModule(javaTimeModule)
        objectMapper.dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        return objectMapper
    }

    fun parseObject(text: String): ObjectNode {
        return try {
            MAPPER.readTree(text) as ObjectNode
        } catch (e: JsonProcessingException) {
            throw RuntimeException(e)
        }
    }

    fun <T> parseObject(text: String, clazz: Class<T>): T {
        return try {
            MAPPER.readValue(text, clazz)
        } catch (e: JsonProcessingException) {
            throw RuntimeException(e)
        }
    }

    fun parseArray(text: String): ArrayNode {
        return parse(text) as ArrayNode
    }

    fun <T> parseArray(text: String, clazz: Class<T>): List<T> {
        return MAPPER.convertValue(parse(text), object : TypeReference<List<T>>() {})
    }

    fun toJsonString(obj: Any): String {
        return try {
            MAPPER.writeValueAsString(obj)
        } catch (e: JsonProcessingException) {
            throw RuntimeException(e)
        }
    }

    fun toJsonString(obj: Any, vararg includes: JsonInclude.Include): String {
        val customMapper = createObjectMapper().apply {
            includes.forEach { setSerializationInclusion(it) }
        }
        return try {
            customMapper.writeValueAsString(obj)
        } catch (e: JsonProcessingException) {
            throw RuntimeException(e)
        }
    }

    fun <T> toJavaObject(text: String, clazz: Class<T>): T {
        return try {
            MAPPER.readValue(text, clazz)
        } catch (e: JsonProcessingException) {
            throw RuntimeException(e)
        }
    }

    fun <T> toJavaObject(text: String, reference: TypeReference<T>): T {
        return try {
            MAPPER.readValue(text, reference)
        } catch (e: JsonProcessingException) {
            throw RuntimeException(e)
        }
    }

    fun <T> toJavaObject(
        text: String,
        reference: TypeReference<T>,
        vararg deserializationFeatures: DeserializationFeature
    ): T {
        val customMapper = createObjectMapper().apply {
            deserializationFeatures.forEach { enable(it) }
        }
        return try {
            customMapper.readValue(text, reference)
        } catch (e: JsonProcessingException) {
            throw RuntimeException(e)
        }
    }

    fun <T> toJavaObject(objectNode: ObjectNode, clazz: Class<T>): T {
        return MAPPER.convertValue(objectNode, clazz)
    }

    fun <T> toJavaObject(objectNode: ObjectNode, reference: TypeReference<T>): T {
        return MAPPER.convertValue(objectNode, reference)
    }

    fun <T> toList(arrayNode: ArrayNode, clazz: Class<T>): List<T> {
        return MAPPER.convertValue(arrayNode, object : TypeReference<List<T>>() {})
    }

    fun createObjectNode(): ObjectNode {
        return MAPPER.createObjectNode()
    }

    fun createObjectNode(obj: Any): ObjectNode {
        return MAPPER.convertValue(obj, ObjectNode::class.java)
    }

    fun isNullNodeOrNull(jsonNode: JsonNode?): Boolean {
        return jsonNode == null || jsonNode.isNull
    }
}