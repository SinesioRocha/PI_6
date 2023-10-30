package com.example.projeto_integrador.model

class Paciente {
    var nome: String = ""
    var idade: Int = 0
    var genero: String = ""
    var altura: Double = 0.0
    var peso: Double = 0.0
    var acamado: String = ""
    var tep_internado: Int = 0
    var contencao: String = ""
    var consciencia: String = ""
    var dependencia: String = ""
    var doencaPreexistentes: String = ""
    var incontinencia: String = ""
    override fun toString(): String {
        return "Paciente(nome='$nome', idade=$idade, genero='$genero', altura=$altura, peso=$peso, acamado='$acamado', tep_internado=$tep_internado, contencao='$contencao', consciencia='$consciencia', dependencia='$dependencia', doencaPreexistentes='$doencaPreexistentes', incontinencia='$incontinencia')"
    }


}
