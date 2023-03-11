package com.killjoy.data.table

import org.jetbrains.exposed.sql.Table

object TaskTable : Table() {

    override val tableName = "task"

    val taskId = varchar("task_id", 128)
    val task = varchar("task", 128)
    val material = varchar("material", 128)
    val imageUrl = varchar("image_url", 1024)
    val lowerAgeLimit = double("lower_age_limit")
    val upperAgeLimit = double("upper_age_limit")

    override val primaryKey = PrimaryKey(taskId)
}