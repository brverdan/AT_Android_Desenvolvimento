package br.infnet.al.todolist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.infnet.al.todolist.R
import br.infnet.al.todolist.model.Todo
import kotlinx.android.synthetic.main.list_todo_recycler.view.*

class TodoRecyclerAdapter(

    private val todos: List<Todo>,
    private val actionClick: (Todo) -> Unit

) : RecyclerView.Adapter<TodoRecyclerAdapter.TodoViewHolder>() {

    class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtTitulo = view.txtListTitulo
        val cbCompletada = view.cbListCompletada
        val btnListDetails = view.btnListDetails
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.list_todo_recycler,
            parent,
            false

        )
        val todoViewHolder = TodoViewHolder(view)
        return todoViewHolder
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todos.get(position)
        holder.txtTitulo.text = todo.titulo
        holder.cbCompletada.isChecked = todo.completada
        holder.btnListDetails.setOnClickListener {
            actionClick(todo)
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}