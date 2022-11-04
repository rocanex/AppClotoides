package dev.matt2393.carreterasnex.clotoide

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dev.matt2393.carreterasnex.R
import dev.matt2393.carreterasnex.databinding.ActivityClotoideBinding

class ClotoideActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClotoideBinding
    private val viewModel: VMClotoide by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClotoideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeFrag(ClotoideHomeFragment())

        binding.navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_datos -> changeFrag(ClotoideHomeFragment())
                R.id.menu_calculos -> changeFrag(ClotoideCalcsFragment())
                R.id.menu_reporte -> changeFrag(ClotoideReportFragment())
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