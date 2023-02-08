package com.npb.mvvm.data

import com.npb.mvvm.data.model.QuoteModel
import com.npb.mvvm.data.model.QuoteProvider
import com.npb.mvvm.data.network.QuoteService
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api:QuoteService,
    private val quoteProvider: QuoteProvider
) {


    suspend fun getAllQuotes() : List<QuoteModel> {
        val response = api.getQuotes()
        quoteProvider.quotes = response
        return response
    }
}