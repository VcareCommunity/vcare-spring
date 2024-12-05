package top.vcare.controller.public

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.vcare.common.VcareProperties
import top.vcare.common.dto.ApiResult

@RestController
@RequestMapping("/public")
class CommunityController {

    @GetMapping("/community")
    fun community(): ApiResult<Map<String, String>> {
        val map = mapOf(
            "version" to VcareProperties.getVersion()
        )
        return ApiResult.ok(map)
    }

}