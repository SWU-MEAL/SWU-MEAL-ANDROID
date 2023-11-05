package com.swubab.presentation.today

import android.os.Bundle
import android.util.Log
import android.view.View
import com.swubab.R
import com.swubab.coreui.base.BindingFragment
import com.swubab.data.ApiPool
import com.swubab.data.dto.response.ResponseTodaySwubabDto
import com.swubab.databinding.FragmentDinnerBinding
import retrofit2.Call
import retrofit2.Response


class DinnerFragment :
    com.swubab.coreui.base.BindingFragment<FragmentDinnerBinding>(R.layout.fragment_dinner) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMenuApi()
    }

    private fun getMenuApi() {
        ApiPool.getTodaySwubab.getTodayMenu("d").enqueue(
            object : retrofit2.Callback<ResponseTodaySwubabDto> {
                override fun onResponse(
                    call: Call<ResponseTodaySwubabDto>, response: Response<ResponseTodaySwubabDto>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { it ->
                            if (it.code == 200) {
                                val data = it.data
                                val result = data?.result?.get(0)
                                if (result?.items != null) {
                                    binding.layoutTodaySwubabDinnerBlank.layoutEmpty.visibility =
                                        View.GONE
                                    var content = ""
                                    for (i in 0..result.items!!.size - 1) {
                                        content = content + result.items!!.get(i).toString() + "\n"
                                    }
                                    binding.tvTodaySwubabDinnerContent.setText(content)
                                } else {
                                    binding.layoutTodaySwubabDinnerBlank.layoutEmpty.visibility =
                                        View.VISIBLE
                                }
                            }
                        }
                    } else {
                        Log.d("error", "실패한 응답")
                    }
                }

                override fun onFailure(call: Call<ResponseTodaySwubabDto>, t: Throwable) {
                    t.message?.let { Log.d("error", it) } ?: "서버통신 실패"
                }
            })
    }

}
