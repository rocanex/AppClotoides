package dev.matt2393.carreterasnex.horizontal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dev.matt2393.carreterasnex.databinding.FragmentHorizontalHomeBinding
import dev.matt2393.carreterasnex.model.DMSModel

class HorizontalHomeFragment : Fragment() {

    private lateinit var binding: FragmentHorizontalHomeBinding
    private val viewModel: VMHorizontal by activityViewModels()

    lateinit var signo: String
    lateinit var decima : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHorizontalHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            buttonSolucion.setOnClickListener {
                viewModel.loadData(
                    progP1 = (etProgPI1.text.toString().toInt() * 1000.0)
                            + etProgPI2.text.toString().toDouble(),
                    delta = DMSModel(
                        etDegree.text.toString().toInt(),
                        etMin.text.toString().toInt(),
                        etSeconds.text.toString().toDouble()
                    ),
                    r = etR.text.toString().toDouble(),
                    cu = etCu.text.toString().toInt(),
                    az = DMSModel(
                        etAzDegree.text.toString().toInt(),
                        etAzMinute.text.toString().toInt(),
                        etAzSeconds.text.toString().toDouble()
                    ),
                    n = etN.text.toString().toDouble(),
                    e = etE.text.toString().toDouble(),
                )
                viewModel.genDeflexionTable(decima)
                viewModel.genCoordenadasTable(signo, decima)
            }

        }

        with(binding){
            toggleSigno.setText("+")
            signo = "+"
            toggleSigno.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    toggleSigno.setText("-")
                    signo = "-"
                } else {
                    toggleSigno.setText("+")
                    signo = "+"
                }
            })
        }

        with(binding){
            toggleDecima.setText("Centena")
            decima = "Centena"
            toggleDecima.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    toggleDecima.setText("Decima")
                    decima = "Decima"
                } else {
                    toggleDecima.setText("Centena")
                    decima = "Centena"
                }
            })
        }

    }
}