package dev.matt2393.carreterasnex.solucion.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.aghajari.graphview.AXGraphOptions
import dev.matt2393.carreterasnex.MyApplication
import dev.matt2393.carreterasnex.databinding.FragmentHomeBinding
import dev.matt2393.carreterasnex.model.Prog
import dev.matt2393.carreterasnex.solucion.SolucionViewModel
//import dev.matt2393.carreterasnex.MyApplication as glob

class HomeFragment : Fragment() {

    private val viewModel: SolucionViewModel by activityViewModels()
    private var binding: FragmentHomeBinding? = null

    var d = 0
    var data1 = ""
    var data2 = ""
    var data3 = ""
    var data4 = ""
    var data5 = ""
    var data6 = ""
    var data7 = 0.0
    var data8 = ""
    var data9 = ""
    var data10 = ""
    var data11 = ""
    var data12 = ""
    var data13 = ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding?.layoutDatos?.buttonSolucion?.setOnClickListener {


            val pr1 = binding?.layoutDatos?.editDato11?.text.toString()
            data1 = pr1
            MyApplication.data1 = pr1
            val pr2 = binding?.layoutDatos?.editDato12?.text.toString()
            data2 = pr2
            MyApplication.data2 = pr2
            val progPI = Prog(pr1.toInt(), pr2.toFloat())
            val seg = binding?.layoutDatos?.editDato2seg?.text.toString()
            data4 = seg
            MyApplication.data4 = seg
            val min = binding?.layoutDatos?.editDato2min?.text.toString()
            data5 = min
            MyApplication.data5 = min
            val gr = binding?.layoutDatos?.editDato2?.text.toString()
            data6 = gr
            MyApplication.data6 = gr
            val angle = gr.toFloat() + (min.toFloat() / 60f) + (seg.toFloat() / 3600f)
            data7 = angle.toDouble()
            MyApplication.data7 = angle.toDouble()
            val ve = binding?.layoutDatos?.editDato3?.text.toString()
            data8 = ve
            MyApplication.data8 = ve
            val cu = binding?.layoutDatos?.editDato4?.text.toString()
            data9 = cu
            MyApplication.data9 = cu
            val n = binding?.layoutDatos?.editDato5?.text.toString()
            data10 = n
            MyApplication.data10 = n
            val a = binding?.layoutDatos?.editDato6?.text.toString()
            data11 = a
            MyApplication.data11 = a
            val r = binding?.layoutDatos?.editDato7?.text.toString()
            data12 = r
            MyApplication.data12 = r
            val aCal = binding?.layoutDatos?.editDato8?.text.toString()
            data13 = aCal
            MyApplication.data13 = aCal

            viewModel.solucionar(
                r.toFloat(),
                angle,
                cu.toFloat(),
                aCal.toFloat(),
                n.toInt(),
                a.toFloat(),
                ve.toFloat(),
                progPI
            )


        }
        return binding?.root
    }

    private fun graph() {
        val options = AXGraphOptions(requireContext())

        options.scrollEnabled = true
        options.xDividerIntervalInPx = 100f
        options.xDividerInterval = 1f
        options.yDividerIntervalInPx = 100f
        options.yDividerInterval = 1f
        options.maxZoom = 6f
        options.minZoom = 0.1f

        // options.drawAxisX = false
        // options.drawAxisY = false
        options.drawAxisXDivider = false
        options.drawAxisYDivider = false
        options.drawXText = false
        options.drawYText = false
        //binding?.graphView?.graphOptions = options

        /* binding?.graphView?.addFormula(
             Clotoide(5f, (42f* PI/180f).toFloat(),(16.5f* PI/180f).toFloat(),0f,0f,0f,0f,0f,1f)
         )*/
        options.drawAxisXDivider = false
        options.drawAxisYDivider = false
        options.drawXText = false
        options.drawYText = false
    }

}