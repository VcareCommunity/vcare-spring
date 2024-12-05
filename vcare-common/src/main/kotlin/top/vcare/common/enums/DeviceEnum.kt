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
    All(0, "all"),

    /**
     * 移动端
     */
    @EnumItem(ordinal = 1)
    Mobile(1, "mobile"),

    /**
     * PC端
     */
    @EnumItem(ordinal = 2)
    Pc(2, "pc")
}