package com.killjoy.data.table

import org.jetbrains.exposed.sql.Table

object DonorTable: Table() {
    override val tableName = "donor"

    val donationId = reference("donation_id", DonationTable.donationId)
    val donorId = reference("uid", UserTable.uid)
    val nominal = double("nominal")
    val isAnonymous = bool("anonymous")
    val timestamp = varchar("timestamp", 64)
}