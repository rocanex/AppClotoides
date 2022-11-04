package dev.matt2393.carreterasnex.solucion.reporte

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.DialogInterface
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dev.matt2393.carreterasnex.*
import dev.matt2393.carreterasnex.databinding.FragmentReporteBinding
import dev.matt2393.carreterasnex.model.Prog
import dev.matt2393.carreterasnex.model.Reporte
import dev.matt2393.carreterasnex.model.Solucion
import dev.matt2393.carreterasnex.solucion.SolucionViewModel
import dev.matt2393.carreterasnex.solucion.home.HomeFragment

class ReporteFragment: Fragment() {
    private val viewModel: SolucionViewModel by activityViewModels()
    private var binding: FragmentReporteBinding? = null
    private var adapter: ReporteAdapter? = null


    private lateinit var templatePdf: TemplatePDF

    private lateinit var fecha: Fecha
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReporteBinding.inflate(inflater, container, false)
        adapter = ReporteAdapter()
        binding?.recyclerReporte?.layoutManager = LinearLayoutManager(requireContext())
        binding?.recyclerReporte?.adapter = adapter
        //createNotificationChannel()
        fecha = Fecha()
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

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*var HomeFragment = HomeFragment()

       // HomeFragment.getDatas()
        //var datos = MyApplication()

        var solucion: Solucion = Solucion()
        var reporte: Reporte = Reporte()*/

        /*viewModel.solucionar(
            MyApplication.data12.toFloat(),
            MyApplication.data7.toFloat(),
            MyApplication.data9.toFloat(),
            MyApplication.data12.toFloat(),
            MyApplication.data10.toInt(),
            MyApplication.data11.toFloat(),
            MyApplication.data8.toFloat(),
            Prog( MyApplication.data1.toInt(),  MyApplication.data2.toFloat())
        )*/

        val data_uno = mutableListOf<Array<String>>()
        val data_dos = mutableListOf<Array<String>>()


       /* data_uno.add(arrayOf("le", solucion.lE.toRoundMillMetherString()))
        data_uno.add(arrayOf("\\u03B8e", solucion.thetaE.toFormatSexadecimal()))
        data_uno.add(arrayOf("Ac", solucion.aC.toFormatSexadecimal()))
        data_uno.add(arrayOf("Gc", solucion.gC.toSexadecimal().toFormatSexadecimal()))
        data_uno.add(arrayOf("Lcc", solucion.lcc.toRoundMillMetherString()))
        data_uno.add(arrayOf("Xc", solucion.xC.toRoundMillMetherString()))
        data_uno.add(arrayOf("Yc", solucion.yC.toRoundMillMetherString()))
        data_uno.add(arrayOf("k", solucion.k.toRoundMillMetherString()))
        data_uno.add(arrayOf("p", solucion.p.toRoundMillMetherString()))
        data_uno.add(arrayOf("Te", solucion.tE.toRoundMillMetherString()))
        data_uno.add(arrayOf("Tl", solucion.tL.toRoundMillMetherString()))
        data_uno.add(arrayOf("Tc", solucion.tC.toRoundMillMetherString()))
        data_uno.add(arrayOf("ee", solucion.ee.toRoundMillMetherString()))
        data_uno.add(arrayOf("Cl", solucion.cL.toRoundMillMetherString()))
        data_uno.add(arrayOf("\\u03C6e", solucion.phiE.toSexadecimal().toFormatSexadecimal()))
        data_uno.add(arrayOf("ProgTE", solucion.progTE.toString()))
        data_uno.add(arrayOf("ProgEC", solucion.progEC.toString()))
        data_uno.add(arrayOf("ProgCE", solucion.progCE.toString()))
        data_uno.add(arrayOf("ProgET", solucion.progET.toString()))

        data_dos.add(arrayOf("1.- Calculo de la longitud espiral", reporte.lE.formatMath().toString()))
        data_dos.add(arrayOf("2.- Calculo del ángulo θe", reporte.thetaE.formatMath().toString()))
        data_dos.add(arrayOf("3.- Calculo de ∆c", reporte.ac.formatMath().toString()))
        data_dos.add(arrayOf("4.- Calculo de la longitud de curva circular Lcc", reporte.lcc.formatMath().toString()))
        data_dos.add(arrayOf("", reporte.lcc.formatMath().toString()))
        data_dos.add(arrayOf("5.- Calculo de coordenadas cartesianas", reporte.xc.formatMath().toString()))
        data_dos.add(arrayOf("", reporte.yc.formatMath().toString()))
        data_dos.add(arrayOf("6.- Calculo de K y P",reporte.k.formatMath().toString()))
        data_dos.add(arrayOf("", reporte.p.formatMath().toString()))
        data_dos.add(arrayOf("7.- Calculo de Te",reporte.te.formatMath().toString()))
        data_dos.add(arrayOf("8.- Calculo de Tl y Tc", reporte.tl.formatMath().toString()))
        data_dos.add(arrayOf("", reporte.tc.formatMath().toString()))
        data_dos.add(arrayOf("9.- Calculo de la externa Ee", reporte.ee.formatMath().toString()))
        data_dos.add(arrayOf("10.- Calculo cuerda larga Cl", reporte.cl.formatMath().toString()))
        data_dos.add(arrayOf("11.- Calculo φe", reporte.phiE.formatMath().toString()))
        data_dos.add(arrayOf("12.- Calculo de la progesiva", reporte.prTe.formatMath().toString()))
        data_dos.add(arrayOf("", reporte.prEc.formatMath().toString()))
        data_dos.add(arrayOf("", reporte.prCe.formatMath().toString()))
        data_dos.add(arrayOf("", reporte.prEt.formatMath().toString()))*/

