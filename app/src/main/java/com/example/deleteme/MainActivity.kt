package com.example.deleteme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.mkvats.same_string_same_color.colorFromString
import com.mkvats.same_string_same_color.colorFromStringSafe

class MainActivity : AppCompatActivity() {
    private lateinit var textId: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textId = findViewById<TextView>(R.id.qwert)
        testLib()
        testSafeLib()
    }

    private fun testSafeLib() {
        textId.setBackgroundColor(colorFromStringSafe("manish"))
    }

    private fun testLib() {
//        textId.setBackgroundColor(colorFromString("manish"))
    }
}