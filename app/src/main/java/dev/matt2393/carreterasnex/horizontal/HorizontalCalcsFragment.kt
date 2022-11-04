package dev.matt2393.carreterasnex.horizontal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.matt2393.carreterasnex.customFormat
import dev.matt2393.carreterasnex.databinding.FragmentHorizontalCalcsBinding
import dev.matt2393.carreterasnex.databinding.ItemCalculosBinding

class HorizontalCalcsFragment : Fragment() {

    private lateinit var binding: FragmentHorizontalCalcsBinding
    private val viewModel: VMHorizontal by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHorizontalCalcsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // t, g, lc, e, f, cl, ic, fc
        Log.e("TAG", "onViewCreated: ${viewModel.calcT().customFormat()}")
        val adapter = CalculatesAdapter(viewModel.getListCalculates())

        binding.rvData.layoutManager = LinearLayoutManager(requireContext())
        binding.rvData.adapter = adapter
    }
}


class CalculatesAdapter(private val calculates: List<CalculateModel>) :
    RecyclerView.Adapter<CalculatesAdapter.MyVH>() {

    inner class MyVH(val item: ItemCalculosBinding) : RecyclerView.ViewHolder(item.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        val lli = LayoutInflater.from(parent.context)
        return MyVH(ItemCalculosBinding.inflate(lli, parent, false))
    }

    override fun onBindViewHolder(holder: MyVH, position: Int) {
        val calc = calculates[position]
        holder.item.textTitItemCalculos.text = calc.text
        holder.item.textItemCalculos.text = calc.value
    }

    override fun getItemCount(): Int = calculates.size
}