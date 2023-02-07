package com.killjoy.route.expert

import com.killjoy.data.repository.expert.IExpertRepository
import com.killjoy.model.expert.ExpertBody
import com.killjoy.route.RouteResponseHelper.generalException
import com.killjoy.route.RouteResponseHelper.generalListSuccess
import com.killjoy.route.RouteResponseHelper.generalSuccess
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.locations.post
import io.ktor.request.*
import io.ktor.routing.*

class ExpertRoute(
    private val repository: IExpertRepository
) {
    private fun Route.addNewExpert() {
        post<ExpertRouteLocation.ExpertAddRoute> {
            val body = try {
                call.receive<ExpertBody>()
            } catch (e: Exception) {
                call.generalException(e)
                return@post
            }
            call.generalSuccess { repository.addNewExpert(body) }
        }
    }


    private fun Route.getAllExperts() {
        get<ExpertRouteLocation.ExpertGetListRoute> {
            val query = try {
                call.request.queryParameters["q"]
            } catch (e: Exception) {
                call.generalException(e)
                return@get
            }

            if (query != null)
                call.generalListSuccess { repository.searchExpert(query) }
            else
                call.generalSuccess { repository.getAllExperts() }
        }
    }


    private fun Route.getExpertDetail() {
        get<ExpertRouteLocation.ExpertGetDetailRoute> {
            val expertId = try {
                call.parameters["expertId"]
            } catch (e: Exception) {
                call.generalException(e)
                return@get
            }
            call.generalSuccess { repository.getExpertDetail(expertId!!) }
        }
    }

    fun initExpertRoute(route: Route) {
        route.apply {
            addNewExpert()
            getAllExperts()
            getExpertDetail()
        }
    }
}