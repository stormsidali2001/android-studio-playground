package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;

public class NotesDetails extends AppCompatActivity {
    private TextView noteTitleView;
    private TextView noteDescriptionView;

    private TextView dateView;

    private TextToSpeech tts;
    private ImageView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_details);

        Intent intent = getIntent();
        String noteTitle = intent.getStringExtra("note_title");
        String noteDescription = intent.getStringExtra("note_description");
        String date = intent.getStringExtra("note_date");
        noteTitleView = (TextView) findViewById(R.id.note_title);
        noteDescriptionView = (TextView) findViewById(R.id.note_description);
        dateView =  (TextView) findViewById(R.id.note_date);



        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });
        imgView = (ImageView) findViewById(R.id.speak_img);
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.speak(noteTitle +" "+noteDescription,  TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        noteTitleView.setText(noteTitle);
        noteDescriptionView.setText(noteDescription);
        dateView.setText(date);


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }
}