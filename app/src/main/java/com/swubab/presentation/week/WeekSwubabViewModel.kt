package com.swubab.presentation.week

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swubab.data.ApiPool
import com.swubab.data.dto.response.ResponseWeekSwuBabDto
import kotlinx.coroutines.launch
import timber.log.Timber

class WeekSwubabViewModel : ViewModel() {
    private val _getWeekSwubab: MutableLiveData<ResponseWeekSwuBabDto?> = MutableLiveData()
    val getWeekSwubab: MutableLiveData<ResponseWeekSwuBabDto?> = _getWeekSwubab

    fun getWeekSwubab(date: String) = viewModelScope.launch {
        runCatching {
            ApiPool.getWeekSwubab.getWeekSwubab(date)
        }.fold(
            {
                _getWeekSwubab.value = it.data
            },
            {
                Timber.d(it.message)
            }
        )
    }
}