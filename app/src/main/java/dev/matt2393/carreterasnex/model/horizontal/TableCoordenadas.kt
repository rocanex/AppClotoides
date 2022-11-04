package dev.matt2393.carreterasnex.model.horizontal

import dev.matt2393.carreterasnex.customFormat
import dev.matt2393.carreterasnex.degreeToRadian
import dev.matt2393.carreterasnex.radianToDegree
import dev.matt2393.carreterasnex.toDMS
import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.sin

data class TableCoordenadas(val rows: List<RowCoordenadas>) {
    fun calcDeflexionUnitaria(cu: Double, r: Double): Double {
        val gc = 2 * asin(cu / (2 * r)).radianToDegree()
        return gc / 2
    }

    fun calcUlitmaDeflexionUnitaria(cu1: Double, cu2: Double, unitaria: Double): Double {
        val gc = cu2 * unitaria
        return gc / cu1
    }

    fun calcDeltaN(dh: Double, azimut: Double): Double {
        val ope = dh * cos(azimut.degreeToRadian())
        return ope.customFormat()
    }

    fun calcDeltaE(dh: Double, azimut: Double): Double {
        val ope = dh * sin(azimut.degreeToRadian())
        return ope.customFormat()
    }

    //
    fun printTable() {
        println("==== TABLE ====")
        for (r in rows.indices) {
            println("${r + 1} ${rows[r]}")
        }
    }
}

data class RowCoordenadas(
    val progresiva: Double = 0.0,
    val cuedaUnitaria: Double = 0.0,
    val delecUnitaria: Double = 0.0,
    val delecAcumulada: Double = 0.0,
    val azimut: Double = 0.0,
    val dh: Double = 0.0,
    val deltaN: Double = 0.0,
    val deltaE: Double = 0.0,
    val n: Double = 0.0,
    val e: Double = 0.0,
    val a: Double = 0.0,
) {
    override fun toString(): String {
        return "$progresiva;$cuedaUnitaria;${delecUnitaria.toDMS()};${delecAcumulada.toDMS()};${azimut.toDMS()};" +
                "$dh;$deltaN;$deltaE;${n.customFormat()};${e.customFormat()}"
    }
}