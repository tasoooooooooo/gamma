package com.example.gamma

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import java.io.*
import kotlin.collections.ArrayList
import kotlin.math.*

class  MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //変数定義
        var _red: Int = 0
        var _blue: Int = 0
        var _green: Int = 0
        var f_x: Float = 0.0f
        var f_y: Float = 0.0f

        //ボタン
        val button_back = findViewById(R.id.button_back) as Button
        val button_byouga = findViewById(R.id.button_byouga) as Button
        val button_renzoku = findViewById(R.id.button_test) as Button
        val button_setting = findViewById(R.id.button_setting) as Button

        //深度テキスト
        val y_1 = findViewById(R.id.y_1) as TextView
        val y_2 = findViewById(R.id.y_2) as TextView
        val y_3 = findViewById(R.id.y_3) as TextView
        val y_4 = findViewById(R.id.y_4) as TextView

        //横
        val x_1 = findViewById(R.id.x_1) as TextView
        val x_2 = findViewById(R.id.x_2) as TextView
        val x_3 = findViewById(R.id.x_3) as TextView
        val x_4 = findViewById(R.id.x_4) as TextView
        val x_5 = findViewById(R.id.x_5) as TextView

        //カスタムView
        var mycanvas = findViewById<mainview>(R.id.testtest)
        var mycanvas2 = findViewById<sample>(R.id.sample)
        var mycanvas3 = findViewById<sample2>(R.id.sample2)

        //データ配列作成
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

        //設定項目表示
        val sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(this /* Activity context */)
        val hiyuudennritu = sharedPreferences.getString("signature", "")
        val sokutei = sharedPreferences.getString("reply4", "")
        val color = sharedPreferences.getString("reply", "")
        val hyouzi = sharedPreferences.getString("reply2", "")
        val kaityou = sharedPreferences.getString("reply3", "")
        val kannkaku = sharedPreferences.getString("yokokannkaku", "")

        var helloText: TextView = findViewById(R.id.hiyuudennritu)
        helloText.text =
            "比誘電率     : " + hiyuudennritu + "\n" + "測定方式     : " + sokutei + "\n" + "表示モード : " + hyouzi + "\n" + "カラー種別 : " + color + "\n" + "階調方式     : " + kaityou

        //深度スケール変更
        val c = 2.9*(10.0.pow(8))
        val yuudenn = hiyuudennritu?.toDouble()
        val tau1 = (256*0.01*2)*(10.0.pow(-9))
        val tau2 = (512*0.01*2)*(10.0.pow(-9))
        val tau3 = (768*0.01*2)*(10.0.pow(-9))
        val tau4 = (1024*0.01*2)*(10.0.pow(-9))
        val R1 = ((tau1*c)/(2* sqrt(yuudenn!!)))*100
        val R2 = ((tau2*c)/(2* sqrt(yuudenn!!)))*100
        val R3 = ((tau3*c)/(2* sqrt(yuudenn!!)))*100
        val R4 = ((tau4*c)/(2* sqrt(yuudenn!!)))*100
        y_1.text = R1.toString()
        y_2.text = R2.toString()
        y_3.text = R3.toString()
        y_4.text = R4.toString()

        //走査スケール変更
        val sousa = kannkaku?.toDouble()
        val r1 = (sousa?.times(801))!! / 6
        val r2 = 2*(sousa?.times(801))!! / 6
        val r3 = 3*(sousa?.times(801))!! / 6
        val r4 = 4*(sousa?.times(801))!! / 6
        val r5 = 5*(sousa?.times(801))!! / 6
        x_1.text = r1.toString()
        x_2.text = r2.toString()
        x_3.text = r3.toString()
        x_4.text = r4.toString()
        x_5.text = r5.toString()




        //ボタンが押されたとき
        button_back.setOnClickListener {
            mycanvas.sakujo()
            mycanvas.invalidate()
            mycanvas2.sakujo2()
            mycanvas2.invalidate()
            mycanvas3.sakujo3()
            mycanvas3.invalidate()
        }

        button_renzoku.setOnClickListener {
            mycanvas.processing(array2D)
            mycanvas.invalidate()
        }

        button_byouga.setOnClickListener {
            if (kaityou == "オフセット") {
                mycanvas2.sample(_red, _green, _blue, f_x, f_y)
                mycanvas2.invalidate()
                mycanvas.autodraw(array2D)
                mycanvas.invalidate()
            }
            if (kaityou == "絶対値"){
                mycanvas2.sample2(_red, _green, _blue, f_x, f_y)
                mycanvas2.invalidate()
                //mycanvas.autodraw2(array2D)
                //mycanvas.invalidate()
            }
        }
        
        button_setting.setOnClickListener{
            val intent = Intent(application, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onTouchEvent(event: MotionEvent) :Boolean {
        var mycanvas3 = findViewById<sample2>(R.id.sample2)
        //変数定義
        var _red: Int = 0
        var _blue: Int = 0
        var _green: Int = 0
        var f_x: Float = 0.0f
        var f_y: Float = 0.0f
        var x : Int
        var y : Int
        x = event.getX().toInt()
        y = event.getY().toInt()

        mycanvas3.sample2(_red, _green, _blue, f_x, f_y, x, y)
        mycanvas3.invalidate()

        return super.onTouchEvent(event)

        /*タッチした座標表示
        val cord : TextView = findViewById(R.id.zahyou)
        var x1 : Int
        var y1 : Int

        when(event.getAction()) {
            MotionEvent.ACTION_DOWN -> {
                x1 = event.getX().toInt()
                y1 = event.getY().toInt()
                cord.text = "($x1 , $y1)"
            }
            MotionEvent.ACTION_UP -> {
                x1 = (event.getX().toInt())
                y1 = (event.getY().toInt())
                cord.text = "($x1, $y1)"
            }
        }
        return super.onTouchEvent(event)
        */

    }
}