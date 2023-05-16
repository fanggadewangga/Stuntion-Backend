package com.killjoy.data.repository.payment

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import com.killjoy.data.database.DatabaseFactory
import com.killjoy.data.table.PaymentMethodTable
import com.killjoy.model.payment.PaymentBody
import com.killjoy.model.payment.PaymentResponse
import com.killjoy.util.mapRowToPaymentResponse
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class PaymentRepository(private val dbFactory: DatabaseFactory) : IPaymentRepository {
    override suspend fun addNewPayment(body: PaymentBody) {
        dbFactory.dbQuery {
            PaymentMethodTable.insert {
                it[paymentId] = "PAYMENT-${NanoIdUtils.randomNanoId()}"
                it[paymentName] = body.paymentName
                it[imageUrl] = body.imageUrl
                it[type] = body.type
            }
        }
    }

    override suspend fun getAllPayments(): List<PaymentResponse> =
        dbFactory.dbQuery {
            PaymentMethodTable.selectAll().mapNotNull { it.mapRowToPaymentResponse() }
        }

}