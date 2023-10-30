package com.example.swubab.presentation.week

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.swubab.R
import com.example.swubab.coreui.base.BindingFragment
import com.example.swubab.coreui.fragment.toast
import com.example.swubab.databinding.FragmentWeekSwubabBinding
import com.google.android.material.tabs.TabLayout
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class WeekSwubabFragment :
    BindingFragment<FragmentWeekSwubabBinding>(R.layout.fragment_week_swubab) {

    private val viewModel by viewModels<WeekSwubabViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        viewModel.getWeekSwubab(dateFormat.format(Date()))
        setTodayTab()
        setClickEventOnTabLayoutCalenderWeekly()
        observe()
        toast(dateFormat.format(Date()))
    }

    private fun setTodayTab() {
        with(binding.layoutWeekCalender.tablayoutCalenderWeekly) {
            val selectedTab = when (todayCalendar.get(Calendar.DAY_OF_WEEK)) {
                Calendar.MONDAY -> getTabAt(0)
                Calendar.TUESDAY -> getTabAt(1)
                Calendar.WEDNESDAY -> getTabAt(2)
                Calendar.THURSDAY -> getTabAt(3)
                Calendar.FRIDAY -> getTabAt(4)
                else -> null
            }
            selectedTab?.select()
            selectedTab?.text = "오늘"
        }
    }

    private fun setClickEventOnTabLayoutCalenderWeekly() {
        with(binding.layoutWeekCalender) {
            tablayoutCalenderWeekly.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        0 -> handleDayClick(Calendar.MONDAY)
                        1 -> handleDayClick(Calendar.TUESDAY)
                        2 -> handleDayClick(Calendar.WEDNESDAY)
                        3 -> handleDayClick(Calendar.THURSDAY)
                        4 -> handleDayClick(Calendar.FRIDAY)
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }
    }

    private fun handleDayClick(clickedDay: Int) {
        val dayOfTheWeek = todayCalendar.get(Calendar.DAY_OF_WEEK)
        val daysToAdd = clickedDay - dayOfTheWeek
        todayCalendar.add(Calendar.DAY_OF_MONTH, daysToAdd)
        val selectedDate = dateFormat.format(todayCalendar.time)
        toast(dayOfTheWeek.toString())
        viewModel.getWeekSwubab(selectedDate)
        toast(selectedDate)
    }

    private fun observe() {
        viewModel.getWeekSwubab.observe(viewLifecycleOwner) { response ->
            response?.let {
                val morningItems = it.result[0].menuList[0].items
                val lunchItems = it.result[1].menuList[0].items
                val dinnerItems = it.result[2].menuList[0].items

                val morningMenu = buildMenuText(morningItems)
                val lunchMenu = buildMenuText(lunchItems)
                val dinnerMenu = buildMenuText(dinnerItems)

                with(binding) {
                    tvWeekMorningDetail.text = morningMenu
                    tvWeekLunchDetail.text = lunchMenu
                    tvWeekDinnerDetail.text = dinnerMenu
                }
            } ?: run {
                with(binding.layoutMorningEmpty) {
                    emptyIcon.visibility = View.VISIBLE
                    emptyText.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun buildMenuText(items: List<String>): String {
        val menuText = StringBuilder()
        for (item in items) {
            menuText.append(item)
            if (item != items.last()) menuText.append("\n")
        }
        return menuText.toString()
    }

    companion object {
        private val todayCalendar = Calendar.getInstance()
        private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    }
}