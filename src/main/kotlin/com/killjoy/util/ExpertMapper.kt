package com.killjoy.util

import com.killjoy.data.table.*
import com.killjoy.model.expert.*
import org.jetbrains.exposed.sql.Avg
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.mapRowToExpertResponse(
    categories: List<ExpertCategory>,
    workplaces: List<ExpertWorkplace>,
    educations: List<ExpertEducation>
) = ExpertResponse(
    expertId = this[ExpertTable.expertId],
    name = this[ExpertTable.name],
    avatarUrl = this[ExpertTable.avatarUrl],
    experienceYear = this[ExpertTable.experienceYear],
    str = this[ExpertTable.str],
    fee = this[ExpertTable.fee],
    rating = this[ExpertTable.rating],
    categories = categories
        .filter { it.expertId == this[ExpertTable.expertId] }
        .map { it.category },
    workplaces = workplaces
        .filter { it.expertId == this[ExpertTable.expertId] }
        .map { it.workplace },
    educations = educations
        .filter { it.expertId == this[ExpertTable.expertId] }
        .map { it.university }
)

fun ResultRow.mapRowToExpertLiteResponse(
    categories: List<ExpertCategory>
) = ExpertLiteResponse(
    expertId = this[ExpertTable.expertId],
    name = this[ExpertTable.name],
    avatarUrl = this[ExpertTable.avatarUrl],
    experienceYear = this[ExpertTable.experienceYear],
    fee = this[ExpertTable.fee],
    rating = this[ExpertTable.rating],
    categories = categories
        .filter { it.expertId == this[ExpertTable.expertId] }
        .map { it.category },
)

fun ResultRow.mapRowToExpertCategory() = ExpertCategory(
    expertId = this[ExpertCategoryTable.expertId],
    category = this[ExpertCategoryTable.category]
)

fun ResultRow.mapRowToExpertEducation() = ExpertEducation(
    expertId = this[ExpertEducationTable.expertId],
    university = this[ExpertEducationTable.university]
)

fun ResultRow.mapRowToExpertWorkplace() = ExpertWorkplace(
    expertId = this[ExpertWorkplaceTable.expertId],
    workplace = this[ExpertWorkplaceTable.workplace]
)