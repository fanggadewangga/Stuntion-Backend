package com.killjoy.data.repository.question

import com.killjoy.model.question.QuestionBody
import com.killjoy.model.question.QuestionExpertAnswerBody
import com.killjoy.model.question.QuestionLiteResponse
import com.killjoy.model.question.QuestionResponse

interface IQuestionRepository {
    suspend fun addNewQuestion(body: QuestionBody) // clear
    suspend fun addExpertAnswer(questionId: String, body: QuestionExpertAnswerBody) // clear
    suspend fun getAllQuestions() : List<QuestionLiteResponse> // clear
    suspend fun getQuestionDetail(questionId : String) : QuestionResponse // clear
    suspend fun searchQuestion(query: String) : List<QuestionLiteResponse> // clear
}