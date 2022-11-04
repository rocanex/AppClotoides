package dev.matt2393.carreterasnex

import dev.matt2393.carreterasnex.model.DMSModel
import kotlin.math.PI
import kotlin.math.ceil
import kotlin.math.round
import kotlin.math.roundToInt

fun Float.toFormatSexadecimal(): String {
    val aux1 = this.toInt()
    val aux2 = ((this - aux1) * 60f).toInt()
    val aux3 = ceil((((this - aux1) * 60f) - aux2) * 60).toInt()
    val saux2 = if (aux2 < 10) "0$aux2" else aux2.toString()
    val saux3 = if (aux3 < 10) "0$aux3" else aux3.toString()
    return "${aux1}º${saux2}'${saux3}\""
}

fun Float.toSexadecimal(): Float = (this * 180f / PI).toFloat()
fun Float.toRad(): Float = (this * PI / 180f).toFloat()

fun Float.toRoundMill(): Float = round(this * 1000f) / 1000f
fun Float.toRoundMillMetherString(): String = "${this.toRoundMill()} [m]"


// Add new extensions
fun Double.customFormat(nroDecimals: Int = 3): Double = "%.${nroDecimals}f".format(this).replace(",", ".").toDouble()

fun Double.toDMS(): DMSModel {
    val d = (this / 1).customFormat()
    val m = ((this % 1) * 60).customFormat()
    val s = ((m % 1) * 60).customFormat()

    return DMSModel(d.toInt(), m.toInt(), s)
}

fun Double.degreeToRadian(): Double = this * PI / 180

fun Double.radianToDegree(): Double = this * 180 / PI

fun Int.roundToNearestTen(): Int {
    val lastDig = this % 10
    var copyNum = this
    if (lastDig < 5) {
        copyNum += 5
    }
    return (copyNum / 10.0).roundToInt() * 10
}

fun Int.roundToFarthestTen(): Int {
    val lastDig = this % 10
    var copyNum = this
    if (lastDig > 5) {
        copyNum -= 5
    }
    return (copyNum / 10.0).roundToInt() * 10
}

fun Double.splitWithPlus(): String {
    return if (this > 0) {
        val u = (this / 1000).roundToInt()
        val d = (this % 1000).customFormat()
        "$u + $d"
    } else {
        ""
    }
}