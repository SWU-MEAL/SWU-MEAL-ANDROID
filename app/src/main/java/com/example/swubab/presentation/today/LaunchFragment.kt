package com.example.swubab.presentation.today

import android.os.Bundle
import android.util.Log
import android.view.View
import coil.memory.MemoryCache
import com.example.swubab.R
import com.example.swubab.coreui.base.BindingFragment
import com.example.swubab.data.ApiPool
import com.example.swubab.data.TodaySwubabDto
import com.example.swubab.databinding.FragmentLaunchBinding
import retrofit2.Call
import retrofit2.Response

class LaunchFragment :
    BindingFragment<FragmentLaunchBinding>(R.layout.fragment_launch){

    var menu_list: HashMap<Int, String> = hashMapOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getTodoApi()
        radio_button_checked()
    }

    private fun radio_button_checked() {
        binding.rgTodaySwubabConner.setOnCheckedChangeListener{ group ,checkId ->
            when(checkId){
                R.id.rb_today_swubab_conner_snack -> {
                    binding.tvTodaySwubabLaunchContent.setText(menu_list[3])
                }
                R.id.rb_today_swubab_conner_staff -> {
                    binding.tvTodaySwubabLaunchContent.setText(menu_list[0])
                }
                R.id.rb_today_swubab_conner_korea -> {
                    binding.tvTodaySwubabLaunchContent.setText(menu_list[1])
                }
                R.id.rb_today_swubab_conner_first -> {
                    binding.tvTodaySwubabLaunchContent.setText(menu_list[2])
                }
            }

        }
    }

    private fun getTodoApi() {
        ApiPool.getTodaySwubab.getTodayMenu("l").enqueue(
            object : retrofit2.Callback<TodaySwubabDto> {
                override fun onResponse(
                    call: Call<TodaySwubabDto>, response: Response<TodaySwubabDto>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { it->
                            if(it.code == 200){
                                var result = it.data?.result
                                if (result != null) {
                                    binding.ivTodaySwubabLaunchBlank.visibility =View.GONE
                                    var num = 0
                                    Log.d("Aaa", result.toString())
                                    for(num in 0..result.size-1) {
                                        var content = ""
                                        for(i in 0..(result[num]!!.items!!.size - 1)){
                                            content = content + result[num]!!.items?.get(i).toString() + "\n"
                                        }

                                        if((result[num]?.type).equals("교직원")){
                                            menu_list.put(0, content)
                                        }
                                        when(result[num]?.corner){
                                            "일품" -> {
                                                menu_list.put(2, content)
                                            }
                                            "한식" -> {
                                                menu_list.put(1, content)
                                                binding.tvTodaySwubabLaunchContent.setText(menu_list.get(1))
                                            }
                                            "스낵" -> {
                                                menu_list.put(3, content)
                                            }
                                        }
                                    }
                                }
                                else{
                                    binding.ivTodaySwubabLaunchBlank.visibility =View.VISIBLE
                                }
                            }
                        }
                    } else {
                        Log.d("error", "실패한 응답")
                    }
                }
                override fun onFailure(call: Call<TodaySwubabDto>, t: Throwable) {
                    t.message?.let { Log.d("error", it) } ?: "서버통신 실패"
                }
            })
    }
}