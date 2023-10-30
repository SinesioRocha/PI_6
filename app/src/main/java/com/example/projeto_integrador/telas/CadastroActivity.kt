package com.example.projeto_integrador.telas

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import com.example.projeto_integrador.R
import com.example.projeto_integrador.model.Paciente
import com.google.gson.Gson

class CadastroActivity : AppCompatActivity() {

    lateinit var hipertensao: CheckBox
    lateinit var diabetes: CheckBox
    lateinit var neurologica: CheckBox
    lateinit var outros: CheckBox
    lateinit var urinaria: CheckBox
    lateinit var fecal: CheckBox
    lateinit var naoApresenta: CheckBox
    lateinit var genero : Spinner
    lateinit var acamado : Spinner
    lateinit var contencao : Spinner
    lateinit var conciencia : Spinner
    lateinit var dependencia : Spinner

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        var buttonGravar = findViewById<Button>(R.id.btnSalvar)


        var nome = findViewById<EditText>(R.id.editNome)
        var idade = findViewById<EditText>(R.id.editIdade)
        var tempInstitucinalizado = findViewById<EditText>(R.id.editInstitucionalizado)
        var altura = findViewById<EditText>(R.id.editAltura)
        var peso = findViewById<EditText>(R.id.editPeso)
        genero = findViewById<Spinner>(R.id.spinnerGenero)
        acamado = findViewById<Spinner>(R.id.spinnerAcamado)
        contencao = findViewById<Spinner>(R.id.spinnerContencao)
        conciencia = findViewById<Spinner>(R.id.spinnerConsciencia)
        var dependencia = findViewById<Spinner>(R.id.spinnerDependencia)

        buttonGravar.setOnClickListener {
            val paciente = Paciente()
            paciente.nome = nome.text.toString()
            paciente.idade = idade.text.toString().toIntOrNull() ?: 0
            paciente.tep_internado = tempInstitucinalizado.text.toString().toIntOrNull() ?: 0
            paciente.altura = altura.text.toString().toDoubleOrNull() ?: 0.0
            paciente.peso = peso.text.toString().toDoubleOrNull() ?: 0.0
            paciente.genero = genero.selectedItem.toString()
            paciente.acamado = acamado.selectedItem.toString()
            paciente.contencao = contencao.selectedItem.toString()
            paciente.consciencia = conciencia .selectedItem.toString()
            paciente.dependencia = dependencia.selectedItem.toString()
            paciente.doencaPreexistentes = getDoencaPreexistentes()
            paciente.incontinencia = getIncontinencia()

            //convertendo paciente para Json
            val gson = Gson()
            val pacienteJson = gson.toJson(paciente)

            println(pacienteJson)
        }
        setupSpinners()
    }

    private fun setupSpinners() {
        val generoArray = arrayOf("--SELECIONE--", "Mulher", "Homem", "Não Informar")
        genero = findViewById(R.id.spinnerGenero)
        genero.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, generoArray)

        val simNaoArray = arrayOf("--SELECIONE--", "SIM", "NÂO")
        acamado = findViewById(R.id.spinnerAcamado)
        acamado.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, simNaoArray)

        contencao = findViewById(R.id.spinnerContencao)
        contencao.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, simNaoArray)

        val conscArray = arrayOf("--SELECIONE--", "Consciente/Responsivo", "Sonolência/letargia", "Delirium", "Catatonia", "Coma")
        conciencia = findViewById(R.id.spinnerConsciencia)
        conciencia.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, conscArray)

        val depArray = arrayOf("--SELECIONE--", "Total", "Parcial", "Independente")
        dependencia = findViewById(R.id.spinnerDependencia)
        dependencia.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, depArray)
    }

    private fun getDoencaPreexistentes(): String {
        val selectedDoencas = mutableListOf<String>()
        if (hipertensao.isChecked) {
            selectedDoencas.add("Hipertensão")
        }
        if (diabetes.isChecked) {
            selectedDoencas.add("Diabetes")
        }
        if (neurologica.isChecked) {
            selectedDoencas.add("Neurológica")
        }
        if (outros.isChecked) {
            selectedDoencas.add("Outros")
        }
        return selectedDoencas.joinToString(", ")
    }

    private fun getIncontinencia(): String {
        val selectedIncontinencias = mutableListOf<String>()
        if (urinaria.isChecked) {
            selectedIncontinencias.add("Urinária")
        }
        if (fecal.isChecked) {
            selectedIncontinencias.add("Fecal")
        }
        if (naoApresenta.isChecked) {
            selectedIncontinencias.add("Não Apresenta")
        }
        return selectedIncontinencias.joinToString(", ")
    }

    fun voltarTela(view: View) {
        finish()
    }
}
