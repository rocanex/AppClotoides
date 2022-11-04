package dev.matt2393.carreterasnex.clotoide

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
import com.alclabs.fasttablelayout.FastTableLayout
import dev.matt2393.carreterasnex.*
import dev.matt2393.carreterasnex.databinding.FragmentClotoideReportBinding
import dev.matt2393.carreterasnex.databinding.ItemCalculosBinding
import dev.matt2393.carreterasnex.model.DMSModel
import java.util.ArrayList

class ClotoideReportFragment : Fragment() {
    private lateinit var binding: FragmentClotoideReportBinding
    private val viewModel: VMClotoide by activityViewModels()
    private lateinit var templatePdf: TemplatePDF

    private lateinit var fecha: Fecha

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClotoideReportBinding.inflate(inflater, container, false)
        fecha = Fecha()
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //createNotificationChannel()
        val data_uno = mutableListOf<Array<String>>()
        val data_dos = mutableListOf<Array<String>>()
        val data_tres = mutableListOf<Array<String>>()
        for (item in viewModel.getListCalculates()) {
            val itemBinding = ItemCalculosBinding.inflate(LayoutInflater.from(requireContext()))
            itemBinding.textTitItemCalculos.text = item.text
            itemBinding.textItemCalculos.text = item.value
            data_uno.add(arrayOf(item.text, item.value))
            binding.llResults.addView(itemBinding.root)
        }

        val headers = arrayOf(
            ""
        )
        /*val headers = arrayOf(
            "PROGRESIVAS",
            "CUERDA\nUNITARIA",
            "L",
            "L2",
            "K = 0e/le",
            "0 = K * L2",
            "P = (0/3) - Z"
        )*/

        val data = mutableListOf<Array<String>>()

        data.add(
            arrayOf(
                "${viewModel.calcProgTE().splitWithPlus()}",
                "",
                "0.0",
                "",
                "",
                "",
                "${DMSModel(0, 0, 0.0)}"
            )
        )

        val rows = viewModel.rows
        for (i in rows.indices) {
            data.add(
                arrayOf(
                    "${rows[i].progresiva.customFormat()}",
                    "${rows[i].cuerdaUnitaria.customFormat()}",
                    "${rows[i].L.customFormat()}",
                    "${rows[i].L2.customFormat(6)}",
                    "${rows[i].K.customFormat(6)}",
                    "${rows[i].theta.toDMS()}",
                    "${rows[i].P.toDMS()}",
                )
            )
        }

        val fastTable =
            FastTableLayout(requireContext(), binding.myTableLayout, headers, data.toTypedArray())
        fastTable.build()

        val headers2 = arrayOf(
            ""
        )
        /*val headers2 = arrayOf(
            "PROGRESIVA",
            "CUERDA\nUNIT",
            "FLEXIONES\nUNITARIAS",
            "DEFLEXIONES\nACUMULADAS",
            "ANGULO DE REPLANTEO",
        )*/

        val data2 = mutableListOf<Array<String>>()

        data2.add(arrayOf("${viewModel.calcProgEC().customFormat()}", "", "", "", ""))

        val rows2 = viewModel.table.rows
        for (i in rows2.indices) {
            data2.add(
                arrayOf(
                    "${rows2[i].progresiva}",
                    "${rows2[i].longPuntos.customFormat()}",
                    "${rows2[i].deflexUnitaria.toDMS()}",
                    "${rows2[i].deflexAcumulada.toDMS()}",
                    "${rows2[i].angulo.toDMS()}",
                    "${rows2[i].progresiva}",
                )
            )
        }

        val fastTable2 =
            FastTableLayout(
                requireContext(),
                binding.myTableLayout2,
                headers2,
                data2.toTypedArray()
            )
        fastTable2.build()


        val data3 = mutableListOf<Array<String>>()

        data3.add(
            arrayOf(
                "${viewModel.calcProgET().splitWithPlus()}",
                "",
                "0.0",
                "",
                "",
                "",
                "${DMSModel(0, 0, 0.0)}"
            )
        )

        val rows3 = viewModel.rows3
        for (i in rows3.indices) {
            data3.add(
                arrayOf(
                    "${rows3[i].progresiva.customFormat()}",
                    "${rows3[i].cuerdaUnitaria.customFormat()}",
                    "${rows3[i].L.customFormat()}",
                    "${rows3[i].L2.customFormat(6)}",
                    "${rows3[i].K.customFormat(6)}",
                    "${rows3[i].theta.toDMS()}",
                    "${rows3[i].P.toDMS()}",
                )
            )
        }

        val fastTable3 =
            FastTableLayout(requireContext(), binding.myTableLayout3, headers, data3.toTypedArray())
        fastTable3.build()

