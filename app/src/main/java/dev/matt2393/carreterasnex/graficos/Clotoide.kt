package dev.matt2393.carreterasnex.graficos

import android.graphics.Color
import android.graphics.Paint
import com.aghajari.graphview.AXGraphCanvas
import com.aghajari.graphview.AXGraphFormula
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class Clotoide(var tam: Float,
               var angle: Float,
               var angleC: Float,
               var thetaE: Float,
               var lE: Float, var lC: Float,
               var k: Float, var tE: Float,
               var ee: Float): AXGraphFormula() {
    override fun onDraw(canvas: AXGraphCanvas?): Boolean {
        val x = 0f
        val y = 0f

        val auxAngle = (PI/2.0).toFloat()
        var x1 = tam * cos(auxAngle - angleC/2f)
        var y1 = tam * sin(auxAngle - angleC/2f)
        var x2 = tam * cos(auxAngle + angleC/2f)
        var y2 = tam * sin(auxAngle + angleC/2f)

        var x3 = tam * cos(auxAngle - angle/2f)
        var y3 = tam * sin(auxAngle - angle/2f)
        var x4 = tam * cos(auxAngle + angle/2f)
        var y4 = tam * sin(auxAngle + angle/2f)
        canvas?.drawLine(x,y, x1,y1)
        canvas?.drawLine(x,y, x2,y2)
        canvas?.drawLine(x,y, x3,y3)
        canvas?.drawLine(x,y, x4,y4)

        canvas?.drawText("∆c", 0f, tam/2f)

        val yMax = tam + ee

        val paint1 = Paint()
        paint1.strokeWidth = 5f

        /*graphPaint.style = Paint.Style.STROKE
        pointPaint.color = graphPaint.color
        pointCircleRadius *= 1.2f*/
        paint1.color = Color.BLACK
        canvas?.drawLine(0f, yMax, x3, y3, paint1)
        canvas?.drawLine(0f, yMax, x4, y4, paint1)
       /* canvas?.drawLine(0f,0f, 0f, tam)
        var auxAngle = (90f-30f)* PI/180f//angleC / 2f
        val x1 = tam * cos(auxAngle).toFloat()
        val y1 = tam * sin(auxAngle).toFloat()
        canvas?.drawLine(0f,0f, x1, y1)
        auxAngle = (90f-45f)* PI/180f//angleC / 2f
        val x2 = tam * cos(auxAngle).toFloat()
        val y2 = tam * sin(auxAngle).toFloat()
        canvas?.drawLine(0f,0f, x2, y2)
        auxAngle = (90f-60f)* PI/180f//angleC / 2f
        val x3 = tam * cos(auxAngle).toFloat()
        val y3 = tam * sin(auxAngle).toFloat()
        canvas?.drawLine(0f,0f, x3, y3)*/

       /* for (i in 0..60) {
            x = tam * cos(i*PI/180.0).toFloat()
            y = tam * sin(i*PI/180.0).toFloat()
            canvas?.drawPoint(x,y)
        }*/
        return true
    }
    override fun function(x: Float): Float {
        return Float.MAX_VALUE
    }
}