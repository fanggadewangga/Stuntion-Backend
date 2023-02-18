@file:OptIn(KtorExperimentalLocationsAPI::class, KtorExperimentalLocationsAPI::class)

package com.killjoy.route.note

import com.killjoy.data.repository.note.INoteRepository
import com.killjoy.model.note.NoteBody
import com.killjoy.route.RouteResponseHelper.generalException
import com.killjoy.route.RouteResponseHelper.generalSuccess
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.locations.post
import io.ktor.request.*
import io.ktor.routing.*

class NoteRoute(
    private val repository: INoteRepository
) {
    private fun Route.addNewNote() {
        post<NoteRouteLocation.NoteAddRoute> {
            val body = try {
                call.receive<NoteBody>()
            } catch (e: Exception) {
                call.generalException(e)
                return@post
            }

            val uid = try {
                call.parameters["uid"]
            } catch (e: Exception) {
                call.generalException(e)
                return@post
            } ?: ""

            call.generalSuccess { repository.addNewNote(uid,body) }
        }
    }

    private fun Route.getAllNotesByUser() {
        get<NoteRouteLocation.NotesGetListByUserRoute> {
            val uid = try {
                call.parameters["uid"]
            } catch (e: Exception) {
                call.generalException(e)
                return@get
            }
            call.generalSuccess { repository.getNotesByUser(uid!!) }
        }
    }

    private fun Route.getNoteDetail() {
        get<NoteRouteLocation.NoteGetDetailRoute> {
            val noteId = try {
                call.parameters["noteId"]
            } catch (e: Exception) {
                call.generalException(e)
                return@get
            }
            call.generalSuccess { repository.getNoteDetail(noteId!!) }
        }
    }

    fun initNoteRoute(route: Route) {
        route.apply {
            addNewNote()
            getAllNotesByUser()
            getNoteDetail()
        }
    }
}