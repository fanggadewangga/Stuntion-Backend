package com.killjoy.data.repository.user

import com.killjoy.data.database.DatabaseFactory

class UserRepository(private val dbFactory: DatabaseFactory): IUserRepository {
}