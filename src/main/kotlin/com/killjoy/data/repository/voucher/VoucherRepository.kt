package com.killjoy.data.repository.voucher

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import com.killjoy.data.database.DatabaseFactory
import com.killjoy.data.table.UserTable
import com.killjoy.data.table.VoucherTable
import com.killjoy.model.voucher.VoucherBody
import com.killjoy.model.voucher.VoucherResponse
import com.killjoy.util.mapRowToVoucherResponse
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class VoucherRepository(private val dbFactory: DatabaseFactory) : IVoucherRepository {
    override suspend fun addNewVoucher(body: VoucherBody) {
        dbFactory.dbQuery {
            VoucherTable.insert {
                it[voucherId] = "VOUCHER-${NanoIdUtils.randomNanoId()}"
                it[title] = body.title
                it[type] = body.type
                it[discount] = body.discount
                it[freeBalance] = body.freeBalance
                it[validLevel] = body.validLevel
                it[imageUrl] = body.imageUrl
            }
        }
    }

    override suspend fun getAllVouchers(uid: String): List<VoucherResponse> =
        dbFactory.dbQuery {
            VoucherTable.join(UserTable, JoinType.INNER) { UserTable.uid.eq(uid) }
                .slice(
                    VoucherTable.voucherId,
                    VoucherTable.title,
                    VoucherTable.type,
                    VoucherTable.discount,
                    VoucherTable.freeBalance,
                    VoucherTable.validLevel,
                    VoucherTable.imageUrl,
                    UserTable.level
                )
                .selectAll()
                .mapNotNull { it.mapRowToVoucherResponse() }
        }
}