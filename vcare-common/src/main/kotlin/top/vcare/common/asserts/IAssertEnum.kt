package top.vcare.common.asserts

import top.vcare.common.exception.VcareException

interface IAssertEnum {

    val code: String
    val msg: String

    fun isTrue(value: Boolean) {
        if (!value) {
            throw VcareException(this)
        }
    }

    fun isFalse(value: Boolean) {
        if (value) {
            throw VcareException(this)
        }
    }

    fun equals(obj1: Any, obj2: Any) {
        if (obj1 != obj2) {
            throw VcareException(this)
        }
    }

    fun notEquals(obj1: Any, obj2: Any) {
        if (obj1 == obj2) {
            throw VcareException(this)
        }
    }
}