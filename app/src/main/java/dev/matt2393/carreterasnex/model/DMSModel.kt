package dev.matt2393.carreterasnex.model

import dev.matt2393.carreterasnex.toDMS

data class DMSModel(val degrees: Int, val minutes: Int, val seconds: Double) {
    fun toDegree(): Double = degrees + (minutes / 60.0) + (seconds / 3600)

    fun add(newData: DMSModel): DMSModel {
        val response = toDegree() + newData.toDegree()
        return response.toDMS()
    }

    fun minus(newData: DMSModel): DMSModel {
        val response = toDegree() - newData.toDegree()
        return response.toDMS()
    }

    fun divide(newData: DMSModel): DMSModel {
        val response = toDegree() / newData.toDegree()
        return response.toDMS()
    }

    override fun toString(): String = "${degrees}º $minutes' $seconds\""
}