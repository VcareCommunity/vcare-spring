package top.vcare.common.asserts

enum class BaseAssertEnum(override val code: String, override val msg: String) : IAssertEnum {
    SUCCESS("000000", "base.success"),
    SQL_EXECUTE_ERROR("999995", "base.sql.execute.error"),
    PARAMS_INVALID("999996", "base.params.invalid"),
    NOT_FOUND("999997", "base.not.found"),
    INTERNAL_SERVER_ERROR("999998", "base.internal.server.error"),
    FAILED("999999", "base.failed"),
}