package com.killjoy.route

import com.killjoy.model.BaseListResponse
import com.killjoy.model.BaseResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*

object RouteResponseHelper {

    suspend inline fun <reified T> ApplicationCall.generalSuccess(noinline action: suspend () -> T) {
        try {
            val data = action()
            this.respond(
                HttpStatusCode.OK,
                BaseResponse(
                    isError = false,
                    status = HttpStatusCode.OK.value.toString(),
                    message = "Success",
                    if (data is Unit) "Nothing" else data
                )
            )
        } catch (e: Exception) {
            this@generalSuccess.generalException(e)
        }
    }

    suspend inline fun ApplicationCall.generalException(exception: Exception) {
        when (exception) {
            is BadRequestException -> this.respond(
                HttpStatusCode.BadRequest,
                BaseResponse(
                    isError = true,
                    status = HttpStatusCode.BadRequest.value.toString(),
                    message = exception.message.toString(),
                    data = null
                )
            )

            is NotFoundException -> this.respond(
                HttpStatusCode.NotFound,
                BaseResponse(
                    isError = true,
                    status = HttpStatusCode.NotFound.value.toString(),
                    message = exception.message.toString(),
                    data = null
                )
            )

            else -> this.respond(
                HttpStatusCode.Conflict,
                BaseResponse(
                    isError = true,
                    status = HttpStatusCode.Conflict.value.toString(),
                    message = exception.message.toString(),
                    data = null
                )
            )
        }
    }

    suspend inline fun <reified T> ApplicationCall.generalListSuccess(noinline action: suspend () -> T) {
        try {
            val count = count { action() as List<*> }
            this.respond(
                HttpStatusCode.OK,
                BaseListResponse(
                    isError = false,
                    status = HttpStatusCode.OK.value.toString(),
                    message = "Request Success",
                    count = count,
                    data = action()
                )
            )

        } catch (e: Exception) {
            this@generalListSuccess.generalListException(e)
        }
    }

    suspend inline fun ApplicationCall.generalListException(e: Exception) {
        val listResponse =
            BaseListResponse(isError = true, message = e.message.toString(), count = 0, data = arrayListOf<Any>())
        when (e) {
            is BadRequestException -> {
                listResponse.status = HttpStatusCode.BadRequest.value.toString()
                this.respond(
                    HttpStatusCode.BadRequest,
                    listResponse
                )
            }

            is NotFoundException -> {
                listResponse.status = HttpStatusCode.NotFound.value.toString()
                this.respond(
                    HttpStatusCode.NotFound,
                    listResponse
                )
            }

            else -> {
                listResponse.status = HttpStatusCode.Conflict.value.toString()
                this.respond(
                    HttpStatusCode.Conflict,
                    listResponse
                )
            }
        }
    }

    inline fun count(block: () -> List<*>) = block().size
}