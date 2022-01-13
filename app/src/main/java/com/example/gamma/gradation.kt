package com.example.gamma

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build

import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import kotlinx.coroutines.processNextEventInCurrentThread
import kotlin.math.*

class sample(context: Context, attrs : AttributeSet) : View(context, attrs) {

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
    private val dotsize : Float = 1.58F

    //MainActivityから引数をもらう
    fun sample ( red :Int ,  green :Int ,  blue :Int ,  f_x :Float ,  f_y :Float)
    {
        listRed.clear()
        listGreen.clear()
        listBlue.clear()
        listX.clear()
        listY.clear()

        for(j in 0..131){
            if(j in 0..42) {
                for(k in 0..40) {
                    listX.add(dotsize * k)
                    listY.add(dotsize * j)
                    listRed.add(0)
                    listBlue.add(255)
                    listGreen.add(((j * 6)))
                }
            }
            if(j in 43..65) {
                for(k in 0..40) {
                    listX.add(dotsize * k)
                    listY.add(dotsize * j)
                    listRed.add(0)
                    listBlue.add((((j * (-7.7)) + 508.2).roundToInt()))
                    listGreen.add(255)
                }
            }
            if(j in 66..98) {
                for(k in 0..40) {
                    listX.add(dotsize * k)
                    listY.add(dotsize * j)
                    listRed.add((((j * (7.7)) - 508.2).roundToInt()))
                    listBlue.add(0)
                    listGreen.add(255)
                }
            }
            if(j in 99..130) {
                for(k in 0..40) {
                    listX.add(dotsize * k)
                    listY.add(dotsize * j)
                    listRed.add(255)
                    listBlue.add(0)
                    listGreen.add(((j * (-7.7)) + 1008.7).toFloat().roundToInt())
                }
            }
        }
    }

    fun sample2 ( red :Int ,  green :Int ,  blue :Int ,  f_x :Float ,  f_y :Float)
    {
        listRed.clear()
        listGreen.clear()
        listBlue.clear()
        listX.clear()
        listY.clear()

        for(j in 0..131){
            if(j in 0..21) {
                for(k in 0..40) {
                    listX.add(dotsize * k)
                    listY.add(dotsize * j)
                    listRed.add(0)
                    listBlue.add(255)
                    listGreen.add(((j * 6)))
                }
            }
            if(j in 22..32) {
                for(k in 0..40) {
                    listX.add(dotsize * k)
                    listY.add(dotsize * j)
                    listRed.add(0)
                    listBlue.add((((j * (-7.7)) + 508.2).roundToInt()))
                    listGreen.add(255)
                }
            }
            if(j in 33..49) {
                for(k in 0..40) {
                    listX.add(dotsize * k)
                    listY.add(dotsize * j)
                    listRed.add((((j * (7.7)) - 508.2).roundToInt()))
                    listBlue.add(0)
                    listGreen.add(255)
                }
            }
            if(j in 50..65) {
                for(k in 0..40) {
                    listX.add(dotsize * k)
                    listY.add(dotsize * j)
                    listRed.add(255)
                    listBlue.add(0)
                    listGreen.add(((j * (-7.7)) + 1008.7).toFloat().roundToInt())
                }
            }
            if(j in 66..81) {
                for(k in 0..40) {
                    listX.add(dotsize * k)
                    listY.add(dotsize * j)
                    listRed.add(0)
                    listBlue.add(255)
                    listGreen.add(((j * 6)))
                }
            }
            if(j in 82..98) {
                for(k in 0..40) {
                    listX.add(dotsize * k)
                    listY.add(dotsize * j)
                    listRed.add(0)
                    listBlue.add((((j * (-7.7)) + 508.2).roundToInt()))
                    listGreen.add(255)
                }
            }
            if(j in 99..115) {
                for(k in 0..40) {
                    listX.add(dotsize * k)
                    listY.add(dotsize * j)
                    listRed.add((((j * (7.7)) - 508.2).roundToInt()))
                    listBlue.add(0)
                    listGreen.add(255)
                }
            }
            if(j in 116..131) {
                for(k in 0..40) {
                    listX.add(dotsize * k)
                    listY.add(dotsize * j)
                    listRed.add(255)
                    listBlue.add(0)
                    listGreen.add(((j * (-7.7)) + 1008.7).toFloat().roundToInt())
                }
            }

        }
    }

    fun sakujo2() {
        listRed.clear()
        listBlue.clear()
        listGreen.clear()
        listX.clear()
        listY.clear()
    }

    @RequiresApi(Build.VERSION_CODES.O)
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

                paint.color = Color.rgb(listRed[i-1],listGreen[i-1],listBlue[i-1])
                canvas.drawPoint(listX[i-1], listY[i-1], paint)
            }
        }
    }
}