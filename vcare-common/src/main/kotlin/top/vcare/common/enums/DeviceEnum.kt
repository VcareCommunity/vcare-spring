package top.vcare.common.enums

import com.fasterxml.jackson.annotation.JsonValue
import org.babyfish.jimmer.sql.EnumItem
import org.babyfish.jimmer.sql.EnumType

@EnumType(EnumType.Strategy.ORDINAL)
enum class DeviceEnum(@JsonValue val value: Short, val deviceName: String) {
    /**
     * 所有
     */
    @EnumItem(ordinal = 0)
    ALL(0, "all"),

    /**
     * 移动端
     */
    @EnumItem(ordinal = 1)
    MOBILE(1, "mobile"),

    /**
     * PC端
     */
    @EnumItem(ordinal = 2)
    PC(2, "pc")
}