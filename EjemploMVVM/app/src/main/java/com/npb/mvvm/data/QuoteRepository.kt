package com.npb.mvvm.data

import com.npb.mvvm.data.model.QuoteModel
import com.npb.mvvm.data.model.QuoteProvider
import com.npb.mvvm.data.network.QuoteService

class QuoteRepository {
    private val api = QuoteService()

    suspend fun getAllQuotes() : List<QuoteModel> {
        val response = api.getQuotes()
        QuoteProvider.quotes = response
        return response
    }
}