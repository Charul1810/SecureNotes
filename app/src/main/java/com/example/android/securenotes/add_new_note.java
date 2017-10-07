package com.example.android.securenotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class add_new_note extends AppCompatActivity {

    EditText note_id,title,note;
    Button add_note;
    ListView listView;
    DatabaseHandler db;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);

     //  add_note= (Button) findViewById(R.id.add_note);
       note_id = (EditText) findViewById(R.id.note_id);
        title = (EditText) findViewById(R.id.title);
        note = (EditText) findViewById(R.id.note);
        listView = (ListView) findViewById(R.id.list_item);
        db = new DatabaseHandler(this);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
       fab.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               db.addNote(new note(title.getText().toString(), note.getText().toString()));
               //Toast.makeText(getApplicationContext(), "note saved successfully!", Toast.LENGTH_LONG).show();
               clear();
               startActivity(new Intent(getApplicationContext(),MainActivity.class));
           }
       });

        /*add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.addNote(new note(title.getText().toString(), note.getText().toString()));
                //Toast.makeText(getApplicationContext(), "note saved successfully!", Toast.LENGTH_LONG).show();
                clear();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });*/


    }

    public void clear(){
        note_id.setText("");
        title.setText("");
        note.setText("");
    }
}
