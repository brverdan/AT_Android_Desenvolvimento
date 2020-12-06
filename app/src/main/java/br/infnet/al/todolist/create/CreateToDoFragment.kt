package br.infnet.al.todolist.create

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
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.create_to_do_fragment.*
import kotlinx.android.synthetic.main.details_to_do_fragment.*

class CreateToDoFragment : Fragment() {

    private lateinit var createToDoViewModel: CreateToDoViewModel
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.create_to_do_fragment, container, false)

        createToDoViewModel = ViewModelProvider(this, CreateToDoViewModelFactory(AppDatabase.getInstance())).get(CreateToDoViewModel::class.java)

        createToDoViewModel.let {
            it.status.observe(viewLifecycleOwner) {
                if(it) {
                    findNavController().popBackStack()
                }
            }
            it.msg.observe(viewLifecycleOwner) {
                if(!it.isNullOrEmpty()) {
                    Snackbar.make(
                        create_todo_layout_root,
                        it,
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        mainViewModel.todo.observe(viewLifecycleOwner) {
            if (it != null) {
                txtCreateTitulo.setText(it.titulo)
                txtCretateConteudo.setText(it.conteudo)
                cbCreateCompletada.isChecked = it.completada
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabCreateSave.setOnClickListener {
            if(txtCreateTitulo.text.isNullOrBlank() || txtCretateConteudo.text.isNullOrBlank()) {
                Snackbar.make(
                    create_todo_layout_root,
                    "Por favor, preencha todos os campos",
                    Snackbar.LENGTH_LONG
                ).show()
            }
            else {
                createToDoViewModel.save(
                    txtCreateTitulo.text.toString(),
                    txtCretateConteudo.text.toString(),
                    cbCreateCompletada.isChecked,
                    mainViewModel.todo.value
                )
            }
        }
    }

}