package com.example.projeto_integrador.telas

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.projeto_integrador.R
import com.example.projeto_integrador.model.Paciente
import com.example.projeto_integrador.network.Endpoint
import com.example.projeto_integrador.network.NetworkUtils
import com.google.gson.Gson
import retrofit2.Response
import retrofit2.Call
import retrofit2.Callback



class CadastroActivity : AppCompatActivity() {

    lateinit var nome: EditText
    lateinit var tempInstitucionalizado: EditText
    lateinit var idade: EditText
    lateinit var altura: EditText
    lateinit var peso: EditText
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

        nome = findViewById<EditText>(R.id.editNome)
        idade = findViewById<EditText>(R.id.editIdade)
        tempInstitucionalizado = findViewById<EditText>(R.id.editInstitucionalizado)
        altura = findViewById<EditText>(R.id.editAltura)
        peso = findViewById<EditText>(R.id.editPeso)
        genero = findViewById<Spinner>(R.id.spinnerGenero)
        acamado = findViewById<Spinner>(R.id.spinnerAcamado)
        contencao = findViewById<Spinner>(R.id.spinnerContencao)
        conciencia = findViewById<Spinner>(R.id.spinnerConsciencia)
        dependencia = findViewById<Spinner>(R.id.spinnerDependencia)
        hipertensao = findViewById(R.id.chkHipertensao)
        diabetes = findViewById(R.id.chkDiabetes)
        neurologica = findViewById(R.id.chkNeurologica)
        outros = findViewById(R.id.chkOutros)
        urinaria = findViewById(R.id.chkUrinaria)
        fecal = findViewById(R.id.chkFecal)
        naoApresenta = findViewById(R.id.chkNApresenta)
        setupSpinners()

    }
    fun  buttonGravar(view: View) {
        // Obtenha os valores dos componentes
        val nomePaciente = nome.text.toString()
        val idadePaciente = idade.text.toString().toInt()
        val alturaPaciente = altura.text.toString().toDouble()
        val pesoPaciente = peso.text.toString().toDouble()
        val generoPaciente = genero.selectedItem.toString()
        val acamadoPaciente = acamado.selectedItem.toString()
        val contencaoPaciente = contencao.selectedItem.toString()
        val concienciaPaciente = conciencia.selectedItem.toString()
        val dependenciaPaciente = dependencia.selectedItem.toString()
        val internadoPaciente = tempInstitucionalizado.text.toString().toInt()
        val doencaPreexistentesPaciente = getDoencaPreexistentes()
        val incontinenciaPaciente = getIncontinencia()

        // Crie um objeto Paciente com os valores obtidos
        val paciente = Paciente(
            id = 0,  // O ID pode ser 0 se você está criando um novo paciente
            nome = nomePaciente,
            idade = idadePaciente,
            genero = generoPaciente,
            altura = alturaPaciente,
            peso = pesoPaciente,
            acamado = acamadoPaciente,
            tepInternado = internadoPaciente,
            contencao = contencaoPaciente,
            consciencia = concienciaPaciente,
            dependencia = dependenciaPaciente,
            doencaPreexistentes = doencaPreexistentesPaciente,
            incontinencia = incontinenciaPaciente
        )

        // Execute a solicitação POST para criar o paciente
        criarPaciente(paciente)

    }

    private fun criarPaciente(paciente: Paciente) {
        val retrofitClient = NetworkUtils.getRetrofitInstance("http://192.168.0.192:8080")
        val endpoint = retrofitClient.create(Endpoint::class.java)

        val call = endpoint.postsPaciente(paciente)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Paciente criado com sucesso
                    exibirMensagem("Paciente criado com sucesso")
                } else {
                    // Erro no servidor ou na solicitação
                    val mensagemErro = "Erro: ${response.code()}"
                    exibirMensagem(mensagemErro)
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Falha na solicitação
                exibirMensagem("Falha na solicitação: ${t.message}")
            }
        })
    }
    private fun exibirMensagem(mensagem: String) {
        // Você pode exibir a mensagem em um TextView ou como um Toast
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
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

        // Debugging: Print the selected diseases
        Log.d("Disease Debug", "Selected Diseases: ${selectedDoencas.joinToString(", ")}")

        return if (selectedDoencas.isEmpty()) {
            "Não selecionado"
        } else {
            val result = selectedDoencas.joinToString(", ")
            Log.d("Disease Debug", "Result: $result")
            result
        }
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

        return if (selectedIncontinencias.isEmpty()) {
            "Não selecionado"
        } else {
            selectedIncontinencias.joinToString(", ")
        }
    }
    fun voltarTela(view: View) {
        finish()
    }
}
