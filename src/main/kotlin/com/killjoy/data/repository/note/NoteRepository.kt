package com.killjoy.data.repository.note

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import com.killjoy.data.database.DatabaseFactory
import com.killjoy.data.table.NoteTable
import com.killjoy.data.table.UserNoteTable
import com.killjoy.model.note.NoteBody
import com.killjoy.util.Const
import com.killjoy.util.mapRowToNoteResponse
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class NoteRepository(private val dbFactory: DatabaseFactory) : INoteRepository {
    override suspend fun addNewNote(uid: String, body: NoteBody) {
        dbFactory.dbQuery {
            val dateObj = Date()
            val df: DateFormat = SimpleDateFormat(Const.DATE_FORMAT)
            val dateCreated = df.format(dateObj)
            val idCreated = "NOTE-${NanoIdUtils.randomNanoId()}"

            NoteTable.insert { table ->
                table[noteId] = idCreated
                table[this.uid] = uid
                table[name] = body.childName
                table[gender] = body.gender
                table[birthDay] = body.birthday
                table[ageYear] = body.ageYear
                table[ageMonth] = body.ageMonth
                table[ageDay] = body.ageDay
                table[height] = body.height
                table[heightDescription] = body.heightDescription
                table[weight] = body.weight
                table[weightDescription] = body.weightDescription
                table[idealHeight] = body.idealHeight
                table[idealWeight] = body.idealWeight
                table[timestamp] = dateCreated
            }

            UserNoteTable.insert { table ->
                table[userId] = uid
                table[noteId] = idCreated
            }
        }
    }

    override suspend fun getNotesByUser(uid: String) =
        dbFactory.dbQuery {
            UserNoteTable.join(NoteTable, JoinType.INNER) {
                UserNoteTable.noteId.eq(NoteTable.noteId)
            }.select {
                UserNoteTable.userId.eq(uid)
            }.mapNotNull {
                it.mapRowToNoteResponse()
            }
        }

    override suspend fun getNoteDetail(noteId: String) = dbFactory.dbQuery {
        NoteTable.select {
            NoteTable.noteId.eq(noteId)
        }.firstNotNullOf {
            it.mapRowToNoteResponse()
        }
    }

}