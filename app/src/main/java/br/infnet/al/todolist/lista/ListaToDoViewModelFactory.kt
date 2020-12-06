package br.infnet.al.todolist.lista

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.infnet.al.todolist.database.AppDatabase

class ListaToDoViewModelFactory(var appDatabase: AppDatabase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ListaToDoViewModel::class.java)) {
            return ListaToDoViewModel(appDatabase) as T
        }
        throw IllegalArgumentException("Argumento ilegal.")
    }
}