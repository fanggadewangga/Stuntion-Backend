package com.killjoy.util

import com.killjoy.data.table.DonationTable
import com.killjoy.data.table.DonorTable
import com.killjoy.data.table.UserTable
import com.killjoy.model.donation.DonationLiteResponse
import com.killjoy.model.donation.DonationResponse
import com.killjoy.model.donation.DonorResponse
import com.killjoy.util.Const.DATE_FORMAT
import com.killjoy.util.Const.DATE_TIME_FORMAT
import org.jetbrains.exposed.sql.ResultRow
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter


fun ResultRow.mapRowToDonationLiteResponse(): DonationLiteResponse {
    val periodBetween = Period.between(
        LocalDate.parse(
            DateTimeFormatter.ofPattern(DATE_FORMAT).format(LocalDateTime.now()),
            DateTimeFormatter.ofPattern(DATE_FORMAT)
        ), LocalDate.parse(
            this[DonationTable.deadline_at],
            DateTimeFormatter.ofPattern(DATE_FORMAT)
        )
    )
    val dayRemaining = periodBetween.months * 30 + periodBetween.days
    return DonationLiteResponse(
        donationId = this[DonationTable.donationId],
        title = this[DonationTable.title],
        address = this[DonationTable.address],
        imageUrl = this[DonationTable.imageUrl],
        currentValue = this[DonationTable.currentValue],
        maxValue = this[DonationTable.maxValue],
        dayRemaining = dayRemaining,
        fee = this[DonationTable.fee],
        lat = this[DonationTable.lat],
        lon = this[DonationTable.lon]
    )
}

fun ResultRow.mapRowToDonationResponse(): DonationResponse {
    val periodBetween = Period.between(
        LocalDate.parse(
            DateTimeFormatter.ofPattern(DATE_FORMAT).format(LocalDateTime.now()),
            DateTimeFormatter.ofPattern(DATE_FORMAT)
        ), LocalDate.parse(
            this[DonationTable.deadline_at],
            DateTimeFormatter.ofPattern(DATE_FORMAT)
        )
    )
    val dayRemaining = periodBetween.months * 30 + periodBetween.days
    val isDone = dayRemaining < 0

    return DonationResponse(
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
        dayRemaining = dayRemaining,
        fee = this[DonationTable.fee],
        done = isDone,
        lat = this[DonationTable.lat],
        lon = this[DonationTable.lon]
    )
}

fun ResultRow.mapRowToDonorResponse(): DonorResponse {
    val periodBetween = Period.between(
        LocalDate.parse(
            DateTimeFormatter.ofPattern(DATE_TIME_FORMAT).format(LocalDateTime.now()),
            DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)
        ), LocalDate.parse(
            this[DonorTable.timestamp],
            DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)
        )
    )
    val period = periodBetween.months * 30 + periodBetween.days

    return DonorResponse(
        donorName = if (this[DonorTable.isAnonymous]) "User" else this[UserTable.name] ?: "User",
        nominal = this[DonorTable.nominal],
        timestamp = this[DonorTable.timestamp],
        dayPeriod = period
    )
}