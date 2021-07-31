package com.sudansh.hopinchallenge.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sudansh.hopinchallenge.domain.GetStreamUrlUseCase
import kotlinx.coroutines.launch

class MainViewModel(private val getStreamUrlUseCase: GetStreamUrlUseCase) : ViewModel() {
    private val _sessonToken = MutableLiveData<String>()
    private val _streamUrl = MutableLiveData<String>()
    val streamUrl: LiveData<String> = _streamUrl

    private fun exchangeToken(token: String) {
        viewModelScope.launch {
            val streamUrl = getStreamUrlUseCase.invoke(
                GetStreamUrlUseCase.Params(
                    sessionToken = token,
                    eventSlug = EVENT_SLUG
                )
            )
            _streamUrl.value = streamUrl
        }
    }

    fun parseToken(cookies: String) {
        "user.token=(.*?);".toRegex().find(cookies)?.groupValues?.getOrNull(1)?.let { token ->
            _sessonToken.value = token
            Log.i("token", token)
            exchangeToken(token)
        }
    }

    companion object {
        //Enter your event slug here
        const val EVENT_SLUG = "copy-of-sud-s-event"
    }
}
