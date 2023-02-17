package com.killjoy.util

import com.killjoy.data.table.NoteTable
import com.killjoy.model.note.NoteResponse
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.mapRowToNoteResponse() = NoteResponse(
    noteId = this[NoteTable.noteId],
    childName = this[NoteTable.name],
    gender = this[NoteTable.gender],
    ageYear = this[NoteTable.ageYear],
    ageMonth = this[NoteTable.ageMonth],
    ageDay = this[NoteTable.ageDay],
    height = this[NoteTable.height],
    weight = this[NoteTable.weight],
    timestamp = this[NoteTable.timestamp]
)