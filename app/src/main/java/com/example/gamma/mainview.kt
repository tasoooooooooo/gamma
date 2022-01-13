package com.example.gamma

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build

import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import kotlin.math.*

class mainview(context: Context, attrs : AttributeSet) : View(context, attrs) {

    //変数定義
    var paint: Paint = Paint()
    var listRed = arrayListOf<Int>()
    var listBlue = arrayListOf<Int>()
    var listGreen = arrayListOf<Int>()
    var listX = arrayListOf<Float>()
    var listY = arrayListOf<Float>()
    //点の大きさ
    private val dotsize : Float = 2F

    fun autodraw (array2D: Array<Array<String?>>) {
        listRed.clear()
        listGreen.clear()
        listBlue.clear()
        listX.clear()
        listY.clear()

        for (j in 0..800) {
            for (k in 0..1023) {
                val num: Int? = array2D[k][j]?.toInt()
                this.listX.add((dotsize/2) * k)
                this.listY.add(dotsize * j)
                if(num != null) {
                    if(num >= -151 && num <= -50) {
                        listRed.add(0)
                        listGreen.add(((num*(2.5)) + 377.5).roundToInt())
                        listBlue.add(255)
                    }
                    if(num > -50 && num <= 52) {
                        listRed.add(0)
                        listGreen.add(255)
                        listBlue.add(((num*(-2.5)) + 130).roundToInt())
                    }
                    if(num > 52 && num <= 153) {
                        listRed.add(((num*(2.5)) - 130).roundToInt())
                        listGreen.add(255)
                        listBlue.add(0)
                    }
                    if(num > 153 && num <= 255) {
                        listRed.add(255)
                        listGreen.add(((num*(-2.5)) + 637.5).roundToInt())
                        listBlue.add(0)
                    }
                }
            }
        }
    }

    fun autodraw2 (array2D: Array<Array<String?>>) {
        listRed.clear()
        listGreen.clear()
        listBlue.clear()
        listX.clear()
        listY.clear()

        for (j in 0..800) {
            for (k in 0..1023) {
                val num: Int? = array2D[k][j]?.toInt()
                this.listX.add((dotsize/2) * k)
                this.listY.add(dotsize * j)
                if(num != null) {
                    if(num >= -151 && num <= -50) {
                        listRed.add(0)
                        listGreen.add(((num*(2.5)) + 377.5).roundToInt())
                        listBlue.add(255)
                    }
                    if(num > -50 && num <= 52) {
                        listRed.add(0)
                        listGreen.add(255)
                        listBlue.add(((num*(-2.5)) + 130).roundToInt())
                    }
                    if(num > 52 && num <= 153) {
                        listRed.add(((num*(2.5)) - 130).roundToInt())
                        listGreen.add(255)
                        listBlue.add(0)
                    }
                    if(num > 153 && num <= 255) {
                        listRed.add(255)
                        listGreen.add(((num*(-2.5)) + 637.5).roundToInt())
                        listBlue.add(0)
                    }
                }
            }
        }
    }

    fun processing (array2D: Array<Array<String?>>) {
        var y_m :Int
        var total :Int = 0
        val x_from :Int = -25
        val x_to :Int = 25
        for(x in 0..800){
            for(y in 0..1023){
                for(n in x_from..x_to){
                    y_m = sqrt(((((x+n)-x)*((x+n)-x))+(y*y)).toDouble()).toInt()
                    if(y<1023+x_from) {
                        if (x <= x_to) {
                            val num: Int? = array2D[y_m][x + abs(n)]?.toInt()
                            total += num!!
                        }else if (x >= 800-x_to) {
                            val num: Int? = array2D[y_m][x - abs(n)]?.toInt()
                            total += num!!
                        } else {
                            val num: Int? = array2D[y_m][x + n]?.toInt()
                            total += num!!
                        }
                    }else{
                        total = 1
                    }
                }
                listX.add((dotsize/2) * y)
                listY.add(dotsize * x)
                if(total >= (-151*50) && total <= (-50*50)) {
                    listRed.add(0)
                    listGreen.add(((total*(2.5/50)) + 377.5).roundToInt())
                    listBlue.add(255)
                }
                if(total > (-50*50) && total <= (52*50)) {
                    listRed.add(0)
                    listGreen.add(255)
                    listBlue.add(((total*(-2.5/50)) + 130).roundToInt())
                }
                if(total > (52*50) && total <= (153*50)) {
                    listRed.add(((total*(2.5/50)) - 130).roundToInt())
                    listGreen.add(255)
                    listBlue.add(0)
                }
                if(total > (153*50) && total <= (255*50)) {
                    listRed.add(255)
                    listGreen.add(((total*(-2.5/50)) + 637.5).roundToInt())
                    listBlue.add(0)
                }
                total = 0
            }
        }

        /*　背景除去処理
        if(hantei == 0) {

            var total: Int = 0

            for (k in 0..1023) {
                for (j in 0..800) {
                    val num: Int? = array2D[k][j]?.toInt()
                    total = total + num!!
                }
                for (j in 0..800) {
                    val num: Int? = array2D[k][j]?.toInt()
                    listX.add((dotsize / 2) * k)
                    listY.add(dotsize * j)
                    if (num != null) {
                        if (num >= -151 && num <= -50) {
                            listRed.add(0)
                            listGreen.add((((num - (total / 801)) * (2.5)) + 377.5).roundToInt())
                            listBlue.add(255)
                        }
                        if (num > -50 && num <= 52) {
                            listRed.add(0)
                            listGreen.add(255)
                            listBlue.add((((num - (total / 801)) * (-2.5)) + 130).roundToInt())
                        }
                        if (num > 52 && num <= 153) {
                            listRed.add((((num - (total / 801)) * (2.5)) - 130).roundToInt())
                            listGreen.add(255)
                            listBlue.add(0)
                        }
                        if (num > 153 && num <= 255) {
                            listRed.add(255)
                            listGreen.add((((num - (total / 801)) * (-2.5)) + 637.5).roundToInt())
                            listBlue.add(0)
                        }
                    }
                }
                total = 0
            }
        }*/
    }


    //削除
    fun sakujo() {
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

