package com.killjoy.data.repository.donation

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import com.killjoy.data.database.DatabaseFactory
import com.killjoy.data.table.DonationTable
import com.killjoy.data.table.DonorTable
import com.killjoy.data.table.UserTable
import com.killjoy.model.donation.*
import com.killjoy.util.Const.DATE_FORMAT
import com.killjoy.util.Const.DATE_TIME_FORMAT
import com.killjoy.util.mapRowToDonationLiteResponse
import com.killjoy.util.mapRowToDonationResponse
import com.killjoy.util.mapRowToDonorResponse
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DonationRepository(private val dbFactory: DatabaseFactory) : IDonationRepository {
    override suspend fun addNewDonation(body: DonationBody) {
        dbFactory.dbQuery {
            val dateObj = Date()
            val df: DateFormat = SimpleDateFormat(DATE_FORMAT)
            val dateCreated = df.format(dateObj)
            val idCreated = "DONATION-${NanoIdUtils.randomNanoId()}"

            DonationTable.insert { table ->
                table[donationId] = idCreated
                table[uid] = body.uid
                table[phone] = body.phone
                table[address] = body.address
                table[title] = body.title
                table[story] = body.story
                table[imageUrl] = body.imageUrl
                table[currentValue] = 0
                table[maxValue] = body.maxValue
                table[uploaded_at] = dateCreated
                table[deadline_at] = body.deadlineAt
                table[fee] = body.fee
                table[done] = false
                table[lat] = body.lat
                table[lon] = body.lon
            }
        }
    }

    override suspend fun updateDonationCurrentValue(donationId: String) {
        dbFactory.dbQuery {
            val currentValue = DonationTable.select { DonationTable.donationId.eq(donationId) }
                .firstNotNullOf { it[DonationTable.currentValue] }
            DonationTable.update(where = { DonationTable.donationId eq donationId }) {
                it[this.currentValue] = currentValue.plus(1)
            }
        }
    }

    override suspend fun getAllDonations(): List<DonationLiteResponse> =
        dbFactory.dbQuery {
            DonationTable.selectAll()
                .mapNotNull { it.mapRowToDonationLiteResponse() }
                .filter { it.dayRemaining >= 0 }
        }

    override suspend fun searchDonation(query: String): List<DonationLiteResponse> =
        dbFactory.dbQuery {
            DonationTable.select {
                LowerCase(DonationTable.title).like("%$query%".lowercase(Locale.getDefault()))
            }.groupBy(DonationTable.donationId)
                .mapNotNull {
                    it.mapRowToDonationLiteResponse()
                }
                .filter { it.dayRemaining >= 0 }
        }

    override suspend fun getDonationDetail(donationId: String): DonationResponse = dbFactory.dbQuery {
        DonationTable
            .join(UserTable, JoinType.INNER) { DonationTable.uid.eq(UserTable.uid) }
            .slice(
                DonationTable.donationId,
                UserTable.name,
                DonationTable.phone,
                DonationTable.address,
                UserTable.avatarUrl,
                DonationTable.title,
                DonationTable.story,
                DonationTable.imageUrl,
                DonationTable.currentValue,
                DonationTable.maxValue,
                DonationTable.uploaded_at,
                DonationTable.deadline_at,
                DonationTable.fee,
                DonationTable.done,
                DonationTable.lat,
                DonationTable.lon
            )
            .select(DonationTable.donationId.eq(donationId))
            .groupBy(DonationTable.donationId)
            .groupBy(UserTable.uid)
            .firstNotNullOf {
                it.mapRowToDonationResponse()
            }
    }

    override suspend fun deleteDonation(donationId: String) {
        dbFactory.dbQuery {
            DonationTable.deleteWhere {
                DonationTable.donationId.eq(donationId)
            }
        }
    }

    override suspend fun addNewDonor(body: DonorBody) {
        val dateObj = Date()
        val df: DateFormat = SimpleDateFormat(DATE_TIME_FORMAT)
        df.timeZone = TimeZone.getTimeZone("Asia/Jakarta")
        val dateCreated = df.format(dateObj)

        dbFactory.dbQuery {
            DonorTable.insert { table ->
                table[donationId] = body.donationId
                table[donorId] = body.uid
                table[nominal] = body.nominal
                table[isAnonymous] = body.isAnonymous
                table[timestamp] = dateCreated
            }
        }
    }

    override suspend fun getAllDonors(donationId: String): List<DonorResponse> = dbFactory.dbQuery {
        val currentValue = DonationTable.select { DonationTable.donationId.eq(donationId) }
            .firstNotNullOf { it[DonationTable.currentValue] }
        DonationTable.update(where = { DonationTable.donationId eq donationId }) {
            it[this.currentValue] = currentValue.plus(1)
        }

        DonorTable
            .join(UserTable, JoinType.INNER) { DonorTable.donorId.eq(UserTable.uid) }
            .slice(
                DonorTable.donationId,
                DonorTable.donorId,
                DonorTable.nominal,
                DonorTable.isAnonymous,
                DonorTable.timestamp,
                UserTable.uid
            )
            .select { DonorTable.donationId.eq(donationId) }
            .mapNotNull { it.mapRowToDonorResponse() }
    }
}