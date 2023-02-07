package com.killjoy.data.repository.question

import com.killjoy.model.question.QuestionBody
import com.killjoy.model.question.QuestionLiteResponse
import com.killjoy.model.question.QuestionResponse

interface IQuestionRepository {
    suspend fun addNewQuestion(body: QuestionBody)
    suspend fun getAllQuestions() : List<QuestionLiteResponse>
    suspend fun getQuestionDetail(questionId : String) : QuestionResponse
    suspend fun searchQuestion(query: String) : List<QuestionLiteResponse>
}