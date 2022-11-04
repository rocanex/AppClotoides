package dev.matt2393.carreterasnex.clotoide

import android.util.Log
import androidx.lifecycle.ViewModel
import dev.matt2393.carreterasnex.*
import dev.matt2393.carreterasnex.horizontal.CalculateModel
import dev.matt2393.carreterasnex.model.DMSModel
import dev.matt2393.carreterasnex.model.clotoide.RowTable1_3
import dev.matt2393.carreterasnex.model.horizontal.RowCoordenadas
import dev.matt2393.carreterasnex.model.horizontal.RowDeflexionModel
import dev.matt2393.carreterasnex.model.horizontal.TableCoordenadas
import dev.matt2393.carreterasnex.model.horizontal.TableDeflexionModel
import kotlin.math.*

class VMClotoide : ViewModel() {
    var progPI = 27941.793
    var vp = 50
    var delta = DMSModel(112, 44, 20.42)
    var rc = 85
    var cu = 20
    var a = 61.84
    var az1 = DMSModel(39, 43, 17.0)
    var az2 = DMSModel(152, 27, 37.2)
    var n = 8048900.102
    var e = 715073.634
    var rows = mutableListOf<RowTable1_3>()
    var rows3 = mutableListOf<RowTable1_3>()
    var rows5 = mutableListOf<RowTable5>()
    var rows7 = mutableListOf<RowTable5>()

    var rows2 = mutableListOf<RowDeflexionModel>()
    val table = TableDeflexionModel(rows2)

    val rows6 = mutableListOf<RowCoordenadas>()
    val table6 = TableCoordenadas(rows6)

    fun loadData(
        progPI: Double,
        vp: Int,
        delta: DMSModel,
        rc: Int,
        cu: Int,
        a: Double,
        az1: DMSModel,
        az2: DMSModel,
        n: Double,
        e: Double,

        ) {
        this.progPI = progPI
        Log.e("TAG", "loadData: $progPI")
        this.vp = vp
        this.delta = delta
        this.rc = rc
        this.cu = cu
        this.a = a
        this.az1 = az1
        this.az2 = az2
        this.n = n
        this.e = e


        table1(calcProgTE(), calcProgEC())
        deflexionTable()
        table3()
        table5()
        table6()
        table7()

    }


    fun calcLe(): Double = (a * a) / rc

    fun calcThetaE(): Double = (90 / PI) * (calcLe() / rc)

    fun calcDeltaC(): Double = delta.toDegree() - (2 * calcThetaE())

    fun calcGc(): Double = 2 * (asin(cu / (2.0 * rc)).radianToDegree())

    fun calcLcc(): Double = calcDeltaC() * (cu / calcGc())

    fun calcSigmaRad(): Double = (PI * calcThetaE()) / 180

    fun calcXc(): Double = calcLe() * (1 - (calcSigmaRad().pow(2) / 10) +
            (calcSigmaRad().pow(4) / 216) - (calcSigmaRad().pow(6) / 9360))

    fun calcYc(): Double = calcLe() * ((calcSigmaRad() / 3) - (calcSigmaRad().pow(3) / 42) +
            (calcSigmaRad().pow(5) / 1320) - (calcSigmaRad().pow(7) / 75600))

    fun calcK(): Double = calcXc() - (rc * sin(calcThetaE().degreeToRadian()))

    fun calcP(): Double = calcYc() - (rc * (1 - cos(calcThetaE().degreeToRadian())))

    fun calcTe(): Double = calcK() + (rc + calcP()) * tan((delta.toDegree() / 2).degreeToRadian())

    fun calcTl(): Double = calcXc() - (calcYc() / tan(calcThetaE().degreeToRadian()))

    fun calcTc(): Double = calcYc() / sin(calcThetaE().degreeToRadian())

    fun calcEe(): Double = ((rc + calcP()) / cos((delta.toDegree() / 2).degreeToRadian())) - rc

    fun calcCl(): Double = sqrt(calcXc().pow(2) + calcYc().pow(2))

    fun calcPhiE(): Double = atan(calcYc() / calcXc()).radianToDegree()

    fun calcProgTE(): Double = progPI - calcTe()

    fun calcProgEC(): Double = calcProgTE() + calcLe()