        val headers5 = arrayOf(
            ""
        )
        /*val headers5 = arrayOf(
            "DE PROG",
            "L",
            "0",
            "0rad",
            "X",
            "Y",
            "C.L.",
            "Phi",
            "AZIMUT",
            "N",
            "E",
            "Norte",
            "Este",
            "A PROG",
        )*/

        val data5 = mutableListOf<Array<String>>()

        data5.add(
            arrayOf(
                "0",
                "0",
                "${DMSModel(0, 0, 0.0)}",
                "0",
                "0",
                "0",
                "0",
                "${DMSModel(0, 0, 0.0)}",
                "${viewModel.az1}",
                "0",
                "0",
                "${viewModel.n}",
                "${viewModel.e}",
                "${viewModel.calcProgTE().customFormat()}",
            )
        )

        val rows5 = viewModel.rows5
        for (i in rows5.indices) {
            data5.add(
                arrayOf(
                    "${rows5[i].deProg.customFormat()}",
                    "${rows5[i].L.customFormat()}",
                    "${rows5[i].theta.toDMS()}",
                    "${rows5[i].thetaRad.customFormat()}",
                    "${rows5[i].X.customFormat()}",
                    "${rows5[i].Y.customFormat()}",
                    "${rows5[i].CL.customFormat()}",
                    "${rows5[i].Phi.toDMS()}",
                    "${rows5[i].az.toDMS()}",
                    "${rows5[i].deltaN.customFormat()}",
                    "${rows5[i].deltaE.customFormat()}",
                    "${rows5[i].Norte.customFormat()}",
                    "${rows5[i].Estte.customFormat()}",
                    "${rows5[i].aProg.customFormat()}",
                )
            )
        }

        val fastTable5 =
            FastTableLayout(requireContext(), binding.myTableLayout5, headers5, data5.toTypedArray())
        fastTable5.build()


        val headers6 = arrayOf(
            ""
        )
        /*val headers6 = arrayOf(
            "PROG",
            "CUEDA\nUNITARIA",
            "DELECCION\nUNITARIA",
            "DELECCION\nACUMULADA",
            "AZIMUT",
            "DH",
            "\u0394N",
            "\u0394E",
            "NORTE",
            "ESTE",
        )*/

        val data6 = mutableListOf<Array<String>>()

        val rows6 = viewModel.rows6
        for (i in rows6.indices) {
            data6.add(
                arrayOf(
                    "${rows6[i].progresiva.customFormat()}",
                    "${rows6[i].cuedaUnitaria.customFormat()}",
                    "${rows6[i].delecUnitaria.toDMS()}",
                    "${rows6[i].delecAcumulada.toDMS()}",
                    "${rows6[i].azimut.toDMS()}",
                    "${rows6[i].dh.customFormat()}",
                    "${rows6[i].deltaN.customFormat()}",
                    "${rows6[i].deltaE.customFormat()}",
                    "${rows6[i].n.customFormat()}",
                    "${rows6[i].e.customFormat()}",
                    "${rows6[i].progresiva.customFormat()}",
                )
            )
        }

        val fastTable6 =
            FastTableLayout(requireContext(), binding.myTableLayout6, headers6, data6.toTypedArray())
        fastTable6.build()

        val data7 = mutableListOf<Array<String>>()

        data7.add(
            arrayOf(
                "0",
                "0",
                "${DMSModel(0, 0, 0.0)}",
                "0",
                "0",
                "0",
                "0",
                "${DMSModel(0, 0, 0.0)}",
                "${viewModel.az1}",
                "0",
                "0",
                "${viewModel.n}",
                "${viewModel.e}",
                "${viewModel.calcProgTE().customFormat()}",
            )
        )

        val rows7 = viewModel.rows7
        for (i in rows7.indices) {
            data7.add(
                arrayOf(
                    "${rows7[i].deProg.customFormat()}",
                    "${rows7[i].L.customFormat()}",
                    "${rows7[i].theta.toDMS()}",
                    "${rows7[i].thetaRad.customFormat()}",
                    "${rows7[i].X.customFormat()}",
                    "${rows7[i].Y.customFormat()}",
                    "${rows7[i].CL.customFormat()}",
                    "${rows7[i].Phi.toDMS()}",
                    "${rows7[i].az.toDMS()}",
                    "${rows7[i].deltaN.customFormat()}",
                    "${rows7[i].deltaE.customFormat()}",
                    "${rows7[i].Norte.customFormat()}",
                    "${rows7[i].Estte.customFormat()}",
                    "${rows7[i].aProg.customFormat()}",
                )
            )
        }

        val fastTable7 =
            FastTableLayout(requireContext(), binding.myTableLayout7, headers5, data7.toTypedArray())
        fastTable7.build()

