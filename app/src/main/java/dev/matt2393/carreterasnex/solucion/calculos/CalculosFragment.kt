package dev.matt2393.carreterasnex.solucion.calculos

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dev.matt2393.carreterasnex.databinding.FragmentCalculosBinding
import dev.matt2393.carreterasnex.solucion.SolucionViewModel

class CalculosFragment: Fragment() {

    private val viewModel: SolucionViewModel by activityViewModels()
    private var binding: FragmentCalculosBinding? = null
    private var adapter: CalculosAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalculosBinding.inflate(inflater, container, false)
        adapter = CalculosAdapter()
        val lm = LinearLayoutManager(requireContext())
        binding?.recyclerCalculos?.layoutManager = lm
        binding?.recyclerCalculos?.adapter = adapter
        binding?.recyclerCalculos?.addItemDecoration(DividerItemDecoration(requireContext(), lm.orientation))

        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        initObservers()
    }

    override fun onStop() {
        super.onStop()
        removeObservers()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObservers() {
        viewModel.allSol.observe(this) {
            adapter?.solucion = it.solucion
            adapter?.notifyDataSetChanged()
        }
    }
    private fun removeObservers() {
        viewModel.allSol.removeObservers(this)
    }
}