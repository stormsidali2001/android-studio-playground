package com.example.notesapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notesapp.adapter.NoteAdapter;
import com.example.notesapp.entities.Note;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements
        AdapterView.OnItemClickListener , AdapterView.OnItemLongClickListener {
    private NoteAdapter adapter;
    private ArrayList<Note> notes;



    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelableArrayList("notes",notes);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "onCreated", Toast.LENGTH_SHORT).show();

        ListView listView = findViewById(R.id.list);
        if(savedInstanceState!= null){
            notes = savedInstanceState.getParcelableArrayList("notes");
        }
            notes = new ArrayList<>();
            notes.add(new Note("sidali assoull","ashfhsafhasfsafkhsakhfsahjafsh"));
            notes.add(new Note("sidali assoull","ashfhsafhasfsafkhsakhfsahjafsh"));
            notes.add(new Note("sidali assoull","ashfhsafhasfsafkhsakhfsahjafsh"));
            notes.add(new Note("sidali assoull","ashfhsafhasfsafkhsakhfsahjafsh"));



        adapter = new NoteAdapter(MainActivity.this,notes);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        // l : the clicked item position
        Toast.makeText(MainActivity.this, "l'élément sélectionné : " +l,Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,NotesDetails.class);
        Note note = notes.get((int)l);
        intent.putExtra("note_title",note.getTitle());
        intent.putExtra("note_description",note.getDescription());
        intent.putExtra("note_date",note.getCreatedAt().toString());
        startActivity(intent);
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

                        notes.remove((int)l);
                        adapter.notifyDataSetChanged();
                    }
                });
        confirm.setNegativeButton(android.R.string.cancel, null);
        confirm.show();
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logo:
                Toast.makeText(MainActivity.this,"Logo",Toast.LENGTH_LONG).show();
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.add_note);
                dialog.setTitle("Création d’un type");
                Button btnValider = (Button)
                        dialog.findViewById(R.id.add_note_submit_btn);
                dialog.show();
                btnValider.setOnClickListener(new
                                                      View.OnClickListener() {
                                                          @Override public void onClick(View v) {
                                                              TextView titleView = (TextView) dialog.findViewById(R.id.add_note_title_input);
                                                              TextView descriptionView = (TextView) dialog.findViewById(R.id.add_note_description_input);
                                                              if(titleView == null || descriptionView ==null){
                                                                  return ;
                                                              }
                                                              String title = titleView.getText().toString();
                                                              String description = descriptionView.getText().toString();
                                                              if(title.length() == 0 || description.length() == 0){
                                                                  Toast.makeText(MainActivity.this,"please enter both text and description",Toast.LENGTH_SHORT).show();
                                                                  return;
                                                              }
                                                              notes.add(new Note(titleView.getText().toString(),descriptionView.getText().toString()));
                                                              adapter.notifyDataSetChanged();
                                                              dialog.dismiss();
                                                          }
                                                      });
                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }

}