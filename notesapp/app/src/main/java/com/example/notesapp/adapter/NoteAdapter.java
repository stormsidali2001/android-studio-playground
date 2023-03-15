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
import com.example.notesapp.ViewHolder.ViewHolder;
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
        if(convertView == null){
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(
                            R.layout.note, // putting  child in parent
                            parent,
                            false //the view is not yet linked to the list
                    );
        }

        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        if(viewHolder == null ){
            //getting the convertView children
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView)  convertView.findViewById(R.id.title);
            viewHolder.description = (TextView) convertView.findViewById(R.id.description);
            convertView.setTag(viewHolder);
        }
        Note note = notes.get(position);
        viewHolder.title.setText(note.getTitle());
        viewHolder.description .setText(note.getDescription());

        return convertView;
    }
    /**
     * the adapter calls the getView for each visible item on the screen
     * the adapter inflate the layout (instanciate the xml file) into the parent ( the list view) using the created/recycled ConvertView
     * it gets the viewHolder from the tag of the convert view (or create a new view holder if its not yet created) (remember if we scroll down upper views will be recycled for memory optimisation purposes)
     * after getting the <position> item from the List the view holder sets those data into the attributes of the item views
     */


}
