package com.killjoy.util

import com.killjoy.data.table.PaymentMethodTable
import com.killjoy.model.payment.PaymentResponse
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.mapRowToPaymentResponse() = PaymentResponse(
    paymentId = this[PaymentMethodTable.paymentId],
    paymentName = this[PaymentMethodTable.paymentName],
    imageUrl = this[PaymentMethodTable.imageUrl],
    type = this[PaymentMethodTable.type]
)