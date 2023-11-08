package com.swubab.presentation.today

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.swubab.R
import com.swubab.coreui.base.BindingFragment
import com.swubab.databinding.FragmentDinnerBinding


class DinnerFragment : BindingFragment<FragmentDinnerBinding>(R.layout.fragment_dinner) {

    private val viewModel by viewModels<TodaySwubabViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTodaySwubab(DINNER)
        observe()
    }

    private fun observe() {
        viewModel.getTodaySwubab.observe(viewLifecycleOwner) { response ->
            response?.let {
                val result = it.result[0]
                binding.layoutTodaySwubabDinnerBlank.layoutEmpty.visibility =
                    View.GONE
                var content = ""
                for (i in 0 until result.items.size) {
                    content = content + result.items[i] + NEW_LINE
                }
                binding.tvTodaySwubabDinnerContent.text = content
            } ?: run {
                binding.layoutTodaySwubabDinnerBlank.layoutEmpty.visibility =
                    View.VISIBLE
            }
        }
    }

    companion object {
        private const val DINNER = "d"
        private const val NEW_LINE = "\n"
    }
}
