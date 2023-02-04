package com.killjoy.data.table

import org.jetbrains.exposed.sql.Table

object ArticleCategoryTable : Table() {

    override val tableName = "article_category"

    val articleId = reference("article_id", ArticleTable.articleId)
    val category = varchar("category", 24)
}