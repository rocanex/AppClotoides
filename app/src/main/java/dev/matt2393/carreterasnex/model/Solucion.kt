package dev.matt2393.carreterasnex.model

data class Solucion(var lE: Float = 0f,
                    var thetaE: Float = 0f,
                    var aC: Float = 0f,
                    var gC: Float = 0f,
                    var lcc: Float = 0f,
                    var xC: Float = 0f,
                    var yC: Float = 0f,
                    var k: Float = 0f,
                    var p: Float = 0f,
                    var tE: Float = 0f,
                    var tL: Float = 0f,
                    var tC: Float = 0f,
                    var ee: Float = 0f,
                    var cL: Float = 0f,
                    var phiE: Float = 0f,
                    var progTE: Prog = Prog(),
                    var progEC: Prog = Prog(),
                    var progCE: Prog = Prog(),
                    var progET: Prog = Prog()) {
    fun size() = 19
}