        with(binding) {
            ivSavePdf.setOnClickListener {
                ivSavePdf.visibility = View.GONE
                ivViewPdf.visibility = View.VISIBLE

                val _headers0 = arrayOf(
                    "PROGRESIVAS",
                    "CUERDA\nUNITARIA",
                    "L",
                    "L^2",
                    "K = 0e/le",
                    "0 = K * L2",
                    "P = (0/3) - Z"
                )

                val _headers1 = arrayOf(
                    "PROGRESIVA",
                    "CUERDA\nUNIT",
                    "FLEXIONES\nUNITARIAS",
                    "DEFLEXIONES\nACUMULADAS",
                    "ANGULO DE REPLANTEO",
                )

                val _headers2 = arrayOf(
                    "PROGRESIVA",
                    "CUERDA\nUNIT",
                    "FLEXIONES\nUNITARIAS",
                    "DEFLEXIONES\nACUMULADAS",
                    "ANGULO DE REPLANTEO",
                )

                val _headers4 = arrayOf(
                    "DE PROG",
                    "L",
                    "0",
                    "0rad",
                    "X",
                    "Y",
                    "C.L.",
                    "Phi",
                    "AZIMUT",
                    "N",
                    "E",
                    "Norte",
                    "Este",
                    "A PROG",
                )

                val _headers6 = arrayOf(
                    "PROG",
                    "CUEDA\nUNITARIA",
                    "DELECCION\nUNITARIA",
                    "DELECCION\nACUMULADA",
                    "AZIMUT",
                    "DH",
                    "\u0394N",
                    "\u0394E",
                    "NORTE",
                    "ESTE",
                    "A",
                )

                val head1 = arrayOf(
                    "SIGLA",
                    "RESULTADO"
                )
                val head = arrayOf(
                    "PUNTO",
                    "PROGRESIVAS",
                    "LONGITUD ENTRE\nPUNTOS",
                    "DEFLEXION\nUNITARIA",
                    "DEFLEXION\nACUMULADA",
                    "ANGULO DE\nREPLANTEO"
                )

                val headers2 = arrayOf(
                    "PROG",
                    "CUEDA\nUNITARIA",
                    "DELECCION\nUNITARIA",
                    "DELECCION\nACUMULADA",
                    "AZIMUT",
                    "DH",
                    "\u0394N",
                    "\u0394E",
                    "N",
                    "E",
                    "A",
                )

                val titleText1 = "REPLANTEO METODO DE COORDENADAS TRAMO TE-IC"
                val titleText2 = "REPLANTEO METODO DE COORDENADAS TRAMO EC-CE"
                val titleText3 = "REPLANTEO METODO DE COORDENADAS TRAMO ET-CE"
                val titleText4 = "REPLANTEO METODO DE DEFLEXION TRAMO TE-IC"
                val titleText5 = "REPLANTEO METODO DE DEFLEXION TRAMO EC-CE"
                val titleText6 = "REPLANTEO METODO DE DEFLEXION TRAMO ET-CE"


                val longText = "HOLA COMO ESTA "

                var name = "CLOTOIDE"+fecha.sacarFechaHora().replace(":", ".")

                templatePdf = TemplatePDF(requireContext())
                templatePdf.openDocument(name)
                templatePdf.addMetada("CARRETERAS", "REPORTE CLOTOIDE", "Nestor Rocha Caceres")
                templatePdf.addTitles("REPORTE CLOTOIDE", "Graficos y tablas", fecha.sacarFechaHora())
                templatePdf.createTable(head1, data_uno as ArrayList<Array<String>>?)
                templatePdf.addParagms(titleText1)
                templatePdf.createTable(_headers0, data as ArrayList<Array<String>>?)
                templatePdf.addParagms(titleText2)
                templatePdf.createTable(_headers2, data2 as ArrayList<Array<String>>?)
                templatePdf.addParagms(titleText3)
                templatePdf.createTable(_headers0, data3 as ArrayList<Array<String>>?)
                templatePdf.addParagms(titleText4)
                templatePdf.createTable(_headers4, data5 as ArrayList<Array<String>>?)
                templatePdf.addParagms(titleText5)
                templatePdf.createTable(_headers6, data6 as ArrayList<Array<String>>?)
                templatePdf.addParagms(titleText6)
                templatePdf.createTable(_headers4, data7 as ArrayList<Array<String>>?)
                /*templatePdf.addParagms(titleTWOText)
                templatePdf.addParagms(shortText)
                templatePdf.addParagms(longText)
                templatePdf.createTable(head, data as ArrayList<Array<String>>?)*/
                templatePdf.addImage(requireContext().getDrawable(R.drawable.clotoide));
                templatePdf.closeDocument()
                showDialog()

            }
        }

        with(binding) {
            ivViewPdf.setOnClickListener {
                templatePdf.viewPdf()
            }
        }
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

   /* private fun createNotificationChannel() {
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