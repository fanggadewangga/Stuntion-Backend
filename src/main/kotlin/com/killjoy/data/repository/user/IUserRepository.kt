package com.killjoy.data.repository.user

import com.killjoy.model.user.UserBalanceResponse
import com.killjoy.model.user.UserBody
import com.killjoy.model.user.UserGeneralInformationBody
import com.killjoy.model.user.UserResponse

interface IUserRepository {
    suspend fun addNewUser(body: UserBody) // clear
    suspend fun getUserDetail(uid: String): UserResponse // clear
    suspend fun getUserWallet(uid: String): UserBalanceResponse
    suspend fun updateUserGeneralInformation(uid: String, body: UserGeneralInformationBody) // clear
    suspend fun updateUserLevel(uid: String) // clear
    suspend fun updateUserAvatarUrl(uid: String, avatarUrl: String) // clear
    suspend fun updateUserWalletBalance(uid: String, balance: Double) // clear
}