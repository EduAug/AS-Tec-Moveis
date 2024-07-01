package com.example.as_tec_moveis

import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.as_tec_moveis.commons.configureToolbar
import com.example.as_tec_moveis.data.Pokemon
import com.example.as_tec_moveis.databinding.FragmentDetailsBinding

class DetailsActivity: AppCompatActivity() {
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.bar)

        binding= FragmentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        configureToolbar("Details",true)

        val prodBund = if (Build.VERSION.SDK_INT >= 33){
            intent?.getSerializableExtra("data", Pokemon::class.java)
        }else{
            intent?.getSerializableExtra("data") as? Pokemon
        }

        prodBund?.let {
                product -> binding.pkmn = product
        }
    }
}