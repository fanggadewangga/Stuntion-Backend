package com.killjoy.data.repository.user

import com.killjoy.model.user.UserBody
import com.killjoy.model.user.UserGeneralInformationBody
import com.killjoy.model.user.UserResponse

interface IUserRepository {
    suspend fun addNewUser(body: UserBody) // clear

    suspend fun getUserDetail(uid: String) : UserResponse // clear

    suspend fun updateUserGeneralInformation(uid: String, body: UserGeneralInformationBody) // clear
}