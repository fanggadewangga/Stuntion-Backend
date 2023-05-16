package com.killjoy.util

import com.killjoy.data.table.UserTable
import com.killjoy.data.table.VoucherTable
import com.killjoy.model.voucher.VoucherResponse
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.mapRowToVoucherResponse(): VoucherResponse {
    val userLevel = this[UserTable.level]
    return VoucherResponse(
        voucherId = this[VoucherTable.voucherId],
        title = this[VoucherTable.title],
        type = this[VoucherTable.type],
        discount = this[VoucherTable.discount],
        freeBalance = this[VoucherTable.freeBalance],
        validLevel = this[VoucherTable.validLevel],
        isValid = userLevel > this[VoucherTable.validLevel],
        imageUrl = this[VoucherTable.imageUrl]
    )
}