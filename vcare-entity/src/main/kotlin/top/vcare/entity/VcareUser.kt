package top.vcare.entity

import cn.dev33.satoken.secure.SaSecureUtil
import org.babyfish.jimmer.Formula
import org.babyfish.jimmer.jackson.JsonConverter
import org.babyfish.jimmer.jackson.LongToStringConverter
import org.babyfish.jimmer.sql.*
import top.vcare.common.VcareProperties
import top.vcare.common.enums.GenderEnum
import top.vcare.common.enums.StatusEnum
import top.vcare.entity.base.CreateTimeEntity
import top.vcare.entity.base.DeletedTimeEntity

@Entity
@Table(name = "vcare_user")
@KeyUniqueConstraint
interface VcareUser : CreateTimeEntity, DeletedTimeEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonConverter(LongToStringConverter::class)
    val id: Long

    /**
     * 用户名
     */
    @Key(group = "username")
    @Column(name = "username")
    val username: String

    /**
     * 昵称
     */
    @Column(name = "nickname")
    val nickname: String

    /**
     * 密码
     */
    @Column(name = "password")
    val password: String

    /**
     * 盐值
     */
    @Column(name = "salt")
    val salt: String

    /**
     * 性别
     */
    @Column(name = "gender")
    val gender: GenderEnum

    /**
     * 邮箱
     */
    @Key(group = "email")
    @Column(name = "email")
    val email: String?

    /**
     * qq
     */
    @Key(group = "qq")
    @Column(name = "qq")
    val qq: String?

    /**
     * 手机号码
     */
    @Key(group = "telephone")
    @Column(name = "telephone")
    val telephone: String?

    /**
     * 状态
     */
    @Column(name = "status")
    val status: StatusEnum


    @Formula(dependencies = ["email"])
    val decodeEmail: String?
        get() {
            if (!email.isNullOrBlank()) {
                return SaSecureUtil.aesDecrypt(VcareProperties.getAesKey(), email)
            }
            return null
        }

    @Formula(dependencies = ["qq"])
    val decodeQq: String?
        get() {
            if (!qq.isNullOrBlank()) {
                return SaSecureUtil.aesDecrypt(VcareProperties.getAesKey(), qq)
            }
            return null
        }

    @Formula(dependencies = ["telephone"])
    val decodeTelephone: String?
        get() {
            if (!telephone.isNullOrBlank()) {
                val telephone = SaSecureUtil.aesDecrypt(VcareProperties.getAesKey(), telephone)
                return "${telephone.substring(0, 3)}****${telephone.substring(7)}"
            }
            return null
        }
}