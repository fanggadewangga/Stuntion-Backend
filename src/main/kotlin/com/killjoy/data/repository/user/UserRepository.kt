package com.killjoy.data.repository.user

import com.killjoy.data.database.DatabaseFactory
import com.killjoy.data.table.UserTable
import com.killjoy.model.user.UserBalanceResponse
import com.killjoy.model.user.UserBody
import com.killjoy.model.user.UserGeneralInformationBody
import com.killjoy.util.mapRowToUserBalanceResponse
import com.killjoy.util.mapRowToUserResponse
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
                it[balance] = 0.0
                it[level] = 1
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

    override suspend fun getUserWallet(uid: String): UserBalanceResponse = dbFactory.dbQuery {
        UserTable.select {
            UserTable.uid eq uid
        }.firstNotNullOf {
            it.mapRowToUserBalanceResponse()
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

    override suspend fun updateUserLevel(uid: String) {
        dbFactory.dbQuery {
            val currentLevel = UserTable.select { UserTable.uid.eq(uid) }
                .firstNotNullOf { it[UserTable.level] }
            UserTable.update(where = { UserTable.uid eq uid }) {
                it[this.level] = currentLevel.plus(1)
            }
        }
    }

    override suspend fun updateUserAvatarUrl(uid: String, avatarUrl: String) {
        dbFactory.dbQuery {
            UserTable.update(where = { UserTable.uid eq uid }) {
                it[this.avatarUrl] = avatarUrl
            }
        }
    }

    override suspend fun updateUserWalletBalance(uid: String, balance: Double) {
        dbFactory.dbQuery {
            UserTable.update(where = { UserTable.uid eq uid }) {
                it[this.balance] =  this.balance.plus(balance)
            }
        }
    }
}