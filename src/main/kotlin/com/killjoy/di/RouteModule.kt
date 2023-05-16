package com.killjoy.di

import com.killjoy.route.article.ArticleRoute
import com.killjoy.route.donation.DonationRoute
import com.killjoy.route.expert.ExpertRoute
import com.killjoy.route.note.NoteRoute
import com.killjoy.route.payment.PaymentRoute
import com.killjoy.route.question.QuestionRoute
import com.killjoy.route.task.TaskRoute
import com.killjoy.route.user.UserRoute
import com.killjoy.route.voucher.VoucherRoute
import org.koin.dsl.module

val routeModule = module {
    factory { ArticleRoute(get()) }
    factory { ExpertRoute(get()) }
    factory { QuestionRoute(get()) }
    factory { UserRoute(get()) }
    factory { DonationRoute(get()) }
    factory { NoteRoute(get()) }
    factory { TaskRoute(get()) }
    factory { VoucherRoute(get()) }
    factory { PaymentRoute(get()) }
}