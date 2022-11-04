package dev.matt2393.carreterasnex.clotoide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dev.matt2393.carreterasnex.databinding.FragmentClotoideHomeBinding
import dev.matt2393.carreterasnex.model.DMSModel

class ClotoideHomeFragment : Fragment() {

    private lateinit var binding: FragmentClotoideHomeBinding
    private val viewModel: VMClotoide by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClotoideHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            buttonSolucion.setOnClickListener {
                viewModel.loadData(
                    progPI = (etProgPI1.text.toString().toInt() * 1000.0)
                            + etProgPI2.text.toString().toDouble(),
                    vp = binding.etVp.text.toString().toInt(),
                    delta = DMSModel(
                        etDegree.text.toString().toInt(),
                        etMin.text.toString().toInt(),
                        etSeconds.text.toString().toDouble()
                    ),
                    rc = etR.text.toString().toInt(),
                    cu = etCu.text.toString().toInt(),
                    a = binding.etA.text.toString().toDouble(),
                    az1 = DMSModel(
                        etAzDegree.text.toString().toInt(),
                        etAzMinute.text.toString().toInt(),
                        etAzSeconds.text.toString().toDouble()
                    ),
                    az2 = DMSModel(
                        etAz2Degree.text.toString().toInt(),
                        etAz2Minute.text.toString().toInt(),
                        etAz2Seconds.text.toString().toDouble()
                    ),
                    n = etN.text.toString().toDouble(),
                    e = etE.text.toString().toDouble(),
                )
            }
        }
    }

}