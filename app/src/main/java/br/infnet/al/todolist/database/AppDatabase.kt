package br.infnet.al.todolist.database

import br.infnet.al.todolist.model.Todo

class AppDatabase {

    private var todos: MutableList<Todo> = mutableListOf(
        Todo("Varrer a casa", "Varrer a casa toda até as 11", false),
        Todo("Limpar a casa", "Limpar a casa toda até as 2", true),
        Todo("Lavar a louça do almoço", "Lavar a louça do almoço", true),
        Todo("Limpar o quarto", "Guardar todos os brinquedos e as tralhas", false)
    )

    fun all(): List<Todo> {
        return todos
    }

    fun save(todo: Todo) {
        todos.add(todo)
    }

    fun delete(todo: Todo) {
        todos.remove(todo)
    }

    fun edit(titulo: String, conteudo: String, completada: Boolean, todo: Todo) {
        var index = todos.indexOf(todo)
        todo.titulo = titulo
        todo.conteudo = conteudo
        todo.completada = completada
        todos[index] = todo
    }

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance() : AppDatabase {
            if(instance == null) {
                instance = AppDatabase()
            }
            return instance as AppDatabase
        }
    }
}