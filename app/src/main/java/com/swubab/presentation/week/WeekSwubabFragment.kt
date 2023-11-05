package com.swubab.presentation.week

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import com.swubab.R
import com.swubab.databinding.FragmentWeekSwubabBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class WeekSwubabFragment :
    com.swubab.coreui.base.BindingFragment<FragmentWeekSwubabBinding>(R.layout.fragment_week_swubab) {

    private val viewModel by viewModels<WeekSwubabViewModel>()
    private val dateFormat = SimpleDateFormat(DAY_FORMAT, Locale.getDefault())
    lateinit var lunchKoreaMenuText: String
    lateinit var lunchJapaneseMenuText: String
    lateinit var lunchSnackMenuText: String
    lateinit var lunchStaffMenuText: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        viewModel.getWeekSwubab(dateFormat.format(Date()))
        setTodayTab()
        checkWeekday()
        observe()
    }

    private fun checkWeekday() {
        val todayCalendar = Calendar.getInstance()
        if (todayCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            with(binding) {
                layoutMorningEmpty.layoutEmpty.visibility = View.VISIBLE
                layoutLunchEmpty.layoutEmpty.visibility = View.VISIBLE
                layoutDinnerEmpty.layoutEmpty.visibility = View.VISIBLE
            }
        } else {
            setClickEventOnTabLayoutCalenderWeekly()
        }
    }

    private fun setTodayTab() {
        with(binding.layoutWeekCalender.tablayoutCalenderWeekly) {
            val todayCalendar = Calendar.getInstance()
            val selectedTab = when (todayCalendar.get(Calendar.DAY_OF_WEEK)) {
                Calendar.MONDAY -> getTabAt(0)
                Calendar.TUESDAY -> getTabAt(1)
                Calendar.WEDNESDAY -> getTabAt(2)
                Calendar.THURSDAY -> getTabAt(3)
                Calendar.FRIDAY -> getTabAt(4)
                else -> null
            }
            selectedTab?.let {
                it.select()
                it.text = context.getString(R.string.text_week_swubab_today)
            }
        }
    }

    private fun setClickEventOnTabLayoutCalenderWeekly() {
        with(binding.layoutWeekCalender) {
            tablayoutCalenderWeekly.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val clickedDay = when (tab?.position) {
                        0 -> Calendar.MONDAY
                        1 -> Calendar.TUESDAY
                        2 -> Calendar.WEDNESDAY
                        3 -> Calendar.THURSDAY
                        4 -> Calendar.FRIDAY
                        else -> -1
                    }
                    handleDayClick(clickedDay)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }
    }

    private fun handleDayClick(clickedDay: Int) {
        val todayCalendar = Calendar.getInstance()
        val dayOfTheWeek = todayCalendar.get(Calendar.DAY_OF_WEEK)
        val daysToAdd = clickedDay - dayOfTheWeek
        todayCalendar.add(Calendar.DAY_OF_MONTH, daysToAdd)
        val selectedDate = dateFormat.format(todayCalendar.time)
        viewModel.getWeekSwubab(selectedDate)
    }

    private fun observe() {
        viewModel.getWeekSwubab.observe(viewLifecycleOwner) { response ->
            response?.let {
                with(binding) {
                    layoutMorningEmpty.layoutEmpty.visibility = View.INVISIBLE
                    layoutLunchEmpty.layoutEmpty.visibility = View.INVISIBLE
                    layoutDinnerEmpty.layoutEmpty.visibility = View.INVISIBLE
                }

                val morningMenuText = buildMenuText(it.result[0].menuList[0].items)
                lunchKoreaMenuText = buildMenuText(it.result[1].menuList[0].items)
                lunchJapaneseMenuText = buildMenuText(it.result[1].menuList[1].items)
                lunchSnackMenuText = buildMenuText(it.result[1].menuList[2].items)
                lunchStaffMenuText = buildMenuText(it.result[1].menuList[3].items)
                val dinnerMenuText = buildMenuText(it.result[2].menuList[0].items)

                with(binding) {
                    val selectedMenuText =
                        when (tablayoutWeekSwubabLunchCornerLabel.selectedTabPosition) {
                            0 -> lunchKoreaMenuText
                            1 -> lunchJapaneseMenuText
                            2 -> lunchSnackMenuText
                            3 -> lunchStaffMenuText
                            else -> ""
                        }
                    tvWeekMorningDetail.text = morningMenuText
                    tvWeekLunchDetail.text = selectedMenuText
                    tvWeekDinnerDetail.text = dinnerMenuText
                }

                setClickEventOnTabLayoutLunchCorner()
            } ?: run {
                with(binding) {
                    tvWeekMorningDetail.text = DUMMY
                    tvWeekLunchDetail.text = DUMMY
                    tvWeekDinnerDetail.text = DUMMY
                    lunchKoreaMenuText = DUMMY
                    lunchJapaneseMenuText = DUMMY
                    lunchSnackMenuText = DUMMY
                    lunchStaffMenuText = DUMMY
                    layoutMorningEmpty.layoutEmpty.visibility = View.VISIBLE
                    layoutLunchEmpty.layoutEmpty.visibility = View.VISIBLE
                    layoutDinnerEmpty.layoutEmpty.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setClickEventOnTabLayoutLunchCorner() {
        with(binding) {
            tablayoutWeekSwubabLunchCornerLabel.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        0 -> tvWeekLunchDetail.text = lunchKoreaMenuText
                        1 -> tvWeekLunchDetail.text = lunchJapaneseMenuText
                        2 -> tvWeekLunchDetail.text = lunchSnackMenuText
                        3 -> tvWeekLunchDetail.text = lunchStaffMenuText
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }
    }

    private fun buildMenuText(items: List<String>): String {
        val menuText = StringBuilder()
        for (item in items) {
            menuText.append(item)
            if (item != items.last()) menuText.append(NEW_LINE)
        }
        return menuText.toString()
    }

    companion object {
        private const val DAY_FORMAT = "yyyy-MM-dd"
        private const val NEW_LINE = "\n"
        private const val DUMMY = "\n\n\n\n"
    }
}