package com.killjoy.util

import com.killjoy.data.table.UserTable
import com.killjoy.model.user.UserBalanceResponse
import com.killjoy.model.user.UserResponse
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.mapRowToUserResponse() = UserResponse(
    uid = this[UserTable.uid],
    email = this[UserTable.email],
    name = this[UserTable.name],
    birthDate = this[UserTable.birthDate],
    gender = this[UserTable.gender],
    avatarUrl = this[UserTable.avatarUrl],
    balance = this[UserTable.balance] ?: 0.0,
    xp = this[UserTable.xp],
    level = this[UserTable.level],
)

fun ResultRow.mapRowToUserBalanceResponse() = UserBalanceResponse(
    balance = this[UserTable.balance] ?: 0.0
)