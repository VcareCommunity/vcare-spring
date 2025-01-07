package top.vcare.controller.app

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.vcare.common.dto.ApiResult
import top.vcare.entity.dto.VcareUserRsponse
import top.vcare.service.VcareUserService


@RestController
@RequestMapping("/mobile/user")
open class UserController(private val vcareUserService: VcareUserService) {

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ApiResult<VcareUserRsponse> {
        return ApiResult.ok(vcareUserService.getUserById(id))
    }

}