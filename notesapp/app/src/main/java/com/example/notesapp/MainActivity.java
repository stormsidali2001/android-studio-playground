package com.example.notesapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.notesapp.adapter.NoteAdapter;
import com.example.notesapp.entities.Note;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements
        AdapterView.OnItemClickListener , AdapterView.OnItemLongClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.list);
        List<Note> notes = new ArrayList<>();
        notes.add(new Note("sidali assoull","ashfhsafhasfsafkhsakhfsahjafsh"));
        notes.add(new Note("sidali assoull","ashfhsafhasfsafkhsakhfsahjafsh"));
        notes.add(new Note("sidali assoull","ashfhsafhasfsafkhsakhfsahjafsh"));
        notes.add(new Note("sidali assoull","ashfhsafhasfsafkhsakhfsahjafsh"));

        NoteAdapter adapter = new NoteAdapter(MainActivity.this,notes);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        // l : the clicked item position
        Toast.makeText(MainActivity.this, "l'élément sélectionné : " +l,Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText( MainActivity.this, "Long click sélectionné :"+l,Toast.LENGTH_LONG).show();
        AlertDialog.Builder confirm = new AlertDialog.Builder(this);
        confirm.setTitle("Suppression");
        confirm.setIcon(android.R.drawable.ic_dialog_alert);
        confirm.setMessage("Vous confirmez la suppression ?");
        confirm.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int idBtn) {
//code
                    }
                });
        confirm.setNegativeButton(android.R.string.cancel, null);
        confirm.show();
        return true;
    }
}