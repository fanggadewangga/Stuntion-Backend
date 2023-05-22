package com.killjoy.di

import com.killjoy.data.database.DatabaseFactory
import com.killjoy.data.repository.article.ArticleRepository
import com.killjoy.data.repository.article.IArticleRepository
import com.killjoy.data.repository.donation.DonationRepository
import com.killjoy.data.repository.donation.IDonationRepository
import com.killjoy.data.repository.expert.ExpertRepository
import com.killjoy.data.repository.expert.IExpertRepository
import com.killjoy.data.repository.note.INoteRepository
import com.killjoy.data.repository.note.NoteRepository
import com.killjoy.data.repository.payment.IPaymentRepository
import com.killjoy.data.repository.payment.PaymentRepository
import com.killjoy.data.repository.question.IQuestionRepository
import com.killjoy.data.repository.question.QuestionRepository
import com.killjoy.data.repository.task.ITaskRepository
import com.killjoy.data.repository.task.TaskRepository
import com.killjoy.data.repository.user.IUserRepository
import com.killjoy.data.repository.user.UserRepository
import com.killjoy.data.repository.voucher.IVoucherRepository
import com.killjoy.data.repository.voucher.VoucherRepository
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.koin.dsl.module
import java.net.URI

val databaseModule = module {

    single {
        DatabaseFactory(get())
    }

    factory {
        val config = HikariConfig()
        config.apply {
            driverClassName = System.getenv("JDBC_DRIVER")
            //jdbcUrl = System.getenv("DATABASE_URL")
            maximumPoolSize = 6
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"

            val uri = URI(System.getenv("DATABASE_URL"))
            val username = uri.userInfo.split(":").toTypedArray()[0]
            val password = uri.userInfo.split(":").toTypedArray()[1]

            jdbcUrl =
                "jdbc:postgresql://" + uri.host + ":" + uri.port + uri.path + "?sslmode=require" + "&user=$username&password=$password"

            validate()
        }
/*        config.apply {
            driverClassName = System.getenv("JDBC_DRIVER")
            jdbcUrl = System.getenv("DATABASE_URL")
            maximumPoolSize = 6
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }*/
        HikariDataSource(config)
    }
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

    single<IDonationRepository> {
        DonationRepository(get())
    }

    single<INoteRepository> {
        NoteRepository(get())
    }

    single<ITaskRepository> {
        TaskRepository(get())
    }

    single<IVoucherRepository> {
        VoucherRepository(get())
    }

    single<IPaymentRepository> {
        PaymentRepository(get())
    }
}
