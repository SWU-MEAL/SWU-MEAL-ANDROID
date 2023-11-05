package com.swubab.presentation.mypage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.swubab.R
import com.swubab.coreui.base.BindingFragment
import com.swubab.databinding.FragmentMyPageBinding

class MypageFragment : com.swubab.coreui.base.BindingFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvSwubabMyVersion.setOnClickListener {
            val intent = Intent(context, MyVersionActivity::class.java)
            startActivity(intent)
        }
        setEventOnMyTerms()
        setEventOnPrivacyPolicy()
    }

    private fun setEventOnMyTerms(){
        binding.tvSwubabMyTerms.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://snapdragon-failing-ab8.notion.site/a1fdc49572b04085b24b18747f2ae006?pvs=4")
            )
            startActivity(intent)
        }
    }

    private fun setEventOnPrivacyPolicy(){
        binding.tvSwubabMyPersonalInformation.setOnClickListener {
            val urlIntentPrivacyPolicy = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://snapdragon-failing-ab8.notion.site/a62a59b497bf4de88cd33ad114e18a0b?pvs=4")
            )
            startActivity(urlIntentPrivacyPolicy)
        }
    }


}