        data_uno.add(arrayOf("le", MyApplication.d1))
        data_uno.add(arrayOf("0e", MyApplication.d2))
        data_uno.add(arrayOf("Ac", MyApplication.d3))
        data_uno.add(arrayOf("Gc", MyApplication.d4))
        data_uno.add(arrayOf("Lcc",MyApplication.d5))
        data_uno.add(arrayOf("Xc", MyApplication.d6))
        data_uno.add(arrayOf("Yc", MyApplication.d7))
        data_uno.add(arrayOf("k", MyApplication.d8))
        data_uno.add(arrayOf("p", MyApplication.d9))
        data_uno.add(arrayOf("Te", MyApplication.d10))
        data_uno.add(arrayOf("Tl", MyApplication.d11))
        data_uno.add(arrayOf("Tc", MyApplication.d12))
        data_uno.add(arrayOf("ee", MyApplication.d13))
        data_uno.add(arrayOf("Cl", MyApplication.d14))
        data_uno.add(arrayOf("ye", MyApplication.d15))
        data_uno.add(arrayOf("ProgTE", MyApplication.d16))
        data_uno.add(arrayOf("ProgEC", MyApplication.d17))
        data_uno.add(arrayOf("ProgCE", MyApplication.d18))
        data_uno.add(arrayOf("ProgET", MyApplication.d19))

        data_dos.add(arrayOf("1.- Calculo de la longitud espiral", MyApplication.d21.substring(MyApplication.d21.lastIndexOf("=")+1).replace("$]", "")))
        data_dos.add(arrayOf("2.- Calculo del ángulo θe", MyApplication.d22.substring(MyApplication.d22.lastIndexOf("=")+1).replace("$]", "")))
        data_dos.add(arrayOf("3.- Calculo de ∆c", MyApplication.d23.substring(MyApplication.d23.lastIndexOf("=")+1).replace("$]", "")))
        data_dos.add(arrayOf("4.- Calculo de la longitud de curva circular Lcc", MyApplication.d24.substring(MyApplication.d24.lastIndexOf("=")+1).replace("$]", "")))
        data_dos.add(arrayOf("", MyApplication.d25.substring(MyApplication.d25.lastIndexOf("=")+1).replace("$]", "")))
        data_dos.add(arrayOf("5.- Calculo de coordenadas cartesianas", MyApplication.d26.substring(MyApplication.d26.lastIndexOf("=")+1).replace("$]", "")))
        data_dos.add(arrayOf("", MyApplication.d27.substring(MyApplication.d27.lastIndexOf("=")+1).replace("$]", "")))
        data_dos.add(arrayOf("6.- Calculo de K y P",MyApplication.d28.substring(MyApplication.d28.lastIndexOf("=")+1).replace("$]", "")))
        data_dos.add(arrayOf("", MyApplication.d29.substring(MyApplication.d29.lastIndexOf("=")+1).replace("$]", "")))
        data_dos.add(arrayOf("7.- Calculo de Te",MyApplication.d210.substring(MyApplication.d210.lastIndexOf("=")+1).replace("$]", "")))
        data_dos.add(arrayOf("8.- Calculo de Tl y Tc", MyApplication.d211.substring(MyApplication.d211.lastIndexOf("=")+1).replace("$]", "")))
        data_dos.add(arrayOf("", MyApplication.d212.substring(MyApplication.d212.lastIndexOf("=")+1).replace("$]", "")))
        data_dos.add(arrayOf("9.- Calculo de la externa Ee", MyApplication.d213.substring(MyApplication.d213.lastIndexOf("=")+1).replace("$]", "")))
        data_dos.add(arrayOf("10.- Calculo cuerda larga Cl", MyApplication.d214.substring(MyApplication.d214.lastIndexOf("=")+1).replace("$]", "")))
        data_dos.add(arrayOf("11.- Calculo φe", MyApplication.d215.substring(MyApplication.d215.lastIndexOf("=")+1).replace("$]", "")))
        data_dos.add(arrayOf("12.- Calculo de la progesiva", MyApplication.d216.substring(MyApplication.d216.lastIndexOf("=")+1).replace("$]", "")))
        data_dos.add(arrayOf("", MyApplication.d217.substring(MyApplication.d217.lastIndexOf("=")+1).replace("$]", "")))
        data_dos.add(arrayOf("", MyApplication.d218.substring(MyApplication.d218.lastIndexOf("=")+1).replace("$]", "")))
        data_dos.add(arrayOf("", MyApplication.d219.substring(MyApplication.d219.lastIndexOf("=")+1).replace("$]", "")))
        
