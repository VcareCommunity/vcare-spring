package top.vcare.entity.interceptor

import cn.dev33.satoken.secure.BCrypt
import cn.dev33.satoken.secure.SaSecureUtil
import org.babyfish.jimmer.kt.isLoaded
import org.babyfish.jimmer.sql.DraftInterceptor
import org.springframework.stereotype.Component
import top.vcare.common.VcareProperties
import top.vcare.entity.VcareUser
import top.vcare.entity.VcareUserDraft

@Component
class VcareUserInterceptor : DraftInterceptor<VcareUser, VcareUserDraft> {

    override fun beforeSave(draft: VcareUserDraft, original: VcareUser?) {
        if (isLoaded(draft, VcareUser::password)) {
            val salt = BCrypt.gensalt()
            draft.salt = salt
            draft.password = BCrypt.hashpw(draft.password)
        }
        if (isLoaded(draft, VcareUser::qq)) {
            draft.qq = SaSecureUtil.aesEncrypt(VcareProperties.getAesKey(), draft.qq)
        }
        if (isLoaded(draft, VcareUser::email)) {
            draft.email = SaSecureUtil.aesEncrypt(VcareProperties.getAesKey(), draft.email)
        }
        if (isLoaded(draft, VcareUser::telephone)) {
            draft.telephone = SaSecureUtil.aesEncrypt(VcareProperties.getAesKey(), draft.telephone)
        }
    }

}