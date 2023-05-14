package com.killjoy.util

import com.killjoy.data.table.DonationTable
import com.killjoy.data.table.UserTable
import com.killjoy.model.donation.DonationLiteResponse
import com.killjoy.model.donation.DonationResponse
import com.killjoy.util.Const.DATE_FORMAT
import org.jetbrains.exposed.sql.ResultRow
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


fun ResultRow.mapRowToDonationLiteResponse() = DonationLiteResponse(
    donationId = this[DonationTable.donationId],
    title = this[DonationTable.title],
    address = this[DonationTable.address],
    imageUrl = this[DonationTable.imageUrl],
    currentValue = this[DonationTable.currentValue],
    maxValue = this[DonationTable.maxValue],
    dayRemaining = ChronoUnit.DAYS.between(
        LocalDate.parse(
            DateTimeFormatter.ofPattern(DATE_FORMAT).format(LocalDateTime.now()),
            DateTimeFormatter.ofPattern(DATE_FORMAT)
        ), LocalDate.parse(
            this[DonationTable.deadline_at],
            DateTimeFormatter.ofPattern(DATE_FORMAT)
        )
    ).toInt(),
    fee = this[DonationTable.fee],
    lat = this[DonationTable.lat],
    lon = this[DonationTable.lon]
)

fun ResultRow.mapRowToDonationResponse() = DonationResponse(
    donationId = this[DonationTable.donationId],
    name = this[UserTable.name]!!,
    phone = this[DonationTable.phone],
    address = this[DonationTable.address],
    userAvatarUrl = this[UserTable.avatarUrl]!!,
    title = this[DonationTable.title],
    story = this[DonationTable.story],
    imageUrl = this[DonationTable.imageUrl],
    currentValue = this[DonationTable.currentValue],
    maxValue = this[DonationTable.maxValue],
    uploadedAt = this[DonationTable.uploaded_at],
    deadlineAt = this[DonationTable.deadline_at],
    dayRemaining = Period.between(
        LocalDate.parse(
            this[DonationTable.uploaded_at],
            DateTimeFormatter.ofPattern(DATE_FORMAT)
        ), LocalDate.parse(
            this[DonationTable.deadline_at],
            DateTimeFormatter.ofPattern(DATE_FORMAT)
        )
    ).days,
    fee = this[DonationTable.fee],
    done = this[DonationTable.done],
    lat = this[DonationTable.lat],
    lon = this[DonationTable.lon]
)