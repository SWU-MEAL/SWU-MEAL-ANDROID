package com.swubab.presentation.today

import android.os.Bundle
import android.view.View
import com.swubab.R
import com.swubab.coreui.base.BindingFragment
import com.swubab.databinding.FragmentTodaySwubabBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TodaySwubabFragment :
    com.swubab.coreui.base.BindingFragment<FragmentTodaySwubabBinding>(R.layout.fragment_today_swubab) {


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

        // tab 제목 text 설정
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()

        // 초기 제목과 시간 text 설정
        binding.tvTodaySwubabSubTitle.setText(R.string.today_swubab_breakfast_title)
        binding.tvTodaySwubabTime.setText(R.string.today_swubab_breakfast_time)

        // tab 선택에 따른 제목과 시간 text 설정
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                text_change_by_tab(tab?.text.toString())
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun text_change_by_tab(title: String){
        when(title) {
            tabTitleArray[0] -> {
                binding.tvTodaySwubabSubTitle.setText(R.string.today_swubab_breakfast_title)
                binding.tvTodaySwubabTime.setText(R.string.today_swubab_breakfast_time)
            }
            tabTitleArray[1] -> {
                binding.tvTodaySwubabSubTitle.setText(R.string.today_swubab_launch_title)
                binding.tvTodaySwubabTime.setText(R.string.today_swubab_launch_time)
            }
            tabTitleArray[2] -> {
                binding.tvTodaySwubabSubTitle.setText(R.string.today_swubab_dinner_title)
                binding.tvTodaySwubabTime.setText(R.string.today_swubab_dinner_time)
            }
        }
    }

}