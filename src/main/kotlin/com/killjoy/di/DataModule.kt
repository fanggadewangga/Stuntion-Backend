package com.killjoy.di

import com.killjoy.data.database.DatabaseFactory
import com.killjoy.data.repository.article.ArticleRepository
import com.killjoy.data.repository.article.IArticleRepository
import com.killjoy.data.repository.expert.ExpertRepository
import com.killjoy.data.repository.expert.IExpertRepository
import com.killjoy.data.repository.question.IQuestionRepository
import com.killjoy.data.repository.question.QuestionRepository
import com.killjoy.data.repository.user.IUserRepository
import com.killjoy.data.repository.user.UserRepository
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.koin.dsl.module

val databaseModule = module {

    single {
        DatabaseFactory(get())
    }

    factory {
        val config = HikariConfig()
        config.apply {
            driverClassName = System.getenv("JDBC_DRIVER")
            jdbcUrl = System.getenv("DATABASE_URL")
            maximumPoolSize = 6
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"

            validate()
        }
        HikariDataSource(config)
    }

    val repositoryModule = module {

        single<IArticleRepository> {
            ArticleRepository(get())
        }

        single<IExpertRepository> {
            ExpertRepository(get())
        }

        single<IQuestionRepository> {
            QuestionRepository(get())
        }

        single<IUserRepository> {
            UserRepository(get())
        }

    }
}