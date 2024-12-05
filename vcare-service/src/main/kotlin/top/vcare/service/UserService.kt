package top.vcare.service

import org.babyfish.jimmer.sql.kt.KSqlClient
import org.springframework.stereotype.Service
import top.vcare.entity.dto.VcareUserRsponse

@Service
class UserService(private val sqlClient: KSqlClient) {

    fun getUserById(id: Long): VcareUserRsponse? {
        return sqlClient.findById(VcareUserRsponse::class, id)
    }

}