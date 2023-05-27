package com.killjoy.data.repository.task

import com.killjoy.model.task.TaskBody
import com.killjoy.model.task.TaskLiteResponse
import com.killjoy.model.task.TaskResponse
import com.killjoy.model.task.UserTaskBody

interface ITaskRepository {
    suspend fun addNewTask(body: TaskBody) // clear
    suspend fun addNewUserTask(body: UserTaskBody) // clear
    suspend fun getAllTasks(): List<TaskLiteResponse>
    suspend fun getTasksByUid(uid: String) : List<TaskLiteResponse> // clear (note : filter by age on notes in front-end)
    suspend fun getTaskDetail(taskId: String) : TaskResponse // clear
}