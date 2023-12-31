package com.swubab.presentation.today

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class TodayViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return BreakfastFragment()
            1 -> return LaunchFragment()
            2 -> return DinnerFragment()
        }

        return BreakfastFragment()
    }

    companion object {
        private const val NUM_TABS = 3
    }
}