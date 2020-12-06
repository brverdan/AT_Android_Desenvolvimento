package br.infnet.al.todolist.lista

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.infnet.al.todolist.database.AppDatabase
import br.infnet.al.todolist.model.Todo

class ListaToDoViewModel(var appDatabase: AppDatabase) : ViewModel() {

    private val _todos = MutableLiveData<List<Todo>>()

    val todos: LiveData<List<Todo>> get() = _todos

    init {
        _todos.value = appDatabase.all()
    }

    fun delete(todo: Todo) {
        appDatabase.delete(todo)
    }
}