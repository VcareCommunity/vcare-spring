package top.vcare.common.dto

import com.fasterxml.jackson.annotation.JsonInclude
import top.vcare.common.asserts.BaseAssertEnum
import top.vcare.common.asserts.IAssertEnum
import top.vcare.common.i18n.I18nUtils

/**
 * 统一响应结果
 * @property success 是否成功
 * @property code 状态码
 * @property msg 响应消息
 * @property data 响应数据
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class ApiResult<T>(
    val success: Boolean,
    val code: String,
    val msg: String,
    val data: T?
) {
    companion object {

        fun ok(): ApiResult<Void> = this.ok(null)

        fun ok(enum: IAssertEnum): ApiResult<Void> = this.ok(enum, null)

        fun ok(msg: String): ApiResult<Void> = this.ok(msg, null)

        fun <T> ok(data: T?): ApiResult<T> = this.ok(BaseAssertEnum.SUCCESS, data)

        fun <T> ok(enum: IAssertEnum, data: T?): ApiResult<T> = this.ok(enum.code, enum.msg, data)

        fun <T> ok(msg: String, data: T?): ApiResult<T> = this.ok(BaseAssertEnum.SUCCESS.code, msg, data)

        fun <T> ok(code: String, msg: String, data: T?): ApiResult<T> =
            ApiResult(true, code, I18nUtils.getMessage(msg), data)


        fun err(): ApiResult<Void> = this.err(null)

        fun err(msg: String): ApiResult<Void> = this.err(msg, null)

        fun err(enum: IAssertEnum): ApiResult<Void> = this.err(enum, null)

        fun <T> err(data: T?): ApiResult<T> = this.err(BaseAssertEnum.FAILED, data)

        fun <T> err(enum: IAssertEnum, data: T?): ApiResult<T> = this.err(enum.code, enum.msg, data)

        fun <T> err(msg: String, data: T?): ApiResult<T> = this.err(BaseAssertEnum.FAILED.code, msg, data)

        fun <T> err(code: String, msg: String, data: T?): ApiResult<T> =
            ApiResult(false, code, I18nUtils.getMessage(msg), data)
    }
}
