package com.example.projeto_integrador.telas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.RadioButton
import com.example.projeto_integrador.R

class EscalaBraden : AppCompatActivity() {
    private val radioGroupMap = mutableMapOf<RadioGroup, Int>()

    var soma: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_escala_braden)
    }

    fun Salvar(view: View){
        val radioGroupIds = listOf(
            R.id.grupradio1,
            R.id.grupradio2,
            R.id.grupradio3,
            R.id.grupradio4,
            R.id.grupradio5,
            R.id.grupradio6
        )
        for (radioGroupId in radioGroupIds) {
            val radioGroup = findViewById<RadioGroup>(radioGroupId)
            val value = radioGroup.tag?.toString()?.toIntOrNull() ?: 0
            radioGroupMap[radioGroup] = value

            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                val selectedValue = radioGroupMap[group]
                val selectedRadioButton = findViewById<RadioButton>(checkedId)
                if (selectedValue != null && selectedRadioButton != null) {
                    soma += selectedValue
                }
            }
        }
        when {
            soma >= 15 -> startActivity(Intent(this, ResultadoEscalaBraden1::class.java))
            soma >= 13 -> startActivity(Intent(this, ResultadoEscalaBraden2::class.java))
            else -> startActivity(Intent(this, ResultadoEscalaBraden3::class.java))
        }
    }
    fun voltarTela(view: View) {
        finish()
    }
}
