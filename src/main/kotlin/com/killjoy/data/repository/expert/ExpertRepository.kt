package com.killjoy.data.repository.expert

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import com.killjoy.data.database.DatabaseFactory
import com.killjoy.data.table.ExpertCategoryTable
import com.killjoy.data.table.ExpertEducationTable
import com.killjoy.data.table.ExpertTable
import com.killjoy.data.table.ExpertWorkplaceTable
import com.killjoy.model.expert.ExpertBody
import com.killjoy.model.expert.ExpertLiteResponse
import com.killjoy.util.*
import org.jetbrains.exposed.sql.LowerCase
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import java.util.*

class ExpertRepository(private val dbFactory: DatabaseFactory) : IExpertRepository {

    override suspend fun addNewExpert(body: ExpertBody) {

        dbFactory.dbQuery {

            val idCreated = "EXPERT-${NanoIdUtils.randomNanoId()}"

            ExpertTable.insert {
                it[expertId] = idCreated
                it[name] = body.name
                it[avatarUrl] = body.avatarUrl
                it[experienceYear] = body.experienceYear
                it[str] = body.strNumber
                it[fee] = body.fee
                it[rating] = 0.0
            }

            body.categories.forEach { category ->
                ExpertCategoryTable.insert {
                    it[expertId] = idCreated
                    it[this.category] = category
                }
            }

            body.workplaces.forEach { workplace ->
                ExpertWorkplaceTable.insert {
                    it[expertId] = idCreated
                    it[this.workplace] = workplace
                }
            }

            body.educations.forEach { education ->
                ExpertEducationTable.insert {
                    it[expertId] = idCreated
                    it[this.university] = education
                }
            }
        }
    }

    override suspend fun getExpertDetail(expertId: String) = dbFactory.dbQuery {

        val categories = ExpertCategoryTable.select {
            ExpertCategoryTable.expertId eq expertId
        }.mapNotNull {
            it.mapRowToExpertCategory()
        }

        val workplaces = ExpertWorkplaceTable.select {
            ExpertWorkplaceTable.expertId eq expertId
        }.mapNotNull {
            it.mapRowToExpertWorkplace()
        }

        val educations = ExpertEducationTable.select {
            ExpertEducationTable.expertId eq expertId
        }.mapNotNull {
            it.mapRowToExpertEducation()
        }

        ExpertTable.select {
            ExpertTable.expertId eq expertId
        }.firstNotNullOf {
            it.mapRowToExpertResponse(categories, workplaces, educations)
        }
    }


    override suspend fun getAllExperts(): List<ExpertLiteResponse> = dbFactory.dbQuery {

        val listOfExpertId = ExpertTable.selectAll().map { it[ExpertTable.expertId] }

        val listOfExpertCategory = listOfExpertId.map { expertId ->
            ExpertCategoryTable.select {
                ExpertCategoryTable.expertId eq expertId
            }.mapNotNull {
                it.mapRowToExpertCategory()
            }
        }.flatten()

        ExpertTable.selectAll().mapNotNull {
            it.mapRowToExpertLiteResponse(listOfExpertCategory)
        }
    }

    override suspend fun searchExpert(query: String): List<ExpertLiteResponse> = dbFactory.dbQuery {

        val listOfExpertId = ExpertTable.selectAll().map { it[ExpertTable.expertId] }

        val listOfExpertCategory = listOfExpertId.map { expertId ->
            ExpertCategoryTable.select {
                ExpertCategoryTable.expertId eq expertId
            }.mapNotNull {
                it.mapRowToExpertCategory()
            }
        }.flatten()

        ExpertTable.select {
            LowerCase(ExpertTable.name).like("%$query%".lowercase(Locale.getDefault()))
        }.groupBy(ExpertTable.expertId)
            .mapNotNull {
                it.mapRowToExpertLiteResponse(listOfExpertCategory)
            }

    }
}