package com.killjoy.data.table

import org.jetbrains.exposed.sql.Table

object UserVoucherTable: Table() {
    val uid = reference("uid", UserTable.uid)
    val voucherId = reference("voucher_id", VoucherTable.voucherId)
    val isUsed = bool("is_used").default(false)
}