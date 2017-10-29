package com.example.android.securenotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import static com.example.android.securenotes.R.id.fab1;

public class update_note extends AppCompatActivity {
    EditText note_id,title,note;
    ListView listView;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);

        note_id = (EditText) findViewById(R.id.note_id);
        title = (EditText) findViewById(R.id.title);
        note = (EditText) findViewById(R.id.note);
        listView = (ListView) findViewById(R.id.list_item);
        db = new DatabaseHandler(this);

        Bundle b = getIntent().getExtras();
        note_id.setText(b.getString("id", "1"));
        title.setText(b.getString("title"));
        note.setText(b.getString("note"));

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(fab1);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.updateNote(new note(Integer.parseInt(note_id.getText().toString()), title.getText().toString(), note.getText().toString()));

                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
    }
}
