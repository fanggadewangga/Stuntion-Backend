package com.killjoy.data.repository.user

import com.killjoy.data.database.DatabaseFactory
import com.killjoy.data.table.UserTable
import com.killjoy.model.user.UserBody
import com.killjoy.model.user.UserGeneralInformationBody
import com.killjoy.util.mapRowToUserResponse
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.plus
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update

class UserRepository(private val dbFactory: DatabaseFactory) : IUserRepository {
    override suspend fun addNewUser(body: UserBody) {
        dbFactory.dbQuery {
            UserTable.insert {
                it[uid] = body.uid
                it[email] = body.email
                it[name] = "Anonymous"
                it[xp] = 0
            }
        }
    }

    override suspend fun getUserDetail(uid: String) = dbFactory.dbQuery {
        UserTable.select {
            UserTable.uid eq uid
        }.firstNotNullOf {
            it.mapRowToUserResponse()
        }
    }

    override suspend fun updateUserGeneralInformation(uid: String, body: UserGeneralInformationBody) {
        dbFactory.dbQuery {
            UserTable.update(where = { UserTable.uid.eq(uid) }) {
                it[name] = body.name
                it[birthDate] = body.birthDate
                it[gender] = body.gender
                it[avatarUrl] = body.avatarUrl
            }
        }
    }
}