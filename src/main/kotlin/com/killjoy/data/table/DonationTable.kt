package com.killjoy.data.table

import org.jetbrains.exposed.sql.Table

object DonationTable : Table() {
    override val tableName = "donation"

    val donationId = varchar("donation_id", 128)
    val uid = reference("uid", UserTable.uid)
    val phone = varchar("phone", 32)
    val address = varchar("address", 64)
    val title = varchar("title", 64)
    val story = varchar("story", 1024)
    val imageUrl = varchar("image_url", 1024)
    val currentValue = integer("current_value")
    val maxValue = integer("max_value")
    val currentNominal = double("current_nominal").default(0.0)
    val uploaded_at = varchar("uploaded_at", 64)
    val deadline_at = varchar("deadline_at", 64)
    val fee = integer("fee")
    val done = bool("done")
    val lat = double("lat")
    val lon = double("lon")

    override val primaryKey = PrimaryKey(donationId)
}