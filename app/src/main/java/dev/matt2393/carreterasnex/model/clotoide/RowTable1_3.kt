package dev.matt2393.carreterasnex.model.clotoide

import dev.matt2393.carreterasnex.customFormat
import dev.matt2393.carreterasnex.model.DMSModel
import dev.matt2393.carreterasnex.toDMS
import kotlin.math.pow

data class RowTable1_3(
    val progresiva: Double,
    val cuerdaUnitaria: Double,
    val L: Double,
    val L2: Double,
    val K: Double,
    val theta: Double,
    var P: Double = 0.0,
    val thetaE: Double,
) {
    private val zValid = DMSModel(16, 0, 0.0).toDegree()

    private fun calcP() {
        P = theta / 3
        var z = 0.0
        if (thetaE > zValid) {
            z = ((31 / 10000) * thetaE.pow(3)) + ((23 / 10.0.pow(9)) * thetaE.pow(5))
        }
        P -= z
    }

    init {
        calcP()
    }

    override fun toString(): String {
        return "$progresiva\t${cuerdaUnitaria.customFormat()}\t${L.customFormat(6)}\t$L2\t$K\t${theta.toDMS()}\t${P.toDMS()}\n"
    }

}