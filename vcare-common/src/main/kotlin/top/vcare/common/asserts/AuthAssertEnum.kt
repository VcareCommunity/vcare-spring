package top.vcare.common.asserts

enum class AuthAssertEnum(override val code: String, override val msg: String) : IAssertEnum {
    TOKEN_READE_FAILED("200001","auth.token.read.failed"),
    TOKEN_INVALID("200002", "auth.token.invalid"),
    TOKEN_EXPIRED("200003", "auth.token.expired"),
    TOKEN_REPLACED_OFFLINE("200004", "auth.token.replaced.offline"),
    TOKEN_FORCEFULLY_LOGGED_OUT("200005", "auth.token.forcefully.logged.out"),
    TOKEN_FROZEN("200006", "auth.token.frozen"),

}