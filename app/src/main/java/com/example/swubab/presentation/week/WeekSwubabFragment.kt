package com.example.swubab.presentation.week

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.swubab.R
import com.example.swubab.coreui.base.BindingFragment
import com.example.swubab.coreui.fragment.toast
import com.example.swubab.databinding.FragmentWeekSwubabBinding
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class WeekSwubabFragment :
    BindingFragment<FragmentWeekSwubabBinding>(R.layout.fragment_week_swubab) {

    private val viewModel by viewModels<WeekSwubabViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        setOnClick()
        observe()
        //viewModel.getWeekSwubab("2023-10-21")
    }

    private fun setToday(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ISO_DATE
        val formatted = current.format(formatter)
        return formatted
    }

    private fun setOnClick() {
        with(binding.layoutWeekCalender) {
            tvCalenderWeeklyMon.setOnClickListener { handleDayClick(Calendar.MONDAY) }
            tvCalenderWeeklyTue.setOnClickListener { handleDayClick(Calendar.TUESDAY) }
            tvCalenderWeeklyWed.setOnClickListener { handleDayClick(Calendar.WEDNESDAY) }
            tvCalenderWeeklyThu.setOnClickListener { handleDayClick(Calendar.THURSDAY) }
            tvCalenderWeeklyFri.setOnClickListener { handleDayClick(Calendar.FRIDAY) }
        }
    }

    private fun handleDayClick(clickedDay: Int) {
        val todayCalendar = Calendar.getInstance()
        val dayOfTheWeek = todayCalendar.get(Calendar.DAY_OF_WEEK)
        val daysToAdd = clickedDay - dayOfTheWeek
        todayCalendar.add(Calendar.DAY_OF_MONTH, daysToAdd)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val selectedDate = dateFormat.format(todayCalendar.time)

        val dayTextView = when (clickedDay) {
            Calendar.MONDAY -> binding.layoutWeekCalender.tvCalenderWeeklyMon
            Calendar.TUESDAY -> binding.layoutWeekCalender.tvCalenderWeeklyTue
            Calendar.WEDNESDAY -> binding.layoutWeekCalender.tvCalenderWeeklyWed
            Calendar.THURSDAY -> binding.layoutWeekCalender.tvCalenderWeeklyThu
            else -> binding.layoutWeekCalender.tvCalenderWeeklyFri
        }

        if (clickedDay == dayOfTheWeek) {
            dayTextView.text = "오늘"
        }

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
            menuText.append("\n")
        }
        return menuText.toString()
    }
}