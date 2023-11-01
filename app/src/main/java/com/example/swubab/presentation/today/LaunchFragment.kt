package com.example.swubab.presentation.today

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.swubab.R
import com.example.swubab.coreui.base.BindingFragment
import com.example.swubab.data.ApiPool
import com.example.swubab.data.dto.response.ResponseTodaySwubabDto
import com.example.swubab.databinding.FragmentLaunchBinding
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber

class LaunchFragment :
    BindingFragment<FragmentLaunchBinding>(R.layout.fragment_launch) {

    var menu_list: HashMap<Int, String> = hashMapOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMenuApi()
        radio_button_checked()
    }

    private fun radio_button_checked() {
        binding.rgTodaySwubabConner.setOnCheckedChangeListener { group, checkId ->
            when (checkId) {
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

    private fun getMenuApi() {
        menu_list.clear()
        ApiPool.getTodaySwubab.getTodayMenu("l").enqueue(
            object : retrofit2.Callback<ResponseTodaySwubabDto> {
                override fun onResponse(
                    call: Call<ResponseTodaySwubabDto>, response: Response<ResponseTodaySwubabDto>
                ) {

                    if (response.isSuccessful) {
                        response.body()?.let { it ->
                            if (it.code == 200) {
                                val data = it.data
                                if (data == null) {
                                    binding.layoutTodaySwubabLaunchBlank.layoutEmpty.visibility = View.VISIBLE

                                } else {
                                    val result = data?.result
                                    if (result != null) {
                                        binding.layoutTodaySwubabLaunchBlank.layoutEmpty.visibility =
                                            View.INVISIBLE
                                        for (num in 0..result.size - 1) {
                                            var content = ""
                                            for (i in 0..(result[num]!!.items!!.size - 1)) {
                                                content = content + result[num]!!.items?.get(i)
                                                    .toString() + "\n"
                                            }

                                            if ((result[num]?.type).equals("교직원")) {
                                                menu_list.put(0, content)
                                            }
                                            when (result[num]?.corner) {
                                                "일품" -> {
                                                    menu_list.put(2, content)
                                                }

                                                "한식" -> {
                                                    menu_list.put(1, content)
                                                    binding.tvTodaySwubabLaunchContent.setText(
                                                        menu_list.get(1)
                                                    )
                                                }

                                                "스낵" -> {
                                                    menu_list.put(3, content)
                                                }
                                            }
                                        }
                                    }
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