    fun calcProgCE(): Double = calcProgEC() + calcLcc()

    fun calcProgET(): Double = calcProgCE() + calcLe()


    fun getListCalculates(): List<CalculateModel> = listOf(
        CalculateModel("le", "${calcLe().customFormat()} m"),
        CalculateModel("θe", "${calcThetaE().toDMS()}"),
        CalculateModel("Δc", "${calcDeltaC().toDMS()}"),
        CalculateModel("Gc", "${calcGc().toDMS()}"),
        CalculateModel("lcc", "${calcLcc().customFormat()} m"),
        CalculateModel("θrad", "${calcSigmaRad().customFormat()}"),
        CalculateModel("Xc", "${calcXc().customFormat()} m"),
        CalculateModel("Yc", "${calcYc().customFormat()} m"),
        CalculateModel("K", "${calcK().customFormat()} m"),
        CalculateModel("P", "${calcP().customFormat()} m"),
        CalculateModel("Te", "${calcTe().customFormat()} m"),
        CalculateModel("Tl", "${calcTl().customFormat()} m"),
        CalculateModel("Tc", "${calcTc().customFormat()} m"),
        CalculateModel("Ee", "${calcEe().customFormat()} m"),
        CalculateModel("Cl", "${calcCl().customFormat()} m"),
        CalculateModel("φe", "${calcPhiE().toDMS()}"),
        CalculateModel("ProgTE", "${calcProgTE().customFormat()}"),
        CalculateModel("ProgEC", "${calcProgEC().customFormat()}"),
        CalculateModel("ProgCE", "${calcProgCE().customFormat()}"),
        CalculateModel("ProgET", "${calcProgET().customFormat()}"),
    )

    // Calc table 5
    fun calcThetaE(argL: Double): Double = (argL * 180) / (2 * rc * PI)

    fun calcThetaRad(argL: Double): Double = argL / (2 * rc)

    fun calcXc(argL: Double, argTheta: Double): Double = argL * (1 - (argTheta.pow(2) / 10) +
            (argTheta.pow(4) / 216) - (argTheta.pow(6) / 9360))

    fun calcYc(argL: Double, argTheta: Double): Double =
        argL * ((argTheta / 3) - (argTheta.pow(3) / 42) +
                (argTheta.pow(5) / 1320) - (argTheta.pow(7) / 75600))

    fun calcCl(argXc: Double, argXy: Double): Double = sqrt(argXc.pow(2) + argXy.pow(2))

    fun calcPhiE(argYc: Double, argXc: Double): Double = atan(argYc / argXc).radianToDegree()

    fun calcDeltaN(dh: Double, azimut: Double): Double {
        val ope = dh * cos(azimut.degreeToRadian())
        return ope.customFormat()
    }

    fun calcDeltaE(dh: Double, azimut: Double): Double {
        val ope = dh * sin(azimut.degreeToRadian())
        return ope.customFormat()
    }
    // Fin calc table 5

    data class RowTable5(
        val deProg: Double,
        val L: Double,
        val theta: Double,
        val thetaRad: Double,
        val X: Double,
        val Y: Double,
        val CL: Double,
        val Phi: Double,
        val az: Double,
        val deltaN: Double,
        val deltaE: Double,
        val Norte: Double,
        val Estte: Double,
        val aProg: Double,
    ) {
        override fun toString(): String {
            return "${deProg.customFormat()}\t" +
                    "${L.customFormat()}\t" +
                    "${theta.toDMS()}\t" +
                    "${thetaRad.customFormat()}\t" +
                    "${X.customFormat()}\t" +
                    "${Y.customFormat()}\t" +
                    "${CL.customFormat()}\t" +
                    "${Phi.toDMS()}\t" +
                    "${az.toDMS()}\t" +
                    "${deltaN.customFormat()}\t" +
                    "${deltaE.customFormat()}\t" +
                    "${Norte.customFormat()}\t" +
                    "${Estte.customFormat()}\t" +
                    "${aProg.customFormat()}\n\n"
        }
    }

