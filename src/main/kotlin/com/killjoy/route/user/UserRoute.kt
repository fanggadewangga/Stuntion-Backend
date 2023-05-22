@file:OptIn(KtorExperimentalLocationsAPI::class)

package com.killjoy.route.user

import com.killjoy.data.repository.user.IUserRepository
import com.killjoy.model.user.UserAvatarBody
import com.killjoy.model.user.UserBalanceBody
import com.killjoy.model.user.UserBody
import com.killjoy.model.user.UserGeneralInformationBody
import com.killjoy.route.RouteResponseHelper.generalException
import com.killjoy.route.RouteResponseHelper.generalSuccess
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.locations.post
import io.ktor.locations.put
import io.ktor.request.*
import io.ktor.routing.*

class UserRoute(
    private val repository: IUserRepository
) {
    private fun Route.addNewUser() {
        post<UserRouteLocation.UserAddRoute> {
            val body = try {
                call.receive<UserBody>()
            } catch (e: Exception) {
                call.generalException(e)
                return@post
            }
            call.generalSuccess { repository.addNewUser(body) }
        }
    }

    private fun Route.getUserDetail() {
        get<UserRouteLocation.UserGetDetailRoute> {
            val uid = try {
                call.parameters["uid"]
            } catch (e: Exception) {
                call.generalException(e)
                return@get
            }
            call.generalSuccess { repository.getUserDetail(uid!!) }
        }
    }

    private fun Route.getUserWallet() {
        get<UserRouteLocation.UserGetWalletRoute> {
            val uid = try {
                call.parameters["uid"]
            } catch (e: Exception) {
                call.generalException(e)
                return@get
            }
            call.generalSuccess { repository.getUserWallet(uid!!) }
        }
    }

    private fun Route.updateUserGeneralInformation() {
        put<UserRouteLocation.UserUpdateGeneralInformationRoute> {

            val uid = try {
                call.parameters["uid"]
            } catch (e: Exception) {
                call.generalException(e)
                return@put
            }

            val body = try {
                call.receive<UserGeneralInformationBody>()
            } catch (e: Exception) {
                call.generalException(e)
                return@put
            }

            call.generalSuccess { repository.updateUserGeneralInformation(uid!!, body) }
        }
    }

    private fun Route.updateUserLevel() {
        put<UserRouteLocation.UserUpdateLevel> {
            val uid = try {
                call.parameters["uid"]
            } catch (e: Exception) {
                call.generalException(e)
                return@put
            }
            call.generalSuccess { repository.updateUserLevel(uid!!) }
        }
    }

    private fun Route.updateUserAvatar() {
        put<UserRouteLocation.UserUpdateAvatar> {
            val uid = try {
                call.parameters["uid"]
            } catch (e: Exception) {
                call.generalException(e)
                return@put
            }

            val body = try {
                call.receive<UserAvatarBody>()
            } catch (e: Exception) {
                call.generalException(e)
                return@put
            }
            call.generalSuccess { repository.updateUserAvatarUrl(uid!!, body.avatarUrl) }
        }
    }

    private fun Route.updateUserWallet() {
        put<UserRouteLocation.UserUpdateWallet> {
            val uid = try {
                call.parameters["uid"]
            } catch (e: Exception) {
                call.generalException(e)
                return@put
            }

            val body = try {
                call.receive<UserBalanceBody>()
            } catch (e: Exception) {
                call.generalException(e)
                return@put
            }
            call.generalSuccess { repository.updateUserWalletBalance(uid!!, body.balance) }
        }
    }

    fun initUserRoute(route: Route) {
        route.apply {
            addNewUser()
            getUserDetail()
            getUserWallet()
            updateUserGeneralInformation()
            updateUserLevel()
            updateUserAvatar()
            updateUserWallet()
        }
    }
}