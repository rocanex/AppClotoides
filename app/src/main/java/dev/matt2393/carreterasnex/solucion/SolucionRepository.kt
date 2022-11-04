package dev.matt2393.carreterasnex.solucion

import dev.matt2393.carreterasnex.model.Prog
import kotlin.math.*

class SolucionRepository {
    fun sol1(aCal: Float, rc: Float) = aCal.pow(2) / rc
    fun sol2(le: Float, rc: Float) = (90/ PI * le/rc)
    fun sol3(angle: Float, thetaE: Float) = angle - 2f * thetaE
    fun sol4_1(cu: Float, rc: Float) = 2f * asin(cu/(2*rc))
    fun sol4_2(angle: Float, cu: Float, gc: Float) = cu * (angle*PI/180).toFloat() / gc
    fun sol5_1(thetaE: Float, le: Float) = le * (1-(thetaE* PI/180f).toFloat().pow(2)/10+(thetaE* PI/180f).toFloat().pow(4)/216-(thetaE* PI/180f).toFloat().pow(6)/9360)
    fun sol5_2(thetaE: Float, le: Float) = le * ((thetaE* PI/180f).toFloat()/3-(thetaE* PI/180f).toFloat().pow(3)/42+(thetaE* PI/180f).toFloat().pow(5)/1320-(thetaE* PI/180f).toFloat().pow(7)/75600)
    fun sol6_1(xc: Float, rc: Float, thetaE: Float) = xc - rc * sin((thetaE* PI/180f).toFloat())
    fun sol6_2(yc: Float, rc: Float, thetaE: Float) = yc - rc * (1- cos((thetaE* PI/180f).toFloat()))
    fun sol7(k: Float, rc: Float, p: Float, angle: Float) = k+(rc+p)* tan((angle* PI/180f).toFloat()/2)
    fun sol8_1(xc: Float, yc: Float, thetaE: Float) = xc - (yc/ tan((thetaE* PI/180f).toFloat()))
    fun sol8_2(yc: Float, thetaE: Float) = yc/ sin((thetaE* PI/180f).toFloat())
    fun sol9(rc: Float, p: Float, angle: Float) = (rc+p)/ cos((angle* PI/180f).toFloat()/2) - rc
    fun sol10(xc: Float, yc: Float) = sqrt(xc.pow(2)+yc.pow(2))
    fun sol11(xc: Float, yc: Float) = atan(yc/xc)

    fun sol12_1TE(progPI: Prog, te: Float) = progSolAux(progPI, te)
    fun sol12_2EC(progTE: Prog, le: Float) = progSolAux(progTE, le, true)
    fun sol12_3CE(progEC: Prog, lc: Float) = progSolAux(progEC, lc, true)
    fun sol12_4ET(progCE: Prog, le: Float) = progSolAux(progCE, le, true)

    private fun progSolAux(progAux: Prog, value: Float, isSum: Boolean = false): Prog {
        val progAux2 = Prog()
        progAux2.p1 = progAux.p1
        progAux2.p2 = if(isSum) progAux.p2 + value else progAux.p2 - value
        if(progAux2.p2 < 0) {
            progAux2.p1-=1
            progAux2.p2 += 1000f
        }
        if(progAux2.p2 >= 1000f) {
            progAux2.p1+=1
            progAux2.p2 -= 1000f
        }
        return progAux2
    }



}