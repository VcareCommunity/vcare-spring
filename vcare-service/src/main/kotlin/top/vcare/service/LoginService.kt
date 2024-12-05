package top.vcare.service

import cn.dev33.satoken.secure.BCrypt
import cn.dev33.satoken.stp.StpUtil
import cn.dev33.satoken.temp.SaTempUtil
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.fetcher.newFetcher
import org.springframework.stereotype.Component
import top.vcare.common.LOGIN_USER
import top.vcare.common.asserts.UserAssertEnum
import top.vcare.common.enums.DeviceEnum
import top.vcare.entity.VcareUser
import top.vcare.entity.by
import top.vcare.entity.dto.LoginRequest
import top.vcare.entity.dto.LoginUser
import top.vcare.entity.username

@Component
class LoginService(private val sqlClient: KSqlClient) {

    fun login(request: LoginRequest): LoginUser {
        val dbUser = sqlClient.createQuery(VcareUser::class) {
            where(table.username eq request.username)
            select(table.fetch(newFetcher(VcareUser::class).by {
                allTableFields()
            }))
        }
            .limit(1)
            .fetchOneOrNull()
        UserAssertEnum.USER_NOT_EXIST.isFalse(dbUser == null)
        UserAssertEnum.USERNAME_OR_PASSWORD_INCORRECT.isTrue(BCrypt.checkpw(request.password, dbUser!!.password))
        StpUtil.login(dbUser.id, DeviceEnum.Mobile.deviceName)
        val tokenInfo = StpUtil.getTokenInfo()
        val refreshToken = SaTempUtil.createToken(DeviceEnum.Mobile.deviceName, dbUser.id, -1)
        val loginUser = LoginUser(dbUser, tokenInfo.tokenValue, refreshToken)
        val session = StpUtil.getTokenSession()
        session.set(LOGIN_USER, loginUser)
        return loginUser
    }

}