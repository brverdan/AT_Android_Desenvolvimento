package br.infnet.al.todolist.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.infnet.al.todolist.database.AppDatabase
import br.infnet.al.todolist.model.Todo

class CreateToDoViewModel(var appDatabase: AppDatabase) : ViewModel() {

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String>
        get() = _msg

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean>
        get() = _status

    init {
        _status.value = false
        _msg.value = null
    }

    fun save (
        titulo: String,
        conteudo: String,
        completada: Boolean,
        todo: Todo?
    ) {
        if (todo == null) {
            appDatabase.save(
                Todo(
                    titulo, conteudo, completada
                )
            )
        }
        else {
            appDatabase.edit(
                titulo, conteudo, completada, todo
            )
        }
        if (true) {
            _status.value = true
            _msg.value = "A tarefa ${titulo} foi persistida com sucesso!"
        }
    }
}