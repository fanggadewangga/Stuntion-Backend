@file:OptIn(KtorExperimentalLocationsAPI::class)

package com.killjoy.route.note

import com.killjoy.route.user.UserRouteLocation.Companion.SELECTED_USER
import io.ktor.locations.*

sealed class NoteRouteLocation {
    companion object {
        private const val NOTE = "$SELECTED_USER/note"

        // GET (all notes by user)
        private const val GET_NOTES = NOTE

        // POST
        const val POST_NOTE = NOTE

        // GET
        const val DETAIL_NOTE = "$NOTE/{noteId}"
    }

    @Location(GET_NOTES)
    data class NotesGetListByUserRoute(val uid: String)

    @Location(POST_NOTE)
    data class NoteAddRoute(val uid: String)

    @Location(DETAIL_NOTE)
    data class NoteGetDetailRoute(val uid: String, val noteId: String)
}