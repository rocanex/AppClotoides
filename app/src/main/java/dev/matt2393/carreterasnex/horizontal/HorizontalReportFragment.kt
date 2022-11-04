package dev.matt2393.carreterasnex.horizontal

import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.alclabs.fasttablelayout.FastTableLayout
import dev.matt2393.carreterasnex.*
import dev.matt2393.carreterasnex.databinding.FragmentHorizontalReportBinding
import dev.matt2393.carreterasnex.databinding.ItemCalculosBinding
import dev.matt2393.carreterasnex.model.DMSModel


class HorizontalReportFragment : Fragment() {

    private lateinit var binding: FragmentHorizontalReportBinding
    private val viewModel: VMHorizontal by activityViewModels()
    private lateinit var templatePdf: TemplatePDF

    private lateinit var fecha: Fecha


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHorizontalReportBinding.inflate(inflater, container, false)
        fecha = Fecha()
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data_uno = mutableListOf<Array<String>>()
        val data_dos = mutableListOf<Array<String>>()
        val data_tres = mutableListOf<Array<String>>()

        // createNotificationChannel()
        for (item in viewModel.getListCalculates()) {
            val itemBinding = ItemCalculosBinding.inflate(LayoutInflater.from(requireContext()))
            itemBinding.textTitItemCalculos.text = item.text
            itemBinding.textItemCalculos.text = item.value
            data_uno.add(arrayOf(item.text, item.value))
            binding.llResults.addView(itemBinding.root)
        }

        with(binding) {
            tvDataA1.text = "Prog IC = ${viewModel.calcProgPc().splitWithPlus()}"
            data_dos.add(arrayOf("Prog IC", viewModel.calcProgPc().splitWithPlus()))
            tvDataA2.text = "Prog FC = ${viewModel.calcProgPt().splitWithPlus()}"
            data_dos.add(arrayOf("Prog FC", viewModel.calcProgPt().splitWithPlus()))
            tvDataA3.text = "G = ${viewModel.calcGc().toDMS()}"
            data_dos.add(arrayOf("G", viewModel.calcGc().toDMS().toString()))
            tvDataA4.text = "Cu = ${viewModel.calcProgPt().splitWithPlus()}"
            data_dos.add(arrayOf("Cu", viewModel.calcProgPt().splitWithPlus()))
        }

        val headers = arrayOf(
            ""
        )

        /*val headers = arrayOf(
            "PUNTO",
            "PROGRESIVAS",
            "LONGITUD ENTRE\nPUNTOS",
            "DEFLEXION\nUNITARIA",
            "DEFLEXION\nACUMULADA",
            "ANGULO DE\nREPLANTEO"
        )*/

        val data = mutableListOf<Array<String>>()

        data.add(
            arrayOf(
                "PC",
                "${viewModel.calcProgPc().customFormat()}",
                "",
                "",
                "${DMSModel(0, 0, 0.0)}",
                "${DMSModel(0, 0, 0.0)}",
            )
        )


        val rows = viewModel.table.rows
        for (i in rows.indices) {
            data.add(
                arrayOf(
                    if (i < rows.size - 1) "${i + 1}" else "PT",
                    "${rows[i].progresiva}",
                    "${rows[i].longPuntos}",
                    "${rows[i].deflexUnitaria.toDMS()}",
                    "${rows[i].deflexAcumulada.toDMS()}",
                    "${rows[i].angulo.toDMS()}",
                )
            )
        }

        val fastTable =
            FastTableLayout(requireContext(), binding.myTableLayout, headers, data.toTypedArray())
        fastTable.build()

