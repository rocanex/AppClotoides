package dev.matt2393.carreterasnex.clotoide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dev.matt2393.carreterasnex.R
import dev.matt2393.carreterasnex.databinding.FragmentClotoideCalcsBinding
import dev.matt2393.carreterasnex.horizontal.CalculatesAdapter

class ClotoideCalcsFragment : Fragment() {

    private lateinit var binding: FragmentClotoideCalcsBinding
    private val viewModel: VMClotoide by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClotoideCalcsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CalculatesAdapter(viewModel.getListCalculates())

        binding.rvData.layoutManager = LinearLayoutManager(requireContext())
        binding.rvData.adapter = adapter
    }
}