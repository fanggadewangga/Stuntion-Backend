package com.killjoy.plugins

import com.killjoy.route.article.ArticleRoute
import com.killjoy.route.donation.DonationRoute
import com.killjoy.route.expert.ExpertRoute
import com.killjoy.route.note.NoteRoute
import com.killjoy.route.question.QuestionRoute
import com.killjoy.route.task.TaskRoute
import com.killjoy.route.user.UserRoute
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val articleRoute by inject<ArticleRoute>()
    val expertRoute by inject<ExpertRoute>()
    val questionRoute by inject<QuestionRoute>()
    val userRoute by inject<UserRoute>()
    val donationRoute by inject<DonationRoute>()
    val noteRoute by inject<NoteRoute>()
    val taskRoute by inject<TaskRoute>()

    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/leaderboards") {
            call.respondText("Hello from leaderboard")
        }

        articleRoute.initArticleRoute(this)
        expertRoute.initExpertRoute(this)
        questionRoute.initQuestionRoute(this)
        userRoute.initUserRoute(this)
        donationRoute.initDonationRoute(this)
        noteRoute.initNoteRoute(this)
        taskRoute.initTaskRoute(this)
    }
}