        with(binding){
            this!!.ivSavePdf.setOnClickListener {
                ivSavePdf.visibility = View.GONE
                ivViewPdf.visibility = View.VISIBLE
                val head1 = arrayOf(
                    "SIGLA",
                    "RESULTADO"
                )

                var name = "VERTICAL"+fecha.sacarFechaHora().replace(":", ".")

                templatePdf = TemplatePDF(requireContext())
                templatePdf.openDocument(name)
                templatePdf.addMetada("CARRETERAS", "REPORTE VERTICAL", "Nestor Rocha Caceres")
                templatePdf.addTitles("REPORTE VERTICAL", "Graficos y tablas", fecha.sacarFechaHora())
                templatePdf.createTable(head1, data_uno as ArrayList<Array<String>>?)
                templatePdf.createTable(head1, data_dos as ArrayList<Array<String>>?)
                templatePdf.addImage(requireContext().getDrawable(R.drawable.vertical));
                templatePdf.closeDocument()
                showDialog()
            }
        }

        with(binding){
            this!!.ivViewPdf.setOnClickListener {
                templatePdf.viewPdf()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObservers() {
        viewModel.allSol.observe(this) {
            adapter?.reporte = it.reporte
            adapter?.notifyDataSetChanged()
        }
    }
    private fun removeObservers() {
        viewModel.allSol.removeObservers(this)
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(
            requireContext(),
            R.style.DialogFragmentThemeBlack
        )
        builder.setTitle("Se descargo el reporte")
        builder.setMessage("El pdf se guardo en la carpeta CLOTOIDE")
        builder.setIcon(R.drawable.ic_save)
        builder.setNeutralButton("Aceptar", null)
        builder.setPositiveButton("ver documento",
            DialogInterface.OnClickListener { dialogInterface, i ->
                templatePdf.viewPdf()
            })
        builder.show()
    }

  /*  private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {   //(1)
            val name = getString(R.string.basic_channel_name)   // (2)
            val channelId = getString(R.string.basic_channel_id) // (3)
            val descriptionText = getString(R.string.basic_channel_description) // (4)
            val importance = NotificationManager.IMPORTANCE_DEFAULT // (5)

            val channel = NotificationChannel(channelId, name, importance).apply { // (6)
                description = descriptionText
            }

            val nm: NotificationManager =
                requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager // (7)
            nm.createNotificationChannel(channel) // (8)
        }
    }


    private fun generateNotification() {
        val v = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        val notificationId = 0
        val channelId = getString(R.string.basic_channel_id) // (1)
        val largeIcon = BitmapFactory.decodeResource( // (2)
            resources,
            R.drawable.logo
        )

        val notification = NotificationCompat.Builder(requireContext(), channelId) // (3)
            .setLargeIcon(largeIcon) // (4)
            .setSmallIcon(R.drawable.logo) // (5)
            .setContentTitle("Se descargo el reporte") // (6)
            .setContentText("El pdf se guardo en la carpeta CLOTOIDE") // (7)
            .setSubText("Nestor.com") // (8)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) // (9)
            .build()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val time = longArrayOf(0, 100, 0, 100, 0, 100)
            val amplitude = intArrayOf(0, 50, 0, 100, 0, 150)
            val vibrationEffect =
                VibrationEffect.createWaveform(time, amplitude, -1 *//*-1 No repeat*//*)
            v.vibrate(vibrationEffect)
        } else {
            v.vibrate(1000)
        }

        with(NotificationManagerCompat.from(requireContext())) {
            notify(notificationId, notification)
        }
    }*/
}