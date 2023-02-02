package com.npb.mvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.npb.mvvm.data.model.QuoteModel
import com.npb.mvvm.data.model.QuoteProvider
import com.npb.mvvm.domain.GetQuotesUseCase
import com.npb.mvvm.domain.GetRandomQuoteUseCase
import kotlinx.coroutines.launch

class QuoteViewModel : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val quoteModelList = MutableLiveData<QuoteModel>()
    var getCuotesUseCase = GetQuotesUseCase()
    var getRandomQuoteUseCase = GetRandomQuoteUseCase()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            //Se llama al caso de uso para que lance la funcion que hemos definido "por defecto"
            val result = getCuotesUseCase()
            if(!result.isNullOrEmpty()) {
                quoteModelList.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }

    fun randomQuote() {
        isLoading.postValue(true)

        val quote : QuoteModel? = getRandomQuoteUseCase()
        if(quote != null) {
            quoteModelList.postValue(quote)
        }


        isLoading.postValue(false)
    }


}