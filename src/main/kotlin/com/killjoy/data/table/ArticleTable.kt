package com.killjoy.data.table

import org.jetbrains.exposed.sql.Table

object ArticleTable : Table() {

    override val tableName = "article"

    val articleId = varchar("article_id", 128)
    val title = varchar("title", 128)
    val description = varchar("description", 2048)
    val videoUrl = varchar("video_url", 1024)
    val imageUrl = varchar("image_url", 1024)
    val reviewer = varchar("reviewer", 128)
    val timestamp = varchar("timestamp", 64)

    override val primaryKey = PrimaryKey(articleId)
}