package dev.matt2393.carreterasnex.solucion

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dev.matt2393.carreterasnex.R
import dev.matt2393.carreterasnex.databinding.ActivitySolucionBinding
import dev.matt2393.carreterasnex.solucion.calculos.CalculosFragment
import dev.matt2393.carreterasnex.solucion.home.HomeFragment
import dev.matt2393.carreterasnex.solucion.reporte.ReporteFragment

class SolucionActivity : AppCompatActivity() {

    private val viewModel: SolucionViewModel by viewModels()
    private lateinit var binding: ActivitySolucionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySolucionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        changeFrag(HomeFragment())

        binding.navView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.menu_datos -> changeFrag(HomeFragment())
                R.id.menu_calculos -> changeFrag(CalculosFragment())
                R.id.menu_reporte -> changeFrag(ReporteFragment())
            }
            true
        }


    }
    private fun changeFrag(fr: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerSolucion, fr)
            .commit()
    }
}