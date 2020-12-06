package br.infnet.al.todolist.lista

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.infnet.al.todolist.MainViewModel
import br.infnet.al.todolist.R
import br.infnet.al.todolist.adapter.TodoRecyclerAdapter
import br.infnet.al.todolist.database.AppDatabase
import br.infnet.al.todolist.model.Todo
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.lista_to_do_fragment.*

class ListaToDoFragment : Fragment() {

    private lateinit var mainViewModel : MainViewModel
    private lateinit var listaToDoViewModel: ListaToDoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.lista_to_do_fragment, container, false)

        listaToDoViewModel = ViewModelProvider(this, ListaToDoViewModelFactory(AppDatabase.getInstance())).get(ListaToDoViewModel::class.java)

        listaToDoViewModel.todos.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                adaptarRecylerList(it)
            }
            else {
                showSnackBar("Nenhum carro encontrado na base")
            }
        }

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabListCreateTodo.setOnClickListener {
            mainViewModel.selectTodo(null)
            findNavController().navigate(R.id.createToDoFragment)
        }
    }

    private fun showSnackBar(msg: String) {
        Snackbar.make(
            list_todo_layout_root,
            msg,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun adaptarRecylerList(listaTodos: List<Todo>) {
        val todoRecyclerViewAdapter = TodoRecyclerAdapter(listaTodos, this::actionClick)
        listViewTodosRecycler.adapter = todoRecyclerViewAdapter
        listViewTodosRecycler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun actionClick(todo: Todo) {
        mainViewModel.selectTodo(todo)
        findNavController().navigate(R.id.detailsToDoFragment)
    }

}