    private fun table1(ini: Double, fin: Double) {
        rows.clear()

        val newTe = calcProgTE().roundToInt().roundToNearestTen()
        val newCuerdaUnitaria = (newTe - ini).customFormat()

        var L2 = (newCuerdaUnitaria.pow(2)).customFormat(10)
        val K = (calcThetaE() / calcLe().pow(2)).customFormat(10)
        var theta = (K * L2)

        rows.add(
            RowTable1_3(
                progresiva = newTe.toDouble(),
                cuerdaUnitaria = newCuerdaUnitaria,
                L = newCuerdaUnitaria,
                L2 = L2,
                K = K,
                theta = theta,
                thetaE = calcThetaE()
            )
        )

        do {
            val lastRow = rows.last()

            val L = lastRow.L + cu
            L2 = (L.pow(2)).customFormat(6)
            theta = (K * L2)

            rows.add(
                RowTable1_3(
                    progresiva = lastRow.progresiva + cu,
                    cuerdaUnitaria = cu.toDouble(),
                    L = L,
                    L2 = L2,
                    K = K,
                    theta = theta,
                    thetaE = calcThetaE()
                )
            )
        } while (rows.last().progresiva <= fin)

        rows.removeLast()

        val lastRow = rows.last()

        val L = lastRow.L + (fin - lastRow.progresiva)
        L2 = (L.pow(2)).customFormat(1)
        theta = (K * L2)

        rows.add(
            RowTable1_3(
                progresiva = fin.customFormat(),
                cuerdaUnitaria = fin - lastRow.progresiva,
                L = L,
                L2 = L2,
                K = K,
                theta = theta,
                thetaE = calcThetaE()
            )
        )
    }

    private fun deflexionTable() {
        ////.roundToInt().roundToNearestTen()

        val newPc = (calcProgEC()+cu).roundToInt().roundToFarthestTen()
        val disPoints = (newPc - calcProgEC()).customFormat()
        rows2.clear()

        val tmp = calcGc() / 2
        Log.e("TAG", "genDeflexionTable: ${calcGc()}")
        Log.e("TAG", "genDeflexionTable: $tmp")
        Log.e("TAG", "genDeflexionTable: ${tmp.toDMS()}")
        val delecUnit = (disPoints.customFormat() * tmp) / cu
        Log.e("TAG", "genDeflexionTable: $disPoints")
        Log.e("TAG", "genDeflexionTable: $tmp")
        Log.e("TAG", "genDeflexionTable: $cu")
        Log.e("TAG", "genDeflexionTable: ${delecUnit.toDMS()}")

        rows2.add(
            RowDeflexionModel(
                newPc.toDouble(),
                disPoints.customFormat(),
                delecUnit,
                delecUnit,
                delecUnit,
            )
        )

        do {
            val lastRow = rows2.last()
            rows2.add(
                RowDeflexionModel(
                    lastRow.progresiva + cu,
                    cu.toDouble(),
                    table.calcDeflexionUnitaria(cu.toDouble(), rc.toDouble()),
                    lastRow.deflexAcumulada + table.calcDeflexionUnitaria(cu.toDouble(), rc.toDouble()),
                    lastRow.deflexAcumulada + table.calcDeflexionUnitaria(cu.toDouble(), rc.toDouble()),
                )
            )
        } while (rows2.last().progresiva <= calcProgCE())
        rows2.removeLast()

        val lastRow = rows2.last()
        rows2.add(
            RowDeflexionModel(
                calcProgCE().customFormat(),
                (calcProgCE() - lastRow.progresiva).customFormat(),
                table.calcDeflexionUnitariaUltima(
                    cu.toDouble(),
                    (calcProgCE() - lastRow.progresiva),
                    lastRow.deflexUnitaria
                ),
                lastRow.deflexAcumulada + table.calcDeflexionUnitaria(
                    calcProgCE() - lastRow.progresiva,
                    rc.toDouble()
                ),
                lastRow.deflexAcumulada + table.calcDeflexionUnitariaUltima(
                    cu.toDouble(),
                    (calcProgCE() - lastRow.progresiva),
                    lastRow.deflexUnitaria
                )   ),
        )
    }

