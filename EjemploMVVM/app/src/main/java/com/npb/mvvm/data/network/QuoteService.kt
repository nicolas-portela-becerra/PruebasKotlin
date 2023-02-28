package com.npb.mvvm.data.network

import com.npb.mvvm.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuoteService @Inject constructor(
    private val api:QuoteApiClient
) {

    suspend fun getQuotes() : List<QuoteModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllQuotes()
            //Devuelve la lista recuperada y si no hay lista (?:) devuelve una lista vacia
            response.body() ?: emptyList()
        }

    }
}