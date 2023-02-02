package com.npb.mvvm.data.network

import com.npb.mvvm.core.RetrofitHelper
import com.npb.mvvm.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getQuotes() : List<QuoteModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(QuoteApiClient::class.java).getAllQuotes()
            //Devuelve la lista recuperada y si no hay lista (?:) devuelve una lista vacia
            response.body() ?: emptyList()
        }

    }
}