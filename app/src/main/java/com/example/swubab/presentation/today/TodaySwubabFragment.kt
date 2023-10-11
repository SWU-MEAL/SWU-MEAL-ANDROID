package com.example.swubab.presentation.today

import android.os.Bundle
import android.view.View
import com.example.swubab.R
import com.example.swubab.coreui.base.BindingFragment
import com.example.swubab.databinding.FragmentTodaySwubabBinding
import com.google.android.material.tabs.TabLayoutMediator

class TodaySwubabFragment :
    BindingFragment<FragmentTodaySwubabBinding>(R.layout.fragment_today_swubab) {

    private val tabTitleArray = arrayOf(
        "조식",
        "중식",
        "석식"
    )



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val viewPager = binding.vpTodaySwubabViewpager
        val tabLayout = binding.tlTodaySwubabTablayout

        viewPager.adapter = TodayViewPagerAdapter(childFragmentManager, lifecycle)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()
    }
}