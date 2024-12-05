package top.vcare.controller

import VcareConfig
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.vcare.common.dto.ApiResult

@RequestMapping("/public")
@RestController
class TestController(private val sqlClient: KSqlClient) {


    @GetMapping("/hello")
    fun hello(): ApiResult<Void> {
        val config = VcareConfig {

        }
        return ApiResult.ok()
    }
}