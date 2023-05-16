package com.killjoy.data.repository.voucher

import com.killjoy.model.voucher.VoucherBody
import com.killjoy.model.voucher.VoucherResponse

interface IVoucherRepository {
    suspend fun addNewVoucher(body: VoucherBody)
    suspend fun getAllVouchers(uid: String): List<VoucherResponse>
}