@file:OptIn(KtorExperimentalLocationsAPI::class)

package com.killjoy.route.user

import com.killjoy.data.repository.user.IUserRepository
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

    fun initUserRoute(route: Route) {
        route.apply {
            addNewUser()
            getUserDetail()
            updateUserGeneralInformation()
        }
    }
}