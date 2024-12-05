package top.vcare.common.enums

import com.fasterxml.jackson.annotation.JsonValue
import org.babyfish.jimmer.sql.EnumItem
import org.babyfish.jimmer.sql.EnumType

@EnumType(EnumType.Strategy.ORDINAL)
enum class StatusEnum(@JsonValue val value: Short) {

    /**
     * 启用
     */
    @EnumItem(ordinal = 0)
    ENABLED(0),

    /**
     * 禁用
     */
    @EnumItem(ordinal = 1)
    DISABLED(1),

}