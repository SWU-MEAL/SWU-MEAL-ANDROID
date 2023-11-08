package com.swubab.presentation.today

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swubab.data.ApiPool
import com.swubab.data.dto.response.ResponseTodaySwubabDto
import kotlinx.coroutines.launch
import timber.log.Timber

class TodaySwubabViewModel : ViewModel() {
    private val _getTodaySwubab: MutableLiveData<ResponseTodaySwubabDto?> = MutableLiveData()
    val getTodaySwubab: MutableLiveData<ResponseTodaySwubabDto?> = _getTodaySwubab

    fun getTodaySwubab(time: String) = viewModelScope.launch {
        runCatching {
            ApiPool.getTodaySwubab.getTodayMenu(time)
        }.fold(
            {
                _getTodaySwubab.value = it.data
            }, {
                Timber.d(it.message)
            }
        )
    }
}