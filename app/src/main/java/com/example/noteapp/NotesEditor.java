package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.HashSet;

public class NotesEditor extends AppCompatActivity {

    EditText edttxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_editor);
        edttxt = findViewById(R.id.edttxt);
        Intent intent = getIntent();
        int notesId = intent.getIntExtra("notesId",-1);
        if(notesId != -1)
        {
            edttxt.setText(MainActivity.notes.get(notesId));
        }
        edttxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
MainActivity.notes.set(notesId,String.valueOf(charSequence));
MainActivity.arrayAdapter.notifyDataSetChanged();
                SharedPreferences sharedPreferences = NotesEditor.this.getSharedPreferences("com.example.noteapp", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(MainActivity.notes);
                sharedPreferences.edit().putStringSet("notes",set).apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

}