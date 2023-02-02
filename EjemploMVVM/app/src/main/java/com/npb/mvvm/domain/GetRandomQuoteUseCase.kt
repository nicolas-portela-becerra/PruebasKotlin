package com.npb.mvvm.domain

import com.npb.mvvm.data.QuoteRepository
import com.npb.mvvm.data.model.QuoteModel
import com.npb.mvvm.data.model.QuoteProvider
import com.npb.mvvm.ui.viewmodel.QuoteViewModel

class GetRandomQuoteUseCase {
    private val repository = QuoteRepository()

    operator fun invoke() : QuoteModel? {
        val quotes : List<QuoteModel> = QuoteProvider.quotes
        if(!quotes.isNullOrEmpty()) {
            //quotes.indices es como poner (0..quotes.size-1)
            val random = (quotes.indices).random()
            return quotes[random]
        }
        return null
    }
}