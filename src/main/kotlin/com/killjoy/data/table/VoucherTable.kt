package com.killjoy.data.table

import org.jetbrains.exposed.sql.Table

object VoucherTable: Table() {

    override val tableName: String = "voucher"

    val voucherId = varchar("voucher_id", 128)
    val title = varchar("title", 128)
    val type = integer("voucher_type") // 1: discount ; 2: free balance
    val discount = integer("discount").nullable()
    val freeBalance = double("free_balance").nullable()
    val validLevel = integer("valid_level")
    val imageUrl = varchar("image_url", 1024)

    override val primaryKey: PrimaryKey = PrimaryKey(voucherId)
}