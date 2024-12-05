package top.vcare.common.asserts

enum class UserAssertEnum (override val code: String, override val msg: String) : IAssertEnum {
    USER_NOT_EXIST("300001","user.not.exist"),
    USERNAME_OR_PASSWORD_INCORRECT("300002","user.username.or.password.incorrect")
}