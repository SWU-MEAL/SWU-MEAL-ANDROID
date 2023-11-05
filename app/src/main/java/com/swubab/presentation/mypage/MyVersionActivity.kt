package com.swubab.presentation.mypage

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import com.swubab.R
import com.swubab.databinding.ActivityMyVersionBinding
import com.swubab.coreui.base.BindingActivity


class MyVersionActivity : BindingActivity<ActivityMyVersionBinding>(R.layout.activity_my_version) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDarkMode()
        initView()
    }

    override fun initView() {
        //toolbar 설정
        this.setSupportActionBar(findViewById(R.id.tb_swubab_my_version_toolbar))
        supportActionBar!!.setDisplayShowTitleEnabled(false) // 타이틀
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}