package dev.matt2393.carreterasnex.model

import dev.matt2393.carreterasnex.toRoundMill

data class Prog(var p1: Int = 0, var p2: Float = 0f) {
    override fun toString(): String {
        val auxP2 = if(p2 < 100f) "0${p2.toRoundMill()}" else p2.toRoundMill().toString()
        return "$p1 + $auxP2"
    }
}