        with(binding) {
            tvDataB1.text = "Prog IC = ${viewModel.calcProgPc().splitWithPlus()}"
            data_tres.add(arrayOf("Prog IC", viewModel.calcProgPc().splitWithPlus()))
            tvDataB2.text = "Prog FC = ${viewModel.calcProgPt().splitWithPlus()}"
            data_tres.add(arrayOf("Prog FC", viewModel.calcProgPt().splitWithPlus()))
            tvDataB3.text = "G = ${viewModel.calcGc().toDMS()}"
            data_tres.add(arrayOf("G", viewModel.calcGc().toDMS().toString()))
            tvDataB4.text = "Cu = ${viewModel.calcProgPt().splitWithPlus()}"
            data_tres.add(arrayOf("Cu", viewModel.calcProgPt().splitWithPlus()))
            tvDataB5.text = "Az = ${viewModel.az}"
            data_tres.add(arrayOf("Az", viewModel.az.toString()))
            tvDataB6.text = "N = ${viewModel.n.customFormat()}"
            data_tres.add(arrayOf("N", viewModel.n.customFormat().toString()))
            tvDataB7.text = "E = ${viewModel.e.customFormat()}"
            data_tres.add(arrayOf("E", viewModel.e.customFormat().toString()))


        }


        val headers2 = arrayOf(
            ""
        )
        /*val headers2 = arrayOf(
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
        )*/

        val data2 = mutableListOf<Array<String>>()

        data2.add(
            arrayOf(
                "IC",
                "",
                "",
                "",
                "${viewModel.az}",
                "",
                "",
                "",
                "${viewModel.n.customFormat()}",
                "${viewModel.e.customFormat()}",
                "IC=${viewModel.calcProgPc().customFormat()}"


            )
        )

        val rows2 = viewModel.table2.rows
        for (i in rows2.indices) {
            data2.add(
                arrayOf(
                    if (i == 0) {
                        "${viewModel.calcProgPc().customFormat()}"
                    } else {
                        "${rows2[i - 1].a.customFormat()}"
                    },
                    "${rows2[i].cuedaUnitaria.customFormat()}",
                    "${rows2[i].delecUnitaria.toDMS()}",
                    "${rows2[i].delecAcumulada.toDMS()}",
                    "${rows2[i].azimut.toDMS()}",
                    "${rows2[i].dh.customFormat()}",
                    "${rows2[i].deltaN.customFormat()}",
                    "${rows2[i].deltaE.customFormat()}",
                    "${rows2[i].n.customFormat()}",
                    "${rows2[i].e.customFormat()}",
                    "${rows2[i].a.customFormat()}",

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

        with(binding) {
            ivSavePdf.setOnClickListener {
                ivSavePdf.visibility = View.GONE
                ivViewPdf.visibility = View.VISIBLE
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
                    "DEFLEXION\nUNITARIA",
                    "DEFLEXION\nACUMULADA",
                    "AZIMUT",
                    "DH",
                    "\u0394N",
                    "\u0394E",
                    "N",
                    "E",
                    "A",
                )

                val shortText = "hola"
                val titleUnoText = "A) REPLANTEO POR DEFLEXION"
                val titleTWOText = "B) METODO DE LAS COORDENADAS"

                val longText = "HOLA "

                var name = "HORIZONTAL" + fecha.sacarFechaHora().replace(":", ".")

                templatePdf = TemplatePDF(requireContext())
                templatePdf.openDocument(name)
                templatePdf.addMetada("CARRETERAS", "REPORTE HORIZONTAL", "Nestor Rocha Caceres")
                templatePdf.addTitles(
                    "REPORTE HORIZONTAL",
                    "Graficos y tablas",
                    fecha.sacarFechaHora()
                )
                templatePdf.createTable(head1, data_uno as ArrayList<Array<String>>?)
                templatePdf.addParagms(titleUnoText)
                templatePdf.createTable(head1, data_dos as ArrayList<Array<String>>?)
                templatePdf.createTable(head, data as ArrayList<Array<String>>?)
                templatePdf.addParagms(titleTWOText)
                templatePdf.createTable(head1, data_tres as ArrayList<Array<String>>?)
                templatePdf.createTable(headers2, data2 as ArrayList<Array<String>>?)
                /*templatePdf.addParagms(titleTWOText)
                templatePdf.addParagms(shortText)
                templatePdf.addParagms(longText)
                templatePdf.createTable(head, data as ArrayList<Array<String>>?)*/
                templatePdf.addImage(requireContext().getDrawable(R.drawable.horizon));
                templatePdf.closeDocument()
                showDialog()
                //generateNotification()

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