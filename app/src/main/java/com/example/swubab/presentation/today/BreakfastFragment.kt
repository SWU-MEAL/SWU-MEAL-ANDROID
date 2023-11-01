package com.example.swubab.presentation.today

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.swubab.R
import com.example.swubab.coreui.base.BindingFragment
import com.example.swubab.data.ApiPool
import com.example.swubab.data.dto.response.ResponseTodaySwubabDto
import com.example.swubab.databinding.FragmentBreakfastBinding
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber


class BreakfastFragment :
    BindingFragment<FragmentBreakfastBinding>(R.layout.fragment_breakfast) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMenuApi()
    }


    private fun getMenuApi() {
        ApiPool.getTodaySwubab.getTodayMenu("b").enqueue(
            object : retrofit2.Callback<ResponseTodaySwubabDto> {
                override fun onResponse(
                    call: Call<ResponseTodaySwubabDto>, response: Response<ResponseTodaySwubabDto>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { it ->
                            if (it.code == 200) {
                                val data = it.data
                                if (it.data == null) {
                                    binding.layoutTodaySwubabBreakfastBlank.layoutEmpty.visibility =
                                        View.VISIBLE
                                } else {
                                    val result = data?.result?.get(0)
                                    if (result?.items != null) {
                                        binding.layoutTodaySwubabBreakfastBlank.layoutEmpty.visibility =
                                            View.GONE
                                        var content = ""
                                        for (i in 0..result.items.size - 1) {
                                            content =
                                                content + result.items.get(i).toString() + "\n"
                                        }
                                        binding.tvTodaySwubabBreakfastContent.setText(content)
                                    }
                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseTodaySwubabDto>, t: Throwable) {
                    t.message?.let { Log.d("error", it) } ?: "서버통신 실패"
                }
            })
    }


}
