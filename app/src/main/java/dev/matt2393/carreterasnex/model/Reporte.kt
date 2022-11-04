package dev.matt2393.carreterasnex.model

import dev.matt2393.carreterasnex.*

data class Reporte(var lE: Le = Le(),
                   var thetaE: ThetaE = ThetaE(),
                   var ac: Ac = Ac(),
                   var gc: Gc = Gc(),
                   var lcc: Lcc = Lcc(),
                   var xc: Xc = Xc(),
                   var yc: Yc = Yc(),
                   var k: K = K(),
                   var p: P = P(),
                   var te: Te = Te(),
                   var tl: Tl = Tl(),
                   var tc: Tc = Tc(),
                   var ee: Ee = Ee(),
                   var cl: Cl= Cl(),
                   var phiE: Phie = Phie(),
                   var prTe: PrTE = PrTE(),
                   var prEc: PrEC = PrEC(),
                   var prCe: PrCE = PrCE(),
                   var prEt: PrET = PrET(),) {

    fun size() = 19
    data class Le(var aCal: Float = 0f, var rc: Float= 0f, val res: Float = 0f) {
        fun formatMath() =  ArrayList<String>().apply {
            add("\$Le = \\frac{A^2}{Rc} = \\frac{${aCal}^2}{${rc}} = ${res.toRoundMillMetherString()}\$")
        }
    }
    data class ThetaE(var le: Float = 0f, var rc: Float= 0f, val res: Float = 0f) {
        fun formatMath() =  ArrayList<String>().apply {
            add("\$θe = \\frac{90º}{π} * \\frac{Le}{Rc} = \\frac{90º}{π} * \\frac{${le.toRoundMill()}}{${rc.toRoundMill()}}\$")
            add("\$θe = ${res.toFormatSexadecimal()}\$")
        }
    }
    data class Ac(var angle: Float = 0f, var thetaE: Float= 0f, val res: Float = 0f) {
        fun formatMath() =  ArrayList<String>().apply {
            add("\$∆e = ∆º - 2 θe\$")
            add("\$∆e = ${angle.toFormatSexadecimal()} - 2(${thetaE.toFormatSexadecimal()}) \$")
            add("\$∆e = ${res.toFormatSexadecimal()}\$")
        }
    }
    data class Gc(var cu: Float = 0f, var rc: Float = 0f, val res: Float = 0f){
        fun formatMath() =  ArrayList<String>().apply {
            add("\$Gºc = 2 arcosen(\\frac{Cu}{2 Rc})\$")
            add("\$Gºc = 2 arcosen(\\frac{${cu.toRoundMill()}}{2 * ${rc.toRoundMill()}})\$")
            add("\$Gºc = ${res.toSexadecimal().toFormatSexadecimal()}\$")
        }
    }
    data class Lcc(var angle: Float = 0f, var cu: Float = 0f, var gc: Float =0f, val res: Float = 0f){
        fun formatMath() =  ArrayList<String>().apply {
            add("\$Lcc = ∆ * \\frac{Cu}{Gºc}\$")
            add("\$Lcc = ∆ * \\frac{${cu.toRoundMill()}}{${gc.toSexadecimal().toFormatSexadecimal()}}\$")
            add("\$Lcc = ${res.toRoundMillMetherString()}\$")
        }
    }
    data class Xc(var thetaE: Float = 0f, var le: Float = 0f, val res: Float = 0f){
        fun formatMath() =  ArrayList<String>().apply {
            add("\$Xc = Le * [1 - \\frac{θe^2}{10} + \\frac{θe^4}{216} - \\frac{θe^6}{9360}]\$")
            add("\$Xc = Le * [1 - \\frac{${thetaE.toRad().toRoundMill()}^2}{10} + \\frac{${thetaE.toRad().toRoundMill()}^4}{216} - \\frac{${thetaE.toRad().toRoundMill()}^6}{9360}]\$")
            add("\$Xc = ${res.toRoundMillMetherString()}\$")
        }
    }
    data class Yc(var thetaE: Float = 0f, var le: Float = 0f, val res: Float = 0f){
        fun formatMath() =  ArrayList<String>().apply {
            add("\$Yc = Le * [\\frac{θe}{3} - \\frac{θe^3}{42} + \\frac{θe^5}{1320} - \\frac{θe^7}{75600}]\$")
            add("\$Yc = Le * [\\frac{${thetaE.toRad().toRoundMill()}}{3} - \\frac{${thetaE.toRad().toRoundMill()}^3}{42} + \\frac{${thetaE.toRad().toRoundMill()}^5}{1320} - \\frac{${thetaE.toRad().toRoundMill()}^7}{75600}]\$")
            add("\$Yc = ${res.toRoundMillMetherString()}\$")
        }
    }
    data class K(var xc: Float = 0f, var rc: Float = 0f, var thetaE: Float = 0f, val res: Float = 0f){
        fun formatMath() =  ArrayList<String>().apply {
            add("\$K = Xc - Rc * sen(θe)\$")
            add("\$K = ${xc.toRoundMill()} - ${rc.toRoundMill()} * sen(${thetaE.toFormatSexadecimal()})\$")
            add("\$Yc = ${res.toRoundMillMetherString()}\$")
        }
    }
    data class P(var yc: Float = 0f, var rc: Float = 0f, var thetaE: Float = 0f, val res: Float = 0f){
        fun formatMath() =  ArrayList<String>().apply {
            add("\$P = Yc - Rc * (1- cos(θe))\$")
            add("\$P = ${yc.toRoundMill()} - ${rc.toRoundMill()} * (1 - cos(${thetaE.toFormatSexadecimal()}))\$")
            val auxText = if(res > 0.5f) " > 0.5 [m]  OK Cumple" else " < 0.5 [m]  No Cumple"
            add("\$P = ${res.toRoundMillMetherString()}$auxText\$")
        }
    }
    data class Te(var k: Float = 0f, var rc: Float = 0f, var p: Float = 0f, var angle: Float = 0f, val res: Float = 0f){
        fun formatMath() =  ArrayList<String>().apply {
            add("\$Te = K + (Rc + P) * tan(\\frac{∆}{2})\$")
            add("\$Te = ${k.toRoundMill()} + (${rc.toRoundMill()} + ${p.toRoundMill()}) * tan(\\frac{${angle.toFormatSexadecimal()}}{2})\$")
            add("\$Te = ${res.toRoundMillMetherString()}\$")
        }
    }
    data class Tl(var xc: Float = 0f, var yc: Float = 0f, var thetaE: Float = 0f, val res: Float = 0f){
        fun formatMath() =  ArrayList<String>().apply {
            add("\$Tl = Xc - \\frac{Yc}{tan(θe)}\$")
            add("\$Tl = ${xc.toRoundMill()} - \\frac{${yc.toRoundMill()}}{tan(${thetaE.toFormatSexadecimal()})}\$")
            add("\$Tl = ${res.toRoundMillMetherString()}\$")
        }
    }
    data class Tc(var yc: Float = 0f, var thetaE: Float = 0f, val res: Float = 0f){
        fun formatMath() =  ArrayList<String>().apply {
            add("\$Tc = \\frac{Yc}{sen(θe)}\$")
            add("\$Tc = \\frac{${yc.toRoundMill()}}{sen(${thetaE.toFormatSexadecimal()})}\$")
            add("\$Tc = ${res.toRoundMillMetherString()}\$")
        }
    }
    data class Ee(var rc: Float =0f, var p: Float = 0f, var angle: Float = 0f, val res: Float = 0f){
        fun formatMath() =  ArrayList<String>().apply {
            add("\$Ee = \\frac{Rc + P}{cos(\\frac{∆}{2})} - Rc\$")
            add("\$Ee = \\frac{${rc.toRoundMill()} + ${p.toRoundMill()}}{cos(\\frac{${angle.toFormatSexadecimal()}}{2})} - ${rc.toRoundMill()}\$")
            add("\$Ee = ${res.toRoundMillMetherString()}\$")
        }
    }
    data class Cl(var xc: Float = 0f, var yc: Float = 0f, val res: Float = 0f){
        fun formatMath() =  ArrayList<String>().apply {
            add("\$Cl = \\sqrt{Xc^2 + Yc^2}\$")
            add("\$Cl = \\sqrt{${xc.toRoundMill()}^2 + ${yc.toRoundMill()}^2}\$")
            add("\$Cl = ${res.toRoundMillMetherString()}\$")
        }
    }
    data class Phie(var xc: Float = 0f, var yc: Float = 0f, val res: Float = 0f){
        fun formatMath() =  ArrayList<String>().apply {
            add("\$φe = arctan(\\frac{Yc}{Xc})\$")
            add("\$φe = arctan(\\frac{${yc.toRoundMill()}}{${xc.toRoundMill()}})\$")
            add("\$φe = ${res.toSexadecimal().toRoundMillMetherString()}\$")
        }
    }

    data class PrTE(var progPI: Prog = Prog(), var te: Float = 0f, val res: Prog = Prog()){
        fun formatMath() =  ArrayList<String>().apply {
            add("\$ProgTE = ProgPI - Te\$")
            add("\$ProgTE = $progPI - ${te.toRoundMill()}\$")
            add("\$ProgTE = ${res}\$")
        }
    }
    data class PrEC(var progTE: Prog = Prog(), var le: Float = 0f, val res: Prog = Prog()){
        fun formatMath() =  ArrayList<String>().apply {
            add("\$ProgEC = ProgTE + Le\$")
            add("\$ProgEC = $progTE + ${le.toRoundMill()}\$")
            add("\$ProgEC = ${res}\$")
        }
    }
    data class PrCE(var progEC: Prog = Prog(), var lc: Float = 0f, val res: Prog = Prog()){
        fun formatMath() =  ArrayList<String>().apply {
            add("\$ProgCE = ProgEC + Lc\$")
            add("\$ProgCE = $progEC + ${lc.toRoundMill()}\$")
            add("\$ProgCE = ${res}\$")
        }
    }
    data class PrET(var progCE: Prog = Prog(), var le: Float = 0f, val res: Prog = Prog()){
        fun formatMath() =  ArrayList<String>().apply {
            add("\$ProgET = ProgCE + Le\$")
            add("\$ProgET = $progCE + ${le.toRoundMill()}\$")
            add("\$ProgET = ${res}\$")
        }
    }
}
