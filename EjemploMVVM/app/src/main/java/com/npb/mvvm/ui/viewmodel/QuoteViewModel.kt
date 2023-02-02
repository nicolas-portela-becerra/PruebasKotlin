package com.npb.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.npb.mvvm.model.QuoteModel
import com.npb.mvvm.model.QuoteProvider

class QuoteViewModel : ViewModel() {
    val quoteModelList = MutableLiveData<QuoteModel>()

    fun randomQuote() {
        val currentQuote : QuoteModel = QuoteProvider.random()
        quoteModelList.postValue(currentQuote)
    }
}