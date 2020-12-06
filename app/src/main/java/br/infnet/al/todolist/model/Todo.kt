package br.infnet.al.todolist.model

class Todo (
    var titulo: String,
    var conteudo: String,
    var completada: Boolean,
) {
    override fun toString(): String {
        return "$titulo \n " +
                "$conteudo \n " +
                "$completada"
    }
}
