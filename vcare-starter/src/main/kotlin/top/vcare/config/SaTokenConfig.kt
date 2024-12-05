package top.vcare.config

import cn.dev33.satoken.context.SaHolder
import cn.dev33.satoken.exception.SaTokenException
import cn.dev33.satoken.filter.SaServletFilter
import cn.dev33.satoken.jwt.StpLogicJwtForSimple
import cn.dev33.satoken.router.SaRouter
import cn.dev33.satoken.stp.StpLogic
import cn.dev33.satoken.stp.StpUtil
import cn.hutool.json.JSONUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import top.vcare.common.asserts.AuthAssertEnum
import top.vcare.common.asserts.BaseAssertEnum
import top.vcare.common.dto.ApiResult
import top.vcare.common.util.JacksonUtil


@Configuration
open class SaTokenConfig {

    private val saTokenErrorMap = mapOf(
        11011 to AuthAssertEnum.TOKEN_READE_FAILED,
        11012 to AuthAssertEnum.TOKEN_INVALID,
        11013 to AuthAssertEnum.TOKEN_EXPIRED,
        11014 to AuthAssertEnum.TOKEN_REPLACED_OFFLINE,
        11015 to AuthAssertEnum.TOKEN_FORCEFULLY_LOGGED_OUT,
        11016 to AuthAssertEnum.TOKEN_FROZEN,
    )


    @Bean
    open fun getStpLogicJwt(): StpLogic {
        return StpLogicJwtForSimple()
    }


    @Bean
    open fun getSaServletFilter(): SaServletFilter =
        SaServletFilter()
            //拦截所有接口
            .addInclude("/**")
            // 排除公共接口
            .addExclude("/public/**")
            // 排除网站图标
            .addExclude("/favicon.ico")
            // 排除登录接口和注册接口
            .addExclude("/app/login")
            .addExclude("/app/register")
            .addExclude("/web/register")
            .addExclude("/web/register")
            // 排除验证码接口
            .addExclude("/captcha")
            .setAuth {
                SaRouter.match("/**")
                    .check { _ -> StpUtil.checkLogin() }

            }
            //异常处理函数：每次认证函数发生异常时执行此函数
            .setError { exception ->
                SaHolder.getResponse().setHeader("Content-Type", "application/json;charset=UTF-8")
                val errorEnum = if (exception is SaTokenException) {
                    saTokenErrorMap[exception.code] ?: BaseAssertEnum.INTERNAL_SERVER_ERROR
                } else {
                    BaseAssertEnum.INTERNAL_SERVER_ERROR
                }
                return@setError JacksonUtil.toJsonString(ApiResult.err(errorEnum))
            }
            // 前置函数：在每次认证函数之前执行
            .setBeforeAuth {
                // ---------- 设置一些安全响应头 ----------
                SaHolder.getResponse()
                    // 服务器名称
                    .setServer("harvesting")
                    // 是否可以在iframe显示视图： DENY=不可以 | SAMEORIGIN=同域下可以 | ALLOW-FROM uri=指定域名下可以
                    .setHeader("X-Frame-Options", "SAMEORIGIN")
                    // 是否启用浏览器默认XSS防护： 0=禁用 | 1=启用 | 1; mode=block 启用, 并在检查到XSS攻击时，停止渲染页面
                    .setHeader("X-XSS-Protection", "1; mode=block")
                    // 禁用浏览器内容嗅探
                    .setHeader("X-Content-Type-Options", "nosniff")
                    // ---------- 设置跨域响应头 ----------
                    // 允许指定域访问跨域资源
                    .setHeader("Access-Control-Allow-Origin", "*")
                    // 允许所有请求方式
                    .setHeader("Access-Control-Allow-Methods", "*")
                    // 允许的header参数
                    .setHeader("Access-Control-Allow-Headers", "*")
                    // 有效时间
                    .setHeader("Access-Control-Max-Age", "3600")
            }

}