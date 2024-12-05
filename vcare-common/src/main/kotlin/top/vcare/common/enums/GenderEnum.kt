package top.vcare.common.enums

import com.fasterxml.jackson.annotation.JsonValue
import org.babyfish.jimmer.sql.EnumItem
import org.babyfish.jimmer.sql.EnumType


@EnumType(EnumType.Strategy.ORDINAL)
enum class GenderEnum(@JsonValue val value: Short) {

    /**
     * 未知
     */
    @EnumItem(ordinal = 0)
    UNKNOWN(0),

    /**
     * 男
     */
    @EnumItem(ordinal = 1)
    MALE(1),

    /**
     * 女
     */
    @EnumItem(ordinal = 2)
    FEMALE(2),


}