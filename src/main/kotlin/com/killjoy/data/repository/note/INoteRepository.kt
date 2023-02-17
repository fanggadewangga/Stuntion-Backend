package com.killjoy.data.repository.note

import com.killjoy.model.note.NoteBody
import com.killjoy.model.note.NoteResponse

interface INoteRepository {
    suspend fun addNewNote(uid: String, body: NoteBody) // clear
    suspend fun getNotesByUser(uid: String): List<NoteResponse> // clear
    suspend fun getNoteDetail(noteId: String): NoteResponse // clear
}