package com.swubab.presentation.today

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.swubab.R
import com.swubab.coreui.base.BindingFragment
import com.swubab.databinding.FragmentLaunchBinding

class LaunchFragment : BindingFragment<FragmentLaunchBinding>(R.layout.fragment_launch) {

    private val viewModel by viewModels<TodaySwubabViewModel>()
    private var menuList: HashMap<Int, String> = hashMapOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTodaySwubab(LAUNCH)
        observe()
        radioButtonChecked()
    }

    private fun radioButtonChecked() {
        binding.rgTodaySwubabConner.setOnCheckedChangeListener { _, checkId ->
            when (checkId) {
                R.id.rb_today_swubab_conner_snack -> {
                    binding.tvTodaySwubabLaunchContent.text = menuList[3]
                }

                R.id.rb_today_swubab_conner_staff -> {
                    binding.tvTodaySwubabLaunchContent.text = menuList[0]
                }

                R.id.rb_today_swubab_conner_korea -> {
                    binding.tvTodaySwubabLaunchContent.text = menuList[1]
                }

                R.id.rb_today_swubab_conner_first -> {
                    binding.tvTodaySwubabLaunchContent.text = menuList[2]
                }
            }

        }
    }

    private fun observe() {
        menuList.clear()
        viewModel.getTodaySwubab.observe(viewLifecycleOwner) { response ->
            response?.let {
                val result = it.result
                binding.layoutTodaySwubabLaunchBlank.layoutEmpty.visibility =
                    View.INVISIBLE
                for (num in result.indices) {
                    var content = ""
                    for (i in 0 until result[num].items.size) {
                        content = content + result[num].items[i] + NEW_LINE
                    }

                    if ((result[num].type) == STAFF) {
                        menuList[0] = content
                    }
                    when (result[num].corner) {
                        JAPANESE -> {
                            menuList[2] = content
                        }

                        KOREA -> {
                            menuList[1] = content
                            binding.tvTodaySwubabLaunchContent.text = menuList[1]
                        }

                        SNACK -> {
                            menuList[3] = content
                        }
                    }
                }
            } ?: run {
                binding.layoutTodaySwubabLaunchBlank.layoutEmpty.visibility =
                    View.VISIBLE
            }
        }
    }

    companion object {
        private const val LAUNCH = "l"
        private const val NEW_LINE = "\n"
        private const val STAFF = "교직원"
        private const val JAPANESE = "일품"
        private const val KOREA = "한식"
        private const val SNACK = "스낵"
    }
}
