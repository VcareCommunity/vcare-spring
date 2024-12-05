import org.babyfish.jimmer.jackson.JsonConverter
import org.babyfish.jimmer.jackson.LongToStringConverter
import org.babyfish.jimmer.sql.*
import top.vcare.common.enums.DeviceEnum
import top.vcare.entity.base.CreateTimeEntity
import top.vcare.entity.base.DeletedTimeEntity

@Entity
@Table(name = "vcare_config")
@KeyUniqueConstraint
interface VcareConfig : CreateTimeEntity, DeletedTimeEntity {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * 配置类型
     */
    @Column(name = "config_type")
    val configType: DeviceEnum

    /**
     * 备注
     */
    @Column(name = "remark")
    val remark: String?

}