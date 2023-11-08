package com.swubab.presentation.today

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.swubab.R
import com.swubab.coreui.base.BindingFragment
import com.swubab.databinding.FragmentBreakfastBinding


class BreakfastFragment : BindingFragment<FragmentBreakfastBinding>(R.layout.fragment_breakfast) {

    private val viewModel by viewModels<TodaySwubabViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTodaySwubab(BREAKFAST)
        observe()
    }

    private fun observe() {
        viewModel.getTodaySwubab.observe(viewLifecycleOwner) { response ->
            response?.let {
                val result = it.result[0]
                binding.layoutTodaySwubabBreakfastBlank.layoutEmpty.visibility =
                    View.GONE
                var content = ""
                for (i in 0 until result.items.size) {
                    content =
                        content + result.items[i].toString() + NEW_LINE
                }
                binding.tvTodaySwubabBreakfastContent.text = content

            } ?: run {
                binding.layoutTodaySwubabBreakfastBlank.layoutEmpty.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        private const val BREAKFAST = "b"
        private const val NEW_LINE = "\n"
    }
}
