package com.example.notesapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.notesapp.R;
import com.example.notesapp.entities.Note;

import java.util.List;
import java.util.logging.Logger;

public class NoteAdapter extends ArrayAdapter<Note> {
    private List<Note> notes;
    public NoteAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public NoteAdapter(@NonNull Context context, List<Note> notes) {
        // 0 : no default layout is used
        super(context, 0, notes);
        this.notes = notes;

    }

    // position (index in array) , convertView (note.xml , child view) , parent : bayn
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        convertView = LayoutInflater.from(this.getContext())
                .inflate(
                        R.layout.note, // putting  child in parent
                        parent,
                        false //the view is not yet linked to the list
                );

        //getting the convertView children
        TextView title = (TextView)  convertView.findViewById(R.id.title);
        TextView description = (TextView) convertView.findViewById(R.id.description);

        Note note = notes.get(position);
        Log.d("info", "getView: position:" +position+"note : "+notes);
        title.setText(note.getTitle());
        description.setText(note.getDescription());
        return convertView;
    }


}
