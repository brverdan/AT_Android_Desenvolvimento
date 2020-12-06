package br.infnet.al.todolist.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.infnet.al.todolist.database.AppDatabase
import br.infnet.al.todolist.model.Todo

class DetailsToDoViewModel(var appDatabase: AppDatabase) : ViewModel() {

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> get() = _msg

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> get() = _status

    init {
        _status.value = false
        _msg.value = null
    }

    fun delete(todo: Todo) {
        _msg.value = "Efetuando a exclusão do registro."
        appDatabase.delete(todo)
        _msg.value = "Exclusão realizada com sucesso."
        _status.value = true
    }
}