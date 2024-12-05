package top.vcare.entity.base

import org.babyfish.jimmer.sql.Column
import org.babyfish.jimmer.sql.LogicalDeleted
import org.babyfish.jimmer.sql.MappedSuperclass
import java.time.LocalDateTime

@MappedSuperclass
interface DeletedTimeEntity {
    /**
     * 逻辑删除(时间戳毫秒)
     */
    @LogicalDeleted(value = "now")
    @Column(name = "deleted_time")
    val deletedTime: LocalDateTime?
}