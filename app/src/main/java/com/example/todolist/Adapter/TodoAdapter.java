package com.example.todolist.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.MainActivity;
import com.example.todolist.Model.TodoModel;
import com.example.todolist.R;

import java.util.List;
import com.example.todolist.R;
public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder>{
    private List<TodoModel> todos;
    private MainActivity activity;
    public TodoAdapter(MainActivity mainActivity){
        this.activity = mainActivity;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent , int viewType){

        View viewItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout,parent,false);

        return new ViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TodoModel item = todos.get(position);
        holder.task.setText(item.getTask());
        holder.task.setChecked(item.getStatus() != 0);
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }


    public static  class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox task;
        ViewHolder(View v){
            super(v);
            task = itemView.findViewById(R.id.todoCheckbox);
        }
    }
}
