package com.killjoy.di

import com.killjoy.route.article.ArticleRoute
import com.killjoy.route.donation.DonationRoute
import com.killjoy.route.expert.ExpertRoute
import com.killjoy.route.question.QuestionRoute
import com.killjoy.route.user.UserRoute
import org.koin.dsl.module

val routeModule = module {
    factory { ArticleRoute(get()) }
    factory { ExpertRoute(get()) }
    factory { QuestionRoute(get()) }
    factory { UserRoute(get()) }
    factory { DonationRoute(get()) }
}