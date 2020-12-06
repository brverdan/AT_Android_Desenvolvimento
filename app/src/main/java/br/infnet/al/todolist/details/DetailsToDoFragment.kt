package br.infnet.al.todolist.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.infnet.al.todolist.MainViewModel
import br.infnet.al.todolist.R
import br.infnet.al.todolist.database.AppDatabase
import br.infnet.al.todolist.model.Todo
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.details_to_do_fragment.*
import android.content.Intent
import android.provider.CalendarContract

class DetailsToDoFragment : Fragment() {

    private lateinit var detailsToDoViewModel: DetailsToDoViewModel
    private lateinit var mainViewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.details_to_do_fragment, container, false)

        detailsToDoViewModel = ViewModelProvider(this, DetailsToDoViewModelFactory(AppDatabase.getInstance())).get(DetailsToDoViewModel::class.java)

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        mainViewModel.todo.observe(viewLifecycleOwner) {
            updateUi(it, detailsToDoViewModel.status.value!!)
        }

        detailsToDoViewModel.msg.observe(viewLifecycleOwner) {
            if(!it.isNullOrBlank()) {
                showSnackbar(it)
            }
        }

        detailsToDoViewModel.status.observe(viewLifecycleOwner) {
            if (it) {
                mainViewModel.selectTodo(null)
                findNavController().popBackStack()
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabDetailsDelete.setOnClickListener {
            var todo = mainViewModel.todo.value
            detailsToDoViewModel.delete(todo!!)
        }

        fabDetailsEdit.setOnClickListener {
            findNavController().navigate(R.id.createToDoFragment)
        }

        btnDetailsAnotação.setOnClickListener {
            addEvent(txtDetailsTitulo.text.toString(), txtDetailsConteudo.text.toString())
        }
    }

    private fun updateUi(todo: Todo?, status: Boolean) {
        if(todo != null) {
            txtDetailsTitulo.text = todo.titulo
            txtDetailsConteudo.text = todo.conteudo
            cbDetailsCompletada.isChecked = todo.completada
        }
        else if (!status) {
            showSnackbar("Nenhum carro foi selecionado")
        }
    }

    private fun showSnackbar(msg: String) {
        Snackbar.make(
            details_todo_layout_root,
            msg,
            Snackbar.LENGTH_LONG
        ).show()
    }

    fun addEvent(titulo: String, conteudo: String) {
        val intent = Intent(Intent.ACTION_INSERT).apply {
            data = CalendarContract.Events.CONTENT_URI
            putExtra(CalendarContract.Events.TITLE, titulo)
            putExtra(CalendarContract.Events.DESCRIPTION, conteudo)
        }
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }
    }
}