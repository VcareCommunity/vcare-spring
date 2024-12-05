package top.vcare.entity.base

import org.babyfish.jimmer.sql.Column
import org.babyfish.jimmer.sql.MappedSuperclass
import java.time.LocalDateTime

@MappedSuperclass
interface CreateTimeEntity {
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    val createTime: LocalDateTime
}