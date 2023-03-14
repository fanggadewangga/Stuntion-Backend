package com.killjoy.util

import com.killjoy.data.table.NoteTable
import com.killjoy.model.note.NoteResponse
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.mapRowToNoteResponse() = this[NoteTable.birthDay]?.let {
    this[NoteTable.heightDescription]?.let { it1 ->
        this[NoteTable.weightDescription]?.let { it2 ->
            this[NoteTable.idealHeight]?.let { it3 ->
                this[NoteTable.idealWeight]?.let { it4 ->
                    NoteResponse(
                        noteId = this[NoteTable.noteId],
                        childName = this[NoteTable.name],
                        gender = this[NoteTable.gender],
                        birthday = it,
                        ageYear = this[NoteTable.ageYear],
                        ageMonth = this[NoteTable.ageMonth],
                        ageDay = this[NoteTable.ageDay],
                        height = this[NoteTable.height],
                        heightDescription = it1,
                        weight = this[NoteTable.weight],
                        weightDescription = it2,
                        idealHeight = it3,
                        idealWeight = it4,
                        timestamp = this[NoteTable.timestamp]
                    )
                }
            }
        }
    }
}