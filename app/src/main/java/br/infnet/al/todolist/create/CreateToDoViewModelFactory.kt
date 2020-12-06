package br.infnet.al.todolist.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.infnet.al.todolist.database.AppDatabase

class CreateToDoViewModelFactory(var appDatabase: AppDatabase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CreateToDoViewModel::class.java)) {
            return CreateToDoViewModel(appDatabase) as T
        }
        throw IllegalArgumentException("Argumento ilegal.")
    }
}