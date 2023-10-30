package com.example.projeto_integrador.telas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.projeto_integrador.R

class AvaliacaoLesaoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avaliacao_lesao)
    }

    fun Estagio1(view: View){
        val intent = Intent(this, ResultadoEstagio1::class.java)
        startActivity(intent)
    }
    fun Estagio2(view: View){
        val intent = Intent(this, ResultadoEstagio2::class.java)
        startActivity(intent)
    }
    fun Estagio3(view: View){
        val intent = Intent(this, ResultadoEstagio3::class.java)
        startActivity(intent)
    }
    fun Estagio4(view: View){
        val intent = Intent(this, ResultadoEstagio4::class.java)
        startActivity(intent)
    }

    fun voltarTela(view: View) {
        finish()
    }
}