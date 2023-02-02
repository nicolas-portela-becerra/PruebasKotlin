package com.npb.mvvm.domain

import com.npb.mvvm.data.QuoteRepository
import com.npb.mvvm.data.model.QuoteModel

class GetQuotesUseCase {
    private val repository = QuoteRepository()

    //Al poner invoke la funcion se lanza "por defecto" cuando se llama al nombre de la clase + () -> GetQuoteUseCase()
    suspend operator fun invoke() : List<QuoteModel>? {
        return repository.getAllQuotes()
    }
}