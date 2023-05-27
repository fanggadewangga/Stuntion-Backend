@file:OptIn(KtorExperimentalLocationsAPI::class)

package com.killjoy.route.task

import com.killjoy.data.repository.task.ITaskRepository
import com.killjoy.model.task.TaskBody
import com.killjoy.model.task.UserTaskBody
import com.killjoy.route.RouteResponseHelper.generalException
import com.killjoy.route.RouteResponseHelper.generalSuccess
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.locations.post
import io.ktor.request.*
import io.ktor.routing.*


class TaskRoute(
    private val repository: ITaskRepository
) {
    private fun Route.addNewTask() {
        post<TaskRouteLocation.TaskAddRoute> {
            val body = try {
                call.receive<TaskBody>()
            } catch (e: Exception) {
                call.generalException(e)
                return@post
            }
            call.generalSuccess { repository.addNewTask(body) }
        }
    }

    private fun Route.addNewUserTask() {
        post<TaskRouteLocation.TaskPostByUserRoute> {
            val body = try {
                call.receive<UserTaskBody>()
            } catch (e: Exception) {
                call.generalException(e)
                return@post
            }
            call.generalSuccess { repository.addNewUserTask(body) }
        }
    }

    private fun Route.getAllTasks() {
        get<TaskRouteLocation.TaskGetAllRoute> {
            call.generalSuccess { repository.getAllTasks() }
        }
    }

    private fun Route.getTasksByUser() {
        get<TaskRouteLocation.TaskGetByUserRoute> {
            val uid = try {
                call.parameters["uid"]
            } catch (e: Exception) {
                call.generalException(e)
                return@get
            }
            call.generalSuccess { repository.getTasksByUid(uid!!) }
        }
    }

    private fun Route.getTaskDetail() {
        get<TaskRouteLocation.TaskGetDetailRoute> {
            val taskId = try {
                call.parameters["taskId"]
            } catch (e: Exception) {
                call.generalException(e)
                return@get
            }
            call.generalSuccess { repository.getTaskDetail(taskId!!) }
        }
    }

    fun initTaskRoute(route: Route) {
        route.apply {
            addNewTask()
            addNewUserTask()
            getAllTasks()
            getTasksByUser()
            getTaskDetail()
        }
    }
}