package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NotesDetails extends AppCompatActivity {
    private TextView noteTitleView;
    private TextView noteDescriptionView;

    private TextToSpeech tts;
    private ImageView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_details);

        Intent intent = getIntent();
        String noteTitle = intent.getStringExtra("note_title");
        String noteDescription = intent.getStringExtra("note_description");
        noteTitleView = (TextView) findViewById(R.id.note_title);
        noteDescriptionView = (TextView) findViewById(R.id.note_description);
        imgView = (ImageView) findViewById(R.id.speak_img);
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
        noteTitleView.setText(noteTitle);
        noteDescriptionView.setText(noteDescription);


    }
}