package com.example.gamma

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import com.example.gamma.R.string.signature_title
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.*

class sample2(context: Context, attrs : AttributeSet) : View(context, attrs) {

    var paint: Paint = Paint()
    var listRed = arrayListOf<Int>()
    var listBlue = arrayListOf<Int>()
    var listGreen = arrayListOf<Int>()
    var listX = arrayListOf<Float>()
    var listY = arrayListOf<Float>()

    private var reds : Int = 0
    private var blues :Int = 0
    private var greens :Int =0
    private var r : Int = 0
    private var g : Int = 0
    private var b : Int = 0
    private var _x :Float = 0f
    private var _y :Float = 0f
    //点の大きさ
    private val dotsize : Float = 7F

    //MainActivityから引数をもらう
    fun sample2 ( red :Int ,  green :Int ,  blue :Int ,  f_x :Float ,  f_y :Float , x :Int ,y :Int)
    {

        listRed.clear()
        listGreen.clear()
        listBlue.clear()
        listX.clear()
        listY.clear()

        r = red
        g = green
        b = blue

        val file = resources.assets.open("test1.csv")
        val br = BufferedReader(InputStreamReader(file))
        val list: MutableList<MutableList<String>> = ArrayList()
        var line = br.readLine()
        val headers = line.split(",").toTypedArray()
        for (header in headers) {
            val subList: MutableList<String> = ArrayList()
            subList.add(header)
            list.add(subList)
        }
        while (br.readLine().also { line = it } != null) {
            val elems = line.split(",").toTypedArray()
            for (i in elems.indices) {
                list[i].add(elems[i])
            }
        }
        br.close()
        val rows = list.size
        val cols = list[0].size
        val array2D = Array(rows) {
            arrayOfNulls<String>(
                cols
            )
        }
        for (row in 0 until rows) {
            for (col in 0 until cols) {
                array2D[row][col] = list[row][col]
            }
        }


        for (k in 0..1023) {
            val num: Float? = array2D[k][(y-800)/2]?.toFloat()
            if(num != null) {
                listX.add(((dotsize/6.8) * (k)).toFloat())
                listY.add((dotsize/16) * ((num)+240))
            }
            listRed.add(0)
            listBlue.add(0)

        }
    }

    fun sakujo3() {
        listRed.clear()
        listBlue.clear()
        listGreen.clear()
        listX.clear()
        listY.clear()


    }

    override fun onDraw(canvas: Canvas) {

        //背景色設定
        canvas.drawColor(Color.argb(100, 50, 50, 100))

        paint.isAntiAlias = false
        paint.strokeWidth = dotsize
        // Styleのストロークを設定する
        paint.style = Paint.Style.STROKE
        // ペイントする色の設定

        // drawPointを使って点を描画する

        //もしlistの中に何もなければ何もしない

        if(listRed.count() == 0){

        }else{
            //listの要素数だけfor文で描画する
            //listRedの要素数だけ見てるので他の要素数と違うと多分落ちる

            for (i in 1..listRed.count()){

                paint.color = Color.rgb(listRed[i-1],0,listBlue[i-1])
                canvas.drawPoint(listX[i-1], listY[i-1], paint)
            }
        }
    }
}