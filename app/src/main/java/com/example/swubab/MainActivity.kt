package com.example.swubab

import android.os.Bundle
import com.example.swubab.coreui.base.BindingActivity
import com.example.swubab.databinding.ActivityMainBinding

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {

    }

}