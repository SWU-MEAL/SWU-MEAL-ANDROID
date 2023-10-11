package com.example.swubab.presentation.today

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.swubab.R
import com.example.swubab.databinding.FragmentTodaySwubabBinding

private const val NUM_TABS = 3

private val pageTitleArray = arrayOf(
    "아침 밥상은?",
    "점심 밥상은?",
    "저녁 밥상은?"
)

private val pageTimeArray = arrayOf(
    "아침시간 : 7:30~9:00",
    "점심시간 : 11:30 ~13:30",
    "저녁시간 : 17:10 ~18:30"
)



class TodayViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fragmentManager, lifecycle){
    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return BreakfastFragment()
            1 -> return LaunchFragment()
            2 -> return DinnerFragment()
        }

        // binding.tvTodaySwubabSubTitle.text = pageTitleArray[position]
        // binding.tvTodaySwubabTime.text = pageTimeArray[position]
        return BreakfastFragment()
    }
}