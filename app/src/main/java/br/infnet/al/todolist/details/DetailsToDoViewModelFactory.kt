package br.infnet.al.todolist.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.infnet.al.todolist.create.CreateToDoViewModel
import br.infnet.al.todolist.database.AppDatabase

class DetailsToDoViewModelFactory(var appDatabase: AppDatabase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailsToDoViewModel::class.java)) {
            return DetailsToDoViewModel(appDatabase) as T
        }
        throw IllegalArgumentException("Argumento ilegal.")
    }
}