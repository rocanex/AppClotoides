package dev.matt2393.carreterasnex.solucion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.matt2393.carreterasnex.model.AllSolution
import dev.matt2393.carreterasnex.model.Prog
import dev.matt2393.carreterasnex.model.Reporte
import dev.matt2393.carreterasnex.model.Solucion
import kotlinx.coroutines.launch


class SolucionViewModel(private val solucionRepository: SolucionRepository = SolucionRepository()): ViewModel() {
    val allSol = MutableLiveData<AllSolution>()

    fun solucionar( rc: Float, angle: Float, cu: Float,aCal:Float, n: Int, a: Float, v: Float, progPI: Prog) {
        viewModelScope.launch {
            val reporte = Reporte()

            val le = solucionRepository.sol1(aCal, rc)
            reporte.lE = Reporte.Le(aCal, rc, le)
            val thetaE: Float = solucionRepository.sol2(le, rc).toFloat()
            reporte.thetaE = Reporte.ThetaE(le, rc, thetaE)
            val aC: Float = solucionRepository.sol3(angle, thetaE)
            reporte.ac = Reporte.Ac(angle, thetaE, aC)
            val gC: Float = solucionRepository.sol4_1(cu, rc)
            reporte.gc = Reporte.Gc(cu, rc, gC)
            val lcc: Float = solucionRepository.sol4_2(angle, cu, gC)
            reporte.lcc = Reporte.Lcc(angle, cu, gC, lcc)
            val xC: Float = solucionRepository.sol5_1(thetaE, le)
            reporte.xc = Reporte.Xc(thetaE, le, xC)
            val yC: Float = solucionRepository.sol5_2(thetaE, le)
            reporte.yc = Reporte.Yc(thetaE, le, yC)
            val k: Float = solucionRepository.sol6_1(xC, rc, thetaE)
            reporte.k = Reporte.K(xC, rc, thetaE, k)
            val p: Float = solucionRepository.sol6_2(yC, rc, thetaE)
            reporte.p = Reporte.P(yC, rc, thetaE, p)
            val tE: Float = solucionRepository.sol7(k, rc, p, angle)
            reporte.te = Reporte.Te(k, rc, p, angle, tE)
            val tL: Float = solucionRepository.sol8_1(xC, yC, thetaE)
            reporte.tl = Reporte.Tl(xC, yC, thetaE, tL)
            val tC: Float = solucionRepository.sol8_2(yC, thetaE)
            reporte.tc = Reporte.Tc(yC, thetaE, tC)
            val ee: Float = solucionRepository.sol9(rc, p, angle)
            reporte.ee = Reporte.Ee(rc, p, angle, ee)
            val cL: Float = solucionRepository.sol10(xC, yC)
            reporte.cl = Reporte.Cl(xC, yC, cL)
            val phiE: Float = solucionRepository.sol11(xC, yC)
            reporte.phiE = Reporte.Phie(xC, yC, phiE)
            val progTE: Prog = solucionRepository.sol12_1TE(progPI, tE)
            reporte.prTe = Reporte.PrTE(progPI, tE, progTE)
            val progEC: Prog = solucionRepository.sol12_2EC(progTE, le)
            reporte.prEc = Reporte.PrEC(progTE, le, progEC)
            val progCE: Prog = solucionRepository.sol12_3CE(progEC, lcc)
            reporte.prCe = Reporte.PrCE(progEC, lcc, progCE)
            val progET: Prog = solucionRepository.sol12_4ET(progCE, le)
            reporte.prEt = Reporte.PrET(progCE, le, progET)
            val solucion = Solucion(
              le, thetaE, aC, gC, lcc, xC, yC, k, p, tE, tL, tC, ee, cL, phiE,
                progTE, progEC, progCE, progET
            )
            val allSolution = AllSolution(solucion = solucion, reporte = reporte)
            allSol.value = allSolution

        }
    }
}