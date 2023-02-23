package com.killjoy.route.question

import com.killjoy.data.repository.question.IQuestionRepository
import com.killjoy.model.question.QuestionBody
import com.killjoy.model.question.QuestionExpertAnswerBody
import com.killjoy.route.RouteResponseHelper.generalException
import com.killjoy.route.RouteResponseHelper.generalListSuccess
import com.killjoy.route.RouteResponseHelper.generalSuccess
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.locations.post
import io.ktor.locations.put
import io.ktor.request.*
import io.ktor.routing.*

class QuestionRoute(
    private val repository: IQuestionRepository
) {

    private fun Route.addNewQuestion() {
        post<QuestionRouteLocation.QuestionAddRoute> {
            val body = try {
                call.receive<QuestionBody>()
            } catch (e: Exception) {
                call.generalException(e)
                return@post
            }
            call.generalSuccess { repository.addNewQuestion(body) }
        }
    }

    private fun Route.getAllQuestions() {
        get<QuestionRouteLocation.QuestionGetListRoute> {
            val query = try {
                call.request.queryParameters["q"]
            } catch (e: Exception) {
                call.generalException(e)
                return@get
            }

            if (query != null)
                call.generalListSuccess { repository.searchQuestion(query) }
            else
                call.generalSuccess { repository.getAllQuestions() }
        }
    }

    private fun Route.getQuestionDetail() {
        get<QuestionRouteLocation.QuestionGetDetailRoute> {
            val questionId = try {
                call.parameters["questionId"]
            } catch (e: Exception) {
                call.generalException(e)
                return@get
            }
            call.generalSuccess { repository.getQuestionDetail(questionId!!) }
        }
    }

    private fun Route.addQuestionoExpertAnswer() {
        put<QuestionRouteLocation.QuestionUpdateExpertAnswer> {
            val questionId = try {
                call.parameters["questionId"]
            } catch (e: Exception) {
                call.generalException(e)
                return@put
            }
            val body = try {
                call.receive<QuestionExpertAnswerBody>()
            } catch (e: Exception) {
                call.generalException(e)
                return@put
            }

            call.generalSuccess { repository.addExpertAnswer(questionId!!, body) }
        }
    }

    fun initQuestionRoute(route: Route) {
        route.apply {
            addNewQuestion()
            addQuestionoExpertAnswer()
            getAllQuestions()
            getQuestionDetail()
        }
    }

}