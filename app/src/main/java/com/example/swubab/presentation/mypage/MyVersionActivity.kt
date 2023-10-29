package com.example.swubab.presentation.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.swubab.R
import com.example.swubab.coreui.base.BindingActivity
import com.example.swubab.databinding.ActivityMyVersionBinding

class MyVersionActivity : BindingActivity<ActivityMyVersionBinding>(R.layout.activity_my_version) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
}