package com.killjoy.data.repository.task

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import com.killjoy.data.database.DatabaseFactory
import com.killjoy.data.table.TaskInstructionTable
import com.killjoy.data.table.TaskTable
import com.killjoy.data.table.UserTaskTable
import com.killjoy.model.task.TaskBody
import com.killjoy.model.task.TaskLiteResponse
import com.killjoy.model.task.TaskResponse
import com.killjoy.model.task.UserTaskBody
import com.killjoy.util.mapRowToTaskListResponse
import com.killjoy.util.mapRowToTaskResponse
import org.jetbrains.exposed.sql.*

class TaskRepository(private val dbFactory: DatabaseFactory) : ITaskRepository {
    override suspend fun addNewTask(body: TaskBody) {
        val idCreated = "TASK-${NanoIdUtils.randomNanoId()}"

        dbFactory.dbQuery {
            TaskTable.insert { table ->
                table[taskId] = idCreated
                table[task] = body.task
                table[material] = body.material
                table[lowerAgeLimit] = body.lowerAgeLimit
                table[upperAgeLimit] = body.upperAgeLimit
            }

            body.instructions.forEach { instruction ->
                TaskInstructionTable.insert { table ->
                    table[taskId] = idCreated
                    table[this.instruction] = instruction
                }
            }
        }
    }

    override suspend fun addNewUserTask(body: UserTaskBody) {
        dbFactory.dbQuery {
            UserTaskTable.insert { table ->
                table[this.taskId] = body.taskId
                table[this.uid] = body.uid
                table[done] = true
            }
        }
    }

    override suspend fun getTasks(uid: String): List<TaskLiteResponse> = dbFactory.dbQuery {
        val listOfCompletedTaskId = UserTaskTable.select { UserTaskTable.uid.eq(uid) }.map { it[UserTaskTable.taskId] }
        TaskTable.selectAll().mapNotNull { it.mapRowToTaskListResponse(listOfCompletedTaskId) }
    }

    override suspend fun getTaskDetail(taskId: String): TaskResponse = dbFactory.dbQuery {
        val instructions = TaskInstructionTable.select {
            TaskInstructionTable.taskId.eq(taskId)
        }.mapNotNull {
            it[TaskInstructionTable.instruction]
        }

        TaskTable.select {
            TaskTable.taskId.eq(taskId)
        }.firstNotNullOf {
            it.mapRowToTaskResponse(instructions)
        }
    }
}