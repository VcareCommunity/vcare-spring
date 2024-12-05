package top.vcare.controller.app

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.vcare.common.dto.ApiResult
import top.vcare.entity.dto.LoginRequest
import top.vcare.entity.dto.LoginUser
import top.vcare.service.LoginService

@RestController
@RequestMapping("/mobile")
@Validated
open class LoginController(private val loginService: LoginService) {

    @PostMapping("/login")
    open fun login(@RequestBody @Validated loginRequest: LoginRequest): ApiResult<LoginUser> {
        return ApiResult.ok(loginService.login(loginRequest))
    }
}