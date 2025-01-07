package top.vcare.controller.public

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.vcare.common.dto.ApiResult
import top.vcare.entity.dto.VcareConfigMobileResp
import top.vcare.service.VcareConfigService

@RestController
@RequestMapping("/public/config")
@Validated
open class VcareConfigController(private val vcareConfigService: VcareConfigService) {

    @GetMapping("/mobile")
    open fun mobileConfig(): ApiResult<VcareConfigMobileResp> =
        ApiResult.ok(vcareConfigService.getMobileConfig())

}