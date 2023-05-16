package com.killjoy.data.repository.payment

import com.killjoy.model.payment.PaymentBody
import com.killjoy.model.payment.PaymentResponse

interface IPaymentRepository {
    suspend fun addNewPayment(body: PaymentBody)
    suspend fun getAllPayments(): List<PaymentResponse>
}