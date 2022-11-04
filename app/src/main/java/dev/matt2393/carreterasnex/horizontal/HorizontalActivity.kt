package dev.matt2393.carreterasnex.horizontal

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dev.matt2393.carreterasnex.R
import dev.matt2393.carreterasnex.databinding.ActivityHorizontalBinding

class HorizontalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHorizontalBinding
    private val vm: VMHorizontal by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHorizontalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeFrag(HorizontalHomeFragment())

        binding.navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_datos -> changeFrag(HorizontalHomeFragment())
                R.id.menu_calculos -> changeFrag(HorizontalCalcsFragment())
                R.id.menu_reporte -> changeFrag(HorizontalReportFragment())
            }
            true
        }
    }

    private fun changeFrag(fr: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fr)
            .commit()
    }
}