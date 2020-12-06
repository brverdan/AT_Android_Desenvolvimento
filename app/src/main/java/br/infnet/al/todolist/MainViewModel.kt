package br.infnet.al.todolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.infnet.al.todolist.model.Todo

class MainViewModel : ViewModel() {

    private val _todo = MutableLiveData<Todo>()
    val todo: LiveData<Todo> get() = _todo

    fun selectTodo(todo: Todo?) {
        _todo.value = todo
    }
}