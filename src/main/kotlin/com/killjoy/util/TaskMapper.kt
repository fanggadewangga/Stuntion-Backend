package com.killjoy.util

import com.killjoy.data.table.TaskTable
import com.killjoy.model.task.TaskLiteResponse
import com.killjoy.model.task.TaskResponse
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.mapRowToTaskListResponse(
    listOfUserTasks: List<String>,
) = TaskLiteResponse(
    taskId = this[TaskTable.taskId],
    task = this[TaskTable.task],
    done = listOfUserTasks.contains(this[TaskTable.taskId])
)

fun ResultRow.mapRowToTaskResponse(
    listOfInstructions: List<String>,
) = this[TaskTable.imageUrl]?.let {
    TaskResponse(
        taskId = this[TaskTable.taskId],
        task = this[TaskTable.task],
        material = this[TaskTable.material],
        imageUrl = it,
        lowerAgeLimit = this[TaskTable.lowerAgeLimit],
        upperAgeLimit = this[TaskTable.upperAgeLimit],
        instructions = listOfInstructions,
    )
}