    private fun table3() {
        rows3.clear()

        val newTe = calcProgET().customFormat().roundToInt().roundToFarthestTen()-(cu/2)
        val newCuerdaUnitaria = (calcProgET() - newTe).customFormat()

        var L2 = (newCuerdaUnitaria.pow(2)).customFormat(6)
        val K = (calcThetaE() / calcLe().pow(2)).customFormat(6)
        var theta = (K * L2)

        rows3.add(
            RowTable1_3(
                progresiva = newTe.toDouble(),
                cuerdaUnitaria = newCuerdaUnitaria,
                L = newCuerdaUnitaria,
                L2 = L2,
                K = K,
                theta = theta,
                thetaE = calcThetaE()
            )
        )

        do {
            val lastRow = rows3.last()

            val L = lastRow.L + cu
            L2 = (L.pow(2)).customFormat(6)
            theta = (K * L2)

            rows3.add(
                RowTable1_3(
                    progresiva = lastRow.progresiva - cu,
                    cuerdaUnitaria = cu.toDouble(),
                    L = L,
                    L2 = L2,
                    K = K,
                    theta = theta,
                    thetaE = calcThetaE()
                )
            )
        } while (rows3.last().progresiva >= calcProgCE())

        rows3.removeLast()

        val lastRow = rows3.last()

        val L = lastRow.L + (lastRow.progresiva - calcProgCE()).customFormat()
        L2 = (L.pow(2)).customFormat(6)
        theta = (K * L2)

        rows3.add(
            RowTable1_3(
                progresiva = calcProgCE().customFormat(),
                cuerdaUnitaria = lastRow.progresiva - calcProgCE(),
                L = L,
                L2 = L2,
                K = K,
                theta = theta,
                thetaE = calcThetaE()
            )
        )
    }

    private fun table5() {
        rows5.clear()

        val dProg = calcProgTE().customFormat()
        var newAProg = calcProgTE().customFormat().roundToInt().roundToNearestTen()
        var newL = (newAProg - calcProgTE()).customFormat()
        var theta = calcThetaE(newL)
        var thetaRad = calcThetaRad(newL)
        var x = calcXc(newL, thetaRad)
        var y = calcYc(newL, thetaRad)
        var cl = calcCl(x, y)
        var phi = calcPhiE(y, x)
        var newAz = az1.toDegree() + phi
        var deltaN = calcDeltaN(newL, newAz)
        var deltaE = calcDeltaE(newL, newAz)
        var newN = n + deltaN
        var newE = e + deltaE


        rows5.add(
            RowTable5(
                deProg = dProg,
                L = newL,
                theta = theta,
                thetaRad = thetaRad,
                X = x,
                Y = y,
                CL = cl,
                Phi = phi,
                az = newAz,
                deltaN = deltaN,
                deltaE = deltaE,
                Norte = newN,
                Estte = newE,
                aProg = newAProg.toDouble()

            )
        )

        do {
            val lastRow = rows5.last()

            newAProg = lastRow.aProg.toInt() + cu
            newL = (newAProg - dProg).customFormat()
            theta = calcThetaE(newL)
            thetaRad = calcThetaRad(newL)
            x = calcXc(newL, thetaRad)
            y = calcYc(newL, thetaRad)
            cl = calcCl(x, y)
            phi = calcPhiE(y, x)
            newAz = az1.toDegree() + phi
            deltaN = calcDeltaN(newL, newAz)
            deltaE = calcDeltaE(newL, newAz)
            newN = n + deltaN
            newE = e + deltaE


            rows5.add(
                RowTable5(
                    deProg = dProg,
                    L = newL,
                    theta = theta,
                    thetaRad = thetaRad,
                    X = x,
                    Y = y,
                    CL = cl,
                    Phi = phi,
                    az = newAz,
                    deltaN = deltaN,
                    deltaE = deltaE,
                    Norte = newN,
                    Estte = newE,
                    aProg = newAProg.toDouble()
                )
            )
        } while (rows5.last().aProg <= calcProgEC())
        rows5.removeLast()

        newL = (calcProgEC() - dProg).customFormat()
        theta = calcThetaE(newL)
        thetaRad = calcThetaRad(newL)
        x = calcXc(newL, thetaRad)
        y = calcYc(newL, thetaRad)
        cl = calcCl(x, y)
        phi = calcPhiE(y, x)
        newAz = az1.toDegree() + phi
        deltaN = calcDeltaN(newL, newAz)
        deltaE = calcDeltaE(newL, newAz)
        newN = n + deltaN
        newE = e + deltaE


        rows5.add(
            RowTable5(
                deProg = dProg,
                L = newL,
                theta = theta,
                thetaRad = thetaRad,
                X = x,
                Y = y,
                CL = cl,
                Phi = phi,
                az = newAz,
                deltaN = deltaN,
                deltaE = deltaE,
                Norte = newN,
                Estte = newE,
                aProg = calcProgEC()
            )
        )

        println(rows)
    }

    private fun table6() {
        println("===========================")

        rows6.clear()

        rows6.add(
            RowCoordenadas(
                progresiva = calcProgEC(),
                cuedaUnitaria = 0.0,
                delecUnitaria = 0.0,
                delecAcumulada = 0.0,
                azimut = az1.toDegree() + calcThetaE(),
                dh = 0.0,
                deltaN = 0.0,
                deltaE = 0.0,
                n = (n + (calcTl() * cos(
                    az1.toDegree().degreeToRadian()
                ))) + ((calcTc() * cos((az1.toDegree() + calcThetaE()).degreeToRadian()))),
                e = (e + (calcTl() * sin(
                    az1.toDegree().degreeToRadian()
                ))) + ((calcTc() * sin((az1.toDegree() + calcThetaE()).degreeToRadian()))),

                )
        )


        var newPc = (calcProgEC()+cu).roundToInt().roundToFarthestTen()
        var disPoints = newPc - calcProgEC()
        var lastRow = rows6.last()

        var defUnitaria = table6.calcDeflexionUnitaria(disPoints, rc.toDouble())
        var defAcumulada = table6.calcDeflexionUnitaria(disPoints, rc.toDouble())
        var azimut = defAcumulada + lastRow.azimut
        var deltaN = calcDeltaN(disPoints, azimut)
        var deltaE = calcDeltaE(disPoints, azimut)
        var valueN = lastRow.n + deltaN
        var valueE = lastRow.e + deltaE


        rows6.add(
            RowCoordenadas(
                newPc.toDouble(),
                disPoints,
                defUnitaria,
                defAcumulada,
                azimut,
                disPoints,
                deltaN,
                deltaE,
                valueN,
                valueE
            )
        )

        do {
            lastRow = rows6.last()

            newPc = lastRow.progresiva.toInt() + cu
            disPoints = cu.toDouble()
            defUnitaria = table6.calcDeflexionUnitaria(disPoints, rc.toDouble())
            defAcumulada = lastRow.delecUnitaria + defUnitaria
            azimut = defAcumulada + lastRow.azimut
            deltaN = calcDeltaN(disPoints, azimut)
            deltaE = calcDeltaE(disPoints, azimut)
            valueN = lastRow.n + deltaN
            valueE = lastRow.e + deltaE


            rows6.add(
                RowCoordenadas(
                    newPc.toDouble(),
                    disPoints,
                    defUnitaria,
                    defAcumulada,
                    azimut,
                    disPoints,
                    deltaN,
                    deltaE,
                    valueN,
                    valueE
                )
            )
        } while (rows6.last().progresiva <= calcProgCE())
//
        rows6.removeLast()

        lastRow = rows6.last()

        val newPcD = calcProgCE()
        disPoints = newPcD - lastRow.progresiva
        defUnitaria = table6.calcUlitmaDeflexionUnitaria(cu.toDouble(), disPoints, lastRow.delecUnitaria)
        defAcumulada = lastRow.delecUnitaria + defUnitaria
        azimut = defAcumulada + lastRow.azimut
        deltaN = calcDeltaN(disPoints, azimut)
        deltaE = calcDeltaE(disPoints, azimut)
        valueN = lastRow.n + deltaN
        valueE = lastRow.e + deltaE


        rows6.add(
            RowCoordenadas(
                newPcD, disPoints, defUnitaria, defAcumulada, azimut, disPoints, deltaN, deltaE,
                valueN, valueE
            )
        )

        table.printTable()
    }

    /////////////////////////////////////////////////////////////////////////////
    private fun table7() {


        rows7.clear()


        val dProg = calcProgET().customFormat()

        var newAProg = calcProgET().customFormat().roundToInt().roundToFarthestTen()-(cu/2)
        var newL = (calcProgET() - newAProg).customFormat()
        var theta = calcThetaE(newL)
        var thetaRad = calcThetaRad(newL)
        var x = calcXc(newL, thetaRad)
        var y = calcYc(newL, thetaRad)
        var cl = calcCl(x, y)
        var phi = calcPhiE(y, x)
        var newAz = az2.toDegree() + 180
        var deltaN = calcDeltaN(newL, newAz)
        var deltaE = calcDeltaE(newL, newAz)
        var newN = (n + (calcTe() * cos(
            az1.toDegree().degreeToRadian()
        ))) + ((calcTe() * cos(az2.toDegree().degreeToRadian()))) + deltaN
        var newE = (e + (calcTe() * sin(
            az1.toDegree().degreeToRadian()
        ))) + ((calcTe() * sin(az2.toDegree().degreeToRadian()))) + deltaE

        /// (n + (calcTe() * cos(az1.toDegree().degreeToRadian())))+((calcTe() * cos(az2.toDegree().degreeToRadian())))
        rows7.add(
            RowTable5(
                deProg = dProg,
                L = newL,
                theta = theta,
                thetaRad = thetaRad,
                X = x,
                Y = y,
                CL = cl,
                Phi = phi,
                az = newAz,
                deltaN = deltaN,
                deltaE = deltaE,
                Norte = newN,
                Estte = newE,
                aProg = newAProg.toDouble()
            )
        )

        do {
            val lastRow = rows7.last()

            newAProg = lastRow.aProg.toInt() - cu
            newL = (dProg - newAProg).customFormat()
            theta = calcThetaE(newL)
            thetaRad = calcThetaRad(newL)
            x = calcXc(newL, thetaRad)
            y = calcYc(newL, thetaRad)
            cl = calcCl(x, y)
            phi = calcPhiE(y, x)
            newAz = az2.toDegree() + 180 - phi
            deltaN = calcDeltaN(newL, newAz)
            deltaE = calcDeltaE(newL, newAz)
            newN = (n + (calcTe() * cos(
                az1.toDegree().degreeToRadian()
            ))) + ((calcTe() * cos(az2.toDegree().degreeToRadian()))) + deltaN
            newE = (e + (calcTe() * sin(
                az1.toDegree().degreeToRadian()
            ))) + ((calcTe() * sin(az2.toDegree().degreeToRadian()))) + deltaE


            rows7.add(
                RowTable5(
                    deProg = dProg,
                    L = newL,
                    theta = theta,
                    thetaRad = thetaRad,
                    X = x,
                    Y = y,
                    CL = cl,
                    Phi = phi,
                    az = newAz,
                    deltaN = deltaN,
                    deltaE = deltaE,
                    Norte = newN,
                    Estte = newE,
                    aProg = newAProg.toDouble()
                )
            )
        } while (rows7.last().aProg >= calcProgCE())
        rows7.removeLast()

        newL = (dProg - calcProgCE()).customFormat()
        theta = calcThetaE(newL)
        thetaRad = calcThetaRad(newL)
        x = calcXc(newL, thetaRad)
        y = calcYc(newL, thetaRad)
        cl = calcCl(x, y)
        phi = calcPhiE(y, x)
        newAz = az2.toDegree() + 180 - phi
        deltaN = calcDeltaN(newL, newAz)
        deltaE = calcDeltaE(newL, newAz)
        newN = (n + (calcTe() * cos(
            az1.toDegree().degreeToRadian()
        ))) + ((calcTe() * cos(az2.toDegree().degreeToRadian()))) + deltaN
        newE = (e + (calcTe() * sin(
            az1.toDegree().degreeToRadian()
        ))) + ((calcTe() * sin(az2.toDegree().degreeToRadian()))) + deltaE

        rows7.add(
            RowTable5(
                deProg = dProg,
                L = newL,
                theta = theta,
                thetaRad = thetaRad,
                X = x,
                Y = y,
                CL = cl,
                Phi = phi,
                az = newAz,
                deltaN = deltaN,
                deltaE = deltaE,
                Norte = newN,
                Estte = newE,
                aProg = calcProgCE()
            )
        )

        println(rows)
    }
}