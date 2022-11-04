package dev.matt2393.carreterasnex.solucion.reporte

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.matt2393.carreterasnex.*
import dev.matt2393.carreterasnex.databinding.ItemReporteBinding
import dev.matt2393.carreterasnex.model.Reporte
import katex.hourglass.`in`.mathlib.MathView

class ReporteAdapter(var reporte: Reporte = Reporte()): RecyclerView.Adapter<ReporteAdapter.ReporteViewHolder>() {
    inner class ReporteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemReporteBinding.bind(itemView)
        fun setMathText(tit:String, texts: ArrayList<String>) {
            if(tit.isEmpty()) {
                binding.textTitItemReporte.visibility = View.GONE
            } else {
                binding.textTitItemReporte.visibility = View.VISIBLE
            }
            binding.textTitItemReporte.text = tit
            binding.linearItemReporte.removeAllViews()
            texts.forEach {
                val mathView = MathView(binding.root.context)
                mathView.isClickable = true;
                mathView.setTextSize(14);
                mathView.setDisplayText(it)
                binding.linearItemReporte.addView(mathView)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReporteViewHolder =
        ReporteViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_reporte, parent, false)
        )

    override fun onBindViewHolder(holder: ReporteViewHolder, position: Int) {
        when(position) {
            0 -> holder.setMathText("1.- Calculo de la longitud espiral",reporte.lE.formatMath())
            1 -> holder.setMathText("2.- Calculo del ángulo θe",reporte.thetaE.formatMath())
            2 -> holder.setMathText("3.- Calculo de ∆c",reporte.ac.formatMath())
            3 -> holder.setMathText("4.- Calculo de la longitud de curva circular Lcc",reporte.gc.formatMath())
            4 -> holder.setMathText("",reporte.lcc.formatMath())
            5 -> holder.setMathText("5.- Calculo de coordenadas cartesianas",reporte.xc.formatMath())
            6 -> holder.setMathText("",reporte.yc.formatMath())
            7 -> holder.setMathText("6.- Calculo de K y P",reporte.k.formatMath())
            8 -> holder.setMathText("",reporte.p.formatMath())
            9 -> holder.setMathText("7.- Calculo de Te",reporte.te.formatMath())
            10 -> holder.setMathText("8.- Calculo de Tl y Tc",reporte.tl.formatMath())
            11 -> holder.setMathText("",reporte.tc.formatMath())
            12 -> holder.setMathText("9.- Calculo de la externa Ee",reporte.ee.formatMath())
            13 -> holder.setMathText("10.- Calculo cuerda larga Cl",reporte.cl.formatMath())
            14 -> holder.setMathText("11.- Calculo φe",reporte.phiE.formatMath())
            15 -> holder.setMathText("12.- Calculo de la progesiva",reporte.prTe.formatMath())
            16 -> holder.setMathText("",reporte.prEc.formatMath())
            17 -> holder.setMathText("",reporte.prCe.formatMath())
            18 -> holder.setMathText("",reporte.prEt.formatMath())
        }

        MyApplication.d21 =reporte.lE.formatMath().toString()
        MyApplication.d22 =reporte.thetaE.formatMath().toString()
        MyApplication.d23 =reporte.ac.formatMath().toString()
        MyApplication.d24 = reporte.gc.formatMath().toString()
        MyApplication.d25 =reporte.lcc.formatMath().toString()
        MyApplication.d26 =reporte.xc.formatMath().toString()
            MyApplication.d27 =reporte.yc.formatMath().toString()
            MyApplication.d28 =reporte.k.formatMath().toString()
            MyApplication.d29 =reporte.p.formatMath().toString()
            MyApplication.d210 =reporte.te.formatMath().toString()
            MyApplication.d211 =reporte.tl.formatMath().toString()
            MyApplication.d212 =reporte.tc.formatMath().toString()
            MyApplication.d213 =reporte.ee.formatMath().toString()
            MyApplication.d214 =reporte.cl.formatMath().toString()
            MyApplication.d215 =reporte.phiE.formatMath().toString()
            MyApplication.d216 =reporte.prTe.formatMath().toString()
            MyApplication.d217 =reporte.prEc.formatMath().toString()
            MyApplication.d218 =reporte.prCe.formatMath().toString()
            MyApplication.d219 =reporte.prEt.formatMath().toString()
    }

    override fun getItemCount(): Int = reporte.size()


}