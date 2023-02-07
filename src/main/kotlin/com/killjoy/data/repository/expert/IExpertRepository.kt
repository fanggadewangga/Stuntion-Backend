package com.killjoy.data.repository.expert

import com.killjoy.model.expert.ExpertBody
import com.killjoy.model.expert.ExpertLiteResponse
import com.killjoy.model.expert.ExpertResponse

interface IExpertRepository {
    suspend fun addNewExpert(body: ExpertBody) // clear
    suspend fun getAllExperts() : List<ExpertLiteResponse> // clear
    suspend fun getExpertDetail(expertId: String) : ExpertResponse // clear
    suspend fun searchExpert(query : String) : List<ExpertLiteResponse> // clear
}