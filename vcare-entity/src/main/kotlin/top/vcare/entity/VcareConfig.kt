package top.vcare.entity

import com.fasterxml.jackson.annotation.JsonProperty
import org.babyfish.jimmer.jackson.JsonConverter
import org.babyfish.jimmer.jackson.LongToStringConverter
import org.babyfish.jimmer.sql.*
import top.vcare.common.enums.DeviceEnum
import top.vcare.entity.base.CreateTimeEntity
import top.vcare.entity.base.DeletedTimeEntity

/**
 * 配置表
 */
@Entity
@Table(name = "vcare_config")
@KeyUniqueConstraint
interface VcareConfig : CreateTimeEntity, DeletedTimeEntity {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    @JsonConverter(LongToStringConverter::class)
    val id: Long

    /**
     * 配置名称
     */
    @Column(name = "config_name")
    val configName: String

    /**
     * 配置键
     */
    @Key
    @Column(name = "config_key")
    val configKey: String

    /**
     * 配置值
     */
    @Column(name = "config_value")
    val configValue: String

    /**
     * 配置类型,0:全部：1：移动端，2：PC端
     */
    @Column(name = "config_type")
    val configType: DeviceEnum

    /**
     * 备注
     */
    @Column(name = "remark")
    val remark: String?
}

