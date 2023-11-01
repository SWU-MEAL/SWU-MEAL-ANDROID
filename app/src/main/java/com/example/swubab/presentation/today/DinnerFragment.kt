package com.example.swubab.presentation.today

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.swubab.R
import com.example.swubab.coreui.base.BindingFragment
import com.example.swubab.data.ApiPool
import com.example.swubab.data.TodaySwubabDto
import com.example.swubab.databinding.FragmentDinnerBinding
import retrofit2.Call
import retrofit2.Response


class DinnerFragment  :
    BindingFragment<FragmentDinnerBinding>(R.layout.fragment_dinner){


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getTodoApi()
    }

    private fun initView() {

    }

    private fun getTodoApi() {
        ApiPool.getTodaySwubab.getTodayMenu("d").enqueue(
            object : retrofit2.Callback<TodaySwubabDto> {
                override fun onResponse(
                    call: Call<TodaySwubabDto>, response: Response<TodaySwubabDto>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { it->
                            if(it.code == 200){
                                var result = it.data.result?.get(0)
                                if (result?.items != null) {
                                    binding.ivTodaySwubabDinnerBlank.layoutEmpty.visibility  =View.GONE
                                    var content = ""
                                    for(i in 0..result.items!!.size -1){
                                        content = content + result.items!!.get(i).toString() + "\n"
                                    }
                                    binding.tvTodaySwubabDinnerContent.setText(content)
                                }
                                else{
                                    binding.ivTodaySwubabDinnerBlank.layoutEmpty.visibility  =View.VISIBLE
                                }
                            }
                        }
                    } else {
                        Log.d("error", "실패한 응답")
                    }
                }
                override fun onFailure(call: Call<TodaySwubabDto> , t: Throwable) {
                    t.message?.let { Log.d("error", it) } ?: "서버통신 실패"
                }
            })
    }

}
