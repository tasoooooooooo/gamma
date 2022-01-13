package com.example.gamma

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import kotlin.math.pow
import kotlin.math.sqrt

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)

        val button_kousinn = findViewById(R.id.button) as Button

        button_kousinn.setOnClickListener{
            val sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this /* Activity context */)
            val hiyuudennritu = sharedPreferences.getString("signature", "")
            val sokutei = sharedPreferences.getString("reply4", "")
            val color = sharedPreferences.getString("reply", "")
            val hyouzi = sharedPreferences.getString("reply2", "")
            val kaityou = sharedPreferences.getString("reply3", "")

            var helloText: TextView = findViewById(com.example.gamma.R.id.hiyuudennritu)
            helloText.text =
                "比誘電率     : " + hiyuudennritu + "\n" + "測定方式     : " + sokutei + "\n" + "表示モード : " + hyouzi + "\n" + "カラー種別 : " + color + "\n" + "階調方式     : " + kaityou
        }

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.settings, SettingsFragment())
                    .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.prifs, rootKey)


        }
    }
}