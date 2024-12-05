package top.vcare.common.exception

import top.vcare.common.asserts.IAssertEnum
import top.vcare.common.i18n.I18nUtils
import java.io.Serial


class VcareException(assert: IAssertEnum) : RuntimeException(I18nUtils.getMessage(assert.code)) {

    private val _assert: IAssertEnum = assert

    companion object {
        @Serial
        private const val serialVersionUID: Long = -2853597189295987529L
    }


    val code: String
        get() = _assert.code


    val msg: String
        get() = I18nUtils.getMessage(_assert.msg)

}