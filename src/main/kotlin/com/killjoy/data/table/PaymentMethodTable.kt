package com.killjoy.data.table

import org.jetbrains.exposed.sql.Table

object PaymentMethodTable : Table() {

    override val tableName: String = "payment"

    val paymentId = varchar("payment_id", 128)
    val paymentName = varchar("payment_name", 64)
    val imageUrl = varchar("image_url", 1024)
    val type = integer("payment_type") // 1: instant ; 2: virtual acc ; 3: manual confirmation

    override val primaryKey: PrimaryKey = PrimaryKey(paymentId)